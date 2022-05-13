import Libraries.coroutinesCore
import Libraries.koinCore

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    Libraries.also {
        implementation(coroutinesCore)
        implementation(koinCore)
    }
}
