package com.rizqanmr.newscompose.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.rizqanmr.newscompose.R
import com.rizqanmr.newscompose.viewmodels.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainUI(viewModel: MainViewModel) {
    val uiState = viewModel.newsUiState.observeAsState().value!!

    Scaffold(
        content = {
            BodyContent(
                newsUIState = uiState,
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
                retry = retryFetchingNews
            )
        }
    }
}