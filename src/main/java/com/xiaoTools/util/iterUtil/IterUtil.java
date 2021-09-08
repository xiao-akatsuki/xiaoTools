package com.xiaoTools.util.iterUtil;

import com.xiaoTools.lang.constant.Constant;

/**
 * [Iterable 和  Iterator 相关工具类](Iteratable and iterator related tool classes)
 * @description zh - Iterable 和  Iterator 相关工具类
 * @description en - Iteratable and iterator related tool classes
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-08 20:34:20
 */
public class IterUtil {

    /* 为空 -------------------------------------------------------------- empty */

    /**
     * [Iterable是否为空](Is Iterable empty)
     * @description zh - Iterable是否为空
     * @description en - Is Iterable empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-08 20:37:06
     * @param iterable Iterable对象
     * @return boolean
     */
    public static boolean isEmpty(Iterable<?> iterable) {
		return Constant.NULL == iterable || isEmpty(iterable.iterator());
	}
    
}
