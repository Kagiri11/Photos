import Configurations.MinSdk
import Configurations.TargetSdk

plugins {
    id(BuildPlugins.androidLib)
    id(BuildPlugins.kotlinAndroid)
}

android {
    compileSdk = Configurations.CompileSdk

    defaultConfig {
        Configurations.also {
            minSdk = MinSdk
            targetSdk = TargetSdk
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
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

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)

    // Modules
    implementation(project(Modules.DOMAIN))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.facebook.fresco)
    implementation(libs.google.accompanist.systemuicontroller)
    implementation(libs.google.accompanist.placeholder)
    implementation(libs.google.accompanist.permissions)
    implementation(libs.google.material)
    implementation(libs.google.android.play.app.update)
    implementation(libs.google.android.play.app.update.ktx)
    implementation(libs.google.accompanist.pager)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android1)
    implementation(libs.koin.core1)
    implementation(libs.skydoves.landscapist.fresco)

    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(libs.junit1)

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.compose.ui.test.manifest)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.koin.androidx.compose)
    androidTestImplementation(composeBom)

}
