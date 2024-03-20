package com.rizqanmr.newscompose.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rizqanmr.newscompose.data.NewsPagingSource
import com.rizqanmr.newscompose.data.models.NewsArticle
import com.rizqanmr.newscompose.repository.NewsRepository
import com.rizqanmr.newscompose.ui.main.NewsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val _isDarkTheme = MutableLiveData<Boolean>().apply { value = false }
    val isDarkTheme: LiveData<Boolean> = _isDarkTheme

    val newsUiState = MutableStateFlow<NewsUIState>(NewsUIState.StartState)

    private val pagingConfig = PagingConfig(pageSize = 30, prefetchDistance = 5)
    private val pager = Pager(config = pagingConfig) {
        NewsPagingSource(newsRepository)
    }

    val lazyPagingItems: Flow<PagingData<NewsArticle>> = getNews()

    init {
        getNews()
    }

    private fun getNews() : Flow<PagingData<NewsArticle>> {
        newsUiState.tryEmit(NewsUIState.LoadingState)
        viewModelScope.launch {
            try {
                pager.flow.cachedIn(viewModelScope).collect { pagingData ->
                    newsUiState.emit(NewsUIState.Success(pagingData))
                }
            } catch (e: Exception) {
                newsUiState.emit(NewsUIState.Error(e.localizedMessage!!))
            }
        }

        return pager.flow.cachedIn(viewModelScope)
    }

    fun performAction(action: Action) {
        when (action) {
            is Action.FetchNews -> {
                getNews()
            }
            Action.SwitchTheme -> {
                _isDarkTheme.value = !_isDarkTheme.value!!
            }
        }
    }

    sealed class Action {
        data object FetchNews : Action()
        object SwitchTheme : Action()
    }
}