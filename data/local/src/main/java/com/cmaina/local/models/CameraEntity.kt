package com.cmaina.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cameraTable")
data class CameraEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "rover_id") val roverId: Int
)
