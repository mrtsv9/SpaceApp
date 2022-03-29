package com.example.space

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.domain.dto.RoverDataResponse
import com.example.space.databinding.ActivityMainBinding
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.presenter.MainPresenter
import com.example.space.presentation.main_screen.view.MainView
import moxy.MvpActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@Suppress("DEPRECATION")
class MainActivity : MvpActivity(), MainView {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var interactor: MainInteractor

    private val presenter by moxyPresenter { MainPresenter(this, interactor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


    }

    override fun displayData(data: List<RoverDataResponse>) {
        TODO("Not yet implemented")
    }

}
