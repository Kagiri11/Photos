package com.cmaina.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyBenchMark {
    @get:Rule
    val benchMarkRule = MacrobenchmarkRule()

    @Test
    fun myFirstBenchMarkTest() {
        benchMarkRule.measureRepeated(
            packageName = "com.cmaina.fotos",
            compilationMode = CompilationMode.DEFAULT,
            startupMode = StartupMode.COLD,
            metrics = listOf(StartupTimingMetric()),
            iterations = 5,
            measureBlock = {
                pressHome()
                startActivityAndWait()
            }
        )
    }
}
