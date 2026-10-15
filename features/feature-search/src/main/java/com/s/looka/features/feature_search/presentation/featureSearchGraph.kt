package com.s.looka.features.feature_search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.s.looka.core.navigation.Navigator

fun NavGraphBuilder.featureSearchGraph(navigator: Navigator) {
    navigation<FeatureSearchNavGraph>(
        startDestination = FeatureSearchNavGraph.Search
    ) {
        composable<FeatureSearchNavGraph.Search> {
            Box(Modifier.fillMaxSize().background(Color.Red))
        }
    }
}