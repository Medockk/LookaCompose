package com.s.looka.core.network.di

import com.s.looka.core.network.api.ConnectivityManagerChecker
import com.s.looka.core.network.api.NetworkClient
import com.s.looka.core.network.api.NetworkClientConfig
import com.s.looka.core.network.impl.ConnectivityManagerCheckerImpl
import com.s.looka.core.network.impl.NetworkClientImpl
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

    @Binds
    abstract fun bindNetworkClient(impl: NetworkClientImpl): NetworkClient

    @Binds
    abstract fun bindConnectivityManagerChecker(impl: ConnectivityManagerCheckerImpl): ConnectivityManagerChecker
}