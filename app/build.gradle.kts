plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("org.jlleitschuh.gradle.ktlint") version Versions.KT_LINT_GRADLE
}

android {
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig {
        applicationId = "hr.kurtovic.weatherkurtovi"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation

        buildConfigField(
            "String",
            "WEATHER_API_BASE_URL",
            "\"https://api.openweathermap.org/data/2.5/\""
        )
        buildConfigField("String", "WEATHER_API_KEY", "\"adb9c161b276d709b4880f1162c2e349\"")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.appLibs)
    kapt(Compilers.libCompilers)
    testImplementation(Testing.testingLibs)
}

ktlint {
    android.set(true)
    outputColorName.set("RED")
}
