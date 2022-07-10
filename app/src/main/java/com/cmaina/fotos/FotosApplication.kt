package com.cmaina.fotos

import android.app.Application
import com.cmaina.domain.di.domainModules
import com.cmaina.network.di.networkModule
import com.cmaina.presentation.di.presentationModule
import com.cmaina.repository.di.repositoryModule
import org.koin.core.context.startKoin

class FotosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val allModules = listOf(presentationModule, domainModules, networkModule, repositoryModule)
        startKoin {
            modules(allModules)
        }
    }
}
