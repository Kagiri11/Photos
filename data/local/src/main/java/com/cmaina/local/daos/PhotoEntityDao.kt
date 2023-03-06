package com.cmaina.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cmaina.local.models.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoEntityDao {

    @Query("SELECT * FROM photoTable")
    fun fetchPhotos(): Flow<List<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotos(photoEntity: PhotoEntity)
}
