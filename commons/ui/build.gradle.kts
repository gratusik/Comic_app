plugins {
    id("commons.android-library")
}

dependencies {
    implementation(project(build.Modules.Common.CORE))
    implementation(dependency.Libs.APPCOMPAT)
    implementation(dependency.Libs.MATERIAL)
    implementation(dependency.Libs.NAVIGATION_FRAGMENT)
    implementation(dependency.Libs.CONSTRAINT_LAYOUT)
    implementation(dependency.Libs.LIFECYCLE_VIEWMODEL)
    implementation(dependency.Libs.LIFECYCLE_LIVEDATA)
    implementation(dependency.Libs.LIFECYCLE_EXTENSIONS)
    implementation(dependency.Libs.LIFECYCLE)
    implementation(dependency.Libs.NAVIGATION_UI)
    implementation(dependency.Libs.GLIDE)
    implementation(dependency.Libs.DAGGER)
    implementation(dependency.Libs.DAGGER_ANDROID)
    implementation(dependency.Libs.RETROFIT)
    implementation(dependency.Libs.GSON_CONVERTER)
    implementation(dependency.Libs.LOGGING_INTERCEPTOR)
    implementation(dependency.Libs.OK_HTTP)
    implementation(dependency.Libs.FRESCO_IMAGE_VIEWER) {
        implementation(dependency.Libs.FRESCO)
    }
    kapt(dependency.AnnotationProcessorsDependencies.GLIDE_COMPILER)
    kapt(dependency.AnnotationProcessorsDependencies.DAGGER_COMPILER)
    kapt(dependency.AnnotationProcessorsDependencies.DAGGER_ANDROID_COMPILER)
}
