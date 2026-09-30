package com.s.looka.features.feature_homepage.presentation

sealed interface HomeAction {

    data class OnCategoryClick(val categoryId: String): HomeAction
}