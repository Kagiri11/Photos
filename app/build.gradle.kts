import Configurations.ApplicationId
import Configurations.MinSdk
import Configurations.TargetSdk
import Configurations.VersionCode
import Configurations.VersionName

plugins {
    id(BuildPlugins.androidApp)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.googleSecrets) version Versions.googleGradleSecrets
}

val composeVersion = "1.2.0-beta01"

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Libraries.core)
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.REPOSITORY))
    implementation(project(Modules.PRESENTATION))
    implementation(Libraries.koinCore)
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.jUnitAndroid)
    androidTestImplementation(TestLibraries.androidEspresso)
}
