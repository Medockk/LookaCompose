package com.s.looka.core.network.api

import com.s.looka.core.common.result.Result
import kotlin.reflect.KClass

interface NetworkClient {
    suspend fun <T> request(
        url: String,
        clazz: KClass<*>,
        methodType: MethodType,
        block: (Builder.() -> Unit)? = null,
    ): Result<T>

    class Builder {

        var body: Any? = null
        val headers: Map<String, String>
            field = mutableMapOf<String, String>()

        val parameters: Map<String, Any>
            field = mutableMapOf<String, Any>()

        fun header(key: String, value: String) {
            headers[key] = value
        }

        fun parameter(key: String, value: String) {
            parameters[key] = value
        }
    }

    enum class MethodType {
        GET, POST, PUT, DELETE,
    }
}