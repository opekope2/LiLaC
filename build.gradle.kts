plugins {
    kotlin("jvm") apply false
    id("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
}

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka", "dokka-base", project.extra["dokka_version"] as String)
    }
}

subprojects {
    configurations.all {
        resolutionStrategy {
            val loader_version: String by project.extra
            force("net.fabricmc:fabric-loader:$loader_version")
        }
    }
}
