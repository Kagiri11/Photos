package com.cmaina.local.converters

import androidx.room.TypeConverter
import com.cmaina.local.models.RoverEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoverEntityConverters {

    val gson = Gson()

    @TypeConverter
    fun fromRoverEntityToString(roverEntity: RoverEntity): String {
        val type = object : TypeToken<RoverEntity>() {}.type
        return gson.toJson(roverEntity, type)
    }

    @TypeConverter
    fun fromStringToRoverEntity(roverEntityString: String): RoverEntity {
        val type = object : TypeToken<RoverEntity>() {}.type
        return gson.fromJson(roverEntityString, type)
    }
}
