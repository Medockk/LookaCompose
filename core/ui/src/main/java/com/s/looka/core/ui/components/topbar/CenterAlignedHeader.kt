package com.s.looka.core.ui.components.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun CenterAlignedHeader(
    label: String,
    modifier: Modifier = Modifier,
    icons: HeaderIcons = HeaderIcons(),
    onTrailingIconClick: (() -> Unit)? = null,
    onLeadingIconClick: (() -> Unit)? = null,
    withBottomDivider: Boolean = true,
) {
    Column {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onLeadingIconClick ?: {}
            ) { icons.leadingIcon.invoke() }

            Spacer(Modifier.weight(1f))

            Text(
                text = label,
                style = LookaTheme.typography.h3Semibold,
                color = LookaTheme.colors.primary900
            )

            Spacer(Modifier.weight(1f))

            IconButton(
                onClick = onTrailingIconClick ?: {}
            ) { icons.trailingIcon.invoke() }
        }

        Spacer(Modifier.height(20.dp))
        if (withBottomDivider) {
            HorizontalDivider(color = LookaTheme.colors.primary100)
        }
    }
}