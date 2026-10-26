package com.s.looka.features.feature_auth.presentation.signup

import androidx.lifecycle.viewModelScope
import com.s.looka.core.common.RootError
import com.s.looka.core.common.utils.EmailValidator
import com.s.looka.core.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


@HiltViewModel
internal class SignupViewModel @Inject constructor() : BaseViewModel<SignupState, SignupEvent>(
    SignupState()
) {

    fun onAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnEmailChange -> {
                updateState {
                    it.copy(
                        email = action.email,
                        isEmailValid = EmailValidator.isValid(action.email)
                    )
                }
            }
            is SignupAction.OnFullNameChange -> {
                updateState {
                    it.copy(fullName = action.fullName)
                }
            }
            is SignupAction.OnPasswordChange -> {
                updateState {
                    it.copy(password = action.password)
                }
            }
            SignupAction.OnPasswordVisibilityChange -> {
                updateState { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }

            SignupAction.OnSignupClick -> {

                checkFields { error ->
                    showError(error)
                    return
                }

                updateState { it.copy(isLoading = true) }
                viewModelScope.launch {
                    delay(5.seconds)
                    updateState { it.copy(isLoading = false) }
                    sendEvent(SignupEvent.OnSuccess)
                }
            }
            SignupAction.OnSignupWithGoogleClick -> TODO()
        }
    }

    private inline fun checkFields(onError: (error: RootError) -> Unit) {
        if (currentState.fullName.isBlank()) {
            onError(SignupError.EmptyFullName)
            return
        }
        if (currentState.email.isBlank()) {
            onError(SignupError.EmptyEmail)
            return
        }
        if (currentState.password.isBlank()) {
            onError(SignupError.EmptyPassword)
            return
        }
        if (!EmailValidator.isValid(currentState.email)) {
            onError(SignupError.InvalidEmail)
            return
        }
    }
}