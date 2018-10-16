plugins {
    id("com.android.application") apply false
    id("com.google.gms.oss.licenses.plugin") version "0.9.3" apply false
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
