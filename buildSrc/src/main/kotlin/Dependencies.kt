

object Plugins {

    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

    const val gradle = "com.android.tools.build:gradle:${Versions.GRADLE}"

    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"

}

object Libs {

    private const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"

    private const val coreKtx = "androidx.core:core-ktx:${Versions.KOTLIN_KTX}"
    private const val appCompat = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    private const val androidxLegacySupport = "androidx.legacy:legacy-support-v4:${Versions.ANDROIDX_LEGACY_SUPPORT}"

    private const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.COORDINATOR_LAYOUT}"

    private const val materialDesign = "com.google.android.material:material:${Versions.MATERIAL_DESIGN}"

    private const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.VIEW_MODEL_KTX}"
    private const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

    private const val hilt = "com.google.dagger:hilt-android:${Versions.HILT}"
    private const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_ANDROIDX}"

    private const val roomRuntime = "androidx.room:room-runtime:${Versions.ROOM}"
    private const val roomKtx = "androidx.room:room-ktx:${Versions.ROOM}"

    val appLibs = arrayListOf<String>().apply {
        add(kotlinStdlib)
        add(coreKtx)
        add(appCompat)
        add(androidxLegacySupport)
        add(coordinatorLayout)
        add(materialDesign)
        add(viewModelKtx)
        add(fragmentKtx)
        add(hilt)
        add(hiltViewModel)
        add(roomRuntime)
        add(roomKtx)
    }

}


object Compilers {

    private const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    private const val roomCompiler = "androidx.room:room-compiler:${Versions.ROOM}"

    private const val hiltAndroidxCompiler  = "androidx.hilt:hilt-compiler:${Versions.HILT_ANDROIDX}"


    val libCompilers = arrayListOf<String>().apply {
        add(hiltCompiler)
        add(roomCompiler)
        add(hiltAndroidxCompiler)
    }


}

object Testing {

    private const val jUnitAndroidx = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT_KOTLIN_RUNNER}"
    private const val jUnit = "junit:junit:${Versions.JUNIT}"
    private const val jUnitAndroidxKtx = "androidx.test.ext:junit-ktx:${Versions.ANDROIDX_TEST_EXT_KOTLIN_RUNNER}"

    val testingLibs = arrayListOf<String>().apply {
        add(jUnitAndroidx)
        add(jUnit)
        add(jUnitAndroidxKtx)
    }
}

