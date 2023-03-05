package com.cmaina.repository.mappers

import com.cmaina.domain.models.CameraDomain
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.models.RoverDomain
import com.cmaina.local.models.CameraEntity
import com.cmaina.local.models.PhotoEntity
import com.cmaina.local.models.RoverEntity

fun PhotoEntity.toDomain() = DomainPhoto(
    id = id,
    cameraDomain = cameraEntity.toDomain(),
    earthDate = earthDate,
    imgSrc = imgSrc,
    roverDomain = roverEntity.toDomain(),
    sol = sol
)

fun CameraEntity.toDomain() = CameraDomain(
    id = id,
    fullName = fullName,
    name = name,
    roverId = roverId
)

fun RoverEntity.toDomain() = RoverDomain(
    id = id,
    landingDate = landingDate,
    launchDate = launchDate,
    name = name,
    status = status
)
