package com.cmaina.presentation

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class PresentationTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, PresentationTestApplication::class.java.name, context)
    }
}
