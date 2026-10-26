package com.s.looka.features.feature_auth.presentation.login

import com.s.looka.core.common.viewmodel.CommonUiEvent

sealed interface LoginEvent: CommonUiEvent {

    data object OnSuccessLogged: LoginEvent
}