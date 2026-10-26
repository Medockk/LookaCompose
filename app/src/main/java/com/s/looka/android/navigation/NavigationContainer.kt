package com.s.looka.android.navigation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.s.looka.core.navigation.Navigator
import com.s.looka.features.favorite.presentation.FeatureFavoriteNavGraph
import com.s.looka.features.feature_account.presentation.FeatureAccountNavGraph
import com.s.looka.features.feature_cart.presentation.FeatureCartNavGraph
import com.s.looka.features.feature_homepage.presentation.FeatureHomeNavigation
import com.s.looka.features.feature_search.presentation.FeatureSearchNavGraph
import kotlinx.coroutines.flow.first

@Composable
fun Navigator.NavigationContainer(
    controller: NavController,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    val backStackEntry by controller.currentBackStackEntryAsState()
    val currentNavDestination = backStackEntry?.destination

    var settledBackStackEntry by remember { mutableStateOf<NavBackStackEntry?>(null) }

    LaunchedEffect(backStackEntry) {
        val entry = backStackEntry ?: return@LaunchedEffect

        entry.lifecycle.currentStateFlow.first { it == Lifecycle.State.STARTED }

        if (controller.currentBackStackEntry == entry) {
            settledBackStackEntry = entry
        }
    }

    val selectedNavItem = when {
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureHomeNavigation>() } == true -> NavBarItem.Type.Home
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureSearchNavGraph>() } == true -> NavBarItem.Type.Search
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureFavoriteNavGraph>() } == true -> NavBarItem.Type.Favorite
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureCartNavGraph>() } == true -> NavBarItem.Type.Cart
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureAccountNavGraph>() } == true -> NavBarItem.Type.Account
        else -> NavBarItem.Type.Search
    }
    Log.d("NavBar", "NavContainer: selectedNavItem: $selectedNavItem")
    Log.i("NavBar", "NavContainer: backStack: $backStackEntry")

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(),
        bottomBar = {
            val isNavigationBarVisible = registry.isNavigationBarVisible(
                settledBackStackEntry?.destination?.route
            )
            AnimatedVisibility(
                isNavigationBarVisible,
                enter = EnterTransition.None,
                exit = fadeOut() /*+ slideOutVertically(tween())*/
            ) {
                if (isNavigationBarVisible) {
                    NavigationBar(
                        selectedItem = selectedNavItem,
                        onNavigate = { type ->
                            Log.d("NavBar", "NavContainer: onNavigate: type: $type")
                            val targetRoute: Any = when (type) {
                                NavBarItem.Type.Home -> FeatureHomeNavigation
                                NavBarItem.Type.Search -> FeatureSearchNavGraph
                                NavBarItem.Type.Favorite -> FeatureFavoriteNavGraph
                                NavBarItem.Type.Cart -> FeatureCartNavGraph
                                NavBarItem.Type.Account -> FeatureAccountNavGraph
                                null -> return@NavigationBar
                            }

                            this@NavigationContainer.navigate(targetRoute) {
                                popUpTo(controller.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues -> content(paddingValues) }
}