plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.aquariumtestapp"
    compileSdk = 34

    val supabasekey: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
        .getProperty("SUPABASE_API_KEY")

    val supabaseurl: String = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
        .getProperty("SUPABASE_URL")


    defaultConfig {
        applicationId = "com.example.aquariumtestapp"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }


        buildConfigField("String","supabaseKey","\"${supabasekey ?: ""}\"")
        buildConfigField("String","supabaseUrl","\"${supabaseurl ?: ""}\"")

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
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"//"2.0.21"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.2")
    implementation("androidx.camera:camera-core:1.3.4")
    implementation("androidx.camera:camera-view:1.3.4")
    implementation("androidx.camera:camera-lifecycle:1.3.4")
    implementation("androidx.camera:camera-camera2:1.3.4")
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("io.github.dokar3:sheets-m3:0.6.0-alpha05")
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.6.1")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.6.1")
    implementation("io.ktor:ktor-client-cio:2.3.12")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:3.0.2")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.0.21")
    implementation("com.google.code.gson:gson:2.8.8")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.6.1")
    implementation("io.ktor:ktor-client-cio:2.3.12")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
}
