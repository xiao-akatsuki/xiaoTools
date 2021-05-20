package com.xiaoTools.core.arrayUtil;

/**
 * [数组工具类](Array tool class)
 * @author HCY
 * @since 2021/5/16 2:21 [下午](afternoon)
*/
public class ArrayUtil {
    /**
     * [初始化方法](Initialization method)
     * @author HCY
     * @since 2021/5/16 2:21 [下午](afternoon)
    */
    public ArrayUtil() { }

    /**
     * [判断数组是否为空](Determine whether the array is empty)
     * @description: zh - 判断数组是否为空
     * @description: en - Determine whether the array is empty
     * @author HCY
     * @since 2021/5/16 2:21 下午
     * @param array: [数组](array)
     * @return boolean
    */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
