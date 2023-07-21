plugins {
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.serialization") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    //ktor
    implementation("io.ktor:ktor-client-core:2.3.2")
    implementation("io.ktor:ktor-client-cio:2.3.2")

    //ktor serialization
    implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
   // implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}