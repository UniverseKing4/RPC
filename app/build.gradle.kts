@file:Suppress("UnstableApiUsage")

plugins {
    id("rpc.android.application")
    id("rpc.android.application.compose")
    id("rpc.android.hilt")
}

android {
    namespace = "com.my.rpc"

    defaultConfig {
        applicationId = "com.my.rpc"
        versionCode = libs.versions.version.code.get().toInt()
        versionName = libs.versions.version.name.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
        }
    }

    packagingOptions.resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")

    // Disables dependency metadata when building APKs.
    dependenciesInfo {
        // Disables dependency metadata when building APKs.
        // This is for the signed .apk that we post to GitHub releases
        includeInApk = false
        // Disables dependency metadata when building Android App Bundles.
        // This is for the Google Play Store if we ever decide to publish there
        includeInBundle = true
    }
}
dependencies {
    implementation (projects.domain)
    implementation (projects.theme)
    implementation (projects.featureStartup)
    implementation (projects.featureCrashHandler)
    implementation (projects.featureProfile)
    implementation (projects.featureAbout)
    implementation (projects.featureSettings)
    implementation (projects.featureLogs)
    implementation (projects.featureRpcBase)
    implementation (projects.featureExperimentalRpc)
    implementation (projects.featureHome)
    implementation (projects.common.preference)
    implementation (projects.common.navigation)

    // Extras
    implementation (libs.app.compat)
    implementation (libs.accompanist.navigation.animation)
    implementation (libs.kotlinx.serialization.json)


    // Material
    implementation (libs.material3)
    implementation (libs.androidx.material)
    implementation (libs.material3.windows.size)
}