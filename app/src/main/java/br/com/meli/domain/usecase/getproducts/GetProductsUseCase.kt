package br.com.meli.domain.usecase.getproducts

import br.com.meli.data.repository.ProductRepository
import br.com.meli.domain.model.Product

class GetProductsUseCase(
    private val repository: ProductRepository
) : IGetProductsUseCase {
    override suspend operator fun invoke(query: String): List<Product> {
        return repository.getProducts(query)
    }
}