package com.s.looka.features.feature_auth.presentation.login

import com.s.looka.core.common.viewmodel.BaseViewModelEvent

sealed interface LoginEvent: BaseViewModelEvent {

    data object OnSuccessLogged: LoginEvent
}