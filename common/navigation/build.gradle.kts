plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
}

android {
    namespace = "com.my.rpc.navigation"
}

dependencies {
    implementation (libs.compose.ui)
    implementation (libs.compose.navigation)
    implementation (libs.accompanist.navigation.animation)
}