package com.rizqanmr.newscompose.network

import com.rizqanmr.newscompose.data.Constant
import com.rizqanmr.newscompose.data.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("page") page: Int,
        @Query("country") country: String = Constant.COUNTRY
    ) : Response<NewsResponse>
}