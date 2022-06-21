package com.example.space.presentation.map_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.space.R
import com.example.space.databinding.FragmentMapBinding
import com.example.space.presentation.base.view.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : BaseFragment<FragmentMapBinding>(), MapView, OnMapReadyCallback,
    GoogleMap.OnMapClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapBinding
        get() = FragmentMapBinding::inflate

    private lateinit var map: GoogleMap

    override fun setup() {

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onMapClick(p0: LatLng) {
        map.addMarker(MarkerOptions().position(p0).title("Title"))
    }

}