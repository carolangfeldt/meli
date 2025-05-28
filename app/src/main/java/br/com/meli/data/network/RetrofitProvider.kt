package br.com.meli.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(authenticated: Boolean, client: OkHttpClient): Retrofit {
    val builder = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/")
        .addConverterFactory(GsonConverterFactory.create())

    if (authenticated) {
        builder.client(client)
    }

    return builder.build()
}