package com.andy.jennifer.common

import com.andy.jennifer.bean.Coordinate
import com.andy.jennifer.bean.Type

/**
 * Author:  andy.xwt
 * Date:    2021/5/31 13:33
 * Description:测试库
 */

val testLibs = listOf(

    Coordinate(
        "junit:junit",
        "junit",
        "4.+",
        Type.TEST_LIB
    ),
    Coordinate(
        "androidx.test.ext:junit",
        "androidxJunit",
        "1.1.2",
        Type.TEST_LIB
    ),

    Coordinate(
        "androidx.test.espresso:espresso-core",
        "androidxEspresso",
        "3.3.0",
        Type.TEST_LIB
    ),
    Coordinate(
        "androidx.room:room-testing",
        "room_testing",
        "2.2.5",
        Type.TEST_LIB
    )
)
