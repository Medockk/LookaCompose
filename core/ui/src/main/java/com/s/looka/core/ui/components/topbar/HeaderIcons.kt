package com.s.looka.core.ui.components.topbar

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.s.looka.core.ui.theme.LookaTheme

data class HeaderIcons(
    val leadingIcon: @Composable () -> Unit = {
        Icon(
            imageVector = ImageVector.vectorResource(com.s.looka.core.ui.R.drawable.ic_back_click_icon),
            contentDescription = null,
            tint = LookaTheme.colors.primary900
        )
    },
    val trailingIcon: @Composable () -> Unit = {
        Icon(
            imageVector = ImageVector.vectorResource(com.s.looka.core.ui.R.drawable.ic_notification_bell),
            contentDescription = null,
            tint = LookaTheme.colors.primary900
        )
    },
)
