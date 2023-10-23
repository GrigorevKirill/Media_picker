package com.example.myapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RvPickerItemBinding

class CustomViewHolder(
    val binding: RvPickerItemBinding,
    val listener: RecyclerViewAdapter.Listener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RecyclerViewAdapter.Types.MediaItem) {
        binding.apply {
            Glide.with(itemView)
                .load(item.uri)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivMediaItem)
            ivMediaItem.setOnClickListener {
                listener.onClick(item.chooseIndex) {

                }
            }
            ivItemCount.setOnClickListener {
                listener.onClick(item.chooseIndex) {

                }
            }
        }
    }
}