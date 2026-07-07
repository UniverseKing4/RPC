plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
}

android {
    namespace = "com.my.rpc.preference"
}

dependencies {
    implementation(projects.color)
    implementation(projects.domain)
    implementation(projects.common.resources)
    implementation(libs.material3)
    implementation(libs.mmkv)
    implementation(libs.kotlinx.coroutine)
    implementation(libs.compose.ui)
    implementation(libs.kotlinx.serialization.json)
}