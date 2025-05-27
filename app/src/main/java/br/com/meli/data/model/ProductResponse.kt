package br.com.meli.data.model

import br.com.meli.domain.model.Product

data class ProductResponse(
    val results: List<ProductDto>
)

data class ProductDto(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String
) {
    fun toDomain() = Product(id, title, price, thumbnail)
}