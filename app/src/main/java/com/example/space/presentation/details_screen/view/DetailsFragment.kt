package com.example.space.presentation.details_screen.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toIcon
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.space.R
import com.example.space.databinding.FragmentDetailsBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.details_screen.presenter.DetailsPresenter
import com.example.space.presentation.details_screen.view.onboarding.OnboardingDialog
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), DetailsView {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    @Inject
    lateinit var router: Router

    private val presenter by moxyPresenter { DetailsPresenter(router, this) }

    private val itemImgLink: String
        get() = requireArguments().getString(FRAGMENT_KEY).toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun setup() {

        val sharedPref: SharedPreferences =
            requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()

        if (!sharedPref.getBoolean("isAlreadyShown", false)) {
            val dialog = OnboardingDialog()
            dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_OnboardingDialog)
            dialog.show(childFragmentManager, "OnboardingDialog")

            editor.putBoolean("isAlreadyShown", true)
            editor.commit()
        }

        binding.icBack.setOnClickListener { presenter.onBackPressed() }

        binding.icShare.setOnClickListener { shareImage() }

        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                presenter.onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, backCallback)
    }

    override fun onStop() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onStop()
    }

    private fun shareImage() {

        val bitmapDrawable = binding.customView.drawable as? BitmapDrawable
        val bitmap = bitmapDrawable?.bitmap

        val imagesFolder = File(activity?.cacheDir, "images")

        var contentUri: Uri? = null

        try {
            imagesFolder.mkdirs()
            Log.d("KEK", "started")
            val file = File(imagesFolder, "shared_image.png")
            val stream = FileOutputStream(file)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 50, stream)
            stream.flush()
            stream.close()

            contentUri = FileProvider.getUriForFile(requireContext(), "com.example.space", file)
            Log.d("KEK", "finished")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.sharing_subject))
        intent.putExtra(Intent.EXTRA_STREAM, contentUri)
        intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.sharing_text))
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(intent, resources.getString(R.string.sharing_via)))

    }

    override fun displayData() {
        Glide.with(requireActivity())
            .load(itemImgLink)
            .into(binding.customView)
    }

    companion object {

        private const val FRAGMENT_KEY = "details"
        private const val PREFS_NAME = "ONBOARDING_PREFS"

        fun getNewInstance(itemImgLink: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply { putString(FRAGMENT_KEY, itemImgLink) }
            }

    }
}