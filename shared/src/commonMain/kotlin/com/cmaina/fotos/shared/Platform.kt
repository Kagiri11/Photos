package com.cmaina.fotos.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform