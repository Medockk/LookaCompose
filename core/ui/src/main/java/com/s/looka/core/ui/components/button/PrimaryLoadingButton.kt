package com.s.looka.core.ui.components.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.s.looka.core.ui.theme.colors.LocalAppColors
import com.s.looka.core.ui.theme.shape.LocalAppShape
import com.s.looka.core.ui.theme.typography.LocalAppTypography

@Composable
fun PrimaryLoadingButton(
    onClick: () -> Unit,
    isLoading: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    background: Color = LocalAppColors.current.primary900,
    onBackground: Color = LocalAppColors.current.primary0,
    shape: Shape = LocalAppShape.current.medium,
    textStyle: TextStyle = LocalAppTypography.current.b1Medium,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = PrimaryButton(
    onClick = onClick,
    modifier = modifier,
    background = background,
    interactionSource = interactionSource,
    enabled = enabled,
    shape = shape,
) {
    Row(
        modifier = Modifier.matchParentSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            targetState = isLoading
        ) { isLoading ->
            if (isLoading) {
                CircularProgressIndicator(
                    color = LocalAppColors.current.primary0,
                )
            } else {
                Text(
                    text = label,
                    color = onBackground,
                    style = textStyle,
                )
            }
        }
    }
}