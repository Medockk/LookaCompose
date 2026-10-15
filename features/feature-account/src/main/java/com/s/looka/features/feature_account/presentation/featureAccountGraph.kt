package com.s.looka.features.feature_account.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.navigation.compose.appComposable

fun NavGraphBuilder.featureAccountGraph(navigator: Navigator) {
    navigation<FeatureAccountNavGraph>(
        startDestination = FeatureAccountNavGraph.Account
    ) {
        appComposable<FeatureAccountNavGraph.Account>(navigator.registry) {}
    }
}