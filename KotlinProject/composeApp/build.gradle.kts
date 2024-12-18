plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight") version "2.0.2"
    kotlin("plugin.serialization") version "1.9.22"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-android")
            implementation("app.cash.sqldelight:android-driver:2.0.2")

            //ktor
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            api(compose.foundation)
            api(compose.animation)
            implementation(compose.material)
            api(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // Navigatilon PreCompose
            api("moe.tlaster:precompose:1.5.10")
            // Viewmodel
            api("moe.tlaster:precompose-viewmodel:1.5.10")

            // Koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-compose")
            api("moe.tlaster:precompose-koin:1.5.10")

            // ktor
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.ktor.serialization)
            implementation(libs.ktor.content.negotiation)

            // SQLDelight
            implementation("app.cash.sqldelight:runtime:2.0.2")
        }
        iosMain.dependencies {
            implementation("app.cash.sqldelight:native-driver:2.0.2")
            implementation("co.touchlab:stately-common:2.0.5")

            //ktor
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "br.com.johncobain"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "br.com.johncobain"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures{
        compose = true
    }
}

sqldelight {
    databases{
        create("AppDatabase"){
            packageName.set("com.expenseApp.db")
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

