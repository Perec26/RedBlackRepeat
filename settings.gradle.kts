pluginManagement {
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
include(":app")
include(":core:database")
include(":data:player")
include(":data:table")
include(":feature:frame")
include(":core:designsystem")
include(":core:navigation")
include(":core:ui")
include(":feature:playerlist")
