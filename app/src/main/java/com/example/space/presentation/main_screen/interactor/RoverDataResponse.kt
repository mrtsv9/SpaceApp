package com.example.space.presentation.main_screen.interactor

import com.google.gson.annotations.SerializedName

data class RoverDataResponse(
    val photos: List<Photos>
)

data class Photos (
    val camera: Camera,
    @SerializedName("img_src")
    val img: String,
    val rover: Rover
)

data class Camera (
    @SerializedName("full_name")
    val fullName: String
)

data class Rover(
    val name: String
)

