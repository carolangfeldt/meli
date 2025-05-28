package br.com.meli.domain.usecase.getproductdetail

import br.com.meli.data.repository.ProductRepository
import br.com.meli.domain.model.ProductDetail

class GetProductDetailUseCase(
    private val repository: ProductRepository
) : IGetProductDetailUseCase {
    override suspend fun invoke(productId: String): ProductDetail {
        return repository.getProductDetail(productId)
    }
}