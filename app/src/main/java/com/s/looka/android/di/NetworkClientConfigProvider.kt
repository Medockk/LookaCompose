package com.s.looka.android.di

import com.s.looka.core.network.api.NetworkClientConfig
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkClientConfigProvider {

    fun provideNetworkClientConfig(): NetworkClientConfig {
        return object : NetworkClientConfig {
            override val baseUrl: String
                get() = "https://looka.com/"
        }
    }
}