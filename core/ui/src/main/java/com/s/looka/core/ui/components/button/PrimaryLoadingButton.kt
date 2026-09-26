package com.s.looka.core.ui.components.button

import androidx.annotation.IntRange
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.s.looka.core.ui.components.indicator.LoadingIndicator
import com.s.looka.core.ui.theme.ApplicationLookaTheme
import com.s.looka.core.ui.theme.colors.LocalAppColors
import com.s.looka.core.ui.theme.shape.LocalAppShape
import com.s.looka.core.ui.theme.typography.LocalAppTypography

@Preview(showSystemUi = true)
@Composable
private fun PrimaryLoadingButtonPreview() {
    ApplicationLookaTheme {
        PrimaryLoadingButton(
            onClick = {},
            isLoading = true,
            label = "Загрузка...",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

/**
 * @param loadingBars Количество палочек в индикаторе.
 * @param indicatorSize Диаметр круга, по которому расположены центры палочек.
 */
@Composable
fun PrimaryLoadingButton(
    onClick: () -> Unit,
    isLoading: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    @IntRange(from = 8)
    loadingBars: Int = 16,
    indicatorSize: Dp = 24.dp,
    background: Color = LocalAppColors.current.primary900,
    onBackground: Color = LocalAppColors.current.primary0,
    shape: Shape = LocalAppShape.current.medium,
    textStyle: TextStyle = LocalAppTypography.current.b1Medium,
    enabled: Boolean = true,
) = PrimaryButton(
    onClick = onClick,
    modifier = modifier,
    background = background,
    enabled = enabled,
    shape = shape,
) {
    AnimatedContent(
        targetState = isLoading,
        label = "PrimaryLoadingButtonAnimatedContent"
    ) { loading ->
        if (loading) {
            LoadingIndicator(
                count = loadingBars,
                size = indicatorSize,
                color = onBackground
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
