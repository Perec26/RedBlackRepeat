plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ktlint.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("hilt") {
            id = "redblackrepeat.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidApplication") {
            id = "redblackrepeat.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "redblackrepeat.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("ktlint") {
            id = "redblackrepeat.ktlint"
            implementationClass = "KtlintConventionPlugin"
        }
        register("androidLibrary") {
            id = "redblackrepeat.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "redblackrepeat.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "redblackrepeat.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
    }
}