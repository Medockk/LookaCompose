package com.s.looka.features.feature_search.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.s.looka.core.navigation.Navigator

fun NavGraphBuilder.featureSearchGraph(navigator: Navigator) {
    navigation<FeatureSearchNavGraph>(
        startDestination = FeatureSearchNavGraph.Search
    ) {
        composable<FeatureSearchNavGraph.Search> {}
    }
}