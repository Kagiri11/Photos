package com.cmaina.repository.mappers

import com.cmaina.domain.models.CameraDomain
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.models.RoverDomain
import com.cmaina.local.models.CameraEntity
import com.cmaina.local.models.PhotoEntity
import com.cmaina.local.models.RoverEntity

fun DomainPhoto.toEntity() = PhotoEntity(
    id = id,
    cameraEntity = cameraDomain.toEntity(),
    earthDate = earthDate,
    imgSrc = imgSrc,
    roverEntity = roverDomain.toEntity(),
    sol = sol
)

fun CameraDomain.toEntity() = CameraEntity(
    id,
    fullName,
    name,
    roverId
)

fun RoverDomain.toEntity() = RoverEntity(
    id,
    landingDate,
    launchDate,
    name,
    status
)
