package br.com.meli.data.api

import br.com.meli.data.model.ProductCategoryResponse
import br.com.meli.data.model.ProductDescriptionResponse
import br.com.meli.data.model.ProductDetailResponse
import br.com.meli.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeliApi {
    @GET("sites/MLB/search")
    suspend fun searchProducts(@Query("q") query: String): ProductResponse

    @GET("items/{id}")
    suspend fun getProductDetail(@Path("id") id: String): ProductDetailResponse

    @GET("items/{id}/description")
    suspend fun getProductDescription(@Path("id") productId: String): ProductDescriptionResponse

    @GET("categories/{id}")
    suspend fun getProductCategory(@Path("id") categoryId: String): ProductCategoryResponse
}