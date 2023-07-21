plugins {
    kotlin("jvm") apply false
}

subprojects {
    configurations.all {
        resolutionStrategy {
            val loader_version: String by project.extra
            force("net.fabricmc:fabric-loader:$loader_version")
        }
    }
}
