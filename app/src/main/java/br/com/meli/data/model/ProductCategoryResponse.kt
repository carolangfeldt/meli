package br.com.meli.data.model

import br.com.meli.domain.model.ProductCategory
import com.google.gson.annotations.SerializedName

data class ProductCategoryResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
) {
    fun toDomain(): ProductCategory {
        return ProductCategory(id, name)
    }
}