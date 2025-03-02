import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.BOTH as BOTH_TYPE

plugins {
    kotlin("js")
}

dependencies {
    implementation(kotlin("stdlib-js"))
}

@Suppress("DEPRECATION")
kotlin {
    js("both")
    js(BOTH)
    js(BOTH_TYPE) {
        useCommonJs()
        browser {
        }
    }
}