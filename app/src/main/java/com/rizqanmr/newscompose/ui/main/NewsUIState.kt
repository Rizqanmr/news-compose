package com.rizqanmr.newscompose.ui.main

import androidx.paging.PagingData
import com.rizqanmr.newscompose.data.models.NewsArticle
import com.rizqanmr.newscompose.network.Result

data class NewsUIState(
    val isLoading: Boolean = true,
    val data: PagingData<NewsArticle>? = null,
    val error: Result.Error? = null
)
