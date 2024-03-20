package com.rizqanmr.newscompose.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rizqanmr.newscompose.data.models.NewsArticle
import com.rizqanmr.newscompose.network.Result
import com.rizqanmr.newscompose.repository.NewsRepository
import java.io.IOException

class NewsPagingSource(
    private val newsRepository: NewsRepository
) : PagingSource<Int, NewsArticle>() {
    override fun getRefreshKey(state: PagingState<Int, NewsArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsArticle> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = newsRepository.getTopHeadlines(nextPageNumber)
            if (response is Result.Success) {
                val news = response.data.articles
                return LoadResult.Page(
                    data = news,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (news.isEmpty()) null else nextPageNumber + 1
                )
            } else {
                return LoadResult.Error(IOException("Unexpected error"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}