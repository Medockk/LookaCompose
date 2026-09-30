package com.s.looka.features.feature_auth

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.s.looka.core.navigation.Navigator
import com.s.looka.features.feature_auth.presentation.components.PolicyBottomSheet
import com.s.looka.features.feature_auth.presentation.login.screen.LoginScreenRoot
import com.s.looka.features.feature_auth.presentation.signup.ui.SignupScreenRoot

fun NavGraphBuilder.featureAuth(
    navigator: Navigator,
    onSuccessAuthentication: () -> Unit
) {
    // TODO: create shared viewmodel for `email`, `password`!
    navigation<FeatureAuthNavigation>(
        startDestination = FeatureAuthNavigation.SignupScreen
    ) {
        composable<FeatureAuthNavigation.SignupScreen> {
            SignupScreenRoot(navigator, onSuccessAuthentication)
        }
        composable<FeatureAuthNavigation.LoginScreen> {
            LoginScreenRoot(navigator, onSuccessAuthentication)
        }
        // other screen

        composable<FeatureAuthNavigation.PolicyBottomSheet>(
            deepLinks = listOf(
                navDeepLink { uriPattern = "looka://market/signup/policy/{type}" }
            ),
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down) },
            sizeTransform = null
        ) {
            val policyType = it.toRoute<FeatureAuthNavigation.PolicyBottomSheet>()
            PolicyBottomSheet(isVisible = true, policyType.type, {
                navigator.navigateUp()
            })
        }
    }
}