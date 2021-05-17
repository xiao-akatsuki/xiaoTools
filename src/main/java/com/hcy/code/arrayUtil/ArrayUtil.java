package com.hcy.code.arrayUtil;

/**
 * 数组工具类
 * @author HCY
 * @since 2021/5/16 2:21 下午
*/
public class ArrayUtil {
    /**
     * 初始化方法
     * @author HCY
     * @since 2021/5/16 2:21 下午
     * @return null
    */
    public ArrayUtil() { }

    /**
     * 判断数组是否为空
     * @author HCY
     * @since 2021/5/16 2:21 下午
     * @param array: 数组
     * @return boolean
    */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
