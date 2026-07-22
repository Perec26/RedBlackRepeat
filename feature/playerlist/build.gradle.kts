plugins {
    alias(libs.plugins.redblackrepeat.android.feature)
}

android {
    namespace = "com.pepekprodakshn.playerlist"
}

dependencies {
    implementation(projects.data.player)
}
