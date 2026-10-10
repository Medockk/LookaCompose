package com.s.looka.features.feature_homepage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.navigation.compose.appComposable
import com.s.looka.features.feature_homepage.presentation.screen.HomeScreenRoot
import kotlinx.serialization.Serializable

@Serializable
data object My

fun NavGraphBuilder.featureHome(navigator: Navigator) {
    navigation<FeatureHomeNavigation>(
        startDestination = FeatureHomeNavigation.HomeScreen
    ) {
        appComposable<FeatureHomeNavigation.HomeScreen>(navigator.registry) {
            HomeScreenRoot(navigator)
        }
        appComposable(
            route = "my",
            navigator.registry,
            deepLinks = listOf(navDeepLink { uriPattern = "looka://market/my" })
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(Color.Red)
            )

        }
    }
}