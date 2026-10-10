package com.s.looka.core.network.impl

import android.util.Log
import com.s.looka.core.common.AppError
import com.s.looka.core.common.result.Result
import com.s.looka.core.network.api.ConnectivityManagerChecker
import com.s.looka.core.network.api.NetworkClient
import com.s.looka.core.network.api.NetworkClientConfig
import com.s.looka.core.network.api.NetworkError
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

internal class NetworkClientImpl @Inject constructor(
    private val httpClient: HttpClient,
    @DefaultNetworkConfig
    private val config: NetworkClientConfig,
    private val connectivityManagerChecker: ConnectivityManagerChecker
) : NetworkClient {

    override suspend fun <T> request(
        url: String,
        clazz: KClass<*>,
        methodType: NetworkClient.MethodType,
        block: (NetworkClient.Builder.() -> Unit)?,
    ): Result<T> {

        val isConnectionEnable = connectivityManagerChecker.isConnectionEnable()
        if (!isConnectionEnable) return Result.failure(AppError.Http.NoInternet)

        val url = resolveUrl(config.baseUrl, endpoint = url)
        Log.i("Network", "NetworkClientImpl: Making request with url: $url")

        return saveNetworkCall(TypeInfo(clazz)) {
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
            baseUrl.startsWith("http", ignoreCase = true) ||
            endpoint.startsWith("https", ignoreCase = true)
        ) { endpoint }
        else {
            val safeBaseUrl = baseUrl.removeSuffix("/")
            val safeEndpoint = endpoint.removePrefix("/")
            "$safeBaseUrl/$safeEndpoint"
        }
    }
}