package br.com.meli.data.model

import br.com.meli.domain.model.ProductDescription
import com.google.gson.annotations.SerializedName

data class ProductDescriptionResponse(
    @SerializedName("plain_text") val plainText: String
) {
    fun toDomain(): ProductDescription {
        return ProductDescription(plainText)
    }
}