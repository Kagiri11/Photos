package com.cmaina.network.utils

import io.ktor.client.plugins.auth.providers.BearerTokens

internal val TokenStorage =
    mutableListOf(BearerTokens("Client-ID pbq2xfRl6EbYjlRQeGfkp5dBfdzSuETZQiBPrbSSswk", ""))