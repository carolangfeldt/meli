package br.com.meli.domain.usecase.getcategory

import android.content.Context
import br.com.meli.data.model.ProductCategoryResponse
import br.com.meli.domain.model.ProductCategory
import com.google.gson.Gson

class MockGetProductCategoryUseCase(
    private val context: Context,
    private val gson: Gson
) : IGetProductCategoryUseCase {

    override suspend fun invoke(categoryId: String): ProductCategory {
        return try {
            val fileName = "category-$categoryId.json"
            val json = context.assets.open(fileName).bufferedReader().use { it.readText() }
            gson.fromJson(json, ProductCategoryResponse::class.java).toDomain()
        } catch (e: Exception) {
            ProductCategory(id = categoryId, name = "Categoria indisponível")
        }
    }
}