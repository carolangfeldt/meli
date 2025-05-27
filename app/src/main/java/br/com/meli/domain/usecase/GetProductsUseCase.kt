package br.com.meli.domain.usecase

import br.com.meli.data.repository.ProductRepository
import br.com.meli.domain.model.Product

class GetProductsUseCase(private val repository: ProductRepository) {
    suspend operator fun invoke(query: String): List<Product> {
        return repository.getProducts(query)
    }
}