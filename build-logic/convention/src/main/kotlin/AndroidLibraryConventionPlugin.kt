import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = libs.findVersion("compileSdk").get()
                    .requiredVersion.toInt()

                defaultConfig {
                    minSdk = libs.findVersion("minSdk").get()
                        .requiredVersion.toInt()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFile("consumer-rules.pro")
                }

                compileOptions {
                    targetCompatibility = JavaVersion.VERSION_11
                    sourceCompatibility = JavaVersion.VERSION_11
                }
            }
        }
    }
}