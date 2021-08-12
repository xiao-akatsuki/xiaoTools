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

    /* 数组是否为非空 ------------------------------------------------------------------------------- Is Not Empty */

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:03 下午
     * @param array: 数组
     * @return boolean
     */
    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:02 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:04 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:05 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:05 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:05 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:05 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    /**
     * [数组是否为非空](array is not empty)
     * @description: zh - 数组是否为非空
     * @description: en - array is not empty
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:06 下午
     * @param array: 数组
     * @return boolean
    */
    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

    /* 重新设置大小的数组 ------------------------------------------------------------------------------- resize*/

    /**
     * [生成一个新的重新设置大小的数组](Generates a new resized array)
     * @description: zh - 生成一个新的重新设置大小的数组
     * @description: en - Generates a new resized array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/8/12 8:16 下午
     * @param bytes: 原数组
     * @param newSize: 新的数组大小
     * @return byte[]
    */
    public static byte[] resize(byte[] bytes, int newSize) {
        if (newSize < Constant.ZERO) {
            return bytes;
        }
        final byte[] newArray = new byte[newSize];
        if (newSize > Constant.ZERO && isNotEmpty(bytes)) {
            System.arraycopy(bytes, Constant.ZERO, newArray, Constant.ZERO, Math.min(bytes.length, newSize));
        }
        return newArray;
    }
}
