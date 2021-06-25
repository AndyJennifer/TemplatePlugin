package com.andy.jennifer.common

import com.andy.jennifer.bean.Coordinate
import com.andy.jennifer.bean.Type


/**
 * Author:  andy.xwt
 * Date:    2021/6/15 23:46
 * Description:
 */

val thirdPartLibs = listOf(

    Coordinate(
        "io.reactivex.rxjava2",
        "rxandroid",
        "2.0.2",
        Type.THIRD_PART_LIB,
        isCheck = false
    ),

    Coordinate(
        "com.squareup.retrofit2",
        "retrofit",
        "2.9.0",
        Type.THIRD_PART_LIB,
        isCheck = true
    ),
    Coordinate(
        "com.squareup.retrofit2",
        "converter-gson",
        "2.9.0",
        Type.THIRD_PART_LIB,
        isCheck = true
    ),

    Coordinate(
        "com.squareup.retrofit2",
        "adapter-rxjava2",
        "2.9.0",
        Type.THIRD_PART_LIB,
        isCheck = true
    ),

    Coordinate(
        "com.squareup.okhttp3",
        "okhttp",
        "3.14.9",
        Type.THIRD_PART_LIB,
        isCheck = true
    ),

    Coordinate(
        "com.squareup.okhttp3",
        "logging-interceptor",
        "3.14.9",
        Type.THIRD_PART_LIB,
        isCheck = true
    ),


    )