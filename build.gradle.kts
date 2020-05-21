group = "org.example"
version = "1.0-SNAPSHOT"

plugins {
    id("org.springframework.boot") version "2.2.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.70"
    kotlin("plugin.spring") version "1.3.70"
}

repositories {
    mavenCentral()
    //jcenter()
}

dependencies {
    implementation(
        fileTree("libs")
            .filter { it.name.matches(Regex("(.*).jar")) }
    )
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("io.arrow-kt:arrow-core:0.7.3")
    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("org.springframework.boot:spring-boot-starter-rsocket")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//    OCR start
    implementation("net.sourceforge.tess4j:tess4j:4.3.1") // update to last 4.5.1 be unsuccessful
    implementation("net.java.dev.jna:jna:5.2.0")
    implementation("net.java.dev.jna:jna-platform:5.2.0")
//    OCR end
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.4")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("spring.profiles.active", "integration-testing")
}