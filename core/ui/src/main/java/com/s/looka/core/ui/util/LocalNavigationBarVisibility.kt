package com.s.looka.core.ui.util

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

val LocalNavigationBarVisibility = compositionLocalOf { mutableStateOf(false) }