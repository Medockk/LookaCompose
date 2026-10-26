package com.s.looka.features.feature_auth.presentation.signup

import com.s.looka.core.common.viewmodel.CommonUiEvent

sealed interface SignupEvent: CommonUiEvent {
    data object OnSuccess: SignupEvent
}