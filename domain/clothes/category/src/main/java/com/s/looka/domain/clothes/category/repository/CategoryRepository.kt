package com.s.looka.domain.clothes.category.repository

import com.s.looka.core.common.result.Result
import com.s.looka.domain.clothes.category.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun observeAllCategories(): Flow<Result<CategoryModel>>
    suspend fun fetchAllCategories(): Result<List<CategoryModel>>

    suspend fun fetchCategoryPage(page: Any): Result<List<CategoryModel>>
}