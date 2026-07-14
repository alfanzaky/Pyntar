pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pyntar"

include(":app")
include(":domain")
include(":data")

include(":core:common")
include(":core:ui")
include(":core:database")
include(":core:datastore")

include(":feature:auth")
include(":feature:task")
include(":feature:dashboard")
include(":feature:analytics")
include(":feature:calendar")
