package com.s.looka.core.network.impl

import com.s.looka.core.network.api.WebSocketClientConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.pingInterval
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

/**
 * # New Title
 * some text
 * ```
 * val type = MyType()
 * ```
 */
internal class HttpClientProvider(
    private val json: Json,
    private val webSocketConfig: WebSocketClientConfig
) {

    fun provide(): HttpClient {
        return HttpClient(engineFactory = OkHttp) {

            engine {
                this.preconfigured = OkHttpClient.Builder()
                    .pingInterval(webSocketConfig.pingInterval)
                    .build()
            }

            install(ContentNegotiation) {
                json(json)
            }

            install(WebSockets) {
                pingInterval = webSocketConfig.pingInterval
            }
        }
    }
}