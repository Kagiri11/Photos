package com.cmaina.fotos.shared.models.auth

data class AuthDomainResponse(
    val accessToken: String,
    val refreshToken: String,
    val createdAt: Int,
    val scope: String,
    val tokenType: String
)
