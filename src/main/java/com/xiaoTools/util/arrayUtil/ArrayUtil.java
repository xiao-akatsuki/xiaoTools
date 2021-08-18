package com.xiaoTools.util.arrayUtil;

import java.lang.reflect.Array;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.primitiveArrayUtil.PrimitiveArrayUtil;

/**
 * [数组工具类](Array tool class)
 * @author HCY
 * @since 2021/5/16 2:21 [下午](afternoon)
*/
public class ArrayUtil extends PrimitiveArrayUtil {

    /* 数组是否为空 ------------------------------------------------------------------------------- Is Empty */

    /**
     * [数组是否为空](Is the array empty)
     * @description zh - 数组是否为空
     * @description en - Is the array empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 14:31:59
     * @param array 数组
     * @return boolean
     */
    public static <T> boolean isEmpty(T[] array) {
		return array == Constant.NULL || array.length == Constant.ZERO;
	}

    /**
     * [数组是否为空](Is the array empty)
     * @description zh - 数组是否为空
     * @description en - Is the array empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-18 14:33:32
     * @param array 数组
     * @return boolean
     */
    public static boolean isEmpty(Object array) {
		if (array != Constant.NULL) {
			if (isArray(array)) {
				return Constant.ZERO == Array.getLength(array);
			}
			return Constant.FALSE;
		}
		return Constant.TRUE;
	}
}
