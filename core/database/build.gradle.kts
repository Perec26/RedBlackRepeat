plugins {
    alias(libs.plugins.redblackrepeat.android.library)
    alias(libs.plugins.redblackrepeat.hilt)
}

android {
    namespace = "com.pepekprodakshn.database"
}

dependencies {
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
}