package com.s.looka.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedHiltViewModel(navController: NavController): T {
    val navGraph = destination.parent?.route ?: return hiltViewModel<T>()
    val parentEntry = remember(this) { navController.getBackStackEntry(navGraph) }

    return hiltViewModel(parentEntry)
}