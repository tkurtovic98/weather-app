

object Plugins {

    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"

    const val gradle = "com.android.tools.build:gradle:${Versions.GRADLE}"

    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"

    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"

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

    private const val fragmentNavigationKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    private const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    private const val navigationDynamicFeaturesFragment = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.NAVIGATION}"

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
        add(fragmentNavigationKtx)
        add(navigationUiKtx)
        add(navigationDynamicFeaturesFragment)
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

