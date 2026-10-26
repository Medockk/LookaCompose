package com.s.looka.domain.clothes.product.repository

import com.s.looka.core.common.result.SimpleResult
import com.s.looka.domain.clothes.product.model.ProductModel

interface ProductRepository {

    suspend fun fetchProducts(): SimpleResult<List<ProductModel>>
}