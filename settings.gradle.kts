import java.net.URI

rootProject.name = "gradlebuildsrcbug"
include(":app")


pluginManagement {
    repositories {
/*
        maven {
            // when using a repository who change the dependency
            // to "com.android.tools.build:gradle:3.2.0"
            // instead of "com.android.tools.build:gradle:2.3.3"
            // no error when compiling app/build.gradle.kts
            url = URI("file:///${rootDir}/repo")
        }
*/

        gradlePluginPortal()
        jcenter()
        google()
    }
    resolutionStrategy {
        eachPlugin {
            val id = requested.id.id
            when {
                id == "com.google.gms.oss.licenses.plugin" -> useModule("com.google.android.gms:oss-licenses-plugin:${requested.version}")
                id.startsWith("com.android.") -> useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}
