package com.cmaina.local.converters

import androidx.room.TypeConverter
import com.cmaina.local.models.CameraEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CameraEntityConverter {

    private val gson = Gson()
    private val type = object : TypeToken<CameraEntity>() {}.type

    @TypeConverter
    fun fromCameraEntityToString(cameraEntity: CameraEntity): String {
        return gson.toJson(cameraEntity, type)
    }

    @TypeConverter
    fun fromStringToCameraEntity(cameraEntityString: String?): CameraEntity {
        return gson.fromJson(cameraEntityString, type)
    }
}
