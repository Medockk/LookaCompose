package com.s.looka.core.ui.theme

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.s.looka.core.ui.theme.colors.LocalAppColors
import com.s.looka.core.ui.theme.colors.darkAppColors
import com.s.looka.core.ui.theme.colors.lightAppColors
import com.s.looka.core.ui.theme.shape.LocalAppShape
import com.s.looka.core.ui.theme.shape.appShape
import com.s.looka.core.ui.theme.typography.LocalAppTypography
import com.s.looka.core.ui.theme.typography.appTypography
import com.s.looka.core.ui.util.DeviceConfiguration
import com.s.looka.core.ui.util.LocalDeviceConfiguration
import com.s.looka.core.ui.util.LocalNavigationBarVisibility

@Composable
fun ApplicationLookaTheme(
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (isSystemInDarkTheme) { darkAppColors }
    else { lightAppColors }
    val windowAdaptiveInfo = currentWindowAdaptiveInfoV2().windowSizeClass
    val view = LocalView.current

    val isNavigationBarVisible = retain { mutableStateOf(false) }

    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as Activity
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = !isSystemInDarkTheme
        }
    }

    CompositionLocalProvider(
        LocalAppColors provides colorScheme,
        LocalAppTypography provides appTypography,
        LocalTextStyle provides appTypography.b1Regular,
        LocalContentColor provides colorScheme.primary900,
        LocalAppShape provides appShape,
        LocalDeviceConfiguration provides DeviceConfiguration.fromWindowSizeClass(windowAdaptiveInfo),
        LocalNavigationBarVisibility provides isNavigationBarVisible,
        content = content
    )

    Log.i("ApplicationLookaTheme", "LocalContentColor: ${LocalContentColor.current}")
    Log.i("ApplicationLookaTheme", "LocalContentColor is unspecified: ${LocalContentColor.current.isUnspecified}")
}