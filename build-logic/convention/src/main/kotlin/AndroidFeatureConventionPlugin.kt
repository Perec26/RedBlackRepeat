import com.pepekprodakshn.convention.findLibraryString
import com.pepekprodakshn.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "redblackrepeat.android.library.compose")
            apply(plugin = "redblackrepeat.hilt")
            apply(plugin = "redblackrepeat.unittest")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                add("implementation", libs.findLibraryString("androidx.hilt.navigation.compose"))
                add("debugImplementation", libs.findLibraryString("androidx.ui.tooling"))
                add("implementation", libs.findLibraryString("kotlinx.serialization.json"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:navigation"))
                add("implementation", project(":core:ui"))

            }
        }
    }
}