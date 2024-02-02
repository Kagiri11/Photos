package com.cmaina.fotos.shared.data.network.sources

import com.cmaina.fotos.shared.data.network.AuthRemoteSource
import com.cmaina.fotos.shared.data.network.PhotosRemoteSource
import com.cmaina.fotos.shared.data.network.UsersRemoteSource
import com.cmaina.fotos.shared.data.network.client.Network

import io.ktor.client.engine.cio.CIO

val NetworkClient = Network(CIO.create()).httpClient
val photosRemoteSource = PhotosRemoteSource(client = NetworkClient)
val usersRemoteSource = UsersRemoteSource(client = NetworkClient)
val authRemoteSource = AuthRemoteSource(client = NetworkClient)
