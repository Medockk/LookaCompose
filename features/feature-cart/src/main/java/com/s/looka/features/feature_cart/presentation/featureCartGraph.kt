package com.s.looka.features.feature_cart.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.navigation.compose.appComposable

fun NavGraphBuilder.featureCartGraph(navigator: Navigator) {
    navigation<FeatureCartNavGraph>(
        startDestination = FeatureCartNavGraph.Cart
    ) {
        appComposable<FeatureCartNavGraph.Cart>(navigator.registry) {}
    }
}