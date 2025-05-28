plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.androidx.navigation.safe.args)
}

android {
    namespace = "br.com.meli"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.com.meli"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    flavorDimensions += "mode"
    productFlavors {
        create("mock") {
            dimension = "mode"
            applicationIdSuffix = ".mock"
            versionNameSuffix = "-mock"
            buildConfigField("Boolean", "IS_MOCK", "true")
        }
        create("prod") {
            dimension = "mode"
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
            buildConfigField("Boolean", "IS_MOCK", "false")
        }
    }

    sourceSets {
        getByName("mock") {
            assets.srcDir("src/mock/assets")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.glide)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.animation.core.android)
    implementation(libs.androidx.motionlayout)
    implementation(libs.com.tbuonomo)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}