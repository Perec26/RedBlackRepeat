plugins {
    alias(libs.plugins.redblackrepeat.android.library.compose)
    alias(libs.plugins.redblackrepeat.hilt)
}

android {
    namespace = "com.pepekprodakshn.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}