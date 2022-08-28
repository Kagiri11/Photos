plugins {
    id(BuildPlugins.androidLib)
    id(BuildPlugins.kotlinAndroid)
}

android {
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.koinCore)
    implementation(Libraries.core)
    implementation(Libraries.appCompat)
    implementation(Libraries.sandwich)
    implementation(Libraries.paging)
    implementation(project(mapOf("path" to ":data:network")))
    implementation(project(mapOf("path" to ":domain")))
    testImplementation(TestLibraries.jUnit)
    testImplementation(TestLibraries.mockK)
    testImplementation(TestLibraries.truth)
}
