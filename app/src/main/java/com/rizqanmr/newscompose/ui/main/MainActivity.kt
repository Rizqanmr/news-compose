package com.rizqanmr.newscompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import com.rizqanmr.newscompose.ui.theme.NewsComposeTheme
import com.rizqanmr.newscompose.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme = viewModel.isDarkTheme.observeAsState(false)
            NewsComposeTheme(darkTheme = darkTheme.value) {
                MainUI(viewModel = viewModel)
            }
        }
    }
}