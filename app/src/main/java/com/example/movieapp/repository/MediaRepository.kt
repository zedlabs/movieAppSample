package com.example.movieapp.repository

import com.example.movieapp.model.MovieNetworkResponse
import javax.inject.Inject

class MediaRepository @Inject constructor(
    private val mediaApi: MediaApi
) {

    // Ideally API key should not be here; use BuildConfig/Network/NDK
    suspend fun getMovieSearchList(
        searchParam: String,
        page: Int,
    ): MovieNetworkResponse {
        return mediaApi.searchMultiList("cbdde054", searchParam, page)
    }
}