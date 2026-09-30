package com.s.looka.features.feature_homepage.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.s.looka.core.navigation.Navigator
import com.s.looka.core.ui.util.DeviceConfiguration
import com.s.looka.core.ui.util.LocalDeviceConfiguration
import com.s.looka.core.ui.util.LocalNavigationBarVisibility
import com.s.looka.features.feature_homepage.presentation.HomeViewModel

@Composable
fun HomeScreenRoot(
    navigator: Navigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val deviceConfiguration = LocalDeviceConfiguration.current
    val state by viewModel.state.collectAsState()

    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            HomePortraitScreen(navigator, state, viewModel::onAction)
        }
        DeviceConfiguration.MOBILE_LANDSCAPE -> {}
    }
}