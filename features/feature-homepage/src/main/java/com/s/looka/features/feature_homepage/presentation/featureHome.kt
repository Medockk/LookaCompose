package com.s.looka.features.feature_homepage.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import coil.compose.AsyncImage
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.navigation.compose.appComposable
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.features.feature_homepage.presentation.screen.HomeScreenRoot
import kotlinx.serialization.Serializable

@Serializable
data object ProductDetails

fun NavGraphBuilder.featureHome(navigator: Navigator) {
    navigation<FeatureHomeNavigation>(
        startDestination = FeatureHomeNavigation.HomeScreen
    ) {
        appComposable<FeatureHomeNavigation.HomeScreen>(navigator.registry) {
            HomeScreenRoot(navigator)
        }
        appComposable("product-details", navigator.registry) {
            Box(Modifier.fillMaxSize().background(LookaTheme.colors.primary0)) {
                AsyncImage("https://www.mydesignation.com/cdn/shop/files/unstoppable-reflective-print-oversized-t-shirt-mydesignation-4800251.jpg?v=1778972951", null,
                    Modifier.fillMaxWidth().aspectRatio(8f / 9f))
            }
        }
        appComposable<ProductDetails>(navigator.registry) {
            Box(Modifier.fillMaxSize().background(LookaTheme.colors.primary0)) {
                AsyncImage("https://www.mydesignation.com/cdn/shop/files/unstoppable-reflective-print-oversized-t-shirt-mydesignation-4800251.jpg?v=1778972951", null,
                    Modifier.fillMaxWidth().aspectRatio(8f / 9f))
            }
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