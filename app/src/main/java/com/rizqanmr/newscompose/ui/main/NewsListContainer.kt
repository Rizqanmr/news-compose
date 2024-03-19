package com.rizqanmr.newscompose.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.rizqanmr.newscompose.R
import com.rizqanmr.newscompose.ui.theme.Typography

@Composable
fun NewsListContainer(
    newsUIState: NewsUIState,
    retry: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp
            )
    ) {

    }
}

@Composable
private fun CircularLoader() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ErrorView(
    errorMessage: String,
    showRetry: Boolean,
    retry: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            style = Typography.titleSmall
        )
        if (showRetry) {
            TextButton(onClick = retry) {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }
}