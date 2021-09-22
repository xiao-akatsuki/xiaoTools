package com.xiaoTools.util.mapUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    /**
     * [一个不可变的默认空集合](An immutable default empty collection)
     * @description zh - 一个不可变的默认空集合
     * @description en - An immutable default empty collection
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-21 14:42:09
     * @param set 集合
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> emptyIfNull(Map<K, V> set) {
		return (Constant.NULL == set) ? Collections.emptyMap() : set;
	}

    /**
     * [如果给定Map为空，返回默认Map](If the given map is empty, the default map is returned)
     * @description zh - 如果给定Map为空，返回默认Map
     * @description en - If the given map is empty, the default map is returned
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-21 14:45:28
     * @param map 集合
     * @param defaultMap 默认集合
     * @return T
     */
    public static <T extends Map<K, V>, K, V> T defaultIfEmpty(T map, T defaultMap) {
		return isEmpty(map) ? defaultMap : map;
	}

    /* 新建一个HashMap ----------------------------------------------------------- new HashMap */

    /**
     * [新建一个HashMap](Create a new HashMap)
     * @description zh - 新建一个HashMap
     * @description en - Create a new HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 08:27:13
     */
    public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<>();
	}

    /**
     * [新建一个HashMap](Create a new HashMap)
     * @description zh - 新建一个HashMap
     * @description en - Create a new HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 08:32:04
     * @param size 初始大小
     * @param isOrder Map的Key是否有序
     * @return java.util.HashMap<K, V>
     */
    public static <K, V> HashMap<K, V> newHashMap(int size, boolean isOrder) {
		int initialCapacity = (int) (size / DEFAULT_LOAD_FACTOR) + Constant.ONE;
		return isOrder ? new LinkedHashMap<>(initialCapacity) : new HashMap<>(initialCapacity);
	}

    /**
     * [新建一个HashMap](Create a new HashMap)
     * @description zh - 新建一个HashMap
     * @description en - Create a new HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 08:33:46
     * @param size 初始大小
     * @return java.util.HashMap<K, V>
     */
    public static <K, V> HashMap<K, V> newHashMap(int size) {
		return newHashMap(size, Constant.FALSE);
	}

}
