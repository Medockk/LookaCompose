package com.s.looka.features.feature_auth

import kotlinx.serialization.Serializable

@Serializable
data object FeatureAuthNavigation {

    @Serializable
    data object SignupScreen
    @Serializable
    data object LoginScreen

    @Serializable
    internal data class PolicyBottomSheet(val type: String) {

    }
}