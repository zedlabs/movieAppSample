package com.example.movieapp.model

data class MovieNetworkResponse(
    val Response: String?,
    val Search: List<Search>?,
    val totalResults: String?
)
