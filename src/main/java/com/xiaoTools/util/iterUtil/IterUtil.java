package com.xiaoTools.util.iterUtil;

import java.util.Iterator;

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

    /**
     * [Iterator是否为空](Is iterator empty)
     * @description zh - Iterator是否为空
     * @description en - Is iterator empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-08 20:41:42
     * @param iterator Iterator对象
     */
    public static boolean isEmpty(Iterator<?> iterator) {
		return Constant.NULL == iterator || Constant.FALSE == iterator.hasNext();
	}

    /**
     * [Iterable是否为非空](Is Iterable non empty)
     * @description zh - Iterable是否为非空
     * @description en - Is Iterable non empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-08 20:43:20
     * @param iterable Iterable对象
     * @return boolean
     */
    public static boolean isNotEmpty(Iterable<?> iterable) {
		return Constant.NULL != iterable && isNotEmpty(iterable.iterator());
	}

    /**
     * [Iterator是否为非空](Is iterator non empty)
     * @description zh - Iterator是否为非空
     * @description en - Is iterator non empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-08 20:47:03
     * @param iterator Iterator对象
     * @return boolean 
     */
    public static boolean isNotEmpty(Iterator<?> iterator) {
		return Constant.NULL != iterator && iterator.hasNext();
	}
    
    /* 含有null -------------------------------------------------------------- has null */ 

    /**
     * [是否包含 null 元素](Contains null elements)
     * @description zh - 是否包含 null 元素
     * @description en - Contains null elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-09 19:58:51
     * @param iter 被检查的对象
     * @return boolean
     */
    public static boolean hasNull(Iterable<?> iter) {
		return hasNull(Constant.NULL == iter ? null : iter.iterator());
	}

    /**
     * [是否包含 null 元素](Contains null elements)
     * @description zh - 是否包含 null 元素
     * @description en - Contains null elements
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-09 20:02:03
     * @param iter 被检查的对象
     * @return boolean
     */
    public static boolean hasNull(Iterator<?> iter) {
		if (Constant.NULL == iter) {
			return Constant.TRUE;
		}
		while (iter.hasNext()) {
			if (Constant.NULL == iter.next()) {
				return Constant.TRUE;
			}
		}

		return Constant.FALSE;
	}

    /**
     * [是否全部元素为null](Are all elements null)
     * @description zh - 是否全部元素为null
     * @description en - Are all elements null
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-09 20:02:45
     * @param iter 被检查的对象
     * @return boolean
     */
    public static boolean isAllNull(Iterable<?> iter) {
		return isAllNull(Constant.NULL == iter ? null : iter.iterator());
	}


    /**
     * [是否全部元素为null](Are all elements null)
     * @description zh - 是否全部元素为null
     * @description en - Are all elements null
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-09 20:04:59
     * @param iter 被检查的对象
     * @return boolean
     */
    public static boolean isAllNull(Iterator<?> iter) {
		if (Constant.NULL == iter) {
			return Constant.TRUE;
		}

		while (iter.hasNext()) {
			if (Constant.NULL != iter.next()) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}
}
