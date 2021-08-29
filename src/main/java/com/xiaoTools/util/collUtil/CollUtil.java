package com.xiaoTools.util.collUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.listUtil.ListUtil;

import java.util.*;

/**
 * [集合相关工具类](Collection related tool classes)
 * @description: zh - 集合相关工具类
 * @description: en - Collection related tool classes
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/24 5:25 下午
*/
public class CollUtil {

    /* 空集合 -------------------------------------------------------------- empty aggregate */

    /**
     * [空集合使用](Empty collection use)
     * @description zh - 空集合使用
     * @description en - Empty collection use
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:24:18
     * @param set 提供的集合
     * @return java.util.Set<T>
     */
    public static <T> Set<T> emptyIfNull(Set<T> set) {
		return (Constant.NULL == set) ? Collections.emptySet() : set;
	}

    /**
     * [空集合使用](Empty collection use)
     * @description zh - 空集合使用
     * @description en - Empty collection use
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:26:14
     * @param set 提供的集合
     * @return java.util.List<T>
     */
    public static <T> List<T> emptyIfNull(List<T> set) {
		return (Constant.NULL == set) ? Collections.emptyList() : set;
	}



    /* 新建 -------------------------------------------------------------- set HashSet */

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description: zh - 新建一个HashSet
     * @description: en - Create a new HashSet
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:31 下午
     * @param isSorted: 是否有序，有序返回 LinkedHashSet，否则返回 HashSet
     * @param ts: 元素数组
     * @return java.util.HashSet<T>
    */
    @SafeVarargs
    public static <T> HashSet<T> set(boolean isSorted, T... ts) {
        if (Constant.NULL == ts) {
            return isSorted ? new LinkedHashSet<>() : new HashSet<>();
        }
        int initialCapacity = Math.max((int) (ts.length / .75f) + Constant.ONE, Constant.SIXTEEN);
        final HashSet<T> set = isSorted ? new LinkedHashSet<>(initialCapacity) : new HashSet<>(initialCapacity);
        Collections.addAll(set, ts);
        return set;
    }

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description: zh - 新建一个HashSet
     * @description: en - Create a new HashSet
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:33 下午
     * @param ts: 元素数组
     * @return java.util.HashSet<T>
    */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        return set(false, ts);
    }

    /*新建一个ArrayList-----------------------------------------------------------new Array List*/

    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 8:17 下午
     * @param values: 数组
     * @return java.util.ArrayList<T>
    */
    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... values) {
        return ListUtil.toList(values);
    }

    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 8:22 下午
     * @param collection: 集合
     * @return java.util.ArrayList<T>
    */
    public static <T> ArrayList<T> newArrayList(Collection<T> collection) {
        return ListUtil.toList(collection);
    }
    
    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 8:14 下午
     * @param iterable: Iterable
     * @return java.util.ArrayList<T>
    */
    public static <T> ArrayList<T> newArrayList(Iterable<T> iterable) {
        return ListUtil.toList(iterable);
    }

    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 8:25 下午
     * @param iterator: Iterator
     * @return java.util.ArrayList<T>
    */
    public static <T> ArrayList<T> newArrayList(Iterator<T> iterator) {
        return ListUtil.toList(iterator);
    }

    /**
     * [新建一个ArrayList](Create a new ArrayList)
     * @description: zh - 新建一个ArrayList
     * @description: en - Create a new ArrayList
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 8:29 下午
     * @param enumeration: Enumeration
     * @return java.util.ArrayList<T>
    */
    public static <T> ArrayList<T> newArrayList(Enumeration<T> enumeration) {
        return ListUtil.toList(enumeration);
    }


}
