package com.s.looka.core.ui.components.textfield

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

data class DefaultTextFieldKeyboardOptions(
    val keyboardType: KeyboardType = KeyboardType.Text,
    val imeType: ImeAction = ImeAction.Done,
    val onImeClick: (() -> Unit)? = null,
)
