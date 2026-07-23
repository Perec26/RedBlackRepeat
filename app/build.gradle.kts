plugins {
    alias(libs.plugins.redblackrepeat.android.aplication)
    alias(libs.plugins.redblackrepeat.android.aplication.compose)
}

android {
    namespace = "com.pepekprodakshn.redblackrepeat"

    defaultConfig {
        applicationId = "com.pepekprodakshn.redblackrepeat"
        versionCode = getBuildNumber()
        versionName = "0.2." + getBuildNumber()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
    implementation(projects.core.config)
    implementation(projects.core.designsystem)
    implementation(projects.data.player)
    implementation(projects.data.table)
    implementation(projects.data.preferences)
    implementation(projects.core.ui)
    implementation(projects.feature.frame)
    implementation(projects.feature.playerlist)
    implementation(projects.feature.start)
    implementation(projects.feature.settings)
}

fun getBuildNumber(): Int {
    if (project.hasProperty("buildNumber")) {
        val buildNumberString = project.property("buildNumber").toString()
        if (buildNumberString.all { it.isDigit() }) {
            return buildNumberString.toInt()
        }
    }
    return 4
}
