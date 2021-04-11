import com.android.build.gradle.internal.tasks.structureplugin.appendToPath

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Plugins.kotlin)
        classpath(Plugins.gradle)
        classpath(Plugins.hilt)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}


tasks.register<Copy>("installGitHook") {
    from (file(rootProject.rootDir.path.appendToPath("./pre-commit.sh")))
    into (file(rootProject.rootDir.path.appendToPath(".git/hooks")))
    fileMode = 777
}

tasks.getByPath(":app:preBuild").dependsOn(tasks["installGitHook"])