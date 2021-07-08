import dependency.Libs


plugins {
    id("commons.android-library")
    id("kotlin-kapt")
}

dependencies {
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
}
