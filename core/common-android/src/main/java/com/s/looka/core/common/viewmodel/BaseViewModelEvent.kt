package com.s.looka.core.common.viewmodel

import com.s.looka.core.common.AppError

interface BaseViewModelEvent {
    data class SendMessage(val message: String): BaseViewModelEvent
    data class OnError(val error: AppError): BaseViewModelEvent
}
