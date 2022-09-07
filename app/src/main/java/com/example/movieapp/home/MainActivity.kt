package com.example.movieapp.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.ItemClick
import com.example.movieapp.MediaListAdapter
import com.example.movieapp.core.Resource
import com.example.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemClick {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    val pagingAdapter = MediaListAdapter( this@MainActivity, MediaListAdapter.SearchItemComparator)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeViews()
        initializeRecyclerView()
        initializeObservers()
    }

    private fun initializeViews() {
        binding.search.edtSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.updateQuery(text.toString())
        }
    }

    private fun initializeRecyclerView() {
        with(binding) {
            rvMediaList.apply {
                adapter = pagingAdapter
                layoutManager = GridLayoutManager(this@MainActivity, 3)
            }
        }
    }

    private fun initializeObservers() {
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }

    }

    override fun onItemClick(position: Int) {
        // item click action
    }
}