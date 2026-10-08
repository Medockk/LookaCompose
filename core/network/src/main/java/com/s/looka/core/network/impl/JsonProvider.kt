package com.s.looka.core.network.impl

import kotlinx.serialization.json.Json

internal class JsonProvider {

    fun provide(): Json {
        return Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }
}