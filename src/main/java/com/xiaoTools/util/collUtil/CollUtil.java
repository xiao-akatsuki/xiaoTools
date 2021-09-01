package com.xiaoTools.util.collUtil;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.editor.Editor;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.core.filter.Filter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.compareUtil.CompareUtil;
import com.xiaoTools.util.listUtil.ListUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import javax.management.Query;

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

    /**
     * [多个集合的交集](Intersection of multiple sets)
     * @description zh - 多个集合的交集
     * @description en - Intersection of multiple sets
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:55:03
     * @param coll1 集合
     * @param coll2 集合
     * @param otherColls 其他集合
     * @return java.util.Collection<T>
     */
    @SafeVarargs
	public static <T> Collection<T> intersection(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
		Collection<T> intersection = intersection(coll1, coll2);
		if (isEmpty(intersection)) {
			return intersection;
		}
		for (Collection<T> coll : otherColls) {
			intersection = intersection(intersection, coll);
			if (isEmpty(intersection)) {
				return intersection;
			}
		}
		return intersection;
	}

    /**
     * [多个集合的交集](Intersection of multiple sets)
     * @description zh - 多个集合的交集
     * @description en - Intersection of multiple sets
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-29 19:57:20
     * @param coll1 集合
     * @param coll2 集合
     * @param otherColls 其他集合
     * @return java.util.Set<T>
     */
    @SafeVarargs
	public static <T> Set<T> intersectionDistinct(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
		final Set<T> result;
		if (isEmpty(coll1) || isEmpty(coll2)) {
			// 有一个空集合就直接返回空
			return new LinkedHashSet<>();
		} else {
			result = new LinkedHashSet<>(coll1);
		}

		if (ArrayUtil.isNotEmpty(otherColls)) {
			for (Collection<T> otherColl : otherColls) {
				if (isNotEmpty(otherColl)) {
					result.retainAll(otherColl);
				} else {
					// 有一个空集合就直接返回空
					return new LinkedHashSet<>();
				}
			}
		}

		result.retainAll(coll2);

		return result;
	}

    /* 差集 -------------------------------------------------------------- disjunction */

    /**
     * [两个集合的差集](Difference set of two sets)
     * @description zh - 两个集合的差集
     * @description en - Difference set of two sets
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 18:32:46
     * @param coll1 集合
     * @param coll2 集合
     * @return java.util.Collection<T>
     */
    public static <T> Collection<T> disjunction(Collection<T> coll1, Collection<T> coll2) {
		if (isEmpty(coll1)) {
			return coll2;
		}
		if (isEmpty(coll2)) {
			return coll1;
		}

		final List<T> result = new ArrayList<>();
		final Map<T, Integer> map1 = countMap(coll1);
		final Map<T, Integer> map2 = countMap(coll2);
		final Set<T> elts = newHashSet(coll2);
		elts.addAll(coll1);
		int m;
		for (T t : elts) {
			m = Math.abs(Convert.toInt(map1.get(t), Constant.ZERO) - Convert.toInt(map2.get(t), Constant.ZERO));
			for (int i = Constant.ZERO; i < m; i++) {
				result.add(t);
			}
		}
		return result;
	}

    /**
     * [计算集合的单差集](Single difference set of calculation set)
     * @description zh - 计算集合的单差集
     * @description en - Single difference set of calculation set
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 18:34:56
     * @param coll1 集合
     * @param coll2 集合
     * @return java.util.Collection<T>
     */
    public static <T> Collection<T> subtract(Collection<T> coll1, Collection<T> coll2) {
		final Collection<T> result = ObjectUtil.clone(coll1);
		result.removeAll(coll2);
		return result;
	}

    /**
     * [计算集合的单差集](Single difference set of calculation set)
     * @description zh - 计算集合的单差集
     * @description en - Single difference set of calculation set
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 18:38:05
     * @param coll1 集合
     * @param coll2 集合
     * @return java.util.List<T>
     */
    public static <T> List<T> subtractToList(Collection<T> coll1, Collection<T> coll2) {

		if (isEmpty(coll1)) {
			return ListUtil.empty();
		}
		if (isEmpty(coll2)) {
			return ListUtil.list(true, coll1);
		}

		//将被交数用链表储存，防止因为频繁扩容影响性能
		final List<T> result = new LinkedList<>();
		Set<T> set = new HashSet<>(coll2);
		for (T t : coll1) {
			if (false == set.contains(t)) {
				result.add(t);
			}
		}
		return result;
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
     * @param values: 元素数组
     * @return java.util.HashSet<T>
    */
    @SafeVarargs
    public static <T> HashSet<T> set(boolean isSorted, T... values) {
        if (Constant.NULL == values) {
            return isSorted ? new LinkedHashSet<>() : new HashSet<>();
        }
        int initialCapacity = Math.max((int) (values.length / .75f) + Constant.ONE, Constant.SIXTEEN);
        final HashSet<T> set = isSorted ? new LinkedHashSet<>(initialCapacity) : new HashSet<>(initialCapacity);
        Collections.addAll(set, values);
        return set;
    }

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description: zh - 新建一个HashSet
     * @description: en - Create a new HashSet
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:33 下午
     * @param values: 元素数组
     * @return java.util.HashSet<T>
    */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... values) {
        return set(false, values);
    }

    /**
     * [新建一个LinkedHashSet](Create a linkedhashset)
     * @description zh - 新建一个LinkedHashSet
     * @description en - Create a linkedhashset
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 14:40:59
     * @param values 元素数组
     * @return java.util.LinkedHashSet<T>
     */
    @SafeVarargs
	public static <T> LinkedHashSet<T> newLinkedHashSet(T... values) {
		return (LinkedHashSet<T>) set(Constant.TRUE, values);
	}

    /* 包含指定值 ----------------------------------------------------------- contains */

    /**
     * [判断指定集合是否包含指定值](Determines whether the specified set contains the specified value)
     * @description zh - 判断指定集合是否包含指定值
     * @description en - Determines whether the specified set contains the specified value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 18:47:50
     * @param collection 集合
     * @param value 需要查找的值
     * @return boolean
     */
    public static boolean contains(Collection<?> collection, Object value) {
		return isNotEmpty(collection) && collection.contains(value);
	}

    /**
     * [自定义函数判断集合是否包含某类值](A user-defined function determines whether a collection contains a certain type of value)
     * @description zh - 自定义函数判断集合是否包含某类值
     * @description en - A user-defined function determines whether a collection contains a certain type of value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 18:52:23
     * @param collection 集合
     * @param contain 自定义判断函数
     * @return boolean
     */
    public static <T> boolean contains(Collection<T> collection, Predicate<? super T> contain) {
		if (isEmpty(collection)) {
			return Constant.FALSE;
		}
		for (T t : collection) {
			if (contain.test(t)) {
				return Constant.TRUE;
			}
		}
		return Constant.FALSE;
	}

    /**
     * [两个集合是否至少有一个共同的元素](Do two collections have at least one element in common)
     * @description zh - 两个集合是否至少有一个共同的元素
     * @description en - Do two collections have at least one element in common
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 18:56:56
     * @param coll1 集合
     * @param coll2 集合
     * @return boolean
     */
    public static boolean containsAny(Collection<?> coll1, Collection<?> coll2) {
		if (isEmpty(coll1) || isEmpty(coll2)) {
			return Constant.FALSE;
		}
		if (coll1.size() < coll2.size()) {
			for (Object object : coll1) {
				if (coll2.contains(object)) {
					return Constant.TRUE;
				}
			}
		} else {
			for (Object object : coll2) {
				if (coll1.contains(object)) {
					return Constant.TRUE;
				}
			}
		}
		return Constant.FALSE;
	}

    /** 
     * [集合2是否为集合1的子集](Is set 2 a subset of set 1)
     * @description zh - 集合2是否为集合1的子集
     * @description en - Is set 2 a subset of set 1
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 18:59:32
     * @param coll1 集合
     * @param coll2 集合
     * @return boolean
     */
    public static boolean containsAll(Collection<?> coll1, Collection<?> coll2) {
		if (isEmpty(coll1)) {
			return isEmpty(coll2);
		}

		if (isEmpty(coll2)) {
			return Constant.TRUE;
		}

		if (coll1.size() < coll2.size()) {
			return Constant.FALSE;
		}

		for (Object object : coll2) {
			if (Constant.FALSE == coll1.contains(object)) {
				return Constant.FALSE;
			}
		}
		return Constant.TRUE;
	}

    /**
     * [根据集合返回一个元素计数的Map](Returns a map of element counts based on the collection)
     * @description zh - 根据集合返回一个元素计数的Map
     * @description en - Returns a map of element counts based on the collection
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 19:00:59
     * @param collection 集合
     * @return java.util.Map<T, Integer>
     */
    public static <T> Map<T, Integer> countMap(Iterable<T> collection) {
		return IterUtil.countMap(Constant.NULL == collection ? Constant.NULL : collection.iterator());
	}

    /*转换-----------------------------------------------------------join*/

    /**
     * [以 conjunction 为分隔符将集合转换为字符串](Converts a collection to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将集合转换为字符串
     * @description en - Converts a collection to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 19:43:33
     * @param iterable 集合元素类型
     * @param conjunction 分隔符
     * @return java.lang.String
     */
    public static <T> String join(Iterable<T> iterable, CharSequence conjunction) {
        return Constant.NULL == iterable ? Constant.STRING_NULL : IterUtil.join(iterable.iterator(), conjunction);
	}

    /**
     * [以 conjunction 为分隔符将集合转换为字符串](Converts a collection to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将集合转换为字符串
     * @description en - Converts a collection to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 19:46:41
	 * @param iterable Iterable
	 * @param conjunction 分隔符
	 * @param prefix 每个元素添加的前缀，null表示不添加
	 * @param suffix 每个元素添加的后缀，null表示不添加
     * @return java.lang.String
     */
    public static <T> String join(Iterable<T> iterable, CharSequence conjunction, String prefix, String suffix) {
        return Constant.NULL = iterable ? Constant.STRING_NULL : IterUtil.join(iterable.iterator(), conjunction, prefix, suffix);
	}

    /*切取部分数据----------------------------------------------------------- Cut */

    /**
     * [切取部分数据](Cut some data)
     * @description zh - 切取部分数据
     * @description en - Cut some data
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 19:49:52
     * @param surplusAlaDatas 原数据
     * @param partSize 每部分数据的长度
     * @return java.util.List<T>
     */
    public static <T> List<T> popPart(Stack<T> surplusAlaDatas, int partSize) {
		if (isEmpty(surplusAlaDatas)) {
			return ListUtil.empty();
		}

		final List<T> currentAlaDatas = new ArrayList<>();
		int size = surplusAlaDatas.size();
		// 切割
		if (size > partSize) {
			for (int i = Constant.ZERO; i < partSize; i++) {
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		} else {
			for (int i = Constant.ZERO; i < size; i++) {
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		}
		return currentAlaDatas;
	}

    /**
     * [切取部分数据](Cut some data)
     * @description zh - 切取部分数据
     * @description en - Cut some data
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 19:53:00
     * @param surplusAlaDatas 原数据
     * @param partSize 每部分数据的长度
     * @return java.util.List<T>
     */
    public static <T> List<T> popPart(Deque<T> surplusAlaDatas, int partSize) {
		if (isEmpty(surplusAlaDatas)) {
			return ListUtil.empty();
		}

		final List<T> currentAlaDatas = new ArrayList<>();
		int size = surplusAlaDatas.size();
		// 切割
		if (size > partSize) {
			for (int i = Constant.ZERO; i < partSize; i++) {
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		} else {
			for (int i = Constant.ZERO; i < size; i++) {
				currentAlaDatas.add(surplusAlaDatas.pop());
			}
		}
		return currentAlaDatas;
	}

    /*新建一个HashMap-----------------------------------------------------------new HashMap*/

    /**
     * [新建一个HashMap](Create a new HashMap)
     * @description zh - 新建一个HashMap
     * @description en - Create a new HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 19:54:27
     * @return java.util.HashMap<K, V>
     */
    public static <K, V> HashMap<K, V> newHashMap() {
		return MapUtil.newHashMap();
	}

    /**
     * [新建一个HashMap](Create a new HashMap)
     * @description zh - 新建一个HashMap
     * @description en - Create a new HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 19:55:58
     * @param size 初始大小
     * @param isOrder Map的Key是否有序
     * @return java.util.HashMap<K, V>
     */
	public static <K, V> HashMap<K, V> newHashMap(int size, boolean isOrder) {
		return MapUtil.newHashMap(size, isOrder);
	}

    /**
     * [新建一个HashMap](Create a new HashMap)
     * @description zh - 新建一个HashMap
     * @description en - Create a new HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-30 19:56:40
     * @param size 初始大小
     * @return java.util.HashMap<K, V>
     */
	public static <K, V> HashMap<K, V> newHashMap(int size) {
		return MapUtil.newHashMap(size);
	}

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description zh - 新建一个HashSet
     * @description en - Create a new HashSet
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 14:50:11
     * @param collection 集合
     * @return java.util.HashSet<T>
     */
	public static <T> HashSet<T> newHashSet(Collection<T> collection) {
		return newHashSet(Constant.FALSE, collection);
	}

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description zh - 新建一个HashSet
     * @description en - Create a new HashSet
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 14:51:56
     * @param isSorted 是否有序
     * @param collection 集合
     * @return java.util.HashSet<T>
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<T> collection) {
		return isSorted ? new LinkedHashSet<>(collection) : new HashSet<>(collection);
	}

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description zh - 新建一个HashSet
     * @description en - Create a new HashSet
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 14:57:35
     * @param isSorted 是否有序
     * @param iter Iterator
     * @return java.util.HashSet<T>
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Iterator<T> iter) {
		if (Constant.NULL == iter) {
			return set(isSorted, (T[]) null);
		}
		final HashSet<T> set = isSorted ? new LinkedHashSet<>() : new HashSet<>();
		while (iter.hasNext()) {
			set.add(iter.next());
		}
		return set;
	}

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description zh - 新建一个HashSet
     * @description en - Create a new HashSet
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 15:03:03
     * @param isSorted
     * @param enumeration
     * @return java.util.HashSet<T>
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Enumeration<T> enumeration) {
		if (Constant.NULL == enumeration) {
			return set(isSorted, (T[]) null);
		}
		final HashSet<T> set = isSorted ? new LinkedHashSet<>() : new HashSet<>();
		while (enumeration.hasMoreElements()) {
			set.add(enumeration.nextElement());
		}
		return set;
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


    /*新建一个List-----------------------------------------------------------List*/

    /**
     * [新建一个空List](Create an empty list)
     * @description zh - 新建一个空List
     * @description en - Create an empty list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 15:11:22
     * @param isLinked 是否新建LinkedList
     * @return java.util.List<T>
     */
    public static <T> List<T> list(boolean isLinked) {
		return ListUtil.list(isLinked);
	}

    /**
     * [新建一个空List](Create an empty list)
     * @description zh - 新建一个空List
     * @description en - Create an empty list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 15:15:07
     * @param isLinked 是否新建LinkedList
     * @param values 数组
     * @return java.util.List<T>
     */
    @SafeVarargs
	public static <T> List<T> list(boolean isLinked, T... values) {
		return ListUtil.list(isLinked, values);
	}

    /**
     * [新建一个List](Create an list)
     * @description zh - 新建一个List
     * @description en - Create an list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 15:19:26
     * @param isLinked 是否新建LinkedList
     * @param collection 集合
     * @return java.util.List<T>
     */
    public static <T> List<T> list(boolean isLinked, Collection<T> collection) {
		return ListUtil.list(isLinked, collection);
	}

    /**
     * [新建一个List](Create an list)
     * @description zh - 新建一个List
     * @description en - Create an list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 15:22:46
     * @param isLinked 是否新建LinkedList
     * @param iterable Iterable
     * @return java.util.List<T>
     */
    public static <T> List<T> list(boolean isLinked, Iterable<T> iterable) {
		return ListUtil.list(isLinked, iterable);
	}

    /**
     * [新建一个ArrayList](Create an ArrayList)
     * @description zh - 新建一个ArrayList
     * @description en - Create an ArrayList
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 15:24:31
     * @param isLinked 是否新建LinkedList
     * @param iter Iterator
     * @return java.util.List<T>
     */
    public static <T> List<T> list(boolean isLinked, Iterator<T> iter) {
		return ListUtil.list(isLinked, iter);
	}

    /**
     * [新建一个List](Create an list)
     * @description zh - 新建一个List
     * @description en - Create an list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 15:26:15
     * @param isLinked 是否新建LinkedList
     * @param enumeration Enumeration
     * @return java.util.List<T>
     */
    public static <T> List<T> list(boolean isLinked, Enumeration<T> enumeration) {
		return ListUtil.list(isLinked, enumeration);
	}

    /*数组转为ArrayList-----------------------------------------------------------toList*/

    /**
     * [数组转为ArrayList](Convert array to ArrayList)
     * @description zh - 数组转为ArrayList
     * @description en - Convert array to ArrayList
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 18:50:45
     * @param values 数组
     * @return java.util.ArrayList<T>
     */
    @SafeVarargs
	public static <T> ArrayList<T> toList(T... values) {
		return ListUtil.toList(values);
	}

    /* 新建LinkedList ----------------------------------------------------------- new LinkedList */    

    /**
     * [新建LinkedList](New LinkedList)
     * @description zh - 新建LinkedList
     * @description en - New LinkedList
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 18:55:59
     * @param values 数组
     * @return java.util.LinkedList<T>
     */
    @SafeVarargs
	public static <T> LinkedList<T> newLinkedList(T... values) {
		return ListUtil.toLinkedList(values);
	}

    /**
     * [新建一个CopyOnWriteArrayList](Create a new copyonwritearraylist)
     * @description zh - 新建一个CopyOnWriteArrayList
     * @description en - Create a new copyonwritearraylist
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 19:02:36
     * @param collection 集合
     * @return java.util.concurrent.CopyOnWriteArrayList<T>
     */
    public static <T> CopyOnWriteArrayList<T> newCopyOnWriteArrayList(Collection<T> collection) {
		return ListUtil.toCopyOnWriteArrayList(collection);
	}

    /**
     * [新建 BlockingQueue](New BlockingQueue)
     * @description zh - 新建 BlockingQueue
     * @description en - New BlockingQueue
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 19:09:00
     * @param capacity 容量
     * @param isLinked 是否为链表形式
     * @return java.util.concurrent.BlockingQueue<T>
     */
    public static <T> BlockingQueue<T> newBlockingQueue(int capacity, boolean isLinked) {
		BlockingQueue<T> queue;
        queue = isLinked ? new LinkedBlockingDeque<>(capacity) : new ArrayBlockingQueue<>(capacity);
		return queue;
	}

    /*创建-----------------------------------------------------------create*/

    /**
     * [创建新的集合对象](Create a new collection object)
     * @description zh - 创建新的集合对象
     * @description en - Create a new collection object
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 19:36:42
     * @param collectionType 集合类型
     * @return java.util.Collection<T>
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> Collection<T> create(Class<?> collectionType) {
		Collection<T> list;
		if (collectionType.isAssignableFrom(AbstractCollection.class)) {
			// 抽象集合默认使用ArrayList
			list = new ArrayList<>();
		}

		// Set
		else if (collectionType.isAssignableFrom(HashSet.class)) {
			list = new HashSet<>();
		} else if (collectionType.isAssignableFrom(LinkedHashSet.class)) {
			list = new LinkedHashSet<>();
		} else if (collectionType.isAssignableFrom(TreeSet.class)) {
			list = new TreeSet<>((o1, o2) -> {
				// 优先按照对象本身比较，如果没有实现比较接口，默认按照toString内容比较
				if (o1 instanceof Comparable) {
					return ((Comparable<T>) o1).compareTo(o2);
				}
				return CompareUtil.compare(o1.toString(), o2.toString());
			});
		} else if (collectionType.isAssignableFrom(EnumSet.class)) {
			list = (Collection<T>) EnumSet.noneOf((Class<Enum>) ClassUtil.getTypeArgument(collectionType));
		}

		// List
		else if (collectionType.isAssignableFrom(ArrayList.class)) {
			list = new ArrayList<>();
		} else if (collectionType.isAssignableFrom(LinkedList.class)) {
			list = new LinkedList<>();
		}

		// Others，直接实例化
		else {
			try {
				list = (Collection<T>) ReflectUtil.newInstance(collectionType);
			} catch (Exception e) {
				throw new UtilException(e);
			}
		}
		return list;
	}

    /**
     * [创建Map](Create map)
     * @description zh - 创建Map
     * @description en - Create map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 19:38:03
     * @param mapType map类型
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> createMap(Class<?> mapType) {
		return MapUtil.createMap(mapType);
	}

    /*去重-----------------------------------------------------------distinct*/

    /**
     * [去重集合](De duplication set)
     * @description zh - 去重集合
     * @description en - De duplication set
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 19:43:16
     * @param collection 集合
     * @return java.util.ArrayList<T>
     */
    public static <T> ArrayList<T> distinct(Collection<T> collection) {
        return isEmpty(collection) ? new ArrayList<>() : 
                collection instanceof Set ? new ArrayList<>(collection) : 
                new ArrayList<>(new LinkedHashSet<>(collection));
	}

    /*截取-----------------------------------------------------------sub*/

    /**
     * [截取集合的部分](Intercepts part of the set)
     * @description zh - 截取集合的部分
     * @description en - Intercepts part of the set
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 19:53:00
     * @param list 被截取的数组
     * @param start 开始位置（包含）
     * @param end 结束位置（不包含）
     * @return java.util.List<T>
     */
    public static <T> List<T> sub(List<T> list, int start, int end) {
		return ListUtil.sub(list, start, end);
	}

    /**
     * [截取集合的部分](Intercepts part of the set)
     * @description zh - 截取集合的部分
     * @description en - Intercepts part of the set
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 19:58:02
     * @param list 被截取的数组
     * @param start 开始位置（包含）
     * @param end 结束位置（不包含）
     * @param step 步进
     * @return java.util.List<T>
     */
    public static <T> List<T> sub(List<T> list, int start, int end, int step) {
		return ListUtil.sub(list, start, end, step);
	}

    /**
     * [截取集合的部分](Intercepts part of the set)
     * @description zh - 截取集合的部分
     * @description en - Intercepts part of the set
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-08-31 20:03:02
     * @param collection 被截取的数组
     * @param start 开始位置（包含）
     * @param end 结束位置（不包含）
     * @return java.util.List<T>
     */
    public static <T> List<T> sub(Collection<T> collection, int start, int end) {
		return sub(collection, start, end, Constant.ONE);
	}

    /**
     * [截取集合的部分](Intercepts part of the set)
     * @description zh - 截取集合的部分
     * @description en - Intercepts part of the set
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 11:28:20
     * @param list 被截取的数组
     * @param start 开始的位置
     * @param end 结束的位置
     * @param step 步进
     * @return java.util.List<T>
     */
    public static <T> List<T> sub(Collection<T> list, int start, int end, int step) {
        return list == null || list.isEmpty() ? ListUtil.empty() : sub(new ArrayList<>(list), start, end, step);
	}

    /*切分-----------------------------------------------------------split*/

    /**
     * [对集合按照指定长度分段](Segments the set by the specified length)
     * @description zh - 对集合按照指定长度分段
     * @description en - Segments the set by the specified length
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 11:34:47
     * @param list 集合
     * @param size 切分的长度
     * @return java.util.List<T>
     */
    public static <T> List<List<T>> splitList(List<T> list, int size) {
		return ListUtil.split(list, size);
	}

    /**
     * [对集合按照指定长度分段](Segments the set by the specified length)
     * @description zh - 对集合按照指定长度分段
     * @description en - Segments the set by the specified length
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 11:37:42
     * @param collection 集合
     * @param size 切分的长度
     * @return java.util.List<T>
     */
    public static <T> List<List<T>> split(Collection<T> collection, int size) {
		final List<List<T>> result = new ArrayList<>();
		if (CollUtil.isEmpty(collection)) {
			return result;
		}

		ArrayList<T> subList = new ArrayList<>(size);
		for (T t : collection) {
			if (subList.size() >= size) {
				result.add(subList);
				subList = new ArrayList<>(size);
			}
			subList.add(t);
		}
		result.add(subList);
		return result;
	}

    /*过滤-----------------------------------------------------------filter*/

    /**
     * [过滤，此方法产生一个新集合](Filter, this method generates a new collection)
     * @description zh - 过滤，此方法产生一个新集合
     * @description en - Filter, this method generates a new collection
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 11:43:53
     * @param collection 集合
     * @param editor 过滤接口
     * @return java.util.Collection<T>
     */
    public static <T> Collection<T> filter(Collection<T> collection, Editor<T> editor) {
		if (Constant.NULL == collection || Constant.NULL == editor) {
			return collection;
		}

		Collection<T> collection2 = ObjectUtil.clone(collection);
		try {
			collection2.clear();
		} catch (UnsupportedOperationException e) {
			// 克隆后的对象不支持清空，说明为不可变集合对象，使用默认的ArrayList保存结果
			collection2 = new ArrayList<>();
		}

		T modified;
		for (T t : collection) {
			modified = editor.edit(t);
			if (Constant.NULL != modified) {
				collection2.add(modified);
			}
		}
		return collection2;
	}

    /**
     * [过滤](filter)
     * @description zh - 过滤
     * @description en - filter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 11:45:10
     * @param list 集合
     * @param editor 编辑器接口
     * @return java.util.List<T>
     */
    public static <T> List<T> filter(List<T> list, Editor<T> editor) {
		return ListUtil.filter(list, editor);
	}

    /**
     * [过滤](filter)
     * @description zh - 过滤
     * @description en - filter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 11:48:27
     * @param collection 集合
     * @param filter 过滤器
     * @return java.util.Collection<T>
     */
    public static <T> Collection<T> filterNew(Collection<T> collection, Filter<T> filter) {
		if (Constant.NULL == collection || Constant.NULL == filter) {
			return collection;
		}

		Collection<T> collection2 = ObjectUtil.clone(collection);
		try {
			collection2.clear();
		} catch (UnsupportedOperationException e) {
			// 克隆后的对象不支持清空，说明为不可变集合对象，使用默认的ArrayList保存结果
			collection2 = new ArrayList<>();
		}

		for (T t : collection) {
			if (filter.accept(t)) {
				collection2.add(t);
			}
		}
		return collection2;
	}

    /**
     * [过滤](filter)
     * @description zh - 过滤
     * @description en - filter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 11:50:28
     * @param list 集合
     * @param filter 过滤器
     * @return java.util.List<T>
     */
    public static <T> List<T> filterNew(List<T> list, Filter<T> filter) {
		return ListUtil.filter(list, t -> filter.accept(t) ? t : null);
	}

    /**
     * [去除指定元素](Removes the specified element)
     * @description zh - 去除指定元素
     * @description en - Removes the specified element
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 12:02:03
     * @param collection 集合
     * @param filter 过滤器
     * @return T
     */
    public static <T extends Collection<E>, E> T filter(T collection, final Filter<E> filter) {
		return IterUtil.filter(collection, filter);
	}

    /* 去掉 -----------------------------------------------------------remove*/    

    /**
     * [去掉集合中的多个元素](Remove multiple elements from the collection)
     * @description zh - 去掉集合中的多个元素
     * @description en - Remove multiple elements from the collection
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 11:59:53
     * @param collection 集合
     * @param elesRemoved 被去掉的元素数组
     * @return T
     */
    @SuppressWarnings("unchecked")
	public static <T extends Collection<E>, E> T removeAny(T collection, E... elesRemoved) {
		collection.removeAll(newHashSet(elesRemoved));
		return collection;
	}


}
