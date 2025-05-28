package br.com.meli.data.model

import br.com.meli.domain.model.ProductDetail
import com.google.gson.annotations.SerializedName
import ensureHttps

data class ProductDetailResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("pictures") val pictures: List<PictureDto>? = null
) {
    fun toDomain(): ProductDetail {
        return ProductDetail(
            id = id,
            title = title,
            price = price,
            thumbnail = thumbnail,
            description = description,
            pictures = pictures?.map { it.url.ensureHttps() } ?: emptyList()
        )
    }
}

data class PictureDto(
    @SerializedName("url") val url: String
)