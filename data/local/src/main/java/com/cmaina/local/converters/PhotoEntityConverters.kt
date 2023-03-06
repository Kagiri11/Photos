package com.cmaina.local.converters

import androidx.room.TypeConverter
import com.cmaina.local.models.CameraEntity
import com.cmaina.local.models.PhotoEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PhotoEntityConverters {

    val gson = Gson()

    @TypeConverter
    fun fromCameraEntityToString(entities: List<PhotoEntity>): String {
        val type = object : TypeToken<List<PhotoEntity>>() {}.type
        return gson.toJson(entities, type)
    }

    @TypeConverter
    fun fromStringToCameraEntity(string: String?): List<PhotoEntity> {
        val type = object : TypeToken<List<PhotoEntity>>() {}.type
        return gson.fromJson(string, type)
    }

}