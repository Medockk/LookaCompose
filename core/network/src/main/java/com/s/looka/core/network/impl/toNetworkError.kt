package com.s.looka.core.network.impl

import com.s.looka.core.common.DataError

// TODO расширить список
fun Throwable.toDataError(): DataError.Network = when (this) {
    is java.net.UnknownHostException -> DataError.Network.NO_INTERNET
    is java.net.SocketTimeoutException -> DataError.Network.REQUEST_TIMEOUT
    is kotlinx.serialization.SerializationException -> DataError.Network.SERIALIZATION
    else -> DataError.Network.UNKNOWN
}