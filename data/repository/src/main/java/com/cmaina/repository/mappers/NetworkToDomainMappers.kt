package com.cmaina.repository.mappers

import com.cmaina.domain.models.CameraDomain
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.models.RoverDomain
import com.cmaina.network.models.NetworkCamera
import com.cmaina.network.models.NetworkPhoto
import com.cmaina.network.models.NetworkRover

fun NetworkCamera.toDomain() = CameraDomain(
    id = id,
    fullName = fullName,
    name = name,
    roverId = roverId
)

fun NetworkRover.toDomain() = RoverDomain(
    id = id,
    landingDate = landingDate,
    launchDate = launchDate,
    name = name,
    status = status
)

fun NetworkPhoto.toDomain() = DomainPhoto(
    id = id,
    earthDate = earthDate,
    imgSrc = imgSrc,
    sol = sol,
    cameraDomain = networkCamera.toDomain(),
    roverDomain = networkRover.toDomain()
)
