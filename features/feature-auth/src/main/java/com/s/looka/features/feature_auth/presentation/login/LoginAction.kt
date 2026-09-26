package com.s.looka.features.feature_auth.presentation.login

sealed interface LoginAction {

    data class OnEmailChange(val email: String): LoginAction
    data class OnPasswordChange(val password: String): LoginAction

    data object OnPasswordVisibilityChange: LoginAction
    data object OnLogin: LoginAction
}