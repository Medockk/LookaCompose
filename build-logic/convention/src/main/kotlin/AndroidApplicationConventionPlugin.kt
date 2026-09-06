import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                val compileSdk = libs.findVersion("compileSdk")
                    .get().requiredVersion.toInt()
                this.compileSdk = compileSdk

                defaultConfig {
                    targetSdk = libs.findVersion("targetSdk")
                        .get().requiredVersion.toInt()

                    minSdk = libs.findVersion("minSdk")
                        .get().requiredVersion.toInt()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )

                        // for why by default optimization in release isn't enable?
//                        optimization {
//                            enable = false
//                        }
                    }
                }
            }
        }
    }
}