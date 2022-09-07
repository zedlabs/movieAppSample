package com.example.movieapp.home

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.movieapp.repository.MediaPagingSource
import com.example.movieapp.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: MediaRepository
) : ViewModel() {

    private val currentQuery = MutableStateFlow("")

    fun updateQuery(update: String) {
        currentQuery.value = update
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    val flow = currentQuery.flatMapLatest { query ->
        Pager(PagingConfig(pageSize = 10)) {
            MediaPagingSource(repository, query)
        }.flow
            .cachedIn(viewModelScope)
    }

}
