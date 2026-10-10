package com.s.looka.data.clothes.category.repository

import com.s.looka.core.common.result.Result
import com.s.looka.domain.clothes.category.model.CategoryModel
import com.s.looka.domain.clothes.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(): CategoryRepository {

    override fun observeAllCategories(): Flow<Result<CategoryModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAllCategories(): Result<List<CategoryModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchCategoryPage(page: Any): Result<List<CategoryModel>> {
        TODO("Not yet implemented")
    }
}