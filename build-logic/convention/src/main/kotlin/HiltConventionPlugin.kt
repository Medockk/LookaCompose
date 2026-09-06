import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                withPlugin("com.android.application") {
                    apply("com.google.dagger.hilt.android")
                }
                withPlugin("com.android.library") {
                    apply("com.google.dagger.hilt.android")
                }
            }

            dependencies {
                add(Dependencies.implementation, libs.findLibrary("hilt-android").get())
                add(Dependencies.ksp, libs.findLibrary("hilt-compiler").get())
            }
        }
    }
}