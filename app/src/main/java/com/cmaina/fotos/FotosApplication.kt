package com.cmaina.fotos

import android.support.multidex.MultiDexApplication
import com.cmaina.domain.di.domainModules
import com.cmaina.fotos.di.presentationModule
import com.cmaina.network.di.networkModule
import org.koin.core.context.startKoin

class FotosApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        val allModules = listOf(presentationModule, domainModules, networkModule)
        startKoin {
            modules(allModules)
        }
    }
}
