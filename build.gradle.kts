// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.5.0") apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.4") // Dette er et eksempel, versionen kan være forskellig
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30") // Dette er et eksempel, versionen kan være forskellig
    }


}
