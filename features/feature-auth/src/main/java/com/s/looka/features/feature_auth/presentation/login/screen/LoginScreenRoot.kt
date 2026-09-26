package com.s.looka.features.feature_auth.presentation.login.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.s.looka.core.common.viewmodel.BaseViewModelEvent
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.util.DeviceConfiguration
import com.s.looka.core.ui.util.LocalDeviceConfiguration
import com.s.looka.core.ui.util.ObserveEvent
import com.s.looka.features.feature_auth.presentation.login.LoginEvent
import com.s.looka.features.feature_auth.presentation.login.LoginViewModel

@Composable
fun LoginScreenRoot(
    navigator: Navigator,
    onSuccessLogged: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val isLoginEnabled by viewModel.isLoginEnabled.collectAsStateWithLifecycle(false)

    var isInvalidEmail by retain { mutableStateOf(false) }

    val deviceConfiguration = LocalDeviceConfiguration.current

    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            LoginPortraitScreen(
                state = state,
                email = email,
                password = password,
                navigator = navigator,
                isInvalidEmail = isInvalidEmail,
                onAction = viewModel::onAction,
                isLoginEnabled = isLoginEnabled
            )
        }
        DeviceConfiguration.MOBILE_LANDSCAPE -> {}
    }

    ObserveEvent(viewModel.events) { event ->
        when (event) {
            is BaseViewModelEvent.OnError -> {

            }
            is LoginEvent -> {
                when (event) {
                    LoginEvent.OnSuccessLogged -> onSuccessLogged()
                }
            }
        }
    }
}