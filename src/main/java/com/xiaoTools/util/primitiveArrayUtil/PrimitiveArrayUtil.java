package com.xiaoTools.util.primitiveArrayUtil;

import com.xiaoTools.lang.constant.Constant;

/**
 * [原始类型数组工具类](Primitive type array utility class)
 * @description: zh - 原始类型数组工具类
 * @description: en - Primitive type array utility class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/8/10 4:53 下午
*/
public class PrimitiveArrayUtil {

    /* 数组是否为空 ------------------------------------------------------------------------------- Is Empty */

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:00 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(long[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:01 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(int[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:08 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(char[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:09 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(short[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:10 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(byte[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:10 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(double[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:11 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(float[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }

    /**
     * [数组是否为空](Array is empty)
     * @description: zh - 数组是否为空
     * @description: en - Array is empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/10 5:11 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isEmpty(boolean[] array) {
        return array == Constant.NULL || array.length == Constant.ZERO;
    }
}
