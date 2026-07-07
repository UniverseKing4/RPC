plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
    id ("rpc.android.feature")
}

android {
    namespace = "com.my.rpc.feature_startup"
}

dependencies {
    implementation (libs.activity.compose)
    implementation (libs.material.icons.extended)
    implementation (libs.accompanist.permission)
    implementation (libs.kotlinx.serialization.json)
}