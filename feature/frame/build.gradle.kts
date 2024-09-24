plugins {
    alias(libs.plugins.redblackrepeat.android.feature)
}

android {
    namespace = "com.pepekprodakshn.frame"
}

dependencies {
    implementation(projects.data.table)
    implementation(projects.data.player)
}