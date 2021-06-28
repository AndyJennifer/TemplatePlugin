package com.andy.jennifer.systemTemplate.src

import com.android.tools.idea.wizard.template.getMaterialComponentName


/**
 * Author:  andy.xwt
 * Date:    2021/6/28 14:23
 * Description:
 */

fun fragmentJava(
    fragmentClass: String,
    fragmentLayout: String,
    fragmentPackage: String,
    packageName: String,
    useAndroidX: Boolean,
    viewModelClass: String): String {

    val viewModelInitializationBlock = if (useAndroidX) "new ViewModelProvider(this).get(${viewModelClass}.class);"
    else "new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(${viewModelClass}.class);"

    return """package ${packageName}.${fragmentPackage};

import ${getMaterialComponentName("android.arch.lifecycle.ViewModelProvider", useAndroidX)};
import android.os.Bundle;
import ${getMaterialComponentName("android.support.annotation.NonNull", useAndroidX)};
import ${getMaterialComponentName("android.support.annotation.Nullable", useAndroidX)};
import ${getMaterialComponentName("android.support.v4.app.Fragment", useAndroidX)};
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ${packageName}.R;

public class $fragmentClass extends Fragment {

    public static $fragmentClass newInstance() {
        return new ${fragmentClass}();
    }

    private $viewModelClass mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.${fragmentLayout}, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = $viewModelInitializationBlock
        // TODO: Use the ViewModel
    }

}
"""
}
