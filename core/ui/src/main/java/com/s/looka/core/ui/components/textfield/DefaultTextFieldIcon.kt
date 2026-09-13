package com.s.looka.core.ui.components.textfield

import androidx.compose.runtime.Composable

data class DefaultTextFieldIcon(
    val leadingIcon: (@Composable () -> Unit)? = null,
    val trailingIcon: (@Composable () -> Unit)? = null,
)
