package com.s.looka.features.feature_auth.presentation.signup

data class SignupState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",

    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,

    val isEmailValid: Boolean? = null,
)
