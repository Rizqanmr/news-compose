package com.rizqanmr.newscompose.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsSource(
    val name: String?
)
