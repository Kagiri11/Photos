package com.cmaina.network

import android.util.Log
import com.cmaina.network.utils.TokenStorage
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.gson.gson

class Network(engine: HttpClientEngine) {
    val httpClient = HttpClient(engine) {

        install(ContentNegotiation) {
            gson()
        }

        install(Auth) {
            bearer {
                loadTokens {
                    TokenStorage.first()
                }
            }
        }

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
}