package com.s.looka.core.navigation.compose

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import com.s.looka.core.navigation.NavCommand
import com.s.looka.core.navigation.Navigator

@SuppressLint("ComposableNaming")
@Composable
fun Navigator.collectNavCommands(navController: NavController) {
    LaunchedEffect(this) {
        this@collectNavCommands.navCommands.collect { navCommand ->
            when (navCommand) {
                is NavCommand.NavigateTo -> {
                    when (val route = navCommand.route) {
                        is Uri -> {
                            val navOptions = navCommand.options?.let{ builder ->
                                navOptions(builder)
                            }
                            navController.navigate(route, navOptions)
                        }
                        is String -> navController.navigate(route, navCommand.options ?: {})
                        else -> navController.navigateTo(route, navCommand.options)
                    }
                }
                NavCommand.NavigateUp -> navController.navigateUp()
            }
        }
    }
}

private inline fun <reified T: Any> NavController.navigateTo(route: T, noinline options: (NavOptionsBuilder.() -> Unit)? = null) {
    Log.d("Navigation", "collectNavCommand: navigateTo: route is {${route::class}}")
    this.navigate(route, options ?: {
        launchSingleTop = true
    })
}