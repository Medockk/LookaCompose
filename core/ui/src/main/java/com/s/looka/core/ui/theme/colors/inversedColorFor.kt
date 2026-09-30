package com.s.looka.core.ui.theme.colors

import androidx.compose.ui.graphics.Color

fun AppColors.inversedColorFor(color: Color): Color {
    return when (color) {
        this.primary0 -> primary900
        this.primary100 -> primary800
        this.primary200 -> primary700
        this.primary300 -> primary600
        this.primary400 -> primary500
        this.primary500 -> primary400
        this.primary600 -> primary300
        this.primary700 -> primary200
        this.primary800 -> primary100
        this.primary900 -> primary0

        else -> Color.Unspecified
    }
}