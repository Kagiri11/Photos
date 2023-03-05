package com.cmaina.local.converters

import androidx.room.TypeConverter
import com.cmaina.local.models.CameraEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CameraEntityConverter {

    val gson = Gson()

    @TypeConverter
    fun fromCameraEntityToString(cameraEntity: CameraEntity): String {
        val type = object : TypeToken<CameraEntity>() {}.type
        return gson.toJson(cameraEntity, type)
    }

    @TypeConverter
    fun fromStringToCameraEntity(cameraEntityString: String?): CameraEntity {
        val type = object : TypeToken<CameraEntity>() {}.type
        return gson.fromJson(cameraEntityString, type)
    }
}
