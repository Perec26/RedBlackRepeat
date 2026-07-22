plugins {
    alias(libs.plugins.redblackrepeat.android.library)
    alias(libs.plugins.redblackrepeat.hilt)
}

android {
    namespace = "com.pepekprodakshn.preferences"
}

dependencies {
    implementation(libs.datastore)
}
