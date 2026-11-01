package com.s.looka.core.network.api

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

interface NetworkClientConfig {

    val baseUrl: String

    companion object {
        internal const val DEFAULT_BASE_URL = "http://"
    }
}

interface WebSocketClientConfig {

    val pingInterval: Duration

    companion object Defaults {
        internal val DEFAULT_PING_INTERVAL = 15.seconds
    }
}