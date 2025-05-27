package br.com.meli.presentation.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.meli.data.api.RetrofitInstance
import br.com.meli.data.repository.ProductRepository
import br.com.meli.domain.model.Product
import br.com.meli.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResultsViewModel : ViewModel() {

    private val repository = ProductRepository(RetrofitInstance.api)
    private val useCase = GetProductsUseCase(repository)

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun search(query: String) {
        viewModelScope.launch {
            val result = useCase(query)
            _products.value = result
        }
    }
}