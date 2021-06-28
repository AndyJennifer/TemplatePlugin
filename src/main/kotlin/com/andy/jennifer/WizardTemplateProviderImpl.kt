package com.andy.jennifer

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.andy.jennifer.ownTemplate.ownTemplate
import com.andy.jennifer.systemTemplate.systemTemplate


/**
 * Author:  andy.xwt
 * Date:    2021/6/8 1:44 下午
 * Description:
 */

class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(
        ownTemplate,
        systemTemplate
    )
}