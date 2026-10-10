plugins {
    id("com.s.looka.android.library")
}

android {
    namespace = "com.s.looka.core.testing"
}

dependencies {
    implementation(libs.junit)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.junit)

    implementation(libs.hilt.android.testing)
}