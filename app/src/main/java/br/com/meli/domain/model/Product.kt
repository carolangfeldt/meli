package br.com.meli.domain.model

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val categoryId: String
)