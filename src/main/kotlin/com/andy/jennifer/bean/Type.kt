package com.andy.jennifer.bean


/**
 * Author:  andy.xwt
 * Date:    2021/6/8 1:48 下午
 * Description:依赖库类型
 */

enum class Type(val prefixName: String) {

    //测试库
    TEST_LIB("testLib"),

    //Google 官方库
    OFFICIAL_LIB("officialLib"),

    //第三方库
    THIRD_PART_LIB("otherLib")

}

