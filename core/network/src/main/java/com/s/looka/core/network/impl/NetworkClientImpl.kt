package com.s.looka.core.network.impl

import android.util.Log
import com.s.looka.core.common.DataError
import com.s.looka.core.common.result.Result
import com.s.looka.core.network.api.NetworkClient
import com.s.looka.core.network.api.NetworkClientConfig
import com.s.looka.core.network.di.DefaultNetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.TypeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class NetworkClientImpl @Inject constructor(
    private val httpClient: HttpClient,
    @DefaultNetworkConfig
    private val config: NetworkClientConfig
) : NetworkClient {

    override suspend fun <T> request(
        url: String,
        kType: KType,
        methodType: NetworkClient.MethodType,
        block: (NetworkClient.Builder.() -> Unit)?,
    ): Result<T, DataError.Network> {

        val kClass = kType.classifier as? KClass<*> ?: error("Unsupported type of $kType")
        val typeInfo = TypeInfo(type = kClass,
            kotlinType = kType
        )

        val url = resolveUrl(config.baseUrl, endpoint = url)
        Log.i("Network", "NetworkClientImpl: Making request with url: $url")

        return saveNetworkCall(typeInfo) {
            // switching at IO dispatchers for all network requests
            withContext(Dispatchers.IO) {
                httpClient.request(urlString = url) {
                    method = when(methodType) {
                        NetworkClient.MethodType.GET -> HttpMethod.Get
                        NetworkClient.MethodType.POST -> HttpMethod.Post
                        NetworkClient.MethodType.PUT -> HttpMethod.Put
                        NetworkClient.MethodType.DELETE -> HttpMethod.Delete
                    }

                    block?.let { block ->
                        val builder = NetworkClient.Builder().apply(block)

                        builder.body?.let { setBody(it) }
                        builder.headers.forEach { (key, value) -> header(key, value) }
                        builder.parameters.forEach { (key, value) -> parameter(key, value) }

                    }
                }
            }
        }
    }

    private fun resolveUrl(baseUrl: String, endpoint: String): String {
        return if (
            endpoint.startsWith("http", ignoreCase = true) ||
            endpoint.startsWith("https", ignoreCase = true)
        ) { endpoint }
        else {
            val safeBaseUrl = baseUrl.removeSuffix("/")
            val safeEndpoint = endpoint.removePrefix("/")
            "$safeBaseUrl/$safeEndpoint"
        }
    }
}