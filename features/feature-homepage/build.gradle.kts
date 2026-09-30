plugins {
    id("com.s.looka.android.feature")
    id("com.s.looka.android.serialization")
}

android {
    namespace = "com.s.looka.features.feature_homepage"
}

dependencies {
    implementation(project(":domain:clothes:category"))
}