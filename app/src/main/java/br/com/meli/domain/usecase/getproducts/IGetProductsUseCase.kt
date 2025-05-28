package br.com.meli.domain.usecase.getproducts

import br.com.meli.domain.model.Product


interface IGetProductsUseCase {
    suspend operator fun invoke(query: String): List<Product>
}