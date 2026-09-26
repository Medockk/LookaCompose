package com.s.looka.core.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.core.ui.theme.colors.LocalAppColors
import com.s.looka.core.ui.theme.shape.LocalAppShape
import com.s.looka.core.ui.theme.typography.LocalAppTypography

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color = LocalAppColors.current.primary900,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    shape: Shape = LocalAppShape.current.medium,
    minHeight: Dp = 56.dp,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .heightIn(min = minHeight)
            .clip(shape)
            .background(color = if (enabled) background else LookaTheme.colors.primary200)
            .semantics { role = Role.Button }
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = contentAlignment,
        content = { content() }
    )
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    background: Color = LocalAppColors.current.primary900,
    onBackground: Color = LocalAppColors.current.primary0,
    shape: Shape = LocalAppShape.current.medium,
    textStyle: TextStyle = LocalAppTypography.current.b1Medium,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) = PrimaryButton(
    onClick = onClick,
    background = background,
    shape = shape,
    enabled = enabled,
    interactionSource = interactionSource,
    modifier = modifier
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let {
            leadingIcon.invoke()
            Spacer(Modifier.width(10.dp))
        }
        Text(
            text = label,
            color = onBackground,
            style = textStyle,
        )
        trailingIcon?.let {
            Spacer(Modifier.width(10.dp))
            trailingIcon.invoke()
        }
    }
}