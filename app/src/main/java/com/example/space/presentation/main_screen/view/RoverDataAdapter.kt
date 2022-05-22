package com.example.space.presentation.main_screen.view

import android.R.attr.radius
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.space.R
import com.example.space.presentation.main_screen.model.RoverDataItem


class RoverDataAdapter(
    private var photosList: List<RoverDataItem> = emptyList()
) : RecyclerView.Adapter<RoverDataAdapter.RoverDataViewHolder>() {

    class RoverDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivPhoto: AppCompatImageView = itemView.findViewById(R.id.iv_photo)
        private val tvRoverName: TextView = itemView.findViewById(R.id.tv_rover_name)
        private val tvCameraName: TextView = itemView.findViewById(R.id.tv_camera_name)
        private val tvShowImage: TextView = itemView.findViewById(R.id.tv_show_image)

        @SuppressLint("SetTextI18n")
        fun bind(data: RoverDataItem) {
            Glide.with(itemView)
                .load(data.imgLink)
                .placeholder(R.drawable.ic_loader)
                .centerCrop()
                .into(ivPhoto)
            tvRoverName.text = data.roverName
            tvCameraName.text =  data.cameraName
            tvShowImage.setOnClickListener {
                // TODO
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverDataViewHolder {
        return RoverDataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RoverDataViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    override fun getItemCount(): Int {
        return photosList.size
    }
}