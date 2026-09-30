package com.s.looka.core.ui.components.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.s.looka.core.ui.R
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun HomepageHeader(
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.discover),
            style = LookaTheme.typography.h2Semibold,
            color = LookaTheme.colors.primary900
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = onNotificationClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_notification_bell),
                contentDescription = "Notifications"
            )
        }
    }
}