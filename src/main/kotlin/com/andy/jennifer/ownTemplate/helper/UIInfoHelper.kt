package com.andy.jennifer.ownTemplate.helper

import com.android.tools.idea.wizard.template.BooleanParameter
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.LabelWidget
import com.android.tools.idea.wizard.template.booleanParameter
import com.andy.jennifer.ownTemplate.bean.Coordinate
import com.andy.jennifer.ownTemplate.bean.Type
import com.andy.jennifer.ownTemplate.bean.UiInfo
import com.andy.jennifer.common.thirdPartLibs


/**
 * Author:  andy.xwt
 * Date:    2021/6/8 1:54 下午
 * Description:
 */

object UIInfoHelper {

    private val allUiInfo = listOf(
        crateUiInfo(Type.THIRD_PART_LIB, "Chose ThirdPartLibs Impl", thirdPartLibs)
    )


    private fun crateUiInfo(type: Type, title: String, list: List<Coordinate>): UiInfo {
        val titleWidget = LabelWidget("------------------$title------------------")
        val widgets = mutableListOf<CheckBoxWidget>()
        val map = mutableMapOf<BooleanParameter, Coordinate>()

        for (coordinate in list) {
            val boolPar = booleanParameter {
                name = coordinate.artifactId
                default = coordinate.isCheck
                if (coordinate.desc.isNotEmpty()) {
                    help = coordinate.desc
                }
            }
            val widget = CheckBoxWidget(boolPar)

            widgets.add(widget)
            map[boolPar] = coordinate;
        }
        return UiInfo(type, titleWidget, widgets, map)
    }


    fun getUiInfoByType(vararg type: Type): List<UiInfo> {
        return allUiInfo.filter { type.contains(it.type) }
    }

}