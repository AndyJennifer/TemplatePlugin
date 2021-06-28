package com.andy.jennifer.ownTemplate

import com.android.tools.idea.wizard.template.*
import com.andy.jennifer.ownTemplate.bean.Type
import com.andy.jennifer.ownTemplate.helper.UIInfoHelper
import java.io.File


/**
 * Author:  andy.xwt
 * Date:    2021/6/8 1:52 下午
 * Description: 删除原有 build.gradle 文件的方式，新建一个新的 build.gradle 文件
 */

val ownTemplate
    get() = template {

        //最小支持的 Android SDK 版本
        minApi = 16

        //版本号
        revision = 1

        //设置模板名称
        name = "Config Template By Own"

        //设置描述
        description = "Delete Old build.gradle file and Config your own build.gradle"

        //设置模板属于哪个菜单项
        category = Category.Activity

        //设置针对的设备类型
        formFactor = FormFactor.Mobile

        //模板显示的区域
        screens = listOf(
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val uiInfos = UIInfoHelper.getUiInfoByType(Type.THIRD_PART_LIB)

        //模板对应的组件
        widgets = uiInfos.flatMap { it.getUIWidget() }

        //模板对应的图片
        thumb { File("template_settings_activity.png") }

        //创建模板
        recipe = { data ->
            autoGenerateConfig(data as ModuleTemplateData, uiInfos)
        }
    }


