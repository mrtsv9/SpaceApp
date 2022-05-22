package com.example.space.presentation.main_screen.interactor

import android.util.Log
import com.example.domain.dto.Rover
import com.example.domain.dto.RoverDataResponse
import com.example.domain.service.ApiService
import com.example.space.presentation.base.repository.BaseInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.runBlocking
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val apiService: ApiService
): BaseInteractor {

    suspend fun fetchRoverData(): RoverDataResponse? {
        var data: RoverDataResponse? = null
        apiService.getRoverData()
            .subscribeOn(Schedulers.io())
            .subscribe {
            data = it
        }
        return data
    }

}