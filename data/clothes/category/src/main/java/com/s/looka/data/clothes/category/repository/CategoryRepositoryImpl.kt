package com.s.looka.data.clothes.category.repository

import com.s.looka.core.common.result.Result
import com.s.looka.core.common.result.SimpleResult
import com.s.looka.domain.clothes.category.model.CategoryModel
import com.s.looka.domain.clothes.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject
import kotlin.uuid.Uuid

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    override fun observeAllCategories(): Flow<SimpleResult<List<CategoryModel>>> {
        return channelFlow {
            val categories = getDumbCategories()
            send(Result.success(categories))
        }
    }

    override suspend fun fetchAllCategories(): SimpleResult<List<CategoryModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchCategoryPage(page: Any): SimpleResult<List<CategoryModel>> {
        TODO("Not yet implemented")
    }

    private fun getDumbCategories(): List<CategoryModel> {
        return (1..15).map {
            CategoryModel(Uuid.random().toString(), "Category #$it")
        }
    }
}