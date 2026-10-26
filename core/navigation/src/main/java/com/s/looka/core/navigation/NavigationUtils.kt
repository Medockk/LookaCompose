package com.s.looka.core.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

private fun AnimatedContentTransitionScope<NavBackStackEntry>.bothDestinationWithBottomBar(registry: NavigationDestinationRegistry): Boolean {
    val initialVisibility = registry.metadataFor(
        this.initialState.destination.route
    )?.isNavigationBarVisible == true

    val targetVisibility = registry.metadataFor(
        this.targetState.destination.route
    )?.isNavigationBarVisible == true

    return initialVisibility && targetVisibility
}

fun NavigationDestinationRegistry.enterTransition():
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            log("enterTransition")
    if (bothDestinationWithBottomBar(this@enterTransition)) {
        fadeIn()
    } else {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Start,
            tween()
        )
    }
}

fun NavigationDestinationRegistry.exitTransition():
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            log("exitTransition")
    if (bothDestinationWithBottomBar(this@exitTransition)) {
        fadeOut()
    } else {
        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween())
    }
}

fun NavigationDestinationRegistry.popEnterTransition():
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            log("popEnterTransition")
    if (bothDestinationWithBottomBar(this@popEnterTransition)) {
        fadeIn()
    } else {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Start,
            tween()
        )
    }
}

fun NavigationDestinationRegistry.popExitTransition():
        AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            log("popExitTransition")
    if (bothDestinationWithBottomBar(this@popExitTransition)) {
        fadeOut()
    } else {
        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween())
    }
}

fun NavigationDestinationRegistry.predictivePopEnterTransition():
        AnimatedContentTransitionScope<NavBackStackEntry>.(Int) -> EnterTransition = {
            log("popEnterTransition")
    if (bothDestinationWithBottomBar(this@predictivePopEnterTransition)) {
        fadeIn()
    } else {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Start,
            tween()
        )
    }
}

fun NavigationDestinationRegistry.predictivePopExitTransition():
        AnimatedContentTransitionScope<NavBackStackEntry>.(Int) -> ExitTransition = {
            log("popExitTransition")
    if (bothDestinationWithBottomBar(this@predictivePopExitTransition)) {
        fadeOut()
    } else {
        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween())
    }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.log(tag: String) {
    Log.d("Nav: $tag", """
        initialRoute: ${this.initialState.destination.route},
        targetRoute: ${this.targetState.destination.route}
    """.trimIndent())
}