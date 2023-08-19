plugins {
    id("fabric-loom")
}

base {
    archivesName.set(project.extra["archives_base_name"] as String)
}

version = project.extra["mod_version"] as String
group = project.extra["maven_group"] as String

repositories {}

loom {
    createRemapConfigurations(sourceSets["test"])
}

dependencies {
    minecraft("com.mojang", "minecraft", project.extra["minecraft_version"] as String)
    mappings("net.fabricmc", "yarn", project.extra["yarn_mappings"] as String, classifier = "v2")
    modApi("net.fabricmc", "fabric-loader", project.extra["loader_version"] as String)
    modImplementation(fabricApi.module("fabric-lifecycle-events-v1", project.extra["fabric_version"] as String))

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.9.3")
    testRuntimeOnly("org.junit.platform", "junit-platform-launcher")

    testImplementation("net.fabricmc", "fabric-loader-junit", project.extra["loader_version"] as String)
    testRuntimeOnly(project(":1.19.4", configuration = "namedElements"))

    "modTestRuntimeOnly"(fabricApi.module("fabric-resource-loader-v0", project.extra["fabric_version"] as String))
    "modTestRuntimeOnly"(
        "net.fabricmc", "fabric-language-kotlin", project.extra["fabric_language_kotlin_version"] as String
    )
}

val packageTestMod by tasks.creating(Jar::class) {
    from(sourceSets["test"].resources)
    include("fabric.mod.json")
    archiveClassifier.set("testmod")
    destinationDirectory.set(project.buildDir.resolve("testlibs"))
}

tasks {
    val javaVersion = JavaVersion.toVersion((project.extra["java_version"] as String).toInt())

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release.set(javaVersion.toString().toInt())
    }

    jar {
        from(rootDir.resolve("LICENSE")) {
            rename { "${it}_${base.archivesName.get()}" }
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

    test {
        dependsOn(packageTestMod)

        useJUnitPlatform()
        workingDir(project.file("run"))
        classpath += packageTestMod.outputs.files

        doFirst {
            mkdir(workingDir)
        }

        testLogging {
            events("PASSED", "SKIPPED", "FAILED")
        }
    }
}
