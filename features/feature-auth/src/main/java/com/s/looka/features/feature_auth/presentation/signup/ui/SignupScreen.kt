package com.s.looka.features.feature_auth.presentation.signup.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.s.looka.core.common.viewmodel.BaseViewModelEvent
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.util.DeviceConfiguration
import com.s.looka.core.ui.util.LocalDeviceConfiguration
import com.s.looka.core.ui.util.ObserveEvent
import com.s.looka.core.ui.util.asString
import com.s.looka.features.feature_auth.presentation.signup.SignupError
import com.s.looka.features.feature_auth.presentation.signup.SignupEvent
import com.s.looka.features.feature_auth.presentation.signup.SignupViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun SignupScreenRoot(
    navigator: Navigator,
    onSuccessAuthentication: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val deviceConfiguration = LocalDeviceConfiguration.current

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var isInvalidEmail by retain { mutableStateOf(false) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        containerColor = Color.Unspecified
    ) {
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                SignupPortraitScreen(
                    state = state,
                    onAction = viewModel::onAction,
                    navigator = navigator,
                    isInvalidEmailError = isInvalidEmail
                )
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                SignupLandscapeScreen(
                    state = state,
                    onAction = viewModel::onAction,
                    navigator = navigator
                )
            }
        }
    }



    ObserveEvent(viewModel.events) { event ->
        when (event) {
            is SignupEvent -> {
                when (event) {
                    SignupEvent.OnSuccess -> onSuccessAuthentication()
                }
            }

            is BaseViewModelEvent.OnError -> {
                if (event.error is SignupError.InvalidEmail) {
                    isInvalidEmail = true
                    return@ObserveEvent
                }

                snackbarHostState.showSnackbar(event.error.asString(context))
                if (isInvalidEmail) {
                    isInvalidEmail = false
                }
            }
        }
    }
}