package com.s.looka.core.common.result

import com.s.looka.core.common.AppError

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(this.data)
    }
    
    return this
}

inline fun <T, reified E> Result<T>.onFailure(action: (AppError, E?) -> Unit): Result<T> {
    if (this is Result.Failure<*>) {
        action(this.error, this.code as? E)
    }

    return this
}

inline fun <T> Result<T>.onFailure(action: (AppError) -> Unit): Result<T> {
    if (this is Result.Failure<*>) {
        action(this.error)
    }

    return this
}

inline fun <T> Result<T>.onState(action: (DataState) -> Unit): Result<T> {
    if (this is Result.State) {
        action(this.state)
    }

    return this
}
