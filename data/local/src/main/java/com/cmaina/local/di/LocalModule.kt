package com.cmaina.local.di

import androidx.room.Room
import com.cmaina.local.AppDatabase
import org.koin.dsl.module

val LocalModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "photos_db").build() }
    single { get<AppDatabase>().photoEntityDao() }
}
