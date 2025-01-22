/*
 * Project: Music Discord Bot
 * Contribution: Reim
 * File: build.gradle.kts
 */

plugins {
    kotlin("jvm") version "2.1.0"
}

group = "MusicDiscordBot"
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("ch.qos.logback:logback-classic:1.5.16")
    implementation("net.dv8tion:JDA:5.2.1")
}

sourceSets {
    main { kotlin.srcDirs("src/main/kotlin") }
}

kotlin {
    jvmToolchain(17)
}
