package com.example.space.presentation.main_screen.interactor

import com.example.domain.dto.RoverDataResponse
import com.example.domain.service.ApiService
import com.example.space.presentation.base.repository.BaseInteractor
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val apiService: ApiService
): BaseInteractor {

    fun fetchRoverData(): List<RoverDataResponse>? = runBlocking {
        return@runBlocking apiService.getRoverData().body()
    }

}