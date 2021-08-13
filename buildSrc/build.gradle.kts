plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
    maven(url = "https://plugins.gradle.org/m2/")
}
kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
}
