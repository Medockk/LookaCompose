package com.s.looka.core.common.result

import com.s.looka.core.common.RootError

typealias SimpleResult<T> = Result<T, RootError>

sealed interface Result<out T, out E: RootError> {

    data class Success<out T>(val data: T): Result<T, Nothing>
    data class Failure<out E: RootError>(val error: E): Result<Nothing, E>

    companion object {
        fun <T> success(data: T): Result<T, Nothing> = Success(data)
        fun <E: RootError> failure(error: E): Result<Nothing, E> = Failure(error)
    }
}