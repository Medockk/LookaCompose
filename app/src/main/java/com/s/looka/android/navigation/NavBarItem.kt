package com.s.looka.android.navigation

import androidx.annotation.DrawableRes

data class NavBarItem(
    @field:DrawableRes
    val icon: Int,

    val label: String,
    val type: Type,
    val isSelected: Boolean
) {
    sealed interface Type {
        data object Home: Type
        data object Search: Type
        data object Favorite: Type
        data object Cart: Type
        data object Account: Type
    }
}
