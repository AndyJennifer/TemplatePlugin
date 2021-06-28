package com.andy.jennifer.systemTemplate

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.*
import com.android.tools.idea.wizard.template.impl.activities.viewModelActivity.res.layout.activityXml
import com.android.tools.idea.wizard.template.impl.activities.viewModelActivity.res.layout.fragmentXml
import com.andy.jennifer.systemTemplate.src.*


/**
 * Author:  andy.xwt
 * Date:    2021/6/25 14:34
 * Description:
 */

fun RecipeExecutor.viewModelActivityRecipe(
    moduleData: ModuleTemplateData,
    activityClass: String,
    activityLayout: String,
    fragmentClass: String,
    fragmentLayout: String,
    viewModelClass: String,
    isLauncher: Boolean,
    packageName: String,
    fragmentPackage: String
) {
    val (projectData, srcOut, resOut) = moduleData
    val apis = moduleData.apis
    val appCompatVersion = apis.appCompatVersion
    val useAndroidX = moduleData.projectTemplateData.androidXSupport
    val ktOrJavaExt = projectData.language.extension
    val generateKotlin = projectData.language == Language.Kotlin


    val superClassFqcn = getMaterialComponentName("android.support.v7.app.AppCompatActivity", useAndroidX)
    addAllKotlinDependencies(moduleData)

    generateManifest(
        moduleData, activityClass, activityClass, packageName, isLauncher, false,
        generateActivityTitle = false
    )

    //添加依赖
    addDependency("com.android.support:appcompat-v7:${appCompatVersion}.+")
    addDependency("com.android.support.constraint:constraint-layout:+")
    addLifecycleDependencies(useAndroidX)
    addMaterialDependency(useAndroidX)

    if (generateKotlin && useAndroidX) {
        //添加Project ext 属性
        setExtVar("lifecycleVersion", "2.2.0")
        addDependency("androidx.lifecycle:lifecycle-viewmodel-ktx:+")
    }



    //创建对应布局文件并打开
    mergeXml(activityXml(activityClass), resOut.resolve("layout/${activityLayout}.xml"))
    mergeXml(
        fragmentXml(fragmentClass, fragmentPackage, useAndroidX),
        resOut.resolve("layout/${fragmentLayout}.xml")
    )
    open(resOut.resolve("layout/${fragmentLayout}.xml"))

    //创建Activity
    val activity = when (projectData.language) {
        Language.Java -> activityJava(
            activityClass,
            activityLayout,
            fragmentClass,
            fragmentPackage,
            packageName,
            superClassFqcn
        )
        Language.Kotlin -> activityKt(
            activityClass,
            activityLayout,
            fragmentClass,
            fragmentPackage,
            packageName,
            superClassFqcn
        )
    }
    save(activity, srcOut.resolve("${activityClass}.${ktOrJavaExt}"))

    //创建fragment
    val fragmentPath = fragmentPackage.replace(".", "/")
    val fragment = when (projectData.language) {
        Language.Java -> fragmentJava(
            fragmentClass,
            fragmentLayout,
            fragmentPackage,
            packageName,
            useAndroidX,
            viewModelClass
        )
        Language.Kotlin -> fragmentKt(
            fragmentClass,
            fragmentLayout,
            fragmentPackage,
            packageName,
            useAndroidX,
            viewModelClass
        )
    }
    save(fragment, srcOut.resolve("${fragmentPath}/${fragmentClass}.${ktOrJavaExt}"))

    //创建ViewModel
    open(srcOut.resolve("${fragmentPath}/${fragmentClass}.${ktOrJavaExt}"))
    val viewModel = when (projectData.language) {
        Language.Java -> viewModelJava(fragmentPackage, packageName, useAndroidX, viewModelClass)
        Language.Kotlin -> viewModelKt(fragmentPackage, packageName, useAndroidX, viewModelClass)
    }
    save(viewModel, srcOut.resolve("${fragmentPath}/${viewModelClass}.${ktOrJavaExt}"))

    open(srcOut.resolve("${fragmentPath}/${viewModelClass}.${ktOrJavaExt}"))
}