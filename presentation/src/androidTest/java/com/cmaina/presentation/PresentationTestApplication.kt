package com.cmaina.presentation

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class PresentationTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}
