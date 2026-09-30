package com.s.looka.features.feature_homepage.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.components.chip.DefaultChip
import com.s.looka.core.ui.components.textfield.DefaultTextField
import com.s.looka.core.ui.components.textfield.DefaultTextFieldState
import com.s.looka.core.ui.components.topbar.DefaultHeader
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.core.ui.util.LocalNavigationBarVisibility
import com.s.looka.features.feature_homepage.R
import com.s.looka.features.feature_homepage.presentation.HomeAction
import com.s.looka.features.feature_homepage.presentation.HomeState
import com.s.looka.features.feature_homepage.presentation.HomeViewModel
import kotlin.random.Random

@Composable
internal fun HomePortraitScreen(
    navigator: Navigator,
    state: HomeState,
    onAction: (HomeAction) -> Unit
) {

    val verticalScrollState = rememberLazyListState()
    var isSearchFieldVisible by remember { mutableStateOf(true) }
    val nestedScroll = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                if (delta > 15f) {
                    isSearchFieldVisible = true
                } else if (delta < -15f) isSearchFieldVisible = false
                return Offset.Zero
            }
        }
    }


    val isNavigationBarVisible = LocalNavigationBarVisibility.current
    LaunchedEffect(Unit) {
        isNavigationBarVisible.value = true
    }

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
            )

            AnimatedContent(isSearchFieldVisible) {
                if (it) {
                    Column {
                        DefaultTextField(
                            state = DefaultTextFieldState(
                                value = "", {}
                            )
                        )

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(state.categories) { category ->
                                DefaultChip(
                                    label = category.name,
                                    isSelected = state.selectedCategoryIds.contains(category.id),
                                    onClick = { onAction(HomeAction.OnCategoryClick(category.id)) }
                                )
                            }
                        }
                    }
                }
            }

            LazyColumn(state = verticalScrollState) {

                items(50) {
                    Spacer(
                        Modifier
                            .size(50.dp)
                            .background(
                                Color(
                                    Random.nextFloat(),
                                    Random.nextFloat(),
                                    Random.nextFloat(),
                                )
                            ).padding(5.dp)
                    )
                }
            }
        }
    }
}