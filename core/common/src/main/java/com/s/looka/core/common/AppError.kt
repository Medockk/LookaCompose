package com.s.looka.core.common

interface AppError {
    data class Http(val httpCode: Int): AppError

    interface Local<T>: AppError {
        val type: T
    }
}

inline fun AppError.onHttpError(action: (Int) -> Unit): AppError {
    if (this is AppError.Http) {
        action(this.httpCode)
    }

    return this
}

inline fun <reified T> AppError.onLocalError(action: (T?) -> Unit): AppError {
    if (this is AppError.Local<*>) {
        action(this.type as? T)
    }

    return this
}