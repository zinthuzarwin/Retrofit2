package com.example.retrofit2.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)