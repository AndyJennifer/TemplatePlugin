package com.andy.jennifer.ownTemplate.helper

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.andy.jennifer.common.officialLibs
import com.andy.jennifer.common.testLibs
import com.andy.jennifer.common.thirdPartLibs
import com.andy.jennifer.ownTemplate.bean.Coordinate


/**
 * Author:  andy.xwt
 * Date:    2021/5/27 16:11
 * Description:
 */

private val allLibs = listOf(
    testLibs,
    officialLibs,
    thirdPartLibs
)

fun createLibrariesVersionGradle(
    moduleData: ModuleTemplateData,
    projectData: ProjectTemplateData
): String {
    return """
        
ext {
    ///////////////////////////////////////////////////////////////////////////
    // versions
    ///////////////////////////////////////////////////////////////////////////
    versions = [
    
            //构建版本
            buildTool                    : "${projectData.buildToolsVersion}",
            minSdk                       : ${moduleData.apis.minApi.api},
            compileSdk                   : ${moduleData.apis.buildApi.api},
            targetSdk                    : ${moduleData.apis.targetApi.api},
            ${getAllLibsNameToVersion()}
    ]

    ${getAllLibsFullAddressWithVersion()}

}

"""
}

fun getAllLibsNameToVersion(): String {
    val list = allLibs.flatten()
    val sb = StringBuilder()
    for (i in list.indices) {
        val info = list[i]
        sb.append("${info.artifactId.replace('-', '_')}:\"${info.version}\"")
        if (i < list.size - 1) {
            sb.append(",\n")
        }
    }
    return sb.toString()
}

fun getAllLibsFullAddressWithVersion(): String {
    val sb = StringBuilder()
    allLibs.forEach {
        sb.append(getLibsStr(it[0].type.prefixName, it))
    }
    return sb.toString()
}

private fun getLibsStr(title: String, info: List<Coordinate>) =
    """
        ///////////////////////////////////////////////////////////////////////////
        // $title
        ///////////////////////////////////////////////////////////////////////////
        $title = [
         ${renderStr(info)}
        ]
        """

//创建版本信息
private fun renderStr(list: List<Coordinate>): String {
    val sb = StringBuilder()
    for (i in list.indices) {
        val info = list[i]
        sb.append("${info.artifactId.replace('-', '_')}:")
        sb.append("\"${info.groupId}:")
        sb.append("${info.artifactId}:")
        sb.append("${'$'}{versions.${info.artifactId.replace('-', '_')}}\"")
        if (i < list.size - 1) {
            sb.append(",\n")
        }
    }
    return sb.toString()
}
