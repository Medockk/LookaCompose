package com.s.looka.core.network.api

import com.s.looka.core.common.AppError

sealed interface NetworkError: AppError {

    data object RequestTimeout: NetworkError
    data object NoContent: NetworkError
    data object Forbidden: NetworkError
    data object NotAuthorized: NetworkError

    data object UnknowError: NetworkError
}