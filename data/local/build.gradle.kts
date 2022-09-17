import Configurations.CompileSdk
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
    implementation(Libraries.koinCore)
    api(Libraries.preferenceDataStore)
    implementation(Libraries.gson)

    TestLibraries.also {
        testImplementation(jUnit)
        androidTestImplementation(jUnitAndroid)
    }
}
