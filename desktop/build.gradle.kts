plugins{
    kotlin("jvm")
  id("org.jetbrains.compose")
}

compose.desktop{
    application{
        mainClass = "MainKt"
    }
}

dependencies{
    implementation(compose.desktop.currentOs)
    implementation(project(":shared"))
}