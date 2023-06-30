import Configurations.ApplicationId
import Configurations.MinSdk
import Configurations.TargetSdk
import Configurations.VersionCode
import Configurations.VersionName

plugins {
    id(libs.plugins.androidApp.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    alias(libs.plugins.googleSecrets)
}

android {
    compileSdk = Configurations.CompileSdk
    defaultConfig {
        Configurations.also {
            applicationId = libs.versions.applicationId.get()
            minSdk = MinSdk
            targetSdk = TargetSdk
            versionCode = VersionCode
            versionName = VersionName
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("benchmark") {
            isDebuggable = true
            initWith(getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.profileinstaller:profileinstaller:1.2.0")

    // Modules
    implementation(project(libs.versions.domain.get()))
    implementation(project(libs.versions.repository.get()))
    implementation(project(libs.versions.presentation.get()))

    // Libs
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)

    implementation(libs.google.accompanist.systemuicontroller)
    implementation(libs.google.android.play.app.update)
    implementation(libs.google.android.play.app.update.ktx)

    implementation(libs.koin.androidx.compose)

    implementation(libs.viewModel)
    implementation(libs.coil)

    implementation(libs.fresco)



    implementation("com.launchdarkly:launchdarkly-android-client-sdk:4.2.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.jUnitAndroid)
    androidTestImplementation(libs.androidEspresso)
}
