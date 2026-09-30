package com.s.looka.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.s.looka.core.ui.R
import com.s.looka.core.ui.util.DeviceConfiguration
import com.s.looka.core.ui.util.LocalDeviceConfiguration

@Composable
fun NavigationBar(
    onNavigate: (NavBarItem.Type?) -> Unit,
    selectedItem: NavBarItem.Type,
    modifier: Modifier = Modifier
) {
    val deviceConfiguration = LocalDeviceConfiguration.current

    val navBarItems = listOf(
        NavBarItem(
            icon = R.drawable.ic_navigation_home_icon,
            label = "Home",
            isSelected = NavBarItem.Type.Home == selectedItem,
            type = NavBarItem.Type.Home
        ),
        NavBarItem(
            icon = R.drawable.ic_navigation_search_icon,
            label = "Search",
            isSelected = NavBarItem.Type.Search == selectedItem,
            type = NavBarItem.Type.Search
        ),
        NavBarItem(
            icon = R.drawable.ic_navigation_favorite_icon,
            label = "Favorite",
            isSelected = NavBarItem.Type.Favorite == selectedItem,
            type = NavBarItem.Type.Favorite
        ),
        NavBarItem(
            icon = R.drawable.ic_navigation_cart_icon,
            label = "Cart",
            isSelected = NavBarItem.Type.Cart == selectedItem,
            type = NavBarItem.Type.Cart
        ),
        NavBarItem(
            icon = R.drawable.ic_navigation_account_icon,
            label = "Account",
            isSelected = NavBarItem.Type.Account == selectedItem,
            type = NavBarItem.Type.Account
        ),
    )

    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            PortraitNavigationBar(onNavigate, navBarItems, modifier)
        }
        DeviceConfiguration.MOBILE_LANDSCAPE -> {

        }
    }
}