object TestLibraries {
    // Storage Testing
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"

    // jUnit
    const val jUnit = "junit:junit:${TestVersions.jUnit}"
    const val jUnitAndroid = "androidx.test.ext:junit:${TestVersions.jUnitAndroid}"

    // espresso
    const val androidEspresso =
        "androidx.test.espresso:espresso-core:${TestVersions.espressoAndroid}"
    // mockK
    const val mockK = "io.mockk:mockk:${TestVersions.mockK}"
    // truth
    const val truth = "com.google.truth:truth:${TestVersions.truth}"

    // compose testing
    const val jUnitComposeTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    // mockWebServer
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.10.0"
}
