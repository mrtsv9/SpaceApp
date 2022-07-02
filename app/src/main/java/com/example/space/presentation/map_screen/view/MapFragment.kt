package com.example.space.presentation.map_screen.view

import android.app.Activity
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.space.R
import com.example.space.databinding.FragmentMapBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.map_screen.presenter.MapPresenter
import com.example.space.presentation.map_screen.repository.MapRepository
import com.example.space.presentation.map_screen.util.MapStyle
import com.example.space.presentation.map_screen.util.OnSwipeListener
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(), MapView, OnMapReadyCallback {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapBinding
        get() = FragmentMapBinding::inflate

    private lateinit var map: GoogleMap

    @Inject
    lateinit var repository: MapRepository

    private val presenter by moxyPresenter { MapPresenter(repository) }

    private var nextMapStyle: MapStyle.MapStyles = MapStyle.MapStyles.SecondStyle

    override fun setup() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0

        addMarkers()

        if (isDarkTheme(requireActivity())) map.setMapStyle(MapStyleOptions(MapStyle.nightModeStyle))
        else binding.btnLandscape.setOnClickListener {
            nextMapStyle = when (nextMapStyle) {
                is MapStyle.MapStyles.DefaultStyle -> {
                    map.setMapStyle(MapStyleOptions(MapStyle.defaultStyle))
                    MapStyle.MapStyles.SecondStyle
                }
                is MapStyle.MapStyles.SecondStyle -> {
                    map.setMapStyle(MapStyleOptions(MapStyle.secondStyle))
                    MapStyle.MapStyles.ThirdStyle
                }
                is MapStyle.MapStyles.ThirdStyle -> {
                    map.setMapStyle(MapStyleOptions(MapStyle.thirdStyle))
                    MapStyle.MapStyles.DefaultStyle
                }
            }
        }

        val markerListFragment = MarkerListFragment(map)
        binding.viewForSwipe.setOnTouchListener(object : OnSwipeListener(requireContext()) {
            override fun onSwipeBottomToTop() {
                super.onSwipeBottomToTop()
                markerListFragment.show(childFragmentManager, "tag")
            }
        })

        map.setOnMapClickListener {
            PositionDialog(map, it).show(childFragmentManager, "PositionDialog")
        }

    }

    private fun addMarkers() {
        presenter.getAllMarkers().forEach { marker ->
            map.addMarker(marker)
        }
    }

    private fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

}