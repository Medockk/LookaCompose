package com.s.looka.core.navigation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

@SuppressLint("ComposableNaming")
@Composable
fun Navigator.collectNavCommands(navController: NavController) {
    LaunchedEffect(this) {
        this@collectNavCommands.navCommands.collect { navCommand ->
            when (navCommand) {
                is NavCommand.NavigateTo -> {
                    when (val route = navCommand.route) {
                        is String -> navController.navigateTo(route, navCommand.options)
                        is Uri -> navController.navigateTo(route, navCommand.options)
                        else -> navController.navigateTo(route, navCommand.options)
                    }
                }
                NavCommand.NavigateUp -> navController.navigateUp()
            }
        }
    }
}

private inline fun <reified T: Any> NavController.navigateTo(route: T, noinline options: (NavOptionsBuilder.() -> Unit)? = null) {
    this.navigate(route, options ?: {
        launchSingleTop = true
    })
}