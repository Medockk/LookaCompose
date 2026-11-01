package com.s.looka.core.network.impl

import android.util.Log
import com.s.looka.core.common.DataError
import com.s.looka.core.network.api.WebSocketClient
import com.s.looka.core.network.api.WebSocketState
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class WebSocketClientImpl @Inject constructor(
    private val httpClient: HttpClient
): WebSocketClient {

    private val TAG = "WebSocketClientImpl"

    private val _state = MutableStateFlow<WebSocketState>(WebSocketState.Disconnected)
    override val state: StateFlow<WebSocketState> = _state.asStateFlow()

    private val _incomingMessages = MutableSharedFlow<String>(extraBufferCapacity = 64)
    override val incomingMessage: Flow<String> = _incomingMessages.asSharedFlow()

    private var session: DefaultWebSocketSession? = null
    private var connectionJob: Job? = null

    override suspend fun connect(url: String) {

        if (_state.value is WebSocketState.Connecting || _state.value == WebSocketState.Disconnected)
            return

        connectionJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                httpClient.webSocket(urlString = url) {
                    session = this
                    _state.update { WebSocketState.Connected }

                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            val message = frame.readText()
                            _incomingMessages.emit(message)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "connect: failed to connect", e)
                _state.emit(WebSocketState.Failed(DataError.Network.SERVER_ERROR))
            } finally {
                session = null
                _state.update { WebSocketState.Disconnected }
            }
        }
    }

    override suspend fun disconnect() {
        connectionJob?.cancel()
        session?.close()
        session = null

        _state.update { WebSocketState.Disconnected }
    }

    override suspend fun send(rawJson: String): Boolean {
        val currentSession = session ?: return false

        try {
            currentSession.send(Frame.Text(rawJson))
            return true
        } catch (e: Exception) {
            Log.e(TAG, "send: failed to send message: $rawJson", e)
            return false
        }
    }
}