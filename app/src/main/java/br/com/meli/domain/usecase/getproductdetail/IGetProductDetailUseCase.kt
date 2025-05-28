package br.com.meli.domain.usecase.getproductdetail

import br.com.meli.domain.model.ProductDetail

interface IGetProductDetailUseCase {
    suspend operator fun invoke(id: String): ProductDetail
}