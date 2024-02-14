pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Fotos"
include(":androidApp")
include(":data:network")
include(":data:local")
include(":data:repository")
include(":domain")
include(":presentation")
include(":benchmark")
enableFeaturePreview("VERSION_CATALOGS")
include(":shared")
include(":desktop")
