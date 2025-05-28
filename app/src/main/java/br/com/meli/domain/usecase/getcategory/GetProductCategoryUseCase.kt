package br.com.meli.domain.usecase.getcategory

import br.com.meli.data.repository.ProductRepository
import br.com.meli.domain.model.ProductCategory

class GetProductCategoryUseCase(
    private val repository: ProductRepository
) : IGetProductCategoryUseCase {
    override suspend fun invoke(categoryId: String): ProductCategory {
        return repository.getProductCategory(categoryId)
    }
}