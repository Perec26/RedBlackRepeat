plugins {
    alias(libs.plugins.redblackrepeat.android.aplication)
    alias(libs.plugins.redblackrepeat.android.aplication.compose)
}

android {
    namespace = "com.pepekprodakshn.redblackrepeat"

    defaultConfig {
        applicationId = "com.pepekprodakshn.redblackrepeat"
        versionCode = 3
        versionName = "0.2.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions.unitTests.all {
        it.useJUnitPlatform()
    }
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.kotest)
    testImplementation(libs.junit.jupiter)
    // Project
    implementation(projects.core.database)
    implementation(projects.data.player)
    implementation(projects.data.table)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.core.ui)
    implementation(projects.feature.frame)
    implementation(projects.feature.playerlist)
    implementation(projects.feature.start)
}
