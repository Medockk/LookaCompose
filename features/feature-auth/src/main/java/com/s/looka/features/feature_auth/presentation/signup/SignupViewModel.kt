package com.s.looka.features.feature_auth.presentation.signup

import androidx.lifecycle.viewModelScope
import com.s.looka.core.common.AppError
import com.s.looka.core.common.utils.EmailValidator
import com.s.looka.core.common.viewmodel.BaseViewModelEvent
import com.s.looka.core.common.viewmodel.BaseViewModelHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


@HiltViewModel
internal class SignupViewModel @Inject constructor() : BaseViewModelHolder<SignupState, SignupEvent>() {

    override val _state = MutableStateFlow(SignupState())

    override val _events = Channel<SignupEvent>(Channel.BUFFERED)

    fun onAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = action.email,
                        isEmailValid = EmailValidator.isValidate(action.email)
                    )
                }
            }
            is SignupAction.OnFullNameChange -> {
                _state.update {
                    it.copy(fullName = action.fullName)
                }
            }
            is SignupAction.OnPasswordChange -> {
                _state.update {
                    it.copy(password = action.password)
                }
            }
            SignupAction.OnPasswordVisibilityChange -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }

            SignupAction.OnSignupClick -> {

                checkFields { error ->
                    sendEvent(BaseViewModelEvent.OnError(error))
                    return
                }

                _state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    delay(5.seconds)
                    _state.update { it.copy(isLoading = false) }
                    sendEvent(SignupEvent.OnSuccess)
                }
            }
            SignupAction.OnSignupWithGoogleClick -> TODO()
        }
    }

    private inline fun checkFields(onError: (error: AppError) -> Unit) {
        val currentState = _state.value
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
        if (!EmailValidator.isValidate(currentState.email)) {
            onError(SignupError.InvalidEmail)
            return
        }
    }
}