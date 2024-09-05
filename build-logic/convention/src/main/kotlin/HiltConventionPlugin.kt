import com.pepekprodakshn.convention.findLibraryString
import com.pepekprodakshn.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.google.devtools.ksp")
            }
            dependencies {
                add("ksp", libs.findLibraryString("hilt.android.compiler"))
                add("implementation", libs.findLibraryString("hilt.android"))
            }
        }
    }
}