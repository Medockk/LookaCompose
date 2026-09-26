package com.s.looka.android

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.navigation.collectNavCommands
import com.s.looka.features.feature_auth.FeatureAuthNavigation
import com.s.looka.features.feature_auth.featureAuth

@Composable
fun MainApplicationUI(navigator: Navigator) {
    val controller = rememberNavController()
    navigator.collectNavCommands(navController = controller)

    NavHost(
        navController = controller,
        startDestination = FeatureAuthNavigation,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End)
        }
    ) {
        featureAuth(navigator) {
            navigator.navigate("home") // TODO
        }
        composable("home") {

        }
    }
}