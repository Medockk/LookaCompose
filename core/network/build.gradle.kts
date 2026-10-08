plugins {
    id("com.s.looka.android.library")
    id("com.s.looka.android.hilt.testing")
}

android {
    namespace = "com.s.looka.core.network"
}

dependencies {
    implementation(libs.bundles.ktor)
}