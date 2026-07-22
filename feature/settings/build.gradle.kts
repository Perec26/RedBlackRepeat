plugins {
    alias(libs.plugins.redblackrepeat.android.feature)
}

android {
    namespace = "com.pepekprodakshn.settings"
}

dependencies {
    implementation(projects.data.preferences)
}
