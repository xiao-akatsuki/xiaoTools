package com.xiaoTools.util.collUtil;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.listUtil.ListUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

import java.util.*;
import java.util.function.Predicate;

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


}
