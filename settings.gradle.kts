rootProject.name = "droidkaigi-sample"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("deps") {
            from(files("config/deps.versions.toml"))
        }
    }
}

include(":androidApp")
include(":shared")