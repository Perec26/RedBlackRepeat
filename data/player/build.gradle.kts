plugins {
    alias(libs.plugins.redblackrepeat.android.library)
    alias(libs.plugins.redblackrepeat.hilt)
}

android {
    namespace = "com.pepekprodakshn.player"
}

dependencies {
    implementation(projects.core.database)
}