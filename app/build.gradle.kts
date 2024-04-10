plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.rk.mynote"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rk.mynote"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.play.services.auth)

    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.firebase.auth)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    annotationProcessor (libs.androidx.room.compiler.v240)

    //for navigation in compose
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.compose.v240alpha10)
    implementation (libs.androidx.hilt.navigation.compose.v100alpha03)

    //for hilt
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.gson)

    //for kotlin coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    //for retrofit
    implementation(libs.retrofit.v2110)
    implementation(libs.gson)

    //viewmodel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.runtime.livedata.v165)

    //for more icons
    implementation(libs.androidx.material.icons.extended.v100rc01)

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}