package com.s.looka.data.clothes.product.dto

import com.s.looka.domain.clothes.product.model.ProductModel

data class ProductDto(
    override val id: String,
    override val name: String,
    override val description: String?,
    override val price: Double,
    override val discount: Float?,
    override val photoUrl: String?,
    override val sizes: List<String>,
    override val rating: Float?,
    override val reviews: Int?
): ProductModel
