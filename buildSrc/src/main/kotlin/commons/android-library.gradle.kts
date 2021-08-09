package commons

import build.AndroidSDK
import build.App
import dependency.Libs

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}
android {
    compileSdk= AndroidSDK.COMPILE_SDK_VERSION
    buildToolsVersion = AndroidSDK.BUILD_VERSION_TOOLS
    
    defaultConfig {
        minSdk = AndroidSDK.MIN_SDK_VERSION
        targetSdk = AndroidSDK.TARGET_SDK_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libs.KOTLIN)
    
    kapt(dependency.AnnotationProcessorsDependencies.DAGGER_COMPILER)
    kapt(dependency.AnnotationProcessorsDependencies.DAGGER_ANDROID_COMPILER)
    kapt(dependency.AnnotationProcessorsDependencies.ROOM_COMPILER)
}
