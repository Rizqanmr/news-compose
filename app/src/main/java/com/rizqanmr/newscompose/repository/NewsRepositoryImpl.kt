package com.rizqanmr.newscompose.repository

import com.rizqanmr.newscompose.network.ApiService
import com.squareup.moshi.Moshi
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moshi: Moshi
) : NewsRepository {
}