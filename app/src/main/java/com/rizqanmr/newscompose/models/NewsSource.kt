package com.rizqanmr.newscompose.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsSource(
    val name: String?
)
