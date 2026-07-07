plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
    id ("rpc.android.feature")
    id ("rpc.android.hilt")
}

android {
    namespace = "com.my.rpc.feature_about"

    defaultConfig {
        buildConfigField("String","VERSION_NAME", "\"${libs.versions.version.name.get()}\"")
    }
}

dependencies {
    implementation (libs.coil)
    implementation (libs.material.icons.extended)
    implementation (libs.activity.compose)
}
