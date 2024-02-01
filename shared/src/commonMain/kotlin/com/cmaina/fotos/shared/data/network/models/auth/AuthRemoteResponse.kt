package com.cmaina.fotos.shared.data.network.models.auth

import com.google.gson.annotations.SerializedName

data class AuthRemoteResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("created_at")
    val createdAt: Int,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)
