package com.s.looka.core.network.api

import com.s.looka.core.common.RootError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface WebSocketClient {

    val state: StateFlow<WebSocketState>

    /** Raw json message **/
    val incomingMessage: Flow<String>

    suspend fun connect(url: String)
    suspend fun disconnect()

    suspend fun send(rawJson: String): Boolean
}

sealed interface WebSocketState {

    data object Connecting: WebSocketState
    data object Connected: WebSocketState
    data object Disconnected: WebSocketState

    data class Failed(val error: RootError): WebSocketState
}