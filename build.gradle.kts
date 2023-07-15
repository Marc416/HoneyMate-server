println(project.name)
plugins {
    base
    kotlin("jvm") version "1.6.21"
}

allprojects {
    group = "com.cheongseolmo.convenience"
    version = "1.0-SNAPSHOT"
    repositories {
        mavenCentral()
    }
}