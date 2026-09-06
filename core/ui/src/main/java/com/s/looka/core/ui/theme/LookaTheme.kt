package com.s.looka.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.s.looka.core.ui.theme.colors.AppColors
import com.s.looka.core.ui.theme.colors.LocalAppColors
import com.s.looka.core.ui.theme.shape.AppShape
import com.s.looka.core.ui.theme.shape.LocalAppShape
import com.s.looka.core.ui.theme.typography.AppTypography
import com.s.looka.core.ui.theme.typography.LocalAppTypography

object LookaTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val shapes: AppShape
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShape.current
}