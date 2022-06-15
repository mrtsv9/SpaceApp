package com.example.space.presentation.details_screen.view.onboarding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.space.databinding.FragmentOnboardingBinding
import com.google.android.material.snackbar.Snackbar

class OnboardingDialog : DialogFragment() {

    private var binding: FragmentOnboardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding?.root?.setOnClickListener {
            Snackbar.make(binding!!.root, "Pressed", Snackbar.LENGTH_SHORT).setAction("Close?") {
                dialog?.dismiss()
            }.show()
        }
    }

}












