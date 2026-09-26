package com.s.looka.core.ui.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.s.looka.core.ui.R

private val UnboundedFontFamily = FontFamily(
    Font(R.font.general_sans_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.general_sans_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.general_sans_bold, FontWeight.Bold, FontStyle.Normal),
)

internal val appTypography = AppTypography(
    h1Semibold = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 64.sp,
        lineHeight = 1.25.em,
        fontWeight = FontWeight.SemiBold
    ),
    h2Semibold = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 32.sp,
        lineHeight = 1.0.em,
        letterSpacing = (-2).sp,
        fontWeight = FontWeight.SemiBold
    ),
    h3Semibold = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 24.sp,
        lineHeight = 1.2.em,
        fontWeight = FontWeight.SemiBold
    ),
    h4Semibold = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 20.sp,
        lineHeight = 1.2.em,
        fontWeight = FontWeight.SemiBold
    ),
    h4Medium = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 20.sp,
        lineHeight = 1.2.em,
        fontWeight = FontWeight.Medium
    ),

    b1Regular = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    b2Regular = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    b3Regular = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal
    ),

    b1Semibold = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold
    ),
    b2Semibold = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    b3Semibold = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),

    b1Medium = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Medium
    ),
    b2Medium = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium
    ),
    b3Medium = TextStyle(
        fontFamily = UnboundedFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium
    ),
)
