plugins {
    id("com.s.looka.android.application")
    id("com.s.looka.android.compose")

    id("com.s.looka.android.hilt")
    id("com.s.looka.android.serialization")
}

android {
    namespace = "com.s.looka.android"

    defaultConfig {
        applicationId = "com.s.looka.android"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))

    implementation(project(":features:feature-auth"))
    implementation(project(":features:feature-homepage"))
    implementation(project(":features:feature-search"))
    implementation(project(":features:feature-favorite"))
    implementation(project(":features:feature-cart"))
    implementation(project(":features:feature-account"))

    // HiltViewModel
    implementation(libs.hilt.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}