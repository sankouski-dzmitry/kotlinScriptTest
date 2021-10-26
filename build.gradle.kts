plugins {
    kotlin("jvm") version "1.5.31"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val kotlinVersion = "1.5.31"
val testngVersion = "7.4.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.testng", "testng", testngVersion)
    implementation("org.jetbrains.kotlin", "kotlin-reflect", kotlinVersion)
    implementation("org.jetbrains.kotlin", "kotlin-scripting-jvm", kotlinVersion)
    implementation("org.jetbrains.kotlin", "kotlin-script-util", kotlinVersion)
    implementation("org.jetbrains.kotlin", "kotlin-scripting-jvm-host", kotlinVersion)
    implementation("org.jetbrains.kotlin", "kotlin-compiler-runner", kotlinVersion)
    implementation("org.jetbrains.kotlin", "kotlin-daemon-client", kotlinVersion)
    implementation("org.jetbrains.kotlin", "kotlin-daemon", kotlinVersion)
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core-jvm", "1.5.2")
}

tasks.test {
    useTestNG {

    }
    testLogging.showStandardStreams = true
    testLogging {
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showStackTraces = true
        showExceptions = true
        showCauses = true
        showStandardStreams = true
        events(
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.STARTED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
        )
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}