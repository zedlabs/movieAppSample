package com.example.movieapp.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.repository.MediaRepository
import com.example.movieapp.model.Search

class MediaPagingSource(
    val network: MediaRepository,
    val query: String
) : PagingSource<Int, Search>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Search> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = network.getMovieSearchList(query, nextPageNumber)
            LoadResult.Page(
                data = response.Search ?: listOf(),
                prevKey = null, // Only paging forward.
                nextKey = if(response.Search.isNullOrEmpty()) null else nextPageNumber+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
