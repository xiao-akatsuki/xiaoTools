package com.xiaoTools.util.mapUtil;

import java.util.Map;

import com.xiaoTools.lang.constant.Constant;

/**
 * [Map相关工具类](Map related tool classes)
 * @description zh - Map相关工具类
 * @description en - Map related tool classes
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-21 08:24:04
 */
public class MapUtil {

    /**
     * [默认初始大小](Default initial size)
     * @description zh - 默认初始大小
     * @description en - Default initial size
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-21 08:25:38
     */
    public static final int DEFAULT_INITIAL_CAPACITY = Constant.SIXTEEN;

    /**
     * [默认增长因子](Default growth factor)
     * @description zh - 默认增长因子 
     * @description en - Default growth factor
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-21 08:26:31
     */
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /* Map是否为空 ----------------------------------------------------------- is Empty */

    /**
     * [Map是否为空](Is the map empty)
     * @description zh - Map是否为空
     * @description en - Is the map empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-21 14:35:51
     * @param map 集合
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
		return Constant.NULL == map || map.isEmpty();
	}

    /**
     * [Map是否为非空](Is the map non empty)
     * @description zh - Map是否为非空
     * @description en - Is the map non empty
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-21 14:39:40
     * @param map 集合
     * @return boolean
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
		return Constant.NULL != map && Constant.FALSE == map.isEmpty();
	}
}
