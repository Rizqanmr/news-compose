package com.rizqanmr.newscompose.data.models

import android.annotation.SuppressLint
import com.rizqanmr.newscompose.data.Constant
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat

@JsonClass(generateAdapter = true)
data class NewsArticle(
    val publishedAt: String,
    val author: String?,
    val source: NewsSource,
    val title: String,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val content: String?
) {
    @SuppressLint("SimpleDateFormat")
    fun getContentDate(): String? {
        return try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(publishedAt)
            val dateFormat = SimpleDateFormat("dd MMMM yyyy")
            date?.let { dateFormat.format(it) }
        } catch (e: Exception) {
            null
        }
    }

    fun getAuthorName(): String {
        val authorName = if (author.isNullOrEmpty()) Constant.NO_AUTHOR else author
        return "Author: $authorName"
    }
}
