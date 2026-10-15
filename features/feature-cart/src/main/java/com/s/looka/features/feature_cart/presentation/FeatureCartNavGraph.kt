package com.s.looka.features.feature_cart.presentation

import com.s.looka.core.navigation.ShowNavigationBar
import kotlinx.serialization.Serializable

@Serializable
object FeatureCartNavGraph {

    @Serializable
    @ShowNavigationBar
    data object Cart
}