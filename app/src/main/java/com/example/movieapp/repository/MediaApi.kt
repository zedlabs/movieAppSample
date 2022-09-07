package com.example.movieapp.repository

import com.example.movieapp.model.MovieNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaApi {

    @GET("/")
    suspend fun searchMultiList(
        @Query("apiKey") api_key: String,
        @Query("s") query: String,
        @Query("page") page: Int,
    ): MovieNetworkResponse
}