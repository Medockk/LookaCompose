package com.s.looka.core.common.viewmodel

import com.s.looka.core.common.RootError

interface CommonUiEvent {

    data class ShowToast(val message: String): CommonUiEvent
    data class OnError(val error: RootError): CommonUiEvent
}
