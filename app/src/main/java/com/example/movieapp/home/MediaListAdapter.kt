package com.example.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.core.ImageHelper
import com.example.movieapp.databinding.ItemListMediaBinding
import com.example.movieapp.model.Search

class MediaListAdapter(
    private val onItemCLick: ItemClick,
    diffCallback: DiffUtil.ItemCallback<Search>,
) : PagingDataAdapter<Search, MediaListAdapter.ItemViewHolder>(diffCallback) {

    inner class ItemViewHolder(private val binding: ItemListMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search?) {
            item?.let { searchItem ->
                // Poster Image
                ImageHelper.loadImage(searchItem.Poster, binding.ivPoster)
                // Media Title
                binding.tvTitle.text = searchItem.Title ?: ""
                // Media Desc
                binding.tvDesc.text = searchItem.Type ?: ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemCLick.onItemClick(position)
        }
    }

    object SearchItemComparator : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            // Id is unique.
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }
    }

}

interface ItemClick {
    fun onItemClick(position: Int)
}