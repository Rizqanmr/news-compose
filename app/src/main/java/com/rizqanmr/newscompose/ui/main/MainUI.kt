package com.rizqanmr.newscompose.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rizqanmr.newscompose.R
import com.rizqanmr.newscompose.data.models.NewsArticle
import com.rizqanmr.newscompose.viewmodels.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainUI(viewModel: MainViewModel) {
    val uiState by viewModel.newsUiState.collectAsState()
    val lazyPagingItems = viewModel.lazyPagingItems.collectAsLazyPagingItems()

    Scaffold(
        content = {
            BodyContent(
                newsUIState = uiState,
                lazyPagingItems = lazyPagingItems,
                onThemeSwitch = {
                    viewModel.performAction(MainViewModel.Action.SwitchTheme)
                },
                retryFetchingNews = {
                    viewModel.performAction(MainViewModel.Action.FetchNews)
                }
            )
        }
    )
}

@Composable
fun BodyContent(
    newsUIState: NewsUIState,
    lazyPagingItems: LazyPagingItems<NewsArticle>,
    onThemeSwitch: () -> Unit,
    retryFetchingNews:() -> Unit
) {
    val appName = R.string.app_name
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column {
            TopAppBar(appName, onThemeSwitch = {
                onThemeSwitch()
            })
            NewsListContainer(
                newsUIState = newsUIState,
                lazyPagingItems = lazyPagingItems,
                retry = retryFetchingNews
            )
        }
    }
}