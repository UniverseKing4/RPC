plugins {
    id ("rpc.android.library")
    id ("rpc.android.feature")
    id ("rpc.android.hilt")
}

android {
    namespace = "com.my.rpc.feature_rpc_base"
}

dependencies {
    implementation (libs.blankj.utilcodex)
    implementation(libs.androidx.material)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil)
}