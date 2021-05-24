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

    /**
     * [输入数组，判断是否包含所需要的值](Input the array to determine whether it contains the required value)
     * @description: zh - 输入数组，判断是否包含所需要的值
     * @description: en - Input the array to determine whether it contains the required value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/24 9:14 上午
     * @param arrays: [数组](Array)
     * @param value: [包含的值](Values contained)
     * @return boolean
    */
    public static boolean contains(char[] arrays, char value) {
        return indexOf(arrays, value) > -1;
    }

    /**
     * [通过数组获取索引的值](Gets the value of the index through an array)
     * @description: zh - 通过数组获取索引的值
     * @description: en - Gets the value of the index through an array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/24 9:22 上午
     * @param array: [数组](Array)
     * @param value: [单个值](Single value)
     * @return int
    */
    public static int indexOf(char[] array, char value) {
        if (null != array) {
            for(int i = 0; i < array.length; ++i) {
                if (value == array[i]) {
                    return i;
                }
            }
        }

        return -1;
    }

}
