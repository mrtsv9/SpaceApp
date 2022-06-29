package com.example.space.presentation.map_screen.presenter

import com.example.space.presentation.map_screen.repository.MapRepository
import com.example.space.presentation.map_screen.view.MapView
import com.google.android.gms.maps.model.MarkerOptions
import moxy.MvpPresenter

class MapPresenter(
    private val repository: MapRepository
) : MvpPresenter<MapView>() {

    fun addMarker(marker: MarkerOptions) {
        repository.addMarker(marker)
    }

    fun removeMarker(marker: MarkerOptions) {
        repository.removeMarker(marker)
    }

    fun getAllMarkers(): MutableList<MarkerOptions> {
        return repository.getAllMarkers()
    }

}