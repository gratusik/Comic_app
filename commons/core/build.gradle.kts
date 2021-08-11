import dependency.Libs

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}
android {
    compileSdk = build.AndroidSDK.COMPILE_SDK_VERSION
    buildToolsVersion = build.AndroidSDK.BUILD_VERSION_TOOLS

    defaultConfig {
        minSdk = build.AndroidSDK.MIN_SDK_VERSION
        targetSdk = build.AndroidSDK.TARGET_SDK_VERSION
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
    implementation(dependency.Libs.KOTLIN)
    implementation(Libs.COROUTINES_CORE)
    implementation(Libs.COROUTINES_ANDROID)
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.ROOM_KTX)
    implementation(Libs.RETROFIT)
    implementation(Libs.GSON_CONVERTER)
    implementation(Libs.LOGGING_INTERCEPTOR)
    implementation(Libs.OK_HTTP)
    implementation(Libs.SCALAR_CONVERTER)
    implementation(dependency.Libs.DAGGER)
    implementation(dependency.Libs.DAGGER_ANDROID)
    implementation(dependency.Libs.FRESCO_IMAGE_VIEWER) {
        implementation(dependency.Libs.FRESCO)
    }
    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.0.0")
    kapt(dependency.AnnotationProcessorsDependencies.DAGGER_COMPILER)
    kapt(dependency.AnnotationProcessorsDependencies.DAGGER_ANDROID_COMPILER)
}
