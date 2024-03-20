package com.rizqanmr.newscompose.repository

import com.rizqanmr.newscompose.data.Constant
import com.rizqanmr.newscompose.data.models.NewsError
import com.rizqanmr.newscompose.data.models.NewsResponse
import com.rizqanmr.newscompose.network.ApiService
import com.rizqanmr.newscompose.network.Result
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val moshi: Moshi
) : NewsRepository {

    override suspend fun getTopHeadlines(page: Int): Result<NewsResponse> {
        try {
            val response = apiService.getTopHeadlines(page)
            return if (response.isSuccessful) {
                if (response.body() != null) {
                    Result.Success(response.body()!!)
                } else {
                    Result.Error(Constant.NO_DATA)
                }
            } else {
                val jsonAdapter: JsonAdapter<NewsError> = moshi.adapter(
                    NewsError::class.java
                )
                withContext(Dispatchers.IO) {
                    val newsError = jsonAdapter.fromJson(response.errorBody()?.string()!!)
                    Result.Error(
                        newsError!!.message,
                        showRetry(newsError.code)
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            var errorMessage = e.localizedMessage
            if (e.localizedMessage!!.contains("Unable to resolve host")) {
                errorMessage = Constant.NO_CONNECTION
            }
            return Result.Error(errorMessage ?: "Something went wrong")
        }
    }

    private fun showRetry(code: String): Boolean = when (code) {
        Constant.API_KEY_DISABLED, Constant.API_KEY_EXHAUSTED,
        Constant.API_KEY_INVALID, Constant.API_KEY_MISSING, Constant.RATE_LIMITED -> false
        else -> true
    }
}