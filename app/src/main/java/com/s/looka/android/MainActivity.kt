package com.s.looka.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import com.s.looka.core.ui.components.textfield.DefaultTextField
import com.s.looka.core.ui.components.textfield.DefaultTextFieldIcon
import com.s.looka.core.ui.components.textfield.DefaultTextFieldKeyboardOptions
import com.s.looka.core.ui.components.textfield.DefaultTextFieldState
import com.s.looka.core.ui.theme.ApplicationLookaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationLookaTheme(isSystemInDarkTheme = false) {

                var textValue by remember { mutableStateOf("") }
                var isError by remember(textValue) { mutableStateOf(textValue.length > 5) }

                DefaultTextField(
                    state = DefaultTextFieldState(
                        value = textValue,
                        onValueChange = { textValue = it },
                        isError = isError,
                        placeholder = "WEZrxtyujikl"
                    ),
                    keyboardOptions = DefaultTextFieldKeyboardOptions(
                        imeType = ImeAction.None
                    ),
                    icon = DefaultTextFieldIcon(),
                    modifier = Modifier.statusBarsPadding()
                )
            }
        }
    }
}
