package br.com.meli.data.repository

import br.com.meli.data.api.MeliApi
import br.com.meli.domain.model.Product

class ProductRepository(private val api: MeliApi) {
    suspend fun getProducts(query: String): List<Product> {
        return api.searchProducts(query).results.map { it.toDomain() }
    }
}