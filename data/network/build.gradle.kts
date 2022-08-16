import Configurations.CompileSdk
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
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.retrofit)
    implementation(Libraries.gson)
    implementation(Libraries.core)
    implementation(Libraries.appCompat)
    implementation(Libraries.sandwich)
    implementation(Libraries.koinCore)
    implementation(Libraries.okHttpLoggingInterceptor)

    TestLibraries.also {
        testImplementation(jUnit)
        androidTestImplementation(jUnitAndroid)
    }
}
