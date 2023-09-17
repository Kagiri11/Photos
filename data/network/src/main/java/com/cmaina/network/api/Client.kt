package com.cmaina.network.api

import io.ktor.client.*
import io.ktor.client.engine.cio.CIO

val NetworkClient = HttpClient(CIO){

    install()

}