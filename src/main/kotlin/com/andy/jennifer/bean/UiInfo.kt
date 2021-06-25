package com.andy.jennifer.bean

import com.android.tools.idea.wizard.template.BooleanParameter
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.LabelWidget
import com.android.tools.idea.wizard.template.Widget
import com.andy.jennifer.bean.Coordinate
import com.andy.jennifer.bean.Type


/**
 * Author:  andy.xwt
 * Date:    2021/5/31 14:41
 * Description:
 */

data class UiInfo(
    val type: Type,//类型
    val titleWidget: LabelWidget,//标题
    val widgets: List<CheckBoxWidget>,//需要选中的依赖
    val map: Map<BooleanParameter, Coordinate>
) {
    fun getUIWidget(): MutableList<Widget<*>> {
        val list = mutableListOf<Widget<*>>()
        list.add(titleWidget)
        list.addAll(widgets)
        return list
    }

}
