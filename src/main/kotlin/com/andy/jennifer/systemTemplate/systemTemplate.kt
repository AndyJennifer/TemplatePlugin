package com.andy.jennifer.systemTemplate

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.Constraint.CLASS
import com.android.tools.idea.wizard.template.Constraint.LAYOUT
import com.android.tools.idea.wizard.template.Constraint.NONEMPTY
import com.android.tools.idea.wizard.template.Constraint.UNIQUE
import com.android.tools.idea.wizard.template.impl.defaultPackageNameParameter
import java.io.File


/**
 * Author:  andy.xwt
 * Date:    2021/6/8 1:52 下午
 * Description: 不删除原有 build.gradle 文件的方式，而是使用系统提供的API修改 build.gradle 文件
 */

val systemTemplate
    get() = template {

        //最小支持的 Android SDK 版本
        minApi = 16

        //版本号
        revision = 1

        //设置模板名称
        name = "Config Activity + Fragment + ViewModel  Template By System"

        //设置描述
        description = "Creates a new activity and a fragment with view model"

        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        val activityClass = stringParameter {
            name = "Activity Name"
            default = "MainActivity"
            help = "The name of the activity class to create"
            constraints = listOf(CLASS, UNIQUE, NONEMPTY)
        }

        val activityLayout = stringParameter {
            name = "Activity Layout Name"
            default = "main_activity"
            help = "The name of the layout to create for the activity"
            constraints = listOf(LAYOUT, UNIQUE, NONEMPTY)
            suggest = { "${classToResource(activityClass.value)}_activity" }
        }

        val fragmentClass = stringParameter {
            name = "Fragment Name"
            default = "MainFragment"
            help = "The name of the fragment class to create"
            constraints = listOf(CLASS, UNIQUE, NONEMPTY)
            suggest = { "${underscoreToCamelCase(classToResource(activityClass.value))}Fragment" }
        }

        val fragmentLayout = stringParameter {
            name = "Fragment Layout Name"
            default = "main_fragment"
            help = "The name of the layout to create for the fragment"
            constraints = listOf(LAYOUT, UNIQUE, NONEMPTY)
            suggest = { "${classToResource(fragmentClass.value)}_fragment" }
        }

        val viewModelClass = stringParameter {
            name = "ViewModel Name"
            default = "MainViewModel"
            help = "The name of the view model class to create"
            constraints = listOf(CLASS, UNIQUE, NONEMPTY)
            suggest = { "${underscoreToCamelCase(classToResource(fragmentClass.value))}ViewModel" }
        }

        val isLauncher = booleanParameter {
            name = "Launcher Activity"
            default = false
            help = "If true, this activity will have a CATEGORY_LAUNCHER intent filter, making it visible in the launcher"
        }

        val packageName = defaultPackageNameParameter

        val fragmentPackage = stringParameter {
            name = "Fragment package path"
            default = "ui.main"
            help = "The package path for the fragment and the view model"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { "ui.${classToResource(fragmentClass.value).replace("_", "")}" }
        }

        widgets(
            TextFieldWidget(activityClass),
            TextFieldWidget(activityLayout),
            TextFieldWidget(fragmentClass),
            TextFieldWidget(fragmentLayout),
            TextFieldWidget(viewModelClass),
            CheckBoxWidget(isLauncher),
            PackageNameWidget(packageName),
            TextFieldWidget(fragmentPackage),
            LanguageWidget()
        )

        thumb { File("template_blank_activity.png") }

        recipe = { data: TemplateData ->
            viewModelActivityRecipe(data as ModuleTemplateData, activityClass.value, activityLayout.value, fragmentClass.value,
                fragmentLayout.value, viewModelClass.value, isLauncher.value, packageName.value, fragmentPackage.value)
        }
    }


