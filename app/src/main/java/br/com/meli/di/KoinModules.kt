package br.com.meli.di

import MeliAuthApi
import SessionManager
import br.com.meli.BuildConfig
import br.com.meli.data.api.MeliApi
import br.com.meli.data.network.AuthInterceptor
import br.com.meli.data.repository.ProductRepository
import br.com.meli.domain.usecase.getcategory.GetProductCategoryUseCase
import br.com.meli.domain.usecase.getcategory.IGetProductCategoryUseCase
import br.com.meli.domain.usecase.getcategory.MockGetProductCategoryUseCase
import br.com.meli.domain.usecase.getdescription.GetProductDescriptionUseCase
import br.com.meli.domain.usecase.getdescription.IGetProductDescriptionUseCase
import br.com.meli.domain.usecase.getdescription.MockGetProductDescriptionUseCase
import br.com.meli.domain.usecase.getproductdetail.GetProductDetailUseCase
import br.com.meli.domain.usecase.getproductdetail.IGetProductDetailUseCase
import br.com.meli.domain.usecase.getproductdetail.MockGetProductDetailUseCase
import br.com.meli.domain.usecase.getproducts.GetProductsUseCase
import br.com.meli.domain.usecase.getproducts.IGetProductsUseCase
import br.com.meli.domain.usecase.getproducts.MockGetProductsUseCase
import br.com.meli.presentation.detail.DetailsViewModel
import br.com.meli.presentation.result.ResultsViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { SessionManager(get()) }

    single(named("auth")) {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MeliAuthApi::class.java)
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(get()))
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(MeliApi::class.java)
    }
}

val repositoryModule = module {
    single { ProductRepository(get()) }
}

val useCaseModule = module {
    single<IGetProductsUseCase> {
        if (BuildConfig.IS_MOCK) {
            MockGetProductsUseCase(androidContext(), get())
        } else {
            GetProductsUseCase(get())
        }
    }
    single { Gson() }
    single<IGetProductDetailUseCase> {
        if (BuildConfig.IS_MOCK) {
            MockGetProductDetailUseCase(androidContext(), get())
        } else {
            GetProductDetailUseCase(get())
        }
    }

    single<IGetProductCategoryUseCase> {
        if (BuildConfig.IS_MOCK) {
            MockGetProductCategoryUseCase(androidContext(), get())
        } else {
            GetProductCategoryUseCase(get())
        }
    }
    single<IGetProductDescriptionUseCase> {
        if (BuildConfig.IS_MOCK) {
            MockGetProductDescriptionUseCase(androidContext(), get())
        } else {
            GetProductDescriptionUseCase(get())
        }
    }
}

val viewModelModule = module {
    viewModel { ResultsViewModel(get()) }
    viewModel { DetailsViewModel(get(), get(), get()) }
}

val appModules = listOf(networkModule, repositoryModule, useCaseModule, viewModelModule)