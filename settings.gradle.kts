@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RedBlackRepeat"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":data:player")
include(":data:preferences")
include(":data:table")
include(":core:database")
include(":core:designsystem")
include(":core:ui")
include(":feature:frame")
include(":feature:playerlist")
include(":feature:settings")
include(":feature:start")
