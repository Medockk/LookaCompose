package com.s.looka.core.network.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * # New Title
 * some text
 * ```
 * val type = MyType()
 * ```
 */
internal class HttpClientProvider(
    private val json: Json
) {

    fun provide(): HttpClient {
        return HttpClient(engineFactory = OkHttp) {
            install(ContentNegotiation) {
                json(json)
            }
        }
    }
}