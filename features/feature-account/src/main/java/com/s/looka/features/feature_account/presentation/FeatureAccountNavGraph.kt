package com.s.looka.features.feature_account.presentation

import com.s.looka.core.navigation.ShowNavigationBar
import kotlinx.serialization.Serializable

@Serializable
object FeatureAccountNavGraph {

    @Serializable
    @ShowNavigationBar
    data object Account
}