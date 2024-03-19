package com.rizqanmr.newscompose.di

import com.rizqanmr.newscompose.network.ApiService
import com.rizqanmr.newscompose.network.RetrofitProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val retrofitProvider by lazy {
        RetrofitProvider()
    }

    @Provides
    fun provideApiService(): ApiService = retrofitProvider.getApiService()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}