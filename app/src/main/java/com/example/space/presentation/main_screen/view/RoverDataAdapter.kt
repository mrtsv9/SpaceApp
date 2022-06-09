package com.example.space.presentation.main_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.space.R
import com.example.space.databinding.ItemImageBinding
import com.example.space.presentation.main_screen.model.RoverDataItem


class RoverDataAdapter(
    private var photosList: List<RoverDataItem> = emptyList(),
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<RoverDataAdapter.RoverDataViewHolder>() {

    inner class RoverDataViewHolder(
        binding: ItemImageBinding,
        private val clickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val ivPhoto: AppCompatImageView = binding.ivPhoto
        private val tvRoverName: TextView = binding.tvRoverName
        private val tvCameraName: TextView = binding.tvCameraName
        private val tvShowImage: TextView = binding.tvShowImage

        fun bind(data: RoverDataItem) {
            Glide.with(itemView)
                .load(data.imgLink)
                .placeholder(R.drawable.ic_loader)
                .centerCrop()
                .into(ivPhoto)
            tvRoverName.text = data.roverName
            tvCameraName.text = data.cameraName
            tvShowImage.setOnClickListener {
                clickListener(data.imgLink)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverDataViewHolder {
        val binding = ItemImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RoverDataViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: RoverDataViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

}