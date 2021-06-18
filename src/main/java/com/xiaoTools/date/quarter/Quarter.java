package com.xiaoTools.date.quarter;

import com.xiaoTools.lang.constant.Constant;

/**
 * [季度枚举](Quarterly enumeration)
 * @description: zh - 季度枚举
 * @description: en - Quarterly enumeration
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 3:10 下午
*/
public enum Quarter {

    /**
     * 第一季度
     */
    Quarter1(Constant.ONE),

    /**
     * 第二季度
     */
    Quarter2(Constant.TWO),

    /**
     * 第三季度
     */
    Quarter3(Constant.THREE),

    /**
     * 第四季度
     */
    Quarter4(Constant.FOUR);

    private final int value;

    Quarter(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Quarter of(int intValue) {
        return switch (intValue) {
            case 1 -> Quarter1;
            case 2 -> Quarter2;
            case 3 -> Quarter3;
            case 4 -> Quarter4;
            default -> null;
        };
    }
}
