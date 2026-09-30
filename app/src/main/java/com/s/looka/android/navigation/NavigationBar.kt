package com.s.looka.core.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.s.looka.core.ui.R
import com.s.looka.core.ui.util.DeviceConfiguration
import com.s.looka.core.ui.util.LocalDeviceConfiguration

@Composable
fun NavigationBar(
    selectedItem: NavBarItem.Items,
    modifier: Modifier = Modifier
) {
    val deviceConfiguration = LocalDeviceConfiguration.current

    val navBarItems = listOf(
        NavBarItem(
            icon = R.drawable.ic_navigation_home_icon,
            label = "Home",
            isSelected = NavBarItem.Items.Home == selectedItem,
            onClick = {}
        ),
        NavBarItem(
            icon = R.drawable.ic_navigation_search_icon,
            label = "Search",
            isSelected = NavBarItem.Items.Search == selectedItem,
            onClick = {}
        ),
        NavBarItem(
            icon = R.drawable.ic_navigation_favorite_icon,
            label = "Favorite",
            isSelected = NavBarItem.Items.Favorite == selectedItem,
            onClick = {}
        ),
        NavBarItem(
            icon = R.drawable.ic_navigation_cart_icon,
            label = "Cart",
            isSelected = NavBarItem.Items.Cart == selectedItem,
            onClick = {}
        ),
        NavBarItem(
            icon = R.drawable.ic_navigation_profile_icon,
            label = "Account",
            isSelected = NavBarItem.Items.Profile == selectedItem,
            onClick = {}
        ),
    )

    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            PortraitNavigationBar(navBarItems, modifier)
        }
        DeviceConfiguration.MOBILE_LANDSCAPE -> {

        }
    }
}