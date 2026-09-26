@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.s.looka.features.feature_auth.presentation.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
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
import com.s.looka.features.feature_auth.presentation.components.PolicyBottomSheet
import com.s.looka.features.feature_auth.presentation.signup.SignupAction
import com.s.looka.features.feature_auth.presentation.signup.SignupState
import com.s.looka.features.feature_auth.presentation.utils.authNavigationOptions
import com.s.looka.features.feature_auth.presentation.utils.isErrorEmailField

@Composable
internal fun SignupPortraitScreen(
    state: SignupState,
    onAction: (SignupAction) -> Unit,
    navigator: Navigator,
    isInvalidEmailError: Boolean,
) {

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    var bottomSheetPolicy by remember { mutableStateOf<String?>(null) }
    var isEmailFieldFocused by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = LookaTheme.colors.primary0,
        contentWindowInsets = WindowInsets.statusBars,
        modifier = Modifier
            .fillMaxSize()
            .background(LookaTheme.colors.primary0)
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
            .padding(horizontal = 24.dp)
    ) { statusBarPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(statusBarPadding)
                .imePadding()
                .verticalScroll(scrollState)
                .imeNestedScroll()
        ) {
            Spacer(Modifier.height(LookaTheme.statusBarPadding))
            AuthHeader(
                title = stringResource(R.string.create_account),
                subTitle = stringResource(R.string.let_s_create_your_account)
            )

            Spacer(Modifier.height(24.dp))
            AuthTextField(
                label = stringResource(R.string.full_name),
                value = state.fullName,
                onValueChange = { onAction(SignupAction.OnFullNameChange(it)) },
                placeholder = stringResource(R.string.enter_your_full_name),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = DefaultTextFieldKeyboardOptions(
                    imeType = ImeAction.Next,
                    onImeClick = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
            )
            Spacer(Modifier.height(16.dp))
            AuthTextField(
                label = stringResource(R.string.email),
                value = state.email,
                onValueChange = { onAction(SignupAction.OnEmailChange(it)) },
                placeholder = stringResource(R.string.enter_your_email_address),
                modifier = Modifier
                    .fillMaxWidth(),
                onFocus = { isFocused ->
                    isEmailFieldFocused = isFocused
                },
                isError = isErrorEmailField(state.email, state.isEmailValid, isEmailFieldFocused),
                keyboardOptions = DefaultTextFieldKeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeType = ImeAction.Next,
                    onImeClick = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            if (isInvalidEmailError && state.isEmailValid == false) {
                InvalidEmailText()
            }

            Spacer(Modifier.height(16.dp))
            AuthTextField(
                label = stringResource(R.string.password),
                value = state.password,
                onValueChange = { onAction(SignupAction.OnPasswordChange(it)) },
                placeholder = stringResource(R.string.enter_your_password),
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = DefaultTextFieldKeyboardOptions(
                    imeType = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                    onImeClick = {
                        focusManager.clearFocus()
                        onAction(SignupAction.OnSignupClick)
                    }
                ),
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                icon = DefaultTextFieldIcon(
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onAction(SignupAction.OnPasswordVisibilityChange)
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = if (state.isPasswordVisible) com.s.looka.core.ui.R.drawable.ic_eye_visible
                                    else com.s.looka.core.ui.R.drawable.ic_eye_unvisible,
                                ),
                                contentDescription = null
                            )
                        }
                    }
                )
            )

            AgreeInfo(
                agreementText = listOf(
                    LinkedText(
                        label = "Terms",
                        onClick = { bottomSheetPolicy = "Terms" }
                    ),
                    LinkedText(
                        label = "Privacy Policy",
                        onClick = { bottomSheetPolicy = "Privacy Policy" }
                    ),
                    LinkedText(
                        label = "Cookie Use",
                        onClick = { bottomSheetPolicy = "Cookie Use" }
                    ),
                )
            )

            Spacer(Modifier.height(24.dp))

            PrimaryLoadingButton(
                onClick = {
                    focusManager.clearFocus()
                    onAction(SignupAction.OnSignupClick)
                },
                isLoading = state.isLoading,
                label = stringResource(R.string.create_an_account),
                modifier = Modifier.fillMaxWidth(),
                enabled = (state.isEmailValid == true) &&
                        state.fullName.isNotBlank()
                        && state.password.isNotBlank()
            )

            Spacer(Modifier.height(10.dp))
            Spacer(Modifier.weight(1f))

            LinkText(
                unlinkedText = "Don't have an account?",
                linkedText = LinkedText(
                    label = "Join",
                    onClick = {
                        navigator.navigate(
                            route = FeatureAuthNavigation.LoginScreen,
                            options = {
                                authNavigationOptions(FeatureAuthNavigation.SignupScreen)
                            }
                        )
                    }
                ),
                modifier = Modifier
                    .padding(WindowInsets.navigationBars.asPaddingValues())
                    .align(Alignment.CenterHorizontally)
            )
        }
    }

    PolicyBottomSheet(
        isVisible = bottomSheetPolicy != null,
        label = bottomSheetPolicy ?: "",
        onDismissRequest = { bottomSheetPolicy = null }
    )
}