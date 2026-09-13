import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

group = "com.s.looka.buildlogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)

    compileOnly(libs.hilt.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidCompose") {
            id = "com.s.looka.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("hilt") {
            id = "com.s.looka.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.s.looka.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "com.s.looka.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidApplication") {
            id = "com.s.looka.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("kotlinxSerialization") {
            id = "com.s.looka.android.serialization"
            implementationClass = "KotlinxSerializationConventionPlugin"
        }
    }
}