package com.s.looka.core.network.api

import kotlinx.coroutines.flow.Flow

interface ConnectivityManagerChecker {

    fun isConnectionEnable(): Boolean
    fun observeConnectionState(): Flow<Boolean>
}