plugins {
    id("com.s.looka.android.library")
    id("com.s.looka.android.compose")
}

android {
    namespace = "com.s.looka.core.ui"
}

dependencies {
    implementation(project(":core:common"))
}