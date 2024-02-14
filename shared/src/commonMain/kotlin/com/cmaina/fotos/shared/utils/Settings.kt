package com.cmaina.fotos.shared.utils

import com.russhwolf.settings.Settings

expect val PhotosSettings: Settings

object AppSettings {

    fun putString(key: String, value: String) {
        PhotosSettings.putString(key = key, value = value)
    }

    fun getString(key: String): String {
        return PhotosSettings.getString(key = key, defaultValue = "")
    }

    fun putBoolean(key: String, value: Boolean) {
        PhotosSettings.putBoolean(key = key, value = value)
    }

    fun getBoolean(key: String): Boolean {
        return PhotosSettings.getBoolean(key = key, defaultValue = false)
    }
}