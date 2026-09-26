package com.s.looka.features.feature_auth.presentation.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.components.button.PrimaryLoadingButton
import com.s.looka.core.ui.components.textfield.DefaultTextFieldIcon
import com.s.looka.core.ui.components.textfield.DefaultTextFieldKeyboardOptions
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.features.feature_auth.FeatureAuthNavigation
import com.s.looka.features.feature_auth.R
import com.s.looka.features.feature_auth.presentation.components.AuthHeader
import com.s.looka.features.feature_auth.presentation.components.AuthTextField
import com.s.looka.features.feature_auth.presentation.components.InvalidEmailText
import com.s.looka.features.feature_auth.presentation.components.LinkText
import com.s.looka.features.feature_auth.presentation.components.LinkedText
import com.s.looka.features.feature_auth.presentation.login.LoginAction
import com.s.looka.features.feature_auth.presentation.login.LoginState
import com.s.looka.features.feature_auth.presentation.utils.authNavigationOptions
import com.s.looka.features.feature_auth.presentation.utils.isErrorEmailField

@Composable
internal fun LoginPortraitScreen(
    state: LoginState,
    email: String,
    password: String,
    navigator: Navigator,
    isInvalidEmail: Boolean,
    isLoginEnabled: Boolean,
    onAction: (LoginAction) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val verticalScrollState = rememberScrollState()

    var isEmailFieldInFocus by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(LookaTheme.colors.primary0)
            .padding(horizontal = 24.dp),
        contentWindowInsets = WindowInsets.systemBars,
        containerColor = LookaTheme.colors.primary0
    ) { systemBarPaddingValues ->
        Column(
            modifier = Modifier
                .padding(systemBarPaddingValues)
                .imePadding()
                .verticalScroll(verticalScrollState)
        ) {
            Spacer(Modifier.height(LookaTheme.statusBarPadding))
            AuthHeader(
                title = "Login to your account",
                subTitle = "It’s great to see you again."
            )

            Spacer(Modifier.height(24.dp))
            AuthTextField(
                label = stringResource(R.string.email),
                value = email,
                onValueChange = { onAction(LoginAction.OnEmailChange(it)) },
                placeholder = stringResource(R.string.enter_your_email_address),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = DefaultTextFieldKeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeType = ImeAction.Next,
                    onImeClick = { focusManager.moveFocus(FocusDirection.Next) },
                ),
                onFocus = { isFocused ->
                    isEmailFieldInFocus = isFocused
                },
                isError = isErrorEmailField(email, state.isEmailValid, isEmailFieldInFocus)
            )
            if (isInvalidEmail) {
                InvalidEmailText()
            }

            Spacer(Modifier.height(16.dp))

            AuthTextField(
                label = stringResource(R.string.password),
                value = password,
                onValueChange = { onAction(LoginAction.OnPasswordChange(it)) },
                placeholder = stringResource(R.string.enter_your_password),
                keyboardOptions = DefaultTextFieldKeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeType = ImeAction.Done,
                    onImeClick = {
                        if (isLoginEnabled) {
                            onAction(LoginAction.OnLogin)
                            focusManager.clearFocus()
                        }
                    }
                ),
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                icon = DefaultTextFieldIcon(
                    trailingIcon = {
                        IconButton(
                            onClick = { onAction(LoginAction.OnPasswordVisibilityChange) }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = if (state.isPasswordVisible) com.s.looka.core.ui.R.drawable.ic_eye_visible
                                    else com.s.looka.core.ui.R.drawable.ic_eye_unvisible
                                ),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                )
            )

            Spacer(Modifier.height(10.dp))
            LinkText(
                unlinkedText = "Forgot your password? ",
                linkedText = LinkedText(
                    label = "Reset your password",
                    onClick = {}
                )
            )

            Spacer(Modifier.height(24.dp))

            PrimaryLoadingButton(
                onClick = { onAction(LoginAction.OnLogin) },
                isLoading = state.isLoading,
                label = stringResource(R.string.login),
                enabled = isLoginEnabled,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))

            LinkText(
                unlinkedText = "Don’t have an account? ",
                linkedText = LinkedText(
                    label = "Join",
                    onClick = {
                        navigator.navigate(
                            route = FeatureAuthNavigation.SignupScreen,
                            options = { authNavigationOptions(FeatureAuthNavigation.LoginScreen) }
                        )
                    }
                )
            )
        }
    }
}