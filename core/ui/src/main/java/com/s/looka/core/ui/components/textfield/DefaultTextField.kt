package com.s.looka.core.ui.components.textfield

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import com.s.looka.core.ui.R
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun DefaultTextField(
    state: DefaultTextFieldState,
    modifier: Modifier = Modifier,
    keyboardOptions: DefaultTextFieldKeyboardOptions = DefaultTextFieldKeyboardOptions(),
    icon: DefaultTextFieldIcon = DefaultTextFieldIcon(),
    onFocusGone: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val localFocusManager = LocalFocusManager.current
    var wasFocused by remember { mutableStateOf(false) }

    LaunchedEffect(isFocused) {
        if (isFocused) {
            wasFocused = true
        } else if (wasFocused) {
            if (onFocusGone != null) {
                onFocusGone()
            }
            wasFocused = false
        }
    }

    OutlinedTextField(
        value = state.value,
        onValueChange = state.onValueChange,
        modifier = modifier,
        isError = state.isError,
        maxLines = state.maxLines,
        singleLine = state.maxLines == 1,
        visualTransformation = state.visualTransformation,
        label = state.placeholder?.let { placeholder ->
            {
                Text(
                    text = placeholder,
                    style = LookaTheme.typography.b1Regular,
                    color = if (state.isError) LookaTheme.colors.error
                    else LookaTheme.colors.primary400
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardOptions.keyboardType,
            imeAction = keyboardOptions.imeType
        ),
        keyboardActions = KeyboardActions {
            if (keyboardOptions.onImeClick != null) {
                keyboardOptions.onImeClick()
                return@KeyboardActions
            }

            val ime = keyboardOptions.imeType
            if (ime == ImeAction.Done) {
                localFocusManager.clearFocus()
                return@KeyboardActions
            }

            if (ime == ImeAction.None) {
                Log.d("ImeAction", "imeAction is ImeAction.None")
                return@KeyboardActions
            }

            Log.d("ImeAction", "Using default action for $ime")
            defaultKeyboardAction(ime)
        },
        interactionSource = interactionSource,
        shape = LookaTheme.shapes.medium,
        trailingIcon = if (icon.trailingIcon == null) {
            {
                if (state.isError) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_warning),
                        contentDescription = "Warning!",
                        tint = LookaTheme.colors.error
                    )
                }
            }
        } else icon.trailingIcon,
        leadingIcon = icon.leadingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = LookaTheme.colors.primary0,
            unfocusedContainerColor = LookaTheme.colors.primary0,
            errorContainerColor = LookaTheme.colors.primary0,

            focusedBorderColor = LookaTheme.colors.primary300,
            unfocusedBorderColor = LookaTheme.colors.primary100,
            errorBorderColor = LookaTheme.colors.error,

            focusedTextColor = LookaTheme.colors.primary900,
            unfocusedTextColor = LookaTheme.colors.primary900,
        )
    )
}