import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.s.looka.android.library")
                apply("com.s.looka.android.compose")
                apply("com.s.looka.android.hilt")
            }

            dependencies {
                // coroutines
                add(Dependencies.implementation, libs.findLibrary("kotlinx-coroutines-core").get())
                add(Dependencies.implementation, libs.findLibrary("kotlinx-coroutines-android").get())

                // viewmodel
                add(Dependencies.implementation, libs.findLibrary("androidx-lifecycle-viewmodel").get())
                add(Dependencies.implementation, libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())

                // hiltViewModel
                add(Dependencies.implementation, libs.findLibrary("hilt-compose").get())

                // ComposeNavigation
                add(Dependencies.implementation, libs.findLibrary("androidx-navigation").get())
                add(Dependencies.implementation, project(":core:navigation"))
                add(Dependencies.implementation, project(":core:common"))
                add(Dependencies.implementation, project(":core:common-android"))
                add(Dependencies.implementation, project(":core:ui"))
            }
        }
    }
}