import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper

class KotlinCompilerOptionsConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            if (
                !pluginManager.hasPlugin("com.android.library") &&
                !pluginManager.hasPlugin("com.android.application")
            ) {
                pluginManager.apply("org.jetbrains.kotlin.jvm")
            }

            // setup for all kotlin modules (JVM, Android)
            plugins.withType<KotlinBasePluginWrapper> {
                extensions.configure(KotlinJvmProjectExtension::class.java) {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                    }
                }
            }

            // setup for pure Kotlin modules
            plugins.withType<JavaPlugin> {
                extensions.configure(JavaPluginExtension::class.java) {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            // setup for Android modules
            plugins.withType<AndroidBasePlugin> {
                extensions.configure(CommonExtension::class.java) {
                    with(compileOptions) {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }
                }
            }
        }
    }
}