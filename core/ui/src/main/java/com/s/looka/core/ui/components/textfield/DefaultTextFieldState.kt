package com.s.looka.core.ui.components.textfield

import androidx.compose.ui.text.input.VisualTransformation

data class DefaultTextFieldState(
    val value: String,
    val onValueChange: (String) -> Unit,
    val placeholder: String? = null,
    val isError: Boolean = false,
    val maxLines: Int = 1,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
)
