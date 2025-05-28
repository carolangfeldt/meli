package br.com.meli.domain.usecase.getdescription

import br.com.meli.domain.model.ProductDescription

interface IGetProductDescriptionUseCase {
    suspend operator fun invoke(productId: String): ProductDescription
}