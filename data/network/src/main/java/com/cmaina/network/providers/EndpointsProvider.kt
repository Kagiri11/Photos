package com.cmaina.network.providers

import com.cmaina.network.api.AuthRemoteSource
import com.cmaina.network.NetworkClient
import com.cmaina.network.api.PhotosRemoteSource
import com.cmaina.network.api.UsersRemoteSource

val photosRemoteSource = PhotosRemoteSource(client = NetworkClient)
val usersRemoteSource = UsersRemoteSource(client = NetworkClient)
val authRemoteSource = AuthRemoteSource(client = NetworkClient)
