package com.example.space.presentation.main_screen.model

import com.example.domain.dto.RoverDataResponse

data class RoverDataItem(
    val imgLink: String,
    val cameraName: String,
    val roverName: String
)

fun RoverDataResponse.toRoverDataItems(): List<RoverDataItem> {

//    val itemsList: MutableList<RoverDataItem> = emptyList<RoverDataItem>().toMutableList()
//
//    photos.forEach {
//        itemsList.add(RoverDataItem(imgLink = it.imgLink,
//            cameraName = it.camera.fullName,
//            roverName = it.rover.name))
//    }
    return photos.map {
        RoverDataItem(imgLink = it.imgLink,
            cameraName = it.camera.fullName,
            roverName = it.rover.name)
    }
}


