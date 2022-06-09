package com.example.space.presentation.main_screen.interactor

import com.example.domain.dto.RoverDataResponse
import com.example.domain.service.ApiService
import com.example.space.presentation.base.interactor.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val apiService: ApiService
) : BaseInteractor {

    fun fetchRoverData(): Single<RoverDataResponse> {
        return apiService.getRoverData()
    }

}
