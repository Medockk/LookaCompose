package com.s.looka.core.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s.looka.core.common.result.Result
import com.s.looka.core.common.result.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<E: BaseViewModelEvent>: ViewModel() {

    protected val _baseEvents = Channel<BaseViewModelEvent>(Channel.BUFFERED)
    protected open val _events: Channel<E> = Channel(Channel.BUFFERED)

    val events: Flow<BaseViewModelEvent>
        get() = merge(_baseEvents.receiveAsFlow(), _events.receiveAsFlow())

    protected fun sendEvent(event: E) {
        _events.trySend(event)
    }

    @JvmName("SendBaseViewModelEvent")
    protected fun sendEvent(event: BaseViewModelEvent) {
        _baseEvents.trySend(event)
    }

    protected fun <T> Flow<T>.collectWithViewModelScope(
        context: CoroutineContext = Dispatchers.IO,
        collector: (T) -> Unit
    ) {
        viewModelScope.launch(context) {
            this@collectWithViewModelScope.collect(collector)
        }
    }

    protected fun <T> Flow<Result<T>>.collectSuccess(
        context: CoroutineContext = Dispatchers.IO,
        collector: (T) -> Unit
    ) {
        viewModelScope.launch(context) {
            this@collectSuccess.collect { result ->
                result.onSuccess(collector)
            }
        }
    }
}