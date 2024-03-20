package com.rizqanmr.newscompose.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.rizqanmr.newscompose.data.models.NewsArticle
import com.rizqanmr.newscompose.ui.components.*
import com.rizqanmr.newscompose.ui.theme.Typography

@Composable
fun NewsList(news: LazyPagingItems<NewsArticle>) {
    val context = LocalContext.current
    val isDark = MaterialTheme.colors.isLight
    LazyColumn {
        items(news.itemCount) { index ->
            val newsArticle = news[index]
            newsArticle?.let {
                NewsItem(newsArticle = it, onClick = {
                    CustomTab.launch(context, it.url.toString(), isDark)
                })
            }
        }
    }
}

@Composable
fun NewsItem(newsArticle: NewsArticle, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = { onClick() })) {
        Row(
            modifier = Modifier.padding(all = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteImage(
                url = newsArticle.urlToImage,
                modifier = Modifier.requiredSize(100.dp)
            )
            WidthSpacer(value = 10.dp)
            Column {
                if (!newsArticle.source.name.isNullOrEmpty()) {
                    Text(
                        text = newsArticle.source.name,
                        style = Typography.bodyMedium.copy(color = MaterialTheme.colors.secondary)
                    )
                    HeightSpacer(value = 4.dp)
                }
                Text(
                    text = newsArticle.title,
                    style = Typography.titleSmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(value = 4.dp)
                Text(
                    text = newsArticle.getContentDate().toString(),
                    style = Typography.bodyMedium.copy(color = MaterialTheme.colors.secondary)
                )
            }
        }
        HeightSpacer(value = 10.dp)
        Divider(
            color = MaterialTheme.colors.secondary.copy(
                alpha = 0.2f
            )
        )
    }
}