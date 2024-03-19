package com.rizqanmr.newscompose.network

import com.rizqanmr.newscompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvider {
    private var retrofit: Retrofit? = null
    private var apiService: ApiService? = null

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private fun getRetrofit(): Retrofit {
        val retrofit = this.retrofit ?: buildRetrofit()
        if (this.retrofit == null) {
            this.retrofit = retrofit
        }
        return retrofit
    }

    private fun buildApiService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }

    fun getApiService(): ApiService {
        val apiService = this.apiService ?: buildApiService()
        if (this.apiService == null) {
            this.apiService = apiService
        }
        return apiService
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            addInterceptor(createAuthInterceptor())
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    private fun createAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()
            val request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }
    }
}