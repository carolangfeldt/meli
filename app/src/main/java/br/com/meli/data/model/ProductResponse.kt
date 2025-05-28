package br.com.meli.data.model

import br.com.meli.domain.model.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val results: List<ProductDto>
)

data class ProductDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("category_id") val categoryId: String
) {
    fun toDomain() = Product(id, title, price, thumbnail, categoryId)
}