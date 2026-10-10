package com.s.looka.android

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.s.looka.android.navigation.NavigationContainer
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.navigation.compose.collectNavCommands
import com.s.looka.features.favorite.presentation.featureFavoriteGraph
import com.s.looka.features.feature_account.presentation.featureAccountGraph
import com.s.looka.features.feature_auth.FeatureAuthNavigation
import com.s.looka.features.feature_auth.featureAuth
import com.s.looka.features.feature_cart.presentation.featureCartGraph
import com.s.looka.features.feature_homepage.presentation.FeatureHomeNavigation
import com.s.looka.features.feature_homepage.presentation.featureHome
import com.s.looka.features.feature_search.presentation.featureSearchGraph

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MainApplicationUI(navigator: Navigator) {
    val controller = rememberNavController()
    navigator.collectNavCommands(navController = controller)

    val currentBackStack by controller.currentBackStackEntryAsState()
    val metadata = navigator.registry.metadataFor(currentBackStack?.destination?.route)

    navigator.NavigationContainer(controller) { p ->
        NavHost(
            modifier = Modifier.padding(p),
            navController = controller,
            startDestination = FeatureHomeNavigation,
//            enterTransition = metadata?.enterTransition ?: { fadeIn() },
//            exitTransition = metadata?.exitTransition ?: { fadeOut() },
            popEnterTransition = {slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End)},
            popExitTransition = {slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)}
        ) {
            featureAuth(navigator) {
                navigator.navigate(FeatureHomeNavigation) {
                    popUpTo<FeatureAuthNavigation> {
                        inclusive = true
                    }
                }
            }
            featureHome(navigator)
            featureSearchGraph(navigator)
            featureFavoriteGraph(navigator)
            featureCartGraph(navigator)
            featureAccountGraph(navigator)
        }
    }
}