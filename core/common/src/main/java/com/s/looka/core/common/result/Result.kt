package com.s.looka.core.common.result

import com.s.looka.core.common.AppError

sealed interface Result<out T> {

    data class Success<T>(val data: T): Result<T>

    data class Failure<E>(val error: AppError, val code: E?): Result<Nothing> {
        constructor(error: AppError): this(error, code = null)
    }

    data class State(val state: DataState): Result<Nothing>

    companion object {
        fun <T> success(data: T) = Success(data)

        fun <E> failure(error: AppError, code: E) = Failure(error, code)
        fun failure(error: AppError) = Failure<Nothing>(error)
    }
}