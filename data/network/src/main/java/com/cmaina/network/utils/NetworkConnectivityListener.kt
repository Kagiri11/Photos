package com.cmaina.network.utils

import android.net.ConnectivityManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object NetworkConnectivityListener {
    lateinit var connectivityManager: ConnectivityManager

    fun produceImageUrl(): Flow<String> {
        return flowOf()
    }
}
