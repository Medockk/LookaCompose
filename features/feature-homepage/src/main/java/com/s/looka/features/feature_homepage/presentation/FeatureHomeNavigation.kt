package com.s.looka.features.feature_homepage.presentation

import com.s.looka.core.navigation.ShowNavigationBar
import kotlinx.serialization.Serializable

@Serializable
object FeatureHomeNavigation {

    @Serializable
    @ShowNavigationBar
    data object HomeScreen
}