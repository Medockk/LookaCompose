package com.s.looka.core.network.di

import android.content.Context
import com.s.looka.core.network.api.NetworkClient
import com.s.looka.core.network.api.NetworkClientConfig
import com.s.looka.core.network.impl.ConnectivityManagerCheckerImpl
import com.s.looka.core.network.impl.HttpClientProvider
import com.s.looka.core.network.impl.JsonProvider
import com.s.looka.core.network.impl.NetworkClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import java.util.Optional
import javax.inject.Singleton

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
    fun provideHttpClient(json: Json): HttpClient {
        return HttpClientProvider(json).provide()
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
    fun provideConnectivityManagerChecker(@ApplicationContext context: Context) =
        ConnectivityManagerCheckerImpl(context)
}