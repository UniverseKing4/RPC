plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
    id ("rpc.android.feature")
    id ("rpc.android.hilt")
}

android {
    namespace = "com.my.rpc.feature_logs"
}

dependencies {
    implementation (projects.theme)
    implementation(libs.activity.compose)
}