plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Coroutines for Flow in repository interfaces
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    // JSR-330 for @Inject annotations (provided by Hilt at runtime, needed at compile time)
    compileOnly("javax.inject:javax.inject:1")
}
