package com.cmaina.network.providers

import com.cmaina.network.Network
import com.cmaina.network.api.AuthRemoteSource
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.api.UsersRemoteSource
import io.ktor.client.engine.cio.CIO

val NetworkClient = Network(CIO.create()).httpClient
val photosRemoteSource = PhotosRemoteSource(client = NetworkClient)
val usersRemoteSource = UsersRemoteSource(client = NetworkClient)
val authRemoteSource = AuthRemoteSource(client = NetworkClient)
