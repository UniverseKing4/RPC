
plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
}
android {
    namespace = "com.rpc.color"
}
dependencies {
    api(libs.compose.ui)
    api(libs.core.ktx)
    api(libs.material3)
}