package com.example.paging3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.paging3.data.model.Result
import com.example.paging3.databinding.CharacterItemBinding
import com.example.paging3.ui.adapter.CharacterAdapter.CharacterViewHolder

class CharacterAdapter : PagingDataAdapter<Result, CharacterViewHolder>(diffCallback) {

    inner class CharacterViewHolder(val binding: CharacterItemBinding) :
        ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            holder.itemView.apply {
                "${currentItem?.name}".also { textViewName.text = it }

                val imageLink = currentItem?.image
                imageView.load(imageLink) {
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterItemBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )
    }


}
