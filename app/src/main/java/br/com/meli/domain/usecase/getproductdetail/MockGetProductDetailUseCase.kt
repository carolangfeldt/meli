package br.com.meli.domain.usecase.getproductdetail

import android.content.Context
import br.com.meli.data.model.ProductDetailResponse
import br.com.meli.domain.model.ProductDetail
import com.google.gson.Gson
import java.lang.Exception

class MockGetProductDetailUseCase(
    private val context: Context,
    private val gson: Gson
) : IGetProductDetailUseCase {

    override suspend fun invoke(productId: String): ProductDetail {
        return try {
            val fileName = "item-$productId.json"
            val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
            gson.fromJson(json, ProductDetailResponse::class.java).toDomain()
        } catch (e: Exception) {
            ProductDetail(
                id = productId,
                title = "Produto não encontrado",
                price = 0.0,
                thumbnail = "",
                description = ""
            )
        }
    }
}