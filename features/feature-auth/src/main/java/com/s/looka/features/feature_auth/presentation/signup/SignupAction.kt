package com.s.looka.features.feature_auth.presentation.signup

sealed interface SignupAction {

    data class OnFullNameChange(val fullName: String): SignupAction
    data class OnEmailChange(val email: String): SignupAction
    data class OnPasswordChange(val password: String): SignupAction

    data object OnPasswordVisibilityChange: SignupAction
    data object OnSignupClick: SignupAction
    data object OnSignupWithGoogleClick: SignupAction
}