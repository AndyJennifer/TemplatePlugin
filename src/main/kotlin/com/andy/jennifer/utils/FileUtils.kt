package com.andy.jennifer.utils


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.intellij.openapi.vfs.LocalFileSystem


/**
 * Author:  andy.xwt
 * Date:    2021/6/8 1:46 下午
 * Description:
 */

const val BUILD_GRADLE_FILE = "build.gradle"
const val BUILD_VERSION_GRADLE_FILE = "libraries_version.gradle"


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
