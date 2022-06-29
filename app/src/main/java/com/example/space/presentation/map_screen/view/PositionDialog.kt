package com.example.space.presentation.map_screen.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.space.databinding.PositionDialogBinding
import com.example.space.presentation.map_screen.presenter.MapPresenter
import com.example.space.presentation.map_screen.repository.MapRepository
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PositionDialog(var map: GoogleMap, var latLng: LatLng) : DialogFragment() {

    private var binding: PositionDialogBinding? = null

    @Inject
    lateinit var presenter: MapPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PositionDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding?.btnCancel?.setOnClickListener {
            dialog?.dismiss()
        }

        binding?.btnSave?.setOnClickListener {
            presenter.addMarker(
                MarkerOptions().position(latLng).title(binding?.etMarkerName?.text.toString())
            )
            map.addMarker(
                MarkerOptions().position(latLng).title(binding?.etMarkerName?.text.toString())
            )
            dialog?.dismiss()
        }
    }

}