plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
    id ("rpc.android.feature")
}

android {
    namespace = "com.my.rpc.feature_crash_handler"
}

dependencies {
    implementation (libs.activity.compose)
    implementation(libs.crashx)
    implementation (libs.blankj.utilcodex)
    implementation (libs.material.icons.extended)
    implementation (projects.theme)
}