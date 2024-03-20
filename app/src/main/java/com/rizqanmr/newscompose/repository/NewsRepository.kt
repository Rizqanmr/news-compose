package com.rizqanmr.newscompose.repository

import com.rizqanmr.newscompose.data.models.NewsResponse
import com.rizqanmr.newscompose.network.Result

interface NewsRepository {

    suspend fun getTopHeadlines(page: Int) : Result<NewsResponse>
}