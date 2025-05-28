package br.com.meli.domain.usecase.getcategory

import br.com.meli.domain.model.ProductCategory

interface IGetProductCategoryUseCase {
    suspend operator fun invoke(categoryId: String): ProductCategory
}