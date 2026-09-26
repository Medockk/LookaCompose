package com.s.looka.features.feature_auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun AuthHeader(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = LookaTheme.typography.h2Semibold
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = subTitle,
            color = LookaTheme.colors.primary500,
            style = LookaTheme.typography.b1Regular
        )
    }
}