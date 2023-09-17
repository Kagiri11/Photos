package com.cmaina.network.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import java.util.logging.Level

val NetworkClient = HttpClient(CIO) {

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Ktor Log ==> ", message)
            }
        }

        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP response:", "${response.body<Any>()}")
        }
    }

}