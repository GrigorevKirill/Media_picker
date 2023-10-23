package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.databinding.RvPickerItemBinding

class RecyclerViewAdapter(
    private val list: List<Types.MediaItem>,
    private val listener: Listener,
) : RecyclerView.Adapter<CustomViewHolder>() {

    interface Listener {
        fun onClick(chooseIndex: Int, callback: (Int) -> Unit)
    }

    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            binding = RvPickerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener = listener
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    sealed class Types {
        data class MediaItem(val uri: String?, val chooseIndex: Int, val position: Int)
    }

}