package com.s.looka.features.favorite.presentation

import com.s.looka.core.navigation.ShowNavigationBar
import kotlinx.serialization.Serializable

@Serializable
object FeatureFavoriteNavGraph {

    @Serializable
    @ShowNavigationBar
    data object Saved
}