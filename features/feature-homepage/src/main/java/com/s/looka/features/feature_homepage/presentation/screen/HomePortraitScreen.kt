package com.s.looka.features.feature_homepage.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.components.ProductCard
import com.s.looka.core.ui.components.chip.DefaultChip
import com.s.looka.core.ui.components.indicator.LoadingIndicator
import com.s.looka.core.ui.components.topbar.DefaultHeader
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.features.feature_homepage.R
import com.s.looka.features.feature_homepage.presentation.HomeAction
import com.s.looka.features.feature_homepage.presentation.HomeState
import com.s.looka.features.feature_homepage.presentation.ProductDetails
import com.s.looka.features.feature_homepage.presentation.screen.component.FilterBox
import com.s.looka.features.feature_homepage.presentation.screen.component.SearchField
import com.s.looka.features.feature_homepage.presentation.screen.component.productNestedScrollConnection

@Composable
internal fun HomePortraitScreen(
    navigator: Navigator,
    state: HomeState,
    searchQuery: String,
    onAction: (HomeAction) -> Unit,
    onSearchQueryChange: (String) -> Unit,
) {
    var isSearchFieldVisible by remember { mutableStateOf(true) }
    val nestedScroll = productNestedScrollConnection { isSearchFieldVisible = it }

    Scaffold(
        contentWindowInsets = WindowInsets.statusBars,
        containerColor = LookaTheme.colors.primary0,
        modifier = Modifier
            .fillMaxSize()
            .background(LookaTheme.colors.primary0)
            .nestedScroll(nestedScroll)
    ) { systemBarsPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(systemBarsPadding)
        ) {

            DefaultHeader(
                label = stringResource(R.string.discover),
                onNotificationClick = { },
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            AnimatedContent(
                targetState = isSearchFieldVisible,
                transitionSpec = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(220))
                        .togetherWith(
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(220))
                        )
                }
            ) {
                if (it) {
                    Row(Modifier.fillMaxWidth()) {
                        SearchField(
                            searchQuery = searchQuery,
                            onSearchQueryChange = onSearchQueryChange,
                            modifier = Modifier.weight(1f).padding(start = 24.dp)
                        )

                        Spacer(Modifier.width(24.dp))

                        FilterBox(
                            onClick = {}
                        )
                    }
                }
            }

            Spacer(Modifier.height(15.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                item {
                    DefaultChip(
                        label = "All",
                        isSelected = true,
                        onClick = {}
                    )
                }
                items(state.categories) { category ->
                    DefaultChip(
                        label = category.name,
                        isSelected = state.selectedCategoryIds.contains(category.id),
                        onClick = { onAction(HomeAction.OnCategoryClick(category.id)) }
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (state.products.isEmpty()) {
                    item {
                        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            LoadingIndicator()
                        }
                    }
                }

                itemsIndexed(state.products) { index, product ->
                    ProductCard(
                        name = product.name,
                        price = product.price,
                        isFavorite = index % 5 == 0,
                        onFavoriteIconClick = {},
                        photoUrl = product.photoUrl,
                        modifier = Modifier.clickable {
                            navigator.navigate(ProductDetails)
                        },
                        discount = product.discount
                    )
                }
            }
        }
    }
}