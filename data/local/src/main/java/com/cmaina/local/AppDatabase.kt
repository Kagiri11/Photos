package com.cmaina.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cmaina.local.daos.CameraEntityDao
import com.cmaina.local.daos.PhotoEntityDao
import com.cmaina.local.daos.RoverEntityDao
import com.cmaina.local.models.CameraEntity
import com.cmaina.local.models.PhotoEntity
import com.cmaina.local.models.RoverEntity

@Database(entities = [CameraEntity::class, PhotoEntity::class, RoverEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoEntityDao(): PhotoEntityDao

    abstract fun cameraEntityDao(): CameraEntityDao

    abstract fun roverEntityDao(): RoverEntityDao
}
