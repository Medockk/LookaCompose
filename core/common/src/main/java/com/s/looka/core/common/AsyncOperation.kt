package com.s.looka.core.common

sealed interface AsyncOperation<out T> {

    data object Loading: AsyncOperation<Nothing>
    data class Progress(val percent: Float): AsyncOperation<Nothing>
    data class Success<T>(val data: T): AsyncOperation<T>
    data class Failure(val error: RootError): AsyncOperation<Nothing>
}