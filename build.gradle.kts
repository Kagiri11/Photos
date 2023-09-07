plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.libary) apply false
    alias(libs.plugins.kotlin.androids) apply false
    id(BuildPlugins.kotlinJVM) version "1.5.30" apply false
    id(BuildPlugins.kotlinKover) version "0.6.0"
    id("com.android.test") version "7.2.0" apply false
}

val ktlint by configurations.creating
val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("-F", "src/**/*.kt")
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {
    dependencies{
        classpath("com.google.gms:google-services:4.3.14")
    }
}

dependencies {
    ktlint("com.pinterest:ktlint:0.45.2") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}
