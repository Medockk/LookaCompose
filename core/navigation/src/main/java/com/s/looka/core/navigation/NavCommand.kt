package com.s.looka.core.navigation

import androidx.navigation.NavOptionsBuilder

sealed interface NavCommand {

    data class NavigateTo(val route: Any, val options: (NavOptionsBuilder.() -> Unit)? = null): NavCommand
    data object NavigateUp: NavCommand
}