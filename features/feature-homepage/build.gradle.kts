plugins {
    alias(libs.plugins.android.library)
    alias { libs.plugins.kotlin.compose }
}

android {
    namespace = "com.s.looka.features.feature_homepage"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}