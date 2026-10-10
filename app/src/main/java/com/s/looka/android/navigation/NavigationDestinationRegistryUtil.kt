package com.s.looka.android.navigation

import androidx.compose.runtime.Composable
import com.s.looka.core.navigation.NavigationDestinationRegistry
import com.s.looka.core.ui.util.LocalNavigationBarVisibility

@Composable
fun NavigationDestinationRegistry.isNavigationBarVisible(route: String?): Boolean {
    val navigationBarVisibility = LocalNavigationBarVisibility.current
    val navigationMetadata = metadataFor(route)

    return (navigationMetadata?.isNavigationBarVisible ?: false) /*&&
            navigationMetadata.withLocalNavigationVisibilityProvider && navigationBarVisibility.value*/
}