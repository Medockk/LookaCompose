package com.s.looka.features.feature_auth.presentation.signup

import com.s.looka.core.common.viewmodel.BaseViewModelEvent

sealed interface SignupEvent: BaseViewModelEvent {
    data object OnSuccess: SignupEvent
}