package com.s.looka.features.feature_auth

import kotlinx.serialization.Serializable

@Serializable
sealed interface FeatureAuthNavigation {


    @Serializable
    data object SignupScreen: FeatureAuthNavigation
    @Serializable
    data object LoginScreen: FeatureAuthNavigation
}