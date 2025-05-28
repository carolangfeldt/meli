package br.com.meli.domain.usecase.getdescription

import android.content.Context
import br.com.meli.data.model.ProductDescriptionResponse
import br.com.meli.domain.model.ProductDescription
import com.google.gson.Gson

class MockGetProductDescriptionUseCase(
    private val context: Context,
    private val gson: Gson
) : IGetProductDescriptionUseCase {

    override suspend fun invoke(productId: String): ProductDescription {
        return try {
            val fileName = "item-$productId-description.json"
            val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
            gson.fromJson(json, ProductDescriptionResponse::class.java).toDomain()
        } catch (e: Exception) {
            ProductDescription("Descrição indisponível")
        }
    }
}