package com.s.looka.features.feature_auth.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun InvalidEmailText(modifier: Modifier = Modifier) {
    Text(
        text = "Пожалуйства, введите корректный адрес электронной почты",
        color = LookaTheme.colors.error,
        style = LookaTheme.typography.b2Medium,
        modifier = modifier.animateContentSize()
    )
}