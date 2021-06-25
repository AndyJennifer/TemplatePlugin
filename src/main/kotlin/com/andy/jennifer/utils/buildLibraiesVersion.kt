package com.andy.jennifer.utils

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.andy.jennifer.helper.BuildLibrariesVersionHelper.getAllLibsFullAddressWithVersion
import com.andy.jennifer.helper.BuildLibrariesVersionHelper.getAllLibsNameToVersion


/**
 * Author:  andy.xwt
 * Date:    2021/5/27 16:11
 * Description:
 */

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

