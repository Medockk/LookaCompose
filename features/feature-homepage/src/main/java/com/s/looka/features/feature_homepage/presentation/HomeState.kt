package com.s.looka.features.feature_homepage.presentation

import com.s.looka.domain.clothes.category.model.CategoryModel
import com.s.looka.domain.clothes.product.model.ProductModel

data class HomeState(
    val selectedCategoryIds: Set<String> = emptySet(),
    val categories: List<CategoryModel> = emptyList(),

    val products: List<ProductModel> = emptyList(),
)
