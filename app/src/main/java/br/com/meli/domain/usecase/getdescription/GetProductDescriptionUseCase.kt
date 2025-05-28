package br.com.meli.domain.usecase.getdescription

import br.com.meli.data.repository.ProductRepository
import br.com.meli.domain.model.ProductDescription

class GetProductDescriptionUseCase(
    private val repository: ProductRepository
) : IGetProductDescriptionUseCase {
    override suspend fun invoke(productId: String): ProductDescription {
        return repository.getProductDescription(productId)
    }
}