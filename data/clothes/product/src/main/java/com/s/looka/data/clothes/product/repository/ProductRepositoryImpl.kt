package com.s.looka.data.clothes.product.repository

import com.s.looka.core.common.result.Result
import com.s.looka.core.common.result.SimpleResult
import com.s.looka.core.network.api.NetworkClient
import com.s.looka.core.network.api.get
import com.s.looka.data.clothes.product.dto.ProductDto
import com.s.looka.domain.clothes.product.model.ProductModel
import com.s.looka.domain.clothes.product.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

internal class ProductRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
): ProductRepository {

    override suspend fun fetchProducts(): SimpleResult<List<ProductModel>> {
        return withContext(Dispatchers.IO) {
            Result.success(
                data = getDumbProducts()
            )
        }
    }

    private fun getDumbProducts(): List<ProductDto> {
        return (1..99).map {
            ProductDto(
                id = it.toString(),
                name = "Product #$it",
                description = if (it % 3 == 0) null else "dsdsd",
                price = Random.nextInt(1, 1200) * Random.nextDouble(it.toDouble()) ,
                discount = if (it % 5 == 0) Random.nextDouble(5.0, 90.0).toFloat() else null,
                photoUrl = "https://www.mydesignation.com/cdn/shop/files/unstoppable-reflective-print-oversized-t-shirt-mydesignation-4800251.jpg?v=1778972951",
                sizes = emptyList(),
                rating = if (it % 6 == 0) Random.nextDouble(0.0, 5.0).toFloat() else null,
                reviews = it
            )
        }
    }
}