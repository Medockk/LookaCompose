package com.s.looka.core.common

import com.s.looka.core.common.result.Result
import com.s.looka.core.common.result.SimpleResult
import com.s.looka.core.common.result.onFailure
import com.s.looka.core.common.result.onSuccess
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Main class to request page
 */
open class Pageable<T : Page<T>, K>(
    protected val initialKey: K,
    protected val onLoad: suspend (K) -> SimpleResult<T>,
    protected val onSuccess: suspend (T, K) -> Unit,
    protected val onNextKey: suspend (T, K) -> K,
    protected val onEndReaching: suspend (T, K) -> Boolean,
    protected val onError: suspend (RootError) -> Unit = {},
    protected val onLoadingUpdate: (Boolean) -> Unit = {},
) {

    private var currentKey: K = initialKey
    private var isLoading: Boolean = false
    private var isEndReaching: Boolean = false

    suspend fun onNext() {
        if (isLoading) {
            println("Pageable: Already making request")
            return
        }

        if (isEndReaching) {
            println("Pageable: All reached yet.")
            return
        }

        onLoadUpdated(true)
        println("Pageable: Making request")

        val result: SimpleResult<T>? = try {
            onLoad(currentKey)
        } catch (e: Exception) {
//            Result.failure(e)
            null
        } finally {
            isLoading = false // to not update UI, with возможность сделать новый запрос
        }

        // TODO обработка ошибочных результатов + преобразование Exception -> RootError
        if (result == null) {
            return
        }

        result?.onSuccess {
            println("Pageable: success request")
            onSuccess(it, currentKey)
            currentKey = onNextKey(it, currentKey)
            isEndReaching = onEndReaching(it, currentKey) // put updated key
        }?.onFailure {
            onError(it)
            println("Pageable: failed request. Error is $it")
        }
        onLoadUpdated(false)
    }

    private fun onLoadUpdated(isLoading: Boolean) {
        this.isLoading = isLoading
        onLoadingUpdate(isLoading)
    }

    fun onClear() {
        currentKey = initialKey
        isLoading = false
        isEndReaching = false
    }
}