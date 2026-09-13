package com.s.looka.core.ui.components.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun LoadingIndicator(
    count: Int,
    size: Dp,
    color: Color,
    modifier: Modifier = Modifier
) {
    var activeIndex by remember { mutableIntStateOf(0) }
    
    LaunchedEffect(count) {
        while (true) {
            delay(150.milliseconds)
            activeIndex = (activeIndex + 1) % count
        }
    }

    val barWidth = 2.dp
    val barHeight = size / 3
    val containerSize = size + barHeight

    Box(
        modifier = modifier.size(containerSize),
        contentAlignment = Alignment.Center
    ) {
        for (i in 0 until count) {
            val degrees = i * 360f / count
            val angleRad = Math.toRadians((degrees - 90).toDouble()).toFloat()
            
            val radiusPx = size.value / 2
            val x = radiusPx * cos(angleRad)
            val y = radiusPx * sin(angleRad)

            // ближайшие палочки анимации (которые уже были, для эффекта плавности)
            val dist = (activeIndex - i + count) % count
            val alpha = when {
                dist == 0 -> 1f
                dist < 5 -> 1f - (dist * 0.15f)
                else -> 0.2f
            }

            Box(
                modifier = Modifier
                    .offset(x.dp, y.dp)
                    .rotate(degrees)
                    .size(barWidth, barHeight)
                    .background(color.copy(alpha = alpha), RoundedCornerShape(50))
            )
        }
    }
}