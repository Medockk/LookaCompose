package com.s.looka.android.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.s.looka.core.ui.theme.LookaTheme

@Composable
internal fun PortraitNavigationBar(
    onNavigate: (NavBarItem.Type?) -> Unit,
    navBarItems: List<NavBarItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = LookaTheme.colors.primary0)
            .navigationBarsPadding()
    ) {
        HorizontalDivider(color = LookaTheme.colors.primary100)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            navBarItems.forEach { navBarItem ->
                val contentColor = if (navBarItem.isSelected) LookaTheme.colors.primary900
                else LookaTheme.colors.primary400

                Column(
                    modifier = Modifier
                        .size(width = 65.dp, height = 45.dp)
                        .clip(CircleShape)
                        .clickable(
                            onClick = {
                                val destination = navBarItem.type
                                Log.d("NavBar", "PortraitNavBar: onClick: destination $destination")
                                onNavigate(destination)
                            },
                            role = Role.Button,
                            indication = ripple(color = LookaTheme.colors.primary900),
                            interactionSource = null
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(navBarItem.icon),
                        contentDescription = navBarItem.label,
                        tint = contentColor
                    )
                    Text(
                        text = navBarItem.label,
                        color = contentColor,
                        style = LookaTheme.typography.b2Medium
                    )
                }
            }
        }
    }
}