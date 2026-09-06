package com.s.looka.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.s.looka.core.ui.theme.colors.LocalAppColors
import com.s.looka.core.ui.theme.colors.darkAppColors
import com.s.looka.core.ui.theme.colors.lightAppColors
import com.s.looka.core.ui.theme.shape.LocalAppShape
import com.s.looka.core.ui.theme.shape.appShape
import com.s.looka.core.ui.theme.typography.LocalAppTypography
import com.s.looka.core.ui.theme.typography.appTypography

@Composable
fun ApplicationLookaTheme(
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (isSystemInDarkTheme) darkAppColors
    else lightAppColors

    CompositionLocalProvider(
        LocalAppColors provides colorScheme,
        LocalAppTypography provides appTypography,
        LocalAppShape provides appShape,
        content = content
    )
}