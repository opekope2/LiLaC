plugins {
    id("fabric-loom")
    kotlin("jvm")
}

evaluationDependsOn(":Api")
evaluationDependsOn(":Common")

base {
    archivesName.set(project.extra["archives_base_name"] as String)
}

version = project.extra["mod_version"] as String
group = project.extra["maven_group"] as String

repositories {}

dependencies {
    minecraft("com.mojang", "minecraft", project.extra["minecraft_version"] as String)
    mappings("net.fabricmc", "yarn", project.extra["yarn_mappings"] as String, null, "v2")
    modImplementation("net.fabricmc", "fabric-loader", project.extra["loader_version"] as String)
    modImplementation(fabricApi.module("fabric-lifecycle-events-v1", project.extra["fabric_version"] as String))
    modImplementation(fabricApi.module("fabric-resource-loader-v0", project.extra["fabric_version"] as String))
    modImplementation(
        "net.fabricmc", "fabric-language-kotlin", project.extra["fabric_language_kotlin_version"] as String
    )

    implementation(project(":Api", configuration = "namedElements"))
    implementation(project(":Common"))
}

tasks {
    val javaVersion = JavaVersion.toVersion((project.extra["java_version"] as String).toInt())

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release.set(javaVersion.toString().toInt())
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = javaVersion.toString()
        }
    }

    jar {
        from(File(rootDir, "LICENSE")) {
            rename { "${it}_${base.archivesName.get()}" }
        }
        from(project(":Api").sourceSets["main"].output) {
            include("**/*.class")
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }
        from(project(":Common").sourceSets["main"].output) {
            include("**/*.class")
        }
    }

    processResources {
        filesMatching("fabric.mod.json") {
            expand(
                mutableMapOf(
                    "version" to project.extra["mod_version"] as String,
                    "fabricloader" to project.extra["loader_version"] as String,
                    "fabric_lifecycle_events_v1" to fabricApi.moduleVersion(
                        "fabric-lifecycle-events-v1",
                        project.extra["fabric_version"] as String
                    ),
                    "fabric_resource_loader_v0" to fabricApi.moduleVersion(
                        "fabric-resource-loader-v0",
                        project.extra["fabric_version"] as String
                    ),
                    "fabric_language_kotlin" to project.extra["fabric_language_kotlin_version"] as String,
                    "minecraft" to project.extra["minecraft_version"] as String,
                    "java" to project.extra["java_version"] as String
                )
            )
        }
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