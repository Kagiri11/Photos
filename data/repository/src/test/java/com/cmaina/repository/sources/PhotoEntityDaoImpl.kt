package com.cmaina.repository.sources

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.cmaina.local.AppDatabase
import com.cmaina.local.daos.PhotoEntityDao
import com.cmaina.local.models.PhotoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

class PhotoEntityDaoImpl : PhotoEntityDao {
    val photos = mutableListOf<PhotoEntity>()
    override fun fetchPhotos(): Flow<List<PhotoEntity>> {
        return flowOf(photos)
    }

    override fun addPhotos(photoEntity: List<PhotoEntity>) {
        photos.addAll(photoEntity)
    }
}

class TestPhotosDb {
    lateinit var photoEntityDao: PhotoEntityDao
    lateinit var database: AppDatabase

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    @Before
    @ExperimentalCoroutinesApi
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        Dispatchers.setMain(testDispatcher)

        database = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()

        photoEntityDao = database.photoEntityDao()
    }

    @After
    @ExperimentalCoroutinesApi
    fun closeDb() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
        database.close()
    }
}
