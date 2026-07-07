plugins {
    id("rpc.android.library")
    id("rpc.android.library.compose")
}

android {
    namespace = "com.my.rpc.ui.components"
}

dependencies {
    implementation (projects.theme)
    implementation (libs.material3)
    implementation (projects.color)
    implementation (libs.android.svg)
    implementation (libs.coil)
    implementation (projects.common.resources)
    implementation (libs.material.icons.extended)
    implementation (libs.blankj.utilcodex)
}