package com.s.looka.core.common.result

import com.s.looka.core.common.RootError

inline fun <T, E: RootError> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    if (this is Result.Success) {
        action(this.data)
    }

    return this
}

inline fun <T, E: RootError> Result<T, E>.onFailure(action: (E) -> Unit): Result<T, E> {
    if (this is Result.Failure<E>) {
        action(this.error)
    }

    return this
}

fun <T, E: RootError> Result<T, E>.requireData(): T = if (this is Result.Success) data
    else error("Failed to extract data")

fun <T, E: RootError> Result<T, E>.getOrNull() = if (this is Result.Success) data
    else null

