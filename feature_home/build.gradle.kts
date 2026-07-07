plugins {
    id ("rpc.android.library")
    id ("rpc.android.library.compose")
    id ("rpc.android.feature")
}

android {
    namespace = "com.my.rpc.feature_home"
    defaultConfig {
        buildConfigField("String","VERSION_NAME", "\"${libs.versions.version.name.get()}\"")
    }
}

dependencies {
    implementation (libs.accompanist.flowLayout)
    implementation (libs.material.icons.extended)
    implementation (projects.featureRpcBase)
    implementation (projects.featureSettings)
    implementation (projects.common.navigation)
    implementation (libs.coil)
    implementation (libs.activity.compose)
}