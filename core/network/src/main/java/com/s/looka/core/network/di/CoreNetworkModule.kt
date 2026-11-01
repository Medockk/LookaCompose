package com.s.looka.core.network.di

import com.s.looka.core.network.api.NetworkClientConfig
import com.s.looka.core.network.api.WebSocketClientConfig
import com.s.looka.core.network.impl.HttpClientProvider
import com.s.looka.core.network.impl.JsonProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import java.util.Optional
import javax.inject.Singleton
import kotlin.time.Duration

@Module
@InstallIn(SingletonComponent::class)
internal object CoreNetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return JsonProvider().provide()
    }
    @Provides
    @Singleton
    fun provideHttpClient(
        json: Json,
        @DefaultWebSocketConfig
        webSocketClientConfig: WebSocketClientConfig
    ): HttpClient {
        return HttpClientProvider(json, webSocketClientConfig).provide()
    }

    @Provides
    @Singleton
    @DefaultNetworkConfig
    fun provideNetworkConfig(config: Optional<NetworkClientConfig>): NetworkClientConfig {
        return if (config.isPresent) config.get()
        else object : NetworkClientConfig {
            override val baseUrl: String = NetworkClientConfig.DEFAULT_BASE_URL
        }
    }

    @Provides
    @Singleton
    @DefaultWebSocketConfig
    fun provideWebSocketConfig(config: Optional<WebSocketClientConfig>): WebSocketClientConfig {
        return if (config.isPresent) config.get()
        else object : WebSocketClientConfig {
            override val pingInterval: Duration = WebSocketClientConfig.DEFAULT_PING_INTERVAL
        }
    }
}