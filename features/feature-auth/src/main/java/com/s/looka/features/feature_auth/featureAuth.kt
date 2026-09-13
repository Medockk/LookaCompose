package com.s.looka.features.feature_auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.featureAuth(
    onAuthenticated: () -> Unit
) {
    navigation<FeatureAuthNavigation>(
        startDestination = FeatureAuthNavigation.SignupScreen
    ) {
        composable<FeatureAuthNavigation.SignupScreen> {
            // ...
        }
        composable<FeatureAuthNavigation.LoginScreen> {
            // ...
        }
        // other screen
    }
}