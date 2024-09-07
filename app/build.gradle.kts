import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.redblackrepeat.hilt)
    alias(libs.plugins.compose.compiler)
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

android {
    namespace = "com.pepekprodakshn.redblackrepeat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pepekprodakshn.redblackrepeat"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "0.1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
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

composeCompiler {
    enableStrongSkippingMode = true
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

ktlint {
    android = true
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // kotest
    testImplementation(libs.kotest.runner.junit5)
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
