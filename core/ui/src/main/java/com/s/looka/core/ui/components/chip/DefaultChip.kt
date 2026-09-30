package com.s.looka.core.ui.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.core.ui.theme.colors.inversedColorFor

@Composable
fun DefaultChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val selectedColor = if (isSelected) LookaTheme.colors.primary900
    else LookaTheme.colors.primary0

    AssistChip(
        onClick = onClick,
        label = {
            Text(
                text = label,
                color = LookaTheme.colors.inversedColorFor(selectedColor),
                style = LookaTheme.typography.b1Medium
            )
        },
        modifier = modifier,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = selectedColor
        ),
        border = if (isSelected) BorderStroke(1.dp, LookaTheme.colors.primary100)
        else null
    )
}