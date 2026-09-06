package com.s.looka.core.ui.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class AppTypography(
    val h1Semibold: TextStyle = TextStyle.Default,
    val h2Semibold: TextStyle = TextStyle.Default,
    val h3Semibold: TextStyle = TextStyle.Default,
    val h4Semibold: TextStyle = TextStyle.Default,
    val h4Medium: TextStyle = TextStyle.Default,

    val b1Regular: TextStyle = TextStyle.Default,
    val b2Regular: TextStyle = TextStyle.Default,
    val b3Regular: TextStyle = TextStyle.Default,

    val b1Semibold: TextStyle = TextStyle.Default,
    val b2Semibold: TextStyle = TextStyle.Default,
    val b3Semibold: TextStyle = TextStyle.Default,

    val b1Medium: TextStyle = TextStyle.Default,
    val b2Medium: TextStyle = TextStyle.Default,
    val b3Medium: TextStyle = TextStyle.Default,
)