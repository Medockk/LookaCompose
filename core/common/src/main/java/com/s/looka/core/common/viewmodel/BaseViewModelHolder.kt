package com.s.looka.core.common.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.jvm.java

abstract class BaseViewModelHolder<S, E : BaseViewModelEvent>: BaseViewModel<E>() {

    protected abstract val _state: MutableStateFlow<S>
    val state
        get() = _state.asStateFlow()
}