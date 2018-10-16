plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}


version = "1.0"

repositories {
    gradlePluginPortal()
    jcenter()
    google()
}

dependencies {
    implementation("com.android.tools.build:gradle:3.2.0")
}
