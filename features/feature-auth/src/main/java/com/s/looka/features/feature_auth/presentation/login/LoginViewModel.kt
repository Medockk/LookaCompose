package com.s.looka.features.feature_auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.s.looka.core.common.utils.EmailValidator
import com.s.looka.core.common.viewmodel.BaseViewModelHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class LoginViewModel @Inject constructor(): BaseViewModelHolder<LoginState, LoginEvent>() {

    override val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val email = _email.asStateFlow()
    val password = _password.asStateFlow()

    val isLoginEnabled = combine(_email, _password) { e, p ->
        EmailValidator.isValidate(e) && p.isNotBlank()
    }

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEmailChange -> {
                _email.update { action.email }

                val currentEmail = _email.value
                _state.update { it.copy(
                    isEmailValid = EmailValidator.isValidate(currentEmail)
                ) }
            }
            is LoginAction.OnPasswordChange -> {
                _password.update { action.password }
            }
            LoginAction.OnPasswordVisibilityChange -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            LoginAction.OnLogin -> {
                _state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    delay(5.seconds)

                    sendEvent(LoginEvent.OnSuccessLogged)

                    delay(2.seconds)
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}