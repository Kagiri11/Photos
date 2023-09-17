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

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore)
    implementation(libs.koin.core1)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.square.retrofit2.retrofit)
    implementation(libs.square.retrofit2.converter.gson)
    implementation(libs.square.retrofit2.okhttp3.logging.interceptor)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)

    testImplementation(libs.junit1)

    androidTestImplementation(libs.androidx.test.ext.junit)
}
