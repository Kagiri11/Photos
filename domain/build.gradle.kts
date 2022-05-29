import Libraries.coroutinesCore
import Libraries.koinCore
import Libraries.paging

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
        implementation(paging)
    }
}
