package com.s.looka.features.feature_homepage.presentation.screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.features.feature_homepage.R

@Composable
fun FilterBox(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        shape = LookaTheme.shapes.medium,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = LookaTheme.colors.primary900
        ),
        modifier = modifier
            .padding(end = 24.dp)
            .size(52.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_filter_icon),
            contentDescription = "Filter",
            tint = LookaTheme.colors.primary0
        )
    }
}