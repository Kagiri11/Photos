package com.cmaina.domain.models

data class DomainPhoto(
    val id: Int,
    val cameraDomain: CameraDomain,
    val earthDate: String,
    val imgSrc: String,
    val roverDomain: RoverDomain,
    val sol: Int
)
