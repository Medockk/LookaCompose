package com.s.looka.core.navigation.compose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.s.looka.core.navigation.NavigationDestinationMetadata
import com.s.looka.core.navigation.NavigationDestinationRegistry
import com.s.looka.core.navigation.ShowNavigationBar
import kotlinx.serialization.serializer
import kotlin.reflect.full.findAnnotation

data object DefaultAnimationTransition {
    val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Start
        )
    }
    val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.End
        )
    }
}

fun NavGraphBuilder.appComposable(
    route: String,
    registry: NavigationDestinationRegistry,
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = DefaultAnimationTransition.enterTransition,
    exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = DefaultAnimationTransition.exitTransition,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    registry.registry(
        route = route,
        metadata = NavigationDestinationMetadata(
            enterTransition = enterTransition,
            exitTransition = exitTransition,
        )
    )

    composable(route, deepLinks = deepLinks, enterTransition = enterTransition, exitTransition = exitTransition, content = content)
}

inline fun <reified T : Any> NavGraphBuilder.appComposable(
    registry: NavigationDestinationRegistry,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    val serializer = serializer<T>()
    val route = serializer.descriptor.serialName

    val showNavigationBarAnnotation = T::class.findAnnotation<ShowNavigationBar>()
    val isNavigationBarVisible = showNavigationBarAnnotation != null

    registry.registry(
        route = route,
        metadata = NavigationDestinationMetadata(
            enterTransition = {
                if (!isNavigationBarVisible) {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
                } else {
                    fadeIn()
                }
            },
            exitTransition = {
                if (!isNavigationBarVisible) {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)
                } else {
                    fadeOut()
                }
            },
            isNavigationBarVisible = showNavigationBarAnnotation != null,
            withLocalNavigationVisibilityProvider = showNavigationBarAnnotation?.withLocalNavigationBarProvider
                ?: true
        )
    )

    composable<T>(content = content, deepLinks = deepLinks)
}