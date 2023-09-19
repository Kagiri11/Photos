plugins {
    id(BuildPlugins.androidLib)
    id(BuildPlugins.kotlinAndroid)
}

android {
    compileSdk = 32
    defaultConfig {
        minSdk = Configurations.MinSdk
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

    /*testOptions{
        unitTests.all {
            if(it.name == "testDebugUnitTest"){
                it.extensions.configure(kotlinx.kover.api.KoverTaskExtension::class){

                }
            }
        }
    }*/
}

dependencies {

    api(project(Modules.NETWORK))
    api(project(Modules.LOCAL))
    implementation(project(Modules.DOMAIN))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.koin.core1)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)

    testImplementation(libs.google.truth)
    testImplementation(libs.junit1)
    testImplementation(libs.mockk)

}
