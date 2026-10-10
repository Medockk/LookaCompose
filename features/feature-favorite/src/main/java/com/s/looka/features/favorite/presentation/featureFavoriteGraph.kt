package com.s.looka.features.favorite.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.navigation.compose.appComposable

fun NavGraphBuilder.featureFavoriteGraph(navigator: Navigator) {
    navigation<FeatureFavoriteNavGraph>(
        startDestination = FeatureFavoriteNavGraph.Saved
    ) {
        appComposable<FeatureFavoriteNavGraph.Saved>(navigator.registry) {

        }
    }
}