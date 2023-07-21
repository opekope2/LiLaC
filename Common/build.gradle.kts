plugins {
    id("java-library")
}

base { archivesName.set(project.extra["archives_base_name"] as String) }

version = project.extra["mod_version"] as String
group = project.extra["maven_group"] as String

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net") { name = "Fabric" }
    maven("https://libraries.minecraft.net") { name = "Mojang" }
}

dependencies {
    implementation("com.mojang", "datafixerupper", "6.0.8")
    implementation("org.jetbrains", "annotations", "24.0.0")

    implementation(project(":Api", configuration = "namedElements"))
}

tasks {
    val javaVersion = JavaVersion.toVersion((project.extra["java_version"] as String).toInt())

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release.set(javaVersion.toString().toInt())
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(javaVersion.toString()))
        }
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        withSourcesJar()
    }
}
