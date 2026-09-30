package com.s.looka.android.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.util.LocalNavigationBarVisibility
import com.s.looka.features.favorite.presentation.FeatureFavoriteNavGraph
import com.s.looka.features.feature_account.presentation.FeatureAccountNavGraph
import com.s.looka.features.feature_auth.FeatureAuthNavigation
import com.s.looka.features.feature_cart.presentation.FeatureCartNavGraph
import com.s.looka.features.feature_homepage.presentation.FeatureHomeNavigation
import com.s.looka.features.feature_search.presentation.FeatureSearchNavGraph

@Composable
fun Navigator.NavigationContainer(
    controller: NavController,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    val navigationBarVisibility = LocalNavigationBarVisibility.current
    val backStackEntry by controller.currentBackStackEntryAsState()
    val currentNavDestination = backStackEntry?.destination

    val selectedNavItem = when {
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureHomeNavigation>() } == true -> NavBarItem.Type.Home
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureSearchNavGraph>() } == true -> NavBarItem.Type.Search
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureFavoriteNavGraph>() } == true -> NavBarItem.Type.Favorite
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureCartNavGraph>() } == true -> NavBarItem.Type.Cart
        currentNavDestination?.hierarchy?.any { it.hasRoute<FeatureAccountNavGraph>() } == true -> NavBarItem.Type.Account
        else -> NavBarItem.Type.Search
    }
    Log.d("NavBar", "NavContainer: selectedNavItem: $selectedNavItem")
    Log.i("NavBar", "NavContainer: backStack: ${backStackEntry}")

    val isTopLevelDestination = when {
        currentNavDestination?.hasRoute<FeatureAuthNavigation>() == true -> false
        else -> navigationBarVisibility.value
    }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(),
        bottomBar = {
            if (isTopLevelDestination) {
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
    ) { paddingValues -> content(paddingValues) }
}