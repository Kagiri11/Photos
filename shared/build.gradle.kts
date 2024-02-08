plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    jvm()
    androidTarget()

    sourceSets{

        androidMain.dependencies {
            api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
        }

        jvmMain.dependencies {

        }

        commonMain.dependencies {
            implementation(compose.material)
            implementation(libs.koin.core)
            implementation(libs.kotlin.coroutines.core)
            implementation("media.kamel:kamel-image:0.9.1")
            implementation("app.cash.paging:paging-common:3.3.0-alpha02-0.4.0")
            implementation("app.cash.paging:paging-compose-common:3.3.0-alpha02-0.4.0")
            implementation("androidx.datastore:datastore-preferences-core:1.1.0-alpha07")
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.logging)
            implementation(libs.androidx.datastore)
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