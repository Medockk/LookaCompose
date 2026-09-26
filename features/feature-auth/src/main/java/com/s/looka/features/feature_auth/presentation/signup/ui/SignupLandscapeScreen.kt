@file:OptIn(ExperimentalLayoutApi::class)

package com.s.looka.features.feature_auth.presentation.signup.ui

import android.util.Log
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.components.button.PrimaryLoadingButton
import com.s.looka.core.ui.components.textfield.DefaultTextFieldKeyboardOptions
import com.s.looka.core.ui.theme.LookaTheme
import com.s.looka.features.feature_auth.R
import com.s.looka.features.feature_auth.presentation.components.AuthHeader
import com.s.looka.features.feature_auth.presentation.components.AuthTextField
import com.s.looka.features.feature_auth.presentation.signup.SignupAction
import com.s.looka.features.feature_auth.presentation.signup.SignupState
import kotlin.math.roundToInt

@Composable
internal fun SignupLandscapeScreen(
    state: SignupState,
    onAction: (SignupAction) -> Unit,
    navigator: Navigator
) {
    val focusManager = LocalFocusManager.current
    var isImeVisible by remember { mutableStateOf(false) }
    val view = LocalView.current

    var imeVerticalDragOffset by remember { mutableStateOf(0f) }
    var contentHeight by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
            isImeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            Log.d("KeyboardListener", "isImeVisible: $isImeVisible")
            insets
        }
    }

    Scaffold(
        containerColor = LookaTheme.colors.primary0,
        contentWindowInsets = WindowInsets.displayCutout
            .add(WindowInsets.statusBars)
            .add(WindowInsets.ime),
        modifier = Modifier
            .onSizeChanged { viewSize ->
                contentHeight = viewSize.height
            }
    ) { systemBarPadding ->
        Row(
            modifier = Modifier
                .offset { IntOffset(0, imeVerticalDragOffset.roundToInt()) }
                .padding(systemBarPadding)
                .padding(horizontal = 4.dp)
                .pointerInput(Unit) {
                    detectVerticalDragGestures { change, dragAmount ->
                        Log.d("DragGesture", "Detect drag gesture")
                        if (isImeVisible) {
                            var newImeVerticalDragOffset = imeVerticalDragOffset + dragAmount

                            if (newImeVerticalDragOffset > contentHeight && dragAmount > 0) {
                                return@detectVerticalDragGestures
                            }

                            imeVerticalDragOffset = newImeVerticalDragOffset
                        }
                    }
                }
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                AuthHeader(
                    title = stringResource(R.string.create_account),
                    subTitle = stringResource(R.string.let_s_create_your_account)
                )

                AuthTextField(
                    label = stringResource(R.string.full_name),
                    value = state.fullName,
                    onValueChange = { onAction(SignupAction.OnFullNameChange(it)) },
                    placeholder = stringResource(R.string.enter_your_full_name),
                    modifier = Modifier.fillMaxWidth(),
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
                    keyboardOptions = DefaultTextFieldKeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeType = ImeAction.Next,
                        onImeClick = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )

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
                    )
                )
            }
            Spacer(Modifier.width(24.dp))
            Column(Modifier.weight(1f)) {
                PrimaryLoadingButton({}, true, "SignUp", Modifier.fillMaxWidth())
            }
        }
    }
}