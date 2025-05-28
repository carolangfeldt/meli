package br.com.meli.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.meli.domain.model.ProductDetail
import br.com.meli.domain.usecase.getcategory.IGetProductCategoryUseCase
import br.com.meli.domain.usecase.getdescription.IGetProductDescriptionUseCase
import br.com.meli.domain.usecase.getproductdetail.IGetProductDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getProductDetailUseCase: IGetProductDetailUseCase,
    private val getProductDescriptionUseCase: IGetProductDescriptionUseCase,
    private val getProductCategoryUseCase: IGetProductCategoryUseCase
) : ViewModel() {

    private val _productDetail = MutableStateFlow<ProductDetail?>(null)
    val productDetail: StateFlow<ProductDetail?> = _productDetail

    private val _description = MutableStateFlow<String?>(null)
    val description: StateFlow<String?> = _description

    private val _category = MutableStateFlow<String?>(null)
    val category: StateFlow<String?> = _category

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadProductDetail(productId: String, categoryId: String) {
        viewModelScope.launch {
            try {
                _productDetail.value = getProductDetailUseCase(productId)
            } catch (e: Exception) {
                _error.value = "Erro ao carregar detalhes do produto."
            }

            try {
                _description.value = getProductDescriptionUseCase(productId).plainText
            } catch (e: Exception) {
                _description.value = "Descrição não disponível."
            }

            try {
                _category.value = getProductCategoryUseCase(categoryId).name
            } catch (e: Exception) {
                _category.value = "Categoria não disponível."
            }
        }
    }
}