package com.s.looka.features.feature_homepage.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.s.looka.core.navigation.Navigator
import com.s.looka.features.feature_homepage.presentation.screen.HomeScreenRoot

fun NavGraphBuilder.featureHome(navigator: Navigator) {
    navigation<FeatureHomeNavigation>(
        startDestination = FeatureHomeNavigation.HomeScreen
    ) {
        composable<FeatureHomeNavigation.HomeScreen> {
            HomeScreenRoot(navigator)
        }
    }
}