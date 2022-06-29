package com.example.space.presentation.map_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.space.databinding.ItemMarkerBinding
import com.google.android.gms.maps.model.MarkerOptions

class MarkerListAdapter(
    private val markerList: List<MarkerOptions> = emptyList(),
    private val clickListener: (MarkerOptions) -> Unit
) : RecyclerView.Adapter<MarkerListAdapter.MarkerListViewHolder>() {

    inner class MarkerListViewHolder(
        binding: ItemMarkerBinding,
        private val clickListener: (MarkerOptions) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val tvMarkerName = binding.tvMarkerName
        private val tvMarkerLongitude = binding.tvLongitudeValue
        private val tvMarkerWidth = binding.tvWidthValue
        private val btnDelete = binding.btnDeleteMarker

        fun bind(marker: MarkerOptions) {
            tvMarkerName.text = marker.title
            tvMarkerLongitude.text = marker.position.longitude.toString()
            tvMarkerWidth.text = marker.position.latitude.toString()

            btnDelete.setOnClickListener {
                clickListener(marker)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerListViewHolder {
        val binding = ItemMarkerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MarkerListViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: MarkerListViewHolder, position: Int) {
        holder.bind(markerList[position])
    }

    override fun getItemCount(): Int {
        return markerList.size
    }
}