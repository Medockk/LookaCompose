package com.s.looka.features.favorite.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.s.looka.core.navigation.Navigator

fun NavGraphBuilder.featureFavoriteGraph(navigator: Navigator) {
    navigation<FeatureFavoriteNavGraph>(
        startDestination = FeatureFavoriteNavGraph.Saved
    ) {
        composable<FeatureFavoriteNavGraph.Saved> {}
    }
}