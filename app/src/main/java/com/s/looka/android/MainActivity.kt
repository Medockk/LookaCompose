package com.s.looka.android

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.theme.ApplicationLookaTheme
import com.s.looka.core.ui.util.KeepScreenOn
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationLookaTheme(isSystemInDarkTheme = false) {
                MainApplicationUI(navigator)
            }

            KeepScreenOn()
        }
    }
}
