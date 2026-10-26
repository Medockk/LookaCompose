package com.s.looka.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.s.looka.core.ui.R
import com.s.looka.core.ui.theme.LookaTheme

@Composable
fun ProductCard(
    name: String,
    price: Double,
    isFavorite: Boolean,
    onFavoriteIconClick: () -> Unit,
    photoUrl: String?,
    modifier: Modifier = Modifier,
    discount: Float? = null,
) {
    var state by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }

    Column(modifier) {
        Box {
            when (state) {
                is AsyncImagePainter.State.Error -> Image(
                    ImageVector.vectorResource(R.drawable.ic_warning),
                    null
                )

                is AsyncImagePainter.State.Loading -> Box(
                    Modifier
                        .matchParentSize()
                        .background(Color.Gray)
                )

                is AsyncImagePainter.State.Success -> Unit
                else -> Unit
            }

            AsyncImage(
                model = photoUrl,
                contentDescription = name,
                onState = { state = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(8f / 9f)
                    .clip(LookaTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 12.dp)
                    .size(34.dp)
                    .clip(LookaTheme.shapes.small)
                    .dropShadow(
                        shape = LookaTheme.shapes.small,
                        shadow = Shadow(
                            radius = 3.dp,
                            color = LookaTheme.colors.primary900,
                            alpha = 0.2f
                        )
                    ).background(LookaTheme.colors.primary0)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            color = LookaTheme.colors.primary900
                        )
                    ) {
                        onFavoriteIconClick()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = if (isFavorite) {
                            R.drawable.heart_filled_icon
                        } else {
                            R.drawable.ic_navigation_favorite_icon
                        }
                    ),
                    contentDescription = "Favorite",
                    tint = Color.Unspecified
                )
            }
//            IconButton(
//                onClick = onFavoriteIconClick,
//                shape = LookaTheme.shapes.small,
//                colors = IconButtonDefaults.iconButtonColors(
//                    containerColor = LookaTheme.colors.primary0
//                ),
//                modifier = Modifier
//                    .size(34.dp)
//                    .dropShadow(
//                        shape = LookaTheme.shapes.small,
//                        shadow = Shadow(
//                            radius = 1.dp,
//                            color = LookaTheme.colors.primary900,
//                            alpha = 0.2f
//                        )
//                    ).background(LookaTheme.colors.primary0, LookaTheme.shapes.small)
//            ) {
//                Icon(
//                    imageVector = ImageVector.vectorResource(
//                        id = if (isFavorite) {
//                            R.drawable.heart_filled_icon
//                        } else {
//                            R.drawable.ic_navigation_favorite_icon
//                        }
//                    ),
//                    contentDescription = "Favorite",
//                    tint = LookaTheme.colors.error
//                )
//            }
        }

        Spacer(Modifier.height(8.dp))
        Text(
            text = name,
            style = LookaTheme.typography.b1Semibold,
            color = LookaTheme.colors.primary900
        )

        Spacer(Modifier.height(3.dp))
        Row {
            Text(
                text = "%.2f".format(LocalLocale.current.platformLocale, price),
                style = LookaTheme.typography.b3Medium,
                color = LookaTheme.colors.primary500
            )

            if (discount != null) {
                Text(
                    text = discount.toString(),
                    style = LookaTheme.typography.b1Medium,
                    color = LookaTheme.colors.error
                )
            }
        }
    }
}