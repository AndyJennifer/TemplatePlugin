package com.andy.jennifer.bean


/**
 * Author:  andy.xwt
 * Date:    2021/5/27 14:36
 * Description:
 */


data class Coordinate(
    var groupId: String,//groupId
    var artifactId: String,//artifactId
    var version: String,//版本号
    var type: Type,//类型
    var desc: String = "",//描述信息
    var isCheck: Boolean = false//是否选中
)


