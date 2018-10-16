Gradle Kotlin DSL: Failure to compile gradle.kts script
==============================

When `buildSrc` depends on a specific version of a plugin `A-vXX` and another plugin `B`
also depends on `A` but at a different version (`A-vYY`), the kotlin script evaluator 
get confused and seems to mix the classpath, that cause a build failure.

Maybe this is specific to these plugins. In this case, `buildSrc` depends on Android Gradle plugin 3.2.0
and the `com.google.gms.oss.licenses.plugin` from the `google()` repository depends on AGP 2.3.3.
This result in the specific compilation error :

```
Parallel execution is an incubating feature.

FAILURE: Build failed with an exception.

* Where:
Build file '/home/darisk/encrypted_data/devel/geekorum/gradlebuildsrcbug/app/build.gradle.kts' line: 1

* What went wrong:
com.android.build.gradle.internal.dsl.DefaultConfig_Decorated cannot be cast to com.android.build.gradle.internal.dsl.ProductFlavor

* Try:
Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Exception is:
java.lang.ClassCastException: com.android.build.gradle.internal.dsl.DefaultConfig_Decorated cannot be cast to com.android.build.gradle.internal.dsl.ProductFlavor
        at Build_gradle$1$1.execute(build.gradle.kts:1)
        at com.android.build.gradle.BaseExtension.defaultConfig(BaseExtension.java:532)
        at Build_gradle$1.invoke(build.gradle.kts:9)
        at Build_gradle$1.invoke(build.gradle.kts:1)
        at org.gradle.kotlin.dsl.Accessors0Kt$sam$org_gradle_api_Action$0.execute(Accessors0.kt)
        at org.gradle.api.internal.plugins.ExtensionsStorage$ExtensionHolder.configure(ExtensionsStorage.java:197)
        at org.gradle.api.internal.plugins.ExtensionsStorage.configureExtension(ExtensionsStorage.java:69)
        at org.gradle.api.internal.plugins.DefaultConvention.configure(DefaultConvention.java:222)
        at org.gradle.kotlin.dsl.Accessors0Kt.android(Accessors0.kt:111)
        at Build_gradle.<init>(Unknown Source)

```

When modifying `settings.gradle` to use a local repository providing a version of `com.google.gms.oss.licenses.plugin`
who depends on AGP 3.2.0, there are no compilation errors.
