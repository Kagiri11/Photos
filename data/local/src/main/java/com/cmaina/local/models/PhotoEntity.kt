package com.cmaina.local.models

data class PhotoEntity(
    val id: Int,
    val cameraEntity: CameraEntity,
    val earthDate: String,
    val imgSrc: String,
    val roverEntity: RoverEntity,
    val sol: Int
)
