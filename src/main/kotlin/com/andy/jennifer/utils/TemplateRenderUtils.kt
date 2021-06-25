package com.andy.jennifer.utils

import com.andy.jennifer.bean.UiInfo


/**
 * Author:  andy.xwt
 * Date:    2021/6/8 1:45 下午
 * Description:
 */


/**
 * 是否渲染控制
 */
inline fun renderIfElse(predicate: Boolean, ifStr: () -> String, elseStr: () -> String) =
    if (predicate)
        ifStr().trim()
    else
        elseStr().trim()

/**
 * 渲染依赖
 *
 * @param list
 */
fun renderDepends(list: List<UiInfo>): String {
    val sb = StringBuilder()
    for (info in list) {
        info.map.filter { entry -> entry.key.value }.values.forEach {
            sb.append("implementation ${it.type.prefixName}.${it.artifactId.replace('-', '_')}").append("\n")
        }
    }
    return sb.toString()
}
