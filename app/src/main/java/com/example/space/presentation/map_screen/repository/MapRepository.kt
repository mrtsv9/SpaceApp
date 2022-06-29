package com.example.space.presentation.map_screen.repository

import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapRepository {

    private val markerList = mutableListOf<MarkerOptions>()

    fun addMarker(marker: MarkerOptions) {
        markerList.add(marker)
    }

    fun removeMarker(marker: MarkerOptions) {
        markerList.forEach { if (it.position == marker.position) markerList.remove(it) }
    }

    fun getAllMarkers(): MutableList<MarkerOptions> {
        return markerList
    }

}