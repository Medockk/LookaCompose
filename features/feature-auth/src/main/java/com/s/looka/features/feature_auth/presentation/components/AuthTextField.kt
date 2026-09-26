package com.s.looka.features.feature_auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.s.looka.core.ui.R
import com.s.looka.core.ui.components.textfield.DefaultTextField
import com.s.looka.core.ui.components.textfield.DefaultTextFieldIcon
import com.s.looka.core.ui.components.textfield.DefaultTextFieldKeyboardOptions
import com.s.looka.core.ui.components.textfield.DefaultTextFieldState
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun AuthTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isError: Boolean? = null,
    onFocus: ((Boolean) -> Unit)? = null,
    keyboardOptions: DefaultTextFieldKeyboardOptions = DefaultTextFieldKeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    icon: DefaultTextFieldIcon? = null,
) {

    Column {
        Text(
            text = label,
            color = LookaTheme.colors.primary900,
            style = LookaTheme.typography.b1Medium
        )
        Spacer(Modifier.height(4.dp))
        DefaultTextField(
            state = DefaultTextFieldState(
                value = value,
                onValueChange = onValueChange,
                placeholder = placeholder,
                isError = isError ?: false,
                visualTransformation = visualTransformation,
                maxLines = 1
            ),
            onFocus = onFocus,
            keyboardOptions = keyboardOptions,
            modifier = modifier,
            icon = icon ?: DefaultTextFieldIcon(
                trailingIcon = isError?.let { isError ->
                    {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = if (isError) R.drawable.ic_warning
                                else R.drawable.ic_check
                            ),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )

                    }
                }
            )
        )
    }
}