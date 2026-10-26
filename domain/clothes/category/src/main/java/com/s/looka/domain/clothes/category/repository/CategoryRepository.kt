package com.s.looka.domain.clothes.category.repository

import com.s.looka.core.common.result.Result
import com.s.looka.core.common.result.SimpleResult
import com.s.looka.domain.clothes.category.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun observeAllCategories(): Flow<SimpleResult<List<CategoryModel>>>
    suspend fun fetchAllCategories(): SimpleResult<List<CategoryModel>>

    suspend fun fetchCategoryPage(page: Any): SimpleResult<List<CategoryModel>>
}