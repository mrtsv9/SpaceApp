package com.example.space.presentation.map_screen.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.space.R
import com.example.space.databinding.FragmentMarkerListBinding
import com.example.space.presentation.map_screen.presenter.MapPresenter
import com.example.space.presentation.map_screen.repository.MapRepository
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MarkerListFragment(var map: GoogleMap): BottomSheetDialogFragment() {

    private var binding: FragmentMarkerListBinding? = null

    @Inject
    lateinit var presenter: MapPresenter

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarkerListBinding.inflate(inflater, container,false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        adapter()
    }

    private fun adapter() {
        val adapter = MarkerListAdapter(presenter.getAllMarkers()) { clickListener(it) }
        binding?.rvMarkers?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvMarkers?.adapter = adapter
    }

    private fun clickListener(marker: MarkerOptions) {
        presenter.removeMarker(marker)
        map.clear()

        presenter.getAllMarkers().forEach {
            map.addMarker(it)
        }

        adapter()
    }

}