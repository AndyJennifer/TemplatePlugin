package com.andy.jennifer.systemTemplate.src

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import com.android.tools.idea.wizard.template.getMaterialComponentName


/**
 * Author:  andy.xwt
 * Date:    2021/6/28 14:23
 * Description:
 */

fun fragmentKt(
    fragmentClass: String,
    fragmentLayout: String,
    fragmentPackage: String,
    packageName: String,
    useAndroidX: Boolean,
    viewModelClass: String): String {

    val viewModelInitializationBlock = if (useAndroidX) "ViewModelProvider(this).get(${viewModelClass}::class.java)"
    else "ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(${viewModelClass}::class.java)"

    return """package ${escapeKotlinIdentifier(packageName)}.${escapeKotlinIdentifier(fragmentPackage)}

import ${getMaterialComponentName("android.arch.lifecycle.ViewModelProvider", useAndroidX)}
import android.os.Bundle
import ${getMaterialComponentName("android.support.v4.app.Fragment", useAndroidX)}
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ${escapeKotlinIdentifier(packageName)}.R

class $fragmentClass : Fragment() {

    companion object {
        fun newInstance() = ${fragmentClass}()
    }

    private lateinit var viewModel: $viewModelClass

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.${fragmentLayout}, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = $viewModelInitializationBlock
        // TODO: Use the ViewModel
    }

}
"""
}