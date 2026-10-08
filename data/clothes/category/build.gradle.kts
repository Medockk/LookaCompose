plugins {
    id("com.s.looka.android.library")
    id("com.s.looka.android.hilt")
    id("com.s.looka.android.serialization")
}

android {
    namespace = "com.s.looka.data.clothes.category"
}

dependencies {
    implementation(project(":core:network"))
    api(project(":domain:clothes:category"))
}