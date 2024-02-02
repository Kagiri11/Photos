package com.cmaina.fotos.shared.data

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val BASEURL = "https://api.unsplash.com/"

    val UserAccessToken = stringPreferencesKey("accessToken")
}