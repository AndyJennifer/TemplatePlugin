package com.andy.jennifer.ownTemplate

import com.android.tools.idea.wizard.template.Language
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.andy.jennifer.ownTemplate.bean.UiInfo
import com.andy.jennifer.ownTemplate.helper.createLibrariesVersionGradle
import com.intellij.openapi.vfs.LocalFileSystem


/**
 * Author:  andy.xwt
 * Date:    2021/6/16 00:11
 * Description:
 */


const val BUILD_GRADLE_FILE = "build.gradle"
const val BUILD_VERSION_GRADLE_FILE = "libraries_version.gradle"

fun RecipeExecutor.autoGenerateConfig(
    moduleData: ModuleTemplateData,
    list: List<UiInfo>,
) {


    val projectData = moduleData.projectTemplateData

    //判断是否是新建项目，还是新建Module
    if (projectData.isNewProject || moduleData.isNewModule) {

        //创建libraries_version.gradle 文件用于依赖库的版本控制
        makeLibrariesBuildGradle(moduleData, projectData)

        //删除原有的build.gradle 文件
        deleteOldBuildGradle(moduleData)

        //创建新的build.gradle 文件
        makeModuleBuildGradle(
            list,
            projectData,
            moduleData
        )

    }
}


private fun RecipeExecutor.makeModuleBuildGradle(
    list: List<UiInfo>,
    projectData: ProjectTemplateData,
    moduleData: ModuleTemplateData
) {

    save(
        source = bizBuildGradle(
            list,
            generateKotlin = projectData.language == Language.Kotlin,
            useAndroidX = projectData.androidXSupport,
        ),
        to = moduleData.rootDir.resolve(moduleData.rootDir.resolve(BUILD_GRADLE_FILE))
    )

    //创建文件后，打开对应文件
    open(moduleData.rootDir.resolve(moduleData.rootDir.resolve(BUILD_GRADLE_FILE)))
}


/**
 * 创建 libraries_version.gradle 版本管理gradle
 */
fun RecipeExecutor.makeLibrariesBuildGradle(
    moduleData: ModuleTemplateData,
    projectData: ProjectTemplateData
) {
    save(
        source = createLibrariesVersionGradle(moduleData, projectData),
        to = moduleData.rootDir.resolve(BUILD_VERSION_GRADLE_FILE)
    )
}

/**
 * 删除原有 Module 的 build.gradle 文件
 */
fun RecipeExecutor.deleteOldBuildGradle(moduleData: ModuleTemplateData) {
    val oldGradle = moduleData.rootDir.resolve(BUILD_GRADLE_FILE)
    LocalFileSystem.getInstance().findFileByIoFile(oldGradle)?.delete(this)
}
