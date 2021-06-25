package com.andy.jennifer.common

import com.andy.jennifer.bean.Coordinate
import com.andy.jennifer.bean.Type
/**
 * Author:  andy.xwt
 * Date:    2021/6/25 10:17 上午
 * Description:  Google 官方库
 */

val officialLibs = listOf(

    Coordinate(
        "org.jetbrains.kotlin:kotlin-stdlib",
        "kotlin_stdlib",
        "1.3.72",
        Type.OFFICIAL_LIB
    ),
    Coordinate(
        "androidx.core:core-ktx",
        "core_ktx",
        "1.2.0",
        Type.OFFICIAL_LIB
    ),

    Coordinate(
        "androidx.appcompat:appcompat",
        "appcompat",
        "1.2.0",
        Type.OFFICIAL_LIB
    ),

    Coordinate(
        "com.google.android.material:material",
        "material",
        "1.2.1",
        Type.OFFICIAL_LIB
    ),

    Coordinate(
        "androidx.constraintlayout:constraintlayout",
        "constaintlayout",
        "2.0.4",
        Type.OFFICIAL_LIB
    )
)