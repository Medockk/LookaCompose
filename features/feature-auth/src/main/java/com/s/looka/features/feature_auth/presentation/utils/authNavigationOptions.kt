package com.s.looka.features.feature_auth.presentation.utils

import androidx.navigation.NavOptionsBuilder

internal inline fun <reified T: Any> NavOptionsBuilder.authNavigationOptions(
    popUpTo: T
) {
    this.popUpTo(popUpTo) {
        inclusive = true
        saveState = true
    }

    launchSingleTop = true
    restoreState = true
}