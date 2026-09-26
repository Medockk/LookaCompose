package com.s.looka.features.feature_auth.presentation.login

data class LoginState(
    val isEmailValid: Boolean? = null,
    val isPasswordVisible: Boolean = false,

    val isLoading: Boolean = false,
)
