package com.cmaina.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roverTable")
data class RoverEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "landing_date") val landingDate: String,
    @ColumnInfo(name = "launch_date") val launchDate: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String
)
