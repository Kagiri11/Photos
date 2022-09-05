package com.cmaina.fotos

import android.app.Application
import com.cmaina.network.di.networkModule
import com.cmaina.presentation.di.presentationModule
import com.cmaina.repository.di.repositoryModule
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import org.koin.core.context.startKoin

class FotosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val pipelineConfig = ImagePipelineConfig.newBuilder(this)
            .setDiskCacheEnabled(true)
            .setDownsampleEnabled(true)
            .setResizeAndRotateEnabledForNetwork(true)
            .build()

        Fresco.initialize(this, pipelineConfig)
        val allModules = listOf(presentationModule, networkModule, repositoryModule)
        startKoin {
            modules(allModules)
        }
    }
}
