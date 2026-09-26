plugins {
    id("com.s.looka.android.library")
    id("com.s.looka.android.compose")
    id("com.s.looka.android.serialization")

    id("com.s.looka.android.hilt")
}

android {
    namespace = "com.s.looka.core.navigation"
}

dependencies {
    api(libs.androidx.navigation)
}