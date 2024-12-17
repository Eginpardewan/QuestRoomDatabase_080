// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) version "1.9.10" apply false // Perbarui versi di sini
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}