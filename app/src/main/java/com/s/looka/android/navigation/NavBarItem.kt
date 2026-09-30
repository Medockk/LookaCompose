package com.s.looka.core.ui.components.navigation

import androidx.annotation.DrawableRes

data class NavBarItem(
    @field:DrawableRes
    val icon: Int,

    val label: String,
    val isSelected: Boolean,
    val onClick: () -> Unit,
) {
    sealed interface Items {
        data object Home: Items
        data object Search: Items
        data object Favorite: Items
        data object Cart: Items
        data object Profile: Items
    }
}
