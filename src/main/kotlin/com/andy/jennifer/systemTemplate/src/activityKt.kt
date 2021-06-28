package com.andy.jennifer.systemTemplate.src


/**
 * Author:  andy.xwt
 * Date:    2021/6/28 14:23
 * Description:
 */

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun activityKt(
    activityClass: String,
    activityLayout: String,
    fragmentClass: String,
    fragmentPackage: String,
    packageName: String,
    superClassFqcn: String
) =

    """package ${escapeKotlinIdentifier(packageName)}

import $superClassFqcn
import android.os.Bundle
import ${escapeKotlinIdentifier(packageName)}.${escapeKotlinIdentifier(fragmentPackage)}.${fragmentClass}

class $activityClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${activityLayout})
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ${fragmentClass}.newInstance())
                .commitNow()
        }
    }
}
"""