package br.com.meli.domain.usecase.getproducts

import android.content.Context
import br.com.meli.data.model.ProductResponse
import br.com.meli.domain.model.Product
import com.google.gson.Gson

class MockGetProductsUseCase(
    private val context: Context, private val gson: Gson
) : IGetProductsUseCase {

    override suspend fun invoke(query: String): List<Product> {
        val normalizedQuery = query.lowercase()
        val assetFiles = context.assets.list("")?.toList() ?: emptyList()

        val matchingFiles = assetFiles.filter {
            it.startsWith("search-MLA-") && it.contains(normalizedQuery, ignoreCase = true)
        }

        val products = mutableListOf<Product>()

        for (file in matchingFiles) {
            val json = context.assets.open(file).bufferedReader().use { it.readText() }
            val response = gson.fromJson(json, ProductResponse::class.java)

            response.results.forEach { dto ->
                if (dto.title.contains(normalizedQuery, ignoreCase = true)) {
                    products.add(dto.toDomain())
                }
            }
        }

        return products
    }
}