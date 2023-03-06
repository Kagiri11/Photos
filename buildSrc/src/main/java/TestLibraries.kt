object TestLibraries {
    // Storage Testing
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"

    // jUnit
    const val jUnit = "junit:junit:${TestVersions.jUnit}"
    const val jUnitAndroid = "androidx.test.ext:junit:${TestVersions.jUnitAndroid}"

    // espresso
    const val androidEspresso =
        "androidx.test.espresso:espresso-core:${TestVersions.espressoAndroid}"

    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val androidArchCoreTesting =
        "android.arch.core:core-testing:${Versions.androidArchCoreTesting}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.test_coroutines_version}"
    const val mockK = "io.mockk:mockk:${Versions.mockK}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
}
