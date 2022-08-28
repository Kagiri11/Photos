object Libraries {
    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"

    // Storage
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomCoroutines = "androidx.room:room-ktx:${Versions.room}"

    // Material
    const val material = "com.google.android.material:material:${Versions.material}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"

    // Android
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    // Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    // Koin
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    // Sandwich
    const val sandwich = "com.github.skydoves:sandwich:${Versions.sandwich}"

    // Reflection
    const val reflection = "org.jetbrains.kotlin:kotlin-reflect:${Versions.reflection}"

    // Coil
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    // Paging
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.pagingCompose}"

    // Accompanist
    const val systemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.systemUIController}"
    const val placeholder = "com.google.accompanist:accompanist-placeholder:${Versions.placeholder}"
    const val permissions = "com.google.accompanist:accompanist-permissions:${Versions.permission}"

    // Navigation
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

    // blurHash
    const val blurHash = "xyz.belvi.blurHash:blurHash:${Versions.blurHash}"

    // composeUi
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"
    const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    // Splash screen
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"

    // LiveData
    const val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"

    // ViewModel
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModel}"

    // Lifecycle
    const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"

    // Activity
    const val activity = "androidx.activity:activity-compose:${Versions.activity}"

    // Datastore
    const val preferenceDataStore =
        "androidx.datastore:datastore-preferences:${Versions.preferenceDatastore}"

    // Landscapist
    const val landscapistGlide = "com.github.skydoves:landscapist-glide:${Versions.landscapist}"
    const val landscapistCoil = "com.github.skydoves:landscapist-coil:${Versions.landscapistCoil}"
}
