package com.example.domain.dto

import com.squareup.moshi.Json

data class RoverDataResponse(
    val photos: List<Photos>
)

data class Photos(
    val camera: Camera,
    @field:Json (name = "img_src")
    val imgLink: String,
    val rover: Rover
)

data class Camera(
    @field:Json (name = "full_name")
    val fullName: String
)

data class Rover(
    val name: String
)

