package br.com.meli.data.api

import br.com.meli.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MeliApi {
    @GET("sites/MLB/search")
    suspend fun searchProducts(@Query("q") query: String): ProductResponse
}