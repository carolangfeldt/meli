package br.com.meli.domain.model

data class ProductDetail(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String?,
    val description: String?,
    val pictures: List<String> = emptyList()
)