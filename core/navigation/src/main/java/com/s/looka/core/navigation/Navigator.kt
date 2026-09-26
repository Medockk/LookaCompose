package com.s.looka.core.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.SharedFlow

interface Navigator {
    val navCommands: SharedFlow<NavCommand>

    fun navigate(route: Any, options: (NavOptionsBuilder.() -> Unit)? = null)
    fun navigateUp()
}