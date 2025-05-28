package br.com.meli.data.repository

import br.com.meli.data.api.MeliApi
import br.com.meli.domain.model.Product
import br.com.meli.domain.model.ProductCategory
import br.com.meli.domain.model.ProductDescription
import br.com.meli.domain.model.ProductDetail

class ProductRepository(private val api: MeliApi) {
    suspend fun getProducts(query: String): List<Product> {
        return api.searchProducts(query).results.map { it.toDomain() }
    }

    suspend fun getProductDetail(productId: String): ProductDetail {
        return api.getProductDetail(productId).toDomain()
    }

    suspend fun getProductDescription(productId: String): ProductDescription {
        return api.getProductDescription(productId).toDomain()
    }

    suspend fun getProductCategory(categoryId: String): ProductCategory {
        return api.getProductCategory(categoryId).toDomain()
    }
}