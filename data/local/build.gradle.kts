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
    id(BuildPlugins.androidLib)
    id(BuildPlugins.kotlinAndroid)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
