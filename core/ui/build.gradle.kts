plugins {
    alias(libs.plugins.redblackrepeat.android.library)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.pepekprodakshn.ui"
}

dependencies {
    implementation(libs.androidx.lifecycle.viewModelCompose)
}