package com.cmaina.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cmaina.local.converters.CameraEntityConverter
import com.cmaina.local.converters.PhotoEntityConverters
import com.cmaina.local.converters.RoverEntityConverters
import com.cmaina.local.daos.PhotoEntityDao
import com.cmaina.local.models.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 2, exportSchema = false)
@TypeConverters(CameraEntityConverter::class, RoverEntityConverters::class, PhotoEntityConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoEntityDao(): PhotoEntityDao
}
