package com.cmaina.tacc

import android.app.Application
import com.cmaina.network.di.NetworkModule
import com.cmaina.repository.di.RepositoryModule
import com.cmaina.tacc.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarsPhotosApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarsPhotosApp)
            modules(listOf(AppModule, RepositoryModule, NetworkModule))
        }
    }
}
