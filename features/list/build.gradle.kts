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
    implementation(project(build.Modules.Common.CORE))
    implementation(project(build.Modules.Common.UI))
    implementation(dependency.Libs.KOTLIN)
    implementation(dependency.Libs.CORE_KTX)
    implementation(dependency.Libs.APPCOMPAT)
    implementation(dependency.Libs.CONSTRAINT_LAYOUT)
    implementation(dependency.Libs.MATERIAL)
    implementation(dependency.Libs.DAGGER)
    implementation(dependency.Libs.DAGGER_ANDROID)
    implementation(dependency.Libs.SPEED_DIAL)
    implementation(dependency.Libs.NAVIGATION_FRAGMENT)
    implementation(dependency.Libs.NAVIGATION_UI)
    implementation(dependency.Libs.LIFECYCLE_LIVEDATA)
    implementation(dependency.Libs.RETROFIT)
    implementation(dependency.Libs.GSON_CONVERTER)
    implementation(dependency.Libs.LOGGING_INTERCEPTOR)
    implementation(dependency.Libs.OK_HTTP)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(dependency.AnnotationProcessorsDependencies.DAGGER_COMPILER)
    kapt(dependency.AnnotationProcessorsDependencies.DAGGER_ANDROID_COMPILER)
}
