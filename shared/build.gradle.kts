plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    sourceSets{
        commonMain.dependencies {
            implementation("androidx.datastore:datastore-preferences-core:1.1.0-alpha07")
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.logging)
        }
    }
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}

android {
    namespace = "com.cmaina.hellomultiplatform"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies{
    implementation(libs.koin.core1)
    implementation(libs.kotlin.coroutines.core)
    implementation("app.cash.paging:paging-common:3.3.0-alpha02-0.4.0")
}