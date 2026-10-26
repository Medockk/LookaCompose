plugins {
    id("com.s.looka.android.library")
}

android {
    namespace = "com.s.looka.core.common"
}

dependencies {
    compileOnly(libs.androidx.lifecycle.viewmodel)
}