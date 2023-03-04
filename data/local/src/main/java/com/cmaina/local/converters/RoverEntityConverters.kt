package com.cmaina.local.converters

import androidx.room.TypeConverter
import com.cmaina.local.models.RoverEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object RoverEntityConverters {

    private val gson = Gson()
    private val type = object : TypeToken<RoverEntity>() {}.type

    @TypeConverter
    fun fromRoverEntityToString(roverEntity: RoverEntity): String {
        return gson.toJson(roverEntity, type)
    }

    @TypeConverter
    fun fromStringToRoverEntity(roverEntityString: String): RoverEntity {
        return gson.fromJson(roverEntityString, type)
    }
}
