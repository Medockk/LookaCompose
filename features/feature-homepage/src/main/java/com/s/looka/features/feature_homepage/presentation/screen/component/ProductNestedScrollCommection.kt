package com.s.looka.features.feature_homepage.presentation.screen.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource

@Composable
fun productNestedScrollConnection(
    onScroll: (Boolean) -> Unit,
): NestedScrollConnection {
    return remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                if (delta > 15f) {
                    onScroll(true)
                } else if (delta < -15f) {
                    onScroll(false)
                }
                return Offset.Zero
            }
        }
    }
}