package br.com.meli.presentation.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.meli.domain.model.Product
import br.com.meli.domain.usecase.getproducts.IGetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ResultsViewModel(
    private val useCase: IGetProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun search(query: String) {
        viewModelScope.launch {
            try {
                val result = useCase(query)
                _products.value = result
                _error.value = null
            } catch (e: HttpException) {
                _error.value = when (e.code()) {
                    403 -> "Acesso negado. Você não tem permissão para ver os produtos."
                    else -> "Erro HTTP: ${e.code()}"
                }
            } catch (e: IOException) {
                _error.value = "Erro de conexão. Verifique sua internet."
            } catch (e: Exception) {
                _error.value = "Erro desconhecido: ${e.localizedMessage}"
            }
        }
    }
}