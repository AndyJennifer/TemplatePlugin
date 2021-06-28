package com.andy.jennifer.systemTemplate.src


/**
 * Author:  andy.xwt
 * Date:    2021/6/28 14:24
 * Description:
 */
import com.android.tools.idea.wizard.template.getMaterialComponentName
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun viewModelKt(
    fragmentPackage: String,
    packageName: String,
    useAndroidX: Boolean,
    viewModelClass: String
) =
    """package ${escapeKotlinIdentifier(packageName)}.${escapeKotlinIdentifier(fragmentPackage)}

import ${getMaterialComponentName("android.arch.lifecycle.ViewModel", useAndroidX)}

class $viewModelClass : ViewModel() {
    // TODO: Implement the ViewModel
}
"""