package com.rizqanmr.newscompose.ui.main

import androidx.paging.PagingData
import com.rizqanmr.newscompose.data.models.NewsArticle

sealed class NewsUIState {

    data object StartState : NewsUIState()
    data object LoadingState : NewsUIState()
    data class Success(val data: PagingData<NewsArticle>) : NewsUIState()
    data class Error(val message: String) : NewsUIState()
}
