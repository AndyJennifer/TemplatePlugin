package com.andy.jennifer

import com.android.tools.idea.wizard.template.getMaterialComponentName
import com.android.tools.idea.wizard.template.renderIf
import com.andy.jennifer.bean.UiInfo
import com.andy.jennifer.utils.renderDepends


/**
 * Author:  andy.xwt
 * Date:    2021/6/16 00:12
 * Description:
 */

fun bizBuildGradle(list: List<UiInfo>, generateKotlin: Boolean, useAndroidX: Boolean) =
    """       
    ${pluginsBlock(generateKotlin)}
    ${androidConfigBlock(useAndroidX = useAndroidX, generateKotlin)}
    ${dependenciesBlock(list, generateKotlin)}
    """


/**
 * 构建插件依赖
 */
private fun pluginsBlock(generateKotlin: Boolean): String {

    val androidPlugin =
        """
        apply plugin: 'com.android.library'
         """

    val kotlinPlugin = renderIf(generateKotlin) {
        """
        apply plugin: 'kotlin-android'
        apply plugin: 'kotlin-android-extensions'
        apply plugin: 'kotlin-kapt'
        """
    }


    val librariesApply = """
        apply from: './libraries_version.gradle'
        """

    return androidPlugin + kotlinPlugin + librariesApply
}


/**
 * 构建AndroidConfig
 */
private fun androidConfigBlock(useAndroidX: Boolean, generateKotlin: Boolean) =
    """ 
    android {
        compileSdkVersion versions.compileSdk
        buildToolsVersion versions.buildTool
        
        defaultConfig {
            minSdkVersion versions.minSdk
            targetSdkVersion versions.compileSdk
            versionCode 1
            versionName "1.0"
        
            testInstrumentationRunner "${
        getMaterialComponentName(
            "android.support.test.runner.AndroidJUnitRunner",
            useAndroidX
        )
    }"
        }
    
    buildTypes {
        debug {
            minifyEnabled false
            debuggable true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
        
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    ${
        renderIf(generateKotlin) {
            """
    kotlinOptions {
        jvmTarget = '1.8'
    } 
        """
        }
    }
    lintOptions {
        abortOnError = false
        checkReleaseBuilds false
    }
    
}"""

/**
 * 构建dependencies
 */
private fun dependenciesBlock(list: List<UiInfo>, generateKotlin: Boolean) =
    """
    dependencies {
    implementation fileTree(dir: "libs", include: ["*.jar"])
    
    ${
        renderIf(generateKotlin) {
            """
        implementation officialLib.kotlin_stdlib
        implementation officialLib.core_ktx
        """
        }
    }
    
    ${renderDepends(list)}
    
    ///////////////////////////////////////////////////////////////////////////
    //Test相关库
    /////////////////////////////////////////////////////////////////////////// 
    testImplementation testLib.junit
    androidTestImplementation testLib.androidxJunit
    androidTestImplementation testLib.androidxEspresso
 
    }
    """





