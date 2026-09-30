package com.s.looka.features.feature_homepage.presentation

import com.s.looka.domain.clothes.category.model.CategoryModel

data class HomeState(
    val selectedCategoryIds: Set<String> = emptySet(),
    val categories: List<CategoryModel> = emptyList()
)
