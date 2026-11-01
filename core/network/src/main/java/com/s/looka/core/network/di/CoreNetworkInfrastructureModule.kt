package com.s.looka.core.network.di

import com.s.looka.core.network.api.ConnectivityManagerChecker
import com.s.looka.core.network.api.NetworkClient
import com.s.looka.core.network.api.NetworkClientConfig
import com.s.looka.core.network.api.WebSocketClient
import com.s.looka.core.network.api.WebSocketClientConfig
import com.s.looka.core.network.impl.ConnectivityManagerCheckerImpl
import com.s.looka.core.network.impl.NetworkClientImpl
import com.s.looka.core.network.impl.WebSocketClientImpl
import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CoreNetworkInfrastructureModule {

    @BindsOptionalOf
    abstract fun bindOptionalConfig(): NetworkClientConfig

    @BindsOptionalOf
    abstract fun bindOptionalWebSocketClientConfig(): WebSocketClientConfig

    @Binds
    abstract fun bindNetworkClient(impl: NetworkClientImpl): NetworkClient

    @Binds
    abstract fun bindWebSocketClient(impl: WebSocketClientImpl): WebSocketClient

    @Binds
    abstract fun bindConnectivityManagerChecker(impl: ConnectivityManagerCheckerImpl): ConnectivityManagerChecker
}