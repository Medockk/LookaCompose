package com.s.looka.domain.clothes.product.model

interface ProductModel {
    val id: String
    val name: String
    val description: String?

    val price: Double
    val discount: Float?

    val photoUrl: String?

    val sizes: List<String>

    val rating: Float?
    val reviews: Int?
}
