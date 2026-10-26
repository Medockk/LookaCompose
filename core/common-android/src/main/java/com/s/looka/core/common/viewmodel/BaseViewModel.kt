package com.s.looka.core.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s.looka.core.common.RootError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseViewModel<S, E>(initialState: S): ViewModel() {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _events: Channel<E> = Channel(Channel.BUFFERED)
    val events: Flow<E> = _events.receiveAsFlow()

    private val _commonUiEvent = Channel<CommonUiEvent>(Channel.BUFFERED)
    val commonUiEvent = _commonUiEvent.receiveAsFlow()

    protected val currentState: S
        get() = _state.value

    protected fun updateState(transform: (S) -> S) {
        _state.update(transform)
    }

    protected fun sendEvent(event: E) {
        viewModelScope.launch { _events.send(event) }
    }

    protected fun showError(error: RootError) {
        viewModelScope.launch { _commonUiEvent.send(CommonUiEvent.OnError(error)) }
    }

    protected fun showToast(message: String) {
        viewModelScope.launch { _commonUiEvent.send(CommonUiEvent.ShowToast(message)) }
    }

    protected fun <T> Flow<T>.collectWithViewModelScope(
        collector: (T) -> Unit
    ) {
        viewModelScope.launch {
            this@collectWithViewModelScope.collect(collector)
        }
    }

    protected fun <T> Flow<Result<T>>.collectSuccess(
        collector: (T) -> Unit
    ) {
        viewModelScope.launch {
            this@collectSuccess.collect { result ->
                result.onSuccess(collector)
            }
        }
    }
}