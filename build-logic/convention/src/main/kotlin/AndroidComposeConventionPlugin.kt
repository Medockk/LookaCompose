import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // apply kotlin-compose plugin
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.getByType<CommonExtension>().apply {
                // applying compose
                buildFeatures.compose = true
            }

            dependencies {
                val composeBom = libs.findLibrary("androidx-compose-bom").get()
                add(Dependencies.implementation, platform(composeBom))

                add(Dependencies.implementation, libs.findLibrary("androidx-core-ktx").get())

                add(Dependencies.implementation, libs.findLibrary("androidx-compose-material3").get())
                add(Dependencies.implementation, libs.findLibrary("material").get())

                add(Dependencies.implementation, libs.findLibrary("androidx-appcompat").get())
                add(Dependencies.implementation, libs.findLibrary("androidx-compose-ui").get())
                add(Dependencies.implementation, libs.findLibrary("androidx-compose-ui-graphics").get())
                add(Dependencies.implementation, libs.findLibrary("androidx-compose-ui-tooling-preview").get())

                add(Dependencies.implementation, libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
            }
        }
    }
}