package com.cmaina.repository.mappers

import com.cmaina.local.models.CameraEntity
import com.cmaina.local.models.PhotoEntity
import com.cmaina.local.models.RoverEntity
import com.cmaina.network.models.NetworkCamera
import com.cmaina.network.models.NetworkPhoto
import com.cmaina.network.models.NetworkRover

fun NetworkPhoto.toEntity() = PhotoEntity(
    id = id,
    earthDate = earthDate,
    sol = sol,
    imgSrc = imgSrc,
    cameraEntity = networkCamera.toEntity(),
    roverEntity = networkRover.toEntity()
)

fun NetworkCamera.toEntity() = CameraEntity(
    id = id,
    fullName = fullName,
    name = name,
    roverId = roverId
)

fun NetworkRover.toEntity() = RoverEntity(
    id = id,
    landingDate = landingDate,
    launchDate = launchDate,
    name = name,
    status = status
)
