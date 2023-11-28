plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.mubarak.androidflavors"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mubarak.androidflavors"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = File("${rootDir}/keystore_androidflavors.jks")
            storePassword = "123456"
            keyAlias = "androidflavors"
            keyPassword = "123456"
        }
    }

    flavorDimensions += "default"

    productFlavors {

        create("dev") {
            applicationIdSuffix = ".dev"
        }

        create("prod") {
            dimension = "default"
        }
    }

    android.applicationVariants.all {

        when (flavorName) {
            "dev" -> {
                buildConfigField("String","BaseURL", project.findProperty("DEV_BASE_URL").toString())
            }

            "prod" -> {
                buildConfigField("String","BaseURL", project.findProperty("PROD_BASE_URL").toString())
            }
        }
    }

    buildTypes {

        debug {
            isDebuggable = true
        }

        release {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs["release"]
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}