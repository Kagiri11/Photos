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
include(":app")
include(":domain")
include(":data:network")
include(":data:local")
include(":data:repository")
