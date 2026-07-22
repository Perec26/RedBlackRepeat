@file:Suppress("unused")

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

class KtlintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            apply(plugin = "org.jlleitschuh.gradle.ktlint")

            extensions.configure<KtlintExtension> {
                debug.set(true)
                verbose.set(true)
                android.set(true)
                outputToConsole.set(true)
                reporters {
                    reporter(ReporterType.PLAIN)
                    reporter(ReporterType.CHECKSTYLE)
                    reporter(ReporterType.SARIF)
                }
                filter {
                    exclude { it.file.path.contains("generated") }
                    include("**/kotlin/**")
                }
            }
        }
    }
}