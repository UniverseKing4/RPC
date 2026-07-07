plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
    id ("rpc.android.feature")
    id ("rpc.android.hilt")
}

android {
    namespace = "com.my.rpc.feature_profile"
}

dependencies {
    implementation (projects.theme)
    implementation (projects.gateway)
    implementation (libs.coil)
    implementation (libs.activity.compose)
    implementation (libs.kotlinx.serialization.json)
}