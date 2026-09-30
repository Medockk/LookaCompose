package com.s.looka.core.ui.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalAppColors = staticCompositionLocalOf { AppColors() }

internal val lightAppColors = AppColors(
    primary900 = Color(0xFF1A1A1A),
    primary800 = Color(0xFF333333),
    primary700 = Color(0xFF4D4D4D),
    primary600 = Color(0xFF666666),
    primary500 = Color(0xFF808080),
    primary400 = Color(0xFF999999),
    primary300 = Color(0xFFB3B3B3),
    primary200 = Color(0xFFCCCCCC),
    primary100 = Color(0xFFE6E6E6),
    primary0 = Color(0xffffffff),
    success = Color(0xFF0C9409),
    error = Color(0xFFED1010)
)

internal val darkAppColors = AppColors(
    primary0 = Color(0xFF1A1A1A),
    primary100 = Color(0xFF333333),
    primary200 = Color(0xFF4D4D4D),
    primary300 = Color(0xFF666666),
    primary400 = Color(0xFF808080),
    primary500 = Color(0xFF999999),
    primary600 = Color(0xFFB3B3B3),
    primary700 = Color(0xFFCCCCCC),
    primary800 = Color(0xFFE6E6E6),
    primary900 = Color(0xffffffff),
    success = Color(0xFF0C9409),
    error = Color(0xFFED1010)
)