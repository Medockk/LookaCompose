package com.s.looka.core.navigation

import android.util.Log
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NavigatorImpl @Inject constructor(): Navigator {

    @Suppress("PrivatePropertyName")
    private val TAG = "Navigator"

    private val _navCommands = MutableSharedFlow<NavCommand>(extraBufferCapacity = 1)
    override val navCommands: SharedFlow<NavCommand> = _navCommands.asSharedFlow()

    override fun navigate(route: Any, options: (NavOptionsBuilder.() -> Unit)?) {
        Log.i(TAG, "navigate: Navigate to $route with options $options")
        _navCommands.tryEmit(NavCommand.NavigateTo(route, options))
    }

    override fun navigateUp() {
        Log.i(TAG, "navigateUp: NavigatingUp")
        _navCommands.tryEmit(NavCommand.NavigateUp)
    }
}