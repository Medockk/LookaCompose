package com.s.looka.features.feature_account.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.s.looka.core.navigation.Navigator

fun NavGraphBuilder.featureAccountGraph(navigator: Navigator) {
    navigation<FeatureAccountNavGraph>(
        startDestination = FeatureAccountNavGraph.Account
    ) {
        composable<FeatureAccountNavGraph.Account> {}
    }
}