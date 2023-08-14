import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

println(project.name)

plugins {
    kotlin("jvm") version "1.6.21" // or "java-library"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("kapt")
    `java-library`
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("jakarta.persistence:jakarta.persistence-api:2.2.3")
    implementation("jakarta.transaction:jakarta.transaction-api:1.3.3")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.4")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.3.50")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("io.mockk:mockk:1.12.4")
}
