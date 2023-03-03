package com.cmaina.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photoTable")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "camera") val cameraEntity: CameraEntity,
    @ColumnInfo(name = "earth_date") val earthDate: String,
    @ColumnInfo(name = "image_src") val imgSrc: String,
    @ColumnInfo(name = "rover") val roverEntity: RoverEntity,
    @ColumnInfo(name = "sol") val sol: Int
)
