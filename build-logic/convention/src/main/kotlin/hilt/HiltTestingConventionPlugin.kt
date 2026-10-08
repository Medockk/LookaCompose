package hilt

import com.android.build.api.dsl.CommonExtension
import library
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltTestingConventionPlugin: Plugin<Project> {

    private val androidTestImplementation = "androidTestImplementation"
    private val kspAndroidTest = "kspAndroidTest"

    override fun apply(target: Project) {
        with(target) {
            // applying hilt dependencies
            pluginManager.apply("com.s.looka.android.hilt")

            extensions.configure<CommonExtension>("android") {
                defaultConfig.testInstrumentationRunner = "com.s.looka.core.testing.LookaHiltTestRunner"
            }

            dependencies {
                // to set visibility of LookaHiltTestRunner
                add(androidTestImplementation, project(":core:testing"))

                // dependencies for android android instrumental test
                add(androidTestImplementation, libs.library("hilt-android-testing"))
                add(kspAndroidTest, libs.library("hilt-compiler"))

                // standard androidx test implementation
                add(androidTestImplementation, libs.library("androidx-junit"))
                add(androidTestImplementation, libs.library("androidx-espresso-core"))
                add(androidTestImplementation, libs.library("androidx-test-runner"))
                add(androidTestImplementation, libs.library("kotlinx-coroutines-test"))
            }
        }
    }
}