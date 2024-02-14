package com.cmaina.fotos.shared.data.network.client

import com.cmaina.fotos.shared.data.network.utils.TokenStorage
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
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
                    Napier.d(tag = "Ktor Log ==> ", message = message)
                }
            }

            level = LogLevel.BODY
        }

        install(ResponseObserver) {
            onResponse { response ->
                Napier.d(tag = "HTTP response:", message = "${response.body<Any>()}")
            }
        }

    }
}