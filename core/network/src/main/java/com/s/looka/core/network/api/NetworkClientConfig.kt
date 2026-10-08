package com.s.looka.core.network.api

interface NetworkClientConfig {

    val baseUrl: String

    companion object {
        internal const val DEFAULT_BASE_URL = "http://"
    }
}