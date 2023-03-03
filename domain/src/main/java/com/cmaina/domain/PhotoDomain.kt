package com.cmaina.domain

data class PhotoDomain(
    val id: Int,
    val cameraDomain: CameraDomain,
    val earthDate: String,
    val imgSrc: String,
    val roverDomain: RoverDomain,
    val sol: Int
)
