import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinModuleConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.s.looka.kotlin.compiler")

            dependencies {
                add(Dependencies.implementation, libs.findLibrary("kotlinx-coroutines-core").get())
                add(Dependencies.implementation, project(":core:common"))
            }
        }
    }
}