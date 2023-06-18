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
            applicationId = ApplicationId
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
        kotlinCompilerExtensionVersion = Versions.compose
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
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.REPOSITORY))
    implementation(project(Modules.PRESENTATION))

    // Libs
    implementation(libs.core)
    implementation(libs.composeUi)
    implementation(libs.constraintLayout)
    implementation(libs.composeMaterial)
    implementation(libs.preview)
    implementation(libs.koinCompose)
    implementation(libs.liveData)
    implementation(libs.viewModel)
    implementation(libs.koinCompose)
    implementation(libs.coil)
    implementation(libs.paging)
    implementation(libs.pagingCompose)
    implementation(libs.systemUiController)
    implementation(libs.composeNavigation)
    implementation(libs.composeMaterial)
    implementation(libs.splashScreen)
    implementation(libs.preview)
    implementation(libs.fresco)
    implementation(libs.lifeCycle)
    implementation(libs.activity)
    implementation(libs.playUpdate)
    implementation(libs.playUpdateKtx)
    implementation("com.launchdarkly:launchdarkly-android-client-sdk:4.2.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.jUnitAndroid)
    androidTestImplementation(libs.androidEspresso)
}
