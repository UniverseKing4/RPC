plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
    id ("rpc.android.hilt")
    id ("rpc.android.feature")
}

android {
    namespace = "com.my.rpc.feature_rpc"
}

dependencies {
    implementation(libs.material.icons.extended)
    implementation(libs.activity.compose)
    implementation(libs.blankj.utilcodex)
    implementation(libs.coil)
    implementation(projects.featureRpcBase)
}
