import Configurations.CompileSdk
import Libraries.appCompat
import Libraries.core
import Libraries.gson
import Libraries.room
import Libraries.roomCompiler
import Libraries.roomCoroutines
import TestLibraries.androidEspresso
import TestLibraries.jUnit
import TestLibraries.jUnitAndroid

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    Configurations.also {
        compileSdk = CompileSdk
        defaultConfig {
            minSdk = Configurations.MinSdk
            targetSdk = Configurations.TargetSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    Libraries.also {
        implementation(room)
        implementation(roomCompiler)
        implementation(roomCoroutines)
        implementation(gson)
        implementation(core)
        implementation(appCompat)
    }

    TestLibraries.also {
        testImplementation(jUnit)
        androidTestImplementation(jUnitAndroid)
        androidTestImplementation(androidEspresso)
    }
}
