package com.andy.jennifer.systemTemplate.src


/**
 * Author:  andy.xwt
 * Date:    2021/6/28 14:24
 * Description:
 */
import com.android.tools.idea.wizard.template.getMaterialComponentName

fun viewModelJava(
    fragmentPackage: String,
    packageName: String,
    useAndroidX: Boolean,
    viewModelClass: String) =

    """package ${packageName}.${fragmentPackage};

import ${getMaterialComponentName("android.arch.lifecycle.ViewModel", useAndroidX)};

public class $viewModelClass extends ViewModel {
    // TODO: Implement the ViewModel
}
"""