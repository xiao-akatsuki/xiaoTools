package com.xiaoTools.util.collUtil;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
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

    /* 并集 -------------------------------------------------------------- union */

    /**
     * [两个集合的并集](Union of two sets)
     * @description zh - 两个集合的并集
     * @description en - Union of two sets
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:29:09
     * @param coll1 集合1
     * @param coll2 集合2
     * @return java.util.Collection<T>
     */
    public static <T> Collection<T> union(Collection<T> coll1, Collection<T> coll2) {
		if (isEmpty(coll1)) {
			return new ArrayList<>(coll2);
		} else if (isEmpty(coll2)) {
			return new ArrayList<>(coll1);
		}

		final ArrayList<T> list = new ArrayList<>(Math.max(coll1.size(), coll2.size()));
		final Map<T, Integer> map1 = countMap(coll1);
		final Map<T, Integer> map2 = countMap(coll2);
		final Set<T> elts = newHashSet(coll2);
		elts.addAll(coll1);
		int m;
		for (T t : elts) {
			m = Math.max(Convert.toInt(map1.get(t), Constant.ZERO), Convert.toInt(map2.get(t), Constant.ZERO));
			for (int i = Constant.ZERO; i < m; i++) {
				list.add(t);
			}
		}
		return list;
	}

    /**
     * [多个集合的并集](Union of multiple sets)
     * @description zh - 多个集合的并集
     * @description en - Union of multiple sets
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:33:01
     * @param coll1 集合1
     * @param coll2 集合2
     * @param otherColls 其它集合
     * @return java.util.Collection<T>
     */
    @SafeVarargs
	public static <T> Collection<T> union(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
		Collection<T> union = union(coll1, coll2);
		for (Collection<T> coll : otherColls) {
			union = union(union, coll);
		}
		return union;
	}

    /**
     * [多个集合的非重复并集](Non repeating union of multiple sets)
     * @description zh - 多个集合的非重复并集
     * @description en - Non repeating union of multiple sets
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:36:02
     * @param coll1 集合1
     * @param coll2 集合2
     * @param otherColls 其它集合
     * @return java.util.Set<T>
     */
	@SafeVarargs
	public static <T> Set<T> unionDistinct(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
		final Set<T> result;
        result = isEmpty(coll1) ? new LinkedHashSet<>() : 
                 new LinkedHashSet<>(coll1);

		if (isNotEmpty(coll2)) {
			result.addAll(coll2);
		}

		if (ArrayUtil.isNotEmpty(otherColls)) {
			for (Collection<T> otherColl : otherColls) {
				result.addAll(otherColl);
			}
		}

		return result;
	}

    /**
     * [多个集合的完全并集](Complete union of multiple sets)
     * @description zh - 多个集合的完全并集
     * @description en - Complete union of multiple sets
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:49:04
     * @param coll1 集合1
     * @param coll2 集合2
     * @param otherColls 其它集合
     * @return java.util.List<T>
     */
    @SafeVarargs
	public static <T> List<T> unionAll(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
		final List<T> result;
        result = isEmpty(coll1) ? new ArrayList<>() :
                 new ArrayList<>(coll1);

		if (isNotEmpty(coll2)) {
			result.addAll(coll2);
		}

		if (ArrayUtil.isNotEmpty(otherColls)) {
			for (Collection<T> otherColl : otherColls) {
				result.addAll(otherColl);
			}
		}

		return result;
	}

    /* 交集 -------------------------------------------------------------- intersection */

    /**
     * [两个集合的交集](Intersection of two sets)
     * @description zh - 两个集合的交集
     * @description en - Intersection of two sets
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:51:12
     * @param coll1
     * @param coll2
     * @return java.util.Collection<T>
     */
    public static <T> Collection<T> intersection(Collection<T> coll1, Collection<T> coll2) {
		if (isNotEmpty(coll1) && isNotEmpty(coll2)) {
			final ArrayList<T> list = new ArrayList<>(Math.min(coll1.size(), coll2.size()));
			final Map<T, Integer> map1 = countMap(coll1);
			final Map<T, Integer> map2 = countMap(coll2);
			final Set<T> elts = newHashSet(coll2);
			int m;
			for (T t : elts) {
				m = Math.min(Convert.toInt(map1.get(t), Constant.ZERO), Convert.toInt(map2.get(t), Constant.ZERO));
				for (int i = Constant.ZERO; i < m; i++) {
					list.add(t);
				}
			}
			return list;
		}

		return new ArrayList<>();
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
