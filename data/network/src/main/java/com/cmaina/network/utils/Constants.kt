package com.cmaina.network.utils

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    val BASEURL = "https://api.unsplash.com/"

    val UserAccessToken = stringPreferencesKey("accessToken")
}