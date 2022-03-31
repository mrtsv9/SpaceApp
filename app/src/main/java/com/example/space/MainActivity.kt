package com.example.space

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.domain.RetrofitInstance
import com.example.domain.dto.RoverDataResponse
import com.example.domain.service.ApiService
import com.example.space.databinding.ActivityMainBinding
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.presenter.MainPresenter
import com.example.space.presentation.main_screen.view.MainView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import moxy.MvpActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var interactor: MainInteractor

    @Inject
    lateinit var apiService: ApiService

    private val presenter by moxyPresenter { MainPresenter(this, interactor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        Log.d("KEK", apiService.getRoverData().toString())
//        val test = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
//        lifecycleScope.launch {
//            Log.d("KEK", test.getRoverData().body().toString())
//        }

    }

    override fun displayData(data: RoverDataResponse) {

    }

}
