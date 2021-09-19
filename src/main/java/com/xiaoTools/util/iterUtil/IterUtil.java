package com.xiaoTools.util.iterUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import com.xiaoTools.core.collection.enumerationIter.EnumerationIter;
import com.xiaoTools.core.escape.filter.Filter;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.regularUtil.method.Func1;
import com.xiaoTools.util.strUtil.StrUtil;

import jdk.javadoc.internal.doclets.formats.html.resources.standard;

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

    /* 总数 -------------------------------------------------------------- count */ 

    /**
     * [根据集合返回一个元素计数的 Map](Returns a map of element counts based on the collection)
     * @description zh - 根据集合返回一个元素计数的 Map
     * @description en - Returns a map of element counts based on the collection
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-10 20:32:29
     * @param iterator Iterator对象
     * @return java.util.Map<T, Integer>
     */
    public static <T> Map<T, Integer> countMap(Iterator<T> iterator) {
		final HashMap<T, Integer> countMap = new HashMap<>();
		if (Constant.NULL != iterator) {
			Integer count;
			T t;
			while (iterator.hasNext()) {
				t = iterator.next();
				count = countMap.get(t);
				if (Constant.NULL == count) {
					countMap.put(t, Constant.ONE);
				} else {
					countMap.put(t, count + Constant.ONE);
				}
			}
		}
		return countMap;
	}

    /** 
     * [字段值与列表值对应的Map](Map corresponding to field value and list value)
     * @description zh - 字段值与列表值对应的Map
     * @description en - Map corresponding to field value and list value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-10 20:35:31
     * @param iterator Iterator对象
     * @param fieldName 字段名
     * @return java.util.Map<K, V>
     */
    @SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> fieldValueMap(Iterator<V> iterator, String fieldName) {
		return toMap(iterator, new HashMap<>(), (value) -> (K) ReflectUtil.getFieldValue(value, fieldName));
	}

    /**
     * [字段值与列表值对应的Map](Map corresponding to field value and list value)
     * @description zh - 字段值与列表值对应的Map
     * @description en - Map corresponding to field value and list value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-10 20:44:55 
     * @param iterator 对象列表
     * @param fieldNameForKey 做为键的字段名
     * @param fieldNameForValue 做为值的字段名
     * @return java.util.Map<K, V>
     */
    @SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> fieldValueAsMap(Iterator<?> iterator, String fieldNameForKey, String fieldNameForValue) {
		return toMap(iterator, new HashMap<>(),
				(value) -> (K) ReflectUtil.getFieldValue(value, fieldNameForKey),
				(value) -> (V) ReflectUtil.getFieldValue(value, fieldNameForValue)
		);
	}

    /**
     * [获取指定Bean列表中某个字段，生成新的列表](Gets a field in the specified bean list and generates a new list)
     * @description zh - 获取指定Bean列表中某个字段，生成新的列表
     * @description en - Gets a field in the specified bean list and generates a new list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-10 20:46:03
     * @param iterable 对象列表
     * @param fieldName 字段名
     * @return java.util.List<Object>
     */
    public static <V> List<Object> fieldValueList(Iterable<V> iterable, String fieldName) {
		return fieldValueList(Constant.NULL == iterable ? null : iterable.iterator(), fieldName);
	}

    /**
     * [获取指定Bean列表中某个字段，生成新的列表](Gets a field in the specified bean list and generates a new list)
     * @description zh - 获取指定Bean列表中某个字段，生成新的列表
     * @description en - Gets a field in the specified bean list and generates a new list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-10 20:47:17
     * @param iterable 对象列表
     * @param fieldName 字段名
     * @return java.util.List<Object>
     */
    public static <V> List<Object> fieldValueList(Iterator<V> iter, String fieldName) {
		final List<Object> result = new ArrayList<>();
		if (Constant.NULL != iter) {
			V value;
			while (iter.hasNext()) {
				value = iter.next();
				result.add(ReflectUtil.getFieldValue(value, fieldName));
			}
		}
		return result;
	}

    /* 分页 -------------------------------------------------------------- join */ 

    /**
     * [以 conjunction 为分隔符将集合转换为字符串](Converts a collection to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将集合转换为字符串
     * @description en - Converts a collection to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-14 21:55:33
     * @param iterator 集合
     * @param conjunction 分隔符
     * @return java.lang.String
     */
    public static <T> String join(Iterator<T> iterator, CharSequence conjunction) {
		return join(iterator, conjunction, Constant.STRING_NULL, Constant.STRING_NULL);
	}

    /**
     * [以 conjunction 为分隔符将集合转换为字符串](Converts a collection to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将集合转换为字符串
     * @description en - Converts a collection to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-14 22:00:45
     * @param iterator 集合
     * @param conjunction 分隔符
     * @param prefix 每个元素添加的前缀，null表示不添加
     * @param suffix 每个元素添加的后缀，null表示不添加
     */
    public static <T> String join(Iterable<T> iterable, CharSequence conjunction, String prefix, String suffix) {
        return Constant.NULL == iterable ? Constant.STRING_NULL : join(iterable.iterator(), conjunction, prefix, suffix);
	}


    /**
     * [以 conjunction 为分隔符将集合转换为字符串](Converts a collection to a string with a conjunction as a delimiter)
     * @description zh - 以 conjunction 为分隔符将集合转换为字符串
     * @description en - Converts a collection to a string with a conjunction as a delimiter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-14 21:58:46
     * @param iterator 集合
     * @param conjunction 分隔符
     * @param prefix 每个元素添加的前缀，null表示不添加
     * @param suffix 每个元素添加的后缀，null表示不添加
     * @return java.lang.String
     */
    public static <T> String join(Iterator<T> iterator, CharSequence conjunction, String prefix, String suffix) {
		if (Constant.NULL == iterator) {
			return Constant.STRING_NULL;
		}

		final StringBuilder sb = new StringBuilder();
		boolean isFirst = Constant.TRUE;
		T item;
		while (iterator.hasNext()) {
			if (isFirst) {
				isFirst = Constant.FALSE;
			} else {
				sb.append(conjunction);
			}

			item = iterator.next();
			if (ArrayUtil.isArray(item)) {
				sb.append(ArrayUtil.join(ArrayUtil.wrap(item), conjunction, prefix, suffix));
			} else if (item instanceof Iterable<?>) {
				sb.append(join((Iterable<?>) item, conjunction, prefix, suffix));
			} else if (item instanceof Iterator<?>) {
				sb.append(join((Iterator<?>) item, conjunction, prefix, suffix));
			} else {
				sb.append(StrUtil.wrap(String.valueOf(item), prefix, suffix));
			}
		}
		return sb.toString();
	}

    /* 将Entry集合转换为HashMap -------------------------------------------------------------- to Map */ 

    /**
     * [将Entry集合转换为HashMap](Convert entry collection to HashMap)
     * @description zh - 将Entry集合转换为HashMap
     * @description en - Convert entry collection to HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-14 22:02:46
     * @param entryIter entry集合
     * @return java.util.HashMap<K, V>
     */
    public static <K, V> HashMap<K, V> toMap(Iterable<Entry<K, V>> entryIter) {
		final HashMap<K, V> map = new HashMap<>();
		if (isNotEmpty(entryIter)) {
			for (Entry<K, V> entry : entryIter) {
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}

    /**
     * [将键列表和值列表转换为Map](Convert key list and value list to map)
     * @description zh - 将键列表和值列表转换为Map
     * @description en - Convert key list and value list to map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-14 22:03:40
     * @param keys 键列表
     * @param values 值列表
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> toMap(Iterable<K> keys, Iterable<V> values) {
		return toMap(keys, values, Constant.FALSE);
	}

    /**
     * [将键列表和值列表转换为Map](Convert key list and value list to map)
     * @description zh - 将键列表和值列表转换为Map
     * @description en - Convert key list and value list to map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-14 22:09:35
     * @param keys 键列表
     * @param values 值列表
     * @param isOrder 是否有序
     */
    public static <K, V> Map<K, V> toMap(Iterable<K> keys, Iterable<V> values, boolean isOrder) {
		return toMap(Constant.NULL == keys ? null : keys.iterator(), Constant.NULL == values ? null : values.iterator(), isOrder);
	}

    /**
     * [将键列表和值列表转换为Map](Convert key list and value list to map)
     * @description zh - 将键列表和值列表转换为Map
     * @description en - Convert key list and value list to map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-14 22:11:05
     * @param keys 键列表
     * @param values 值列表
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> toMap(Iterator<K> keys, Iterator<V> values) {
		return toMap(keys, values, false);
	}

    /**
     * [将键列表和值列表转换为Map](Convert key list and value list to map)
     * @description zh - 将键列表和值列表转换为Map
     * @description en - Convert key list and value list to map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-14 22:17:07
     * @param keys 键列表
     * @param values 值列表
     * @param isOrder 是否有序
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> toMap(Iterator<K> keys, Iterator<V> values, boolean isOrder) {
		final Map<K, V> resultMap = MapUtil.newHashMap(isOrder);
		if (isNotEmpty(keys)) {
			while (keys.hasNext()) {
				resultMap.put(keys.next(), (Constant.NULL != values && values.hasNext()) ? values.next() : null);
			}
		}
		return resultMap;
	}

    /**
     * [将列表转成值为List的HashMap](Convert the list to a HashMap with a value of list)
     * @description zh - 将列表转成值为List的HashMap
     * @description en - Convert the list to a HashMap with a value of list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-15 22:05:33
     * @param iterable 值列表
     * @param keyMapper Map的键映射
     * @param java.util.Map<K, java.util.List<V>>
     */
    public static <K, V> Map<K, List<V>> toListMap(Iterable<V> iterable, Function<V, K> keyMapper) {
		return toListMap(iterable, keyMapper, v -> v);
	}

    /**
     * [将列表转成值为List的HashMap](Convert the list to a HashMap with a value of list)
     * @description zh - 将列表转成值为List的HashMap
     * @description en - Convert the list to a HashMap with a value of list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-15 22:08:09
     * @param iterable 值列表
     * @param keyMapper Map的键映射
     * @param valueMapper Map中List的值映射
     * @param java.util.Map<K, java.util.List<V>>
     */
    public static <T, K, V> Map<K, List<V>> toListMap(Iterable<T> iterable, Function<T, K> keyMapper, Function<T, V> valueMapper) {
		return toListMap(MapUtil.newHashMap(), iterable, keyMapper, valueMapper);
	}

    /**
     * [将列表转成值为List的HashMap](Convert the list to a HashMap with a value of list)
     * @description zh - 将列表转成值为List的HashMap
     * @description en - Convert the list to a HashMap with a value of list
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-15 22:09:52
     * @param resultMap 结果Map，可自定义结果Map类型
     * @param iterable 值列表
     * @param keyMapper Map的键映射
     * @param valueMapper Map中List的值映射
     * @return java.util.Map<K, java.util.List<V>>
     */
    public static <T, K, V> Map<K, List<V>> toListMap(Map<K, List<V>> resultMap, Iterable<T> iterable, Function<T, K> keyMapper, Function<T, V> valueMapper) {
		if (Constant.NULL == resultMap) {
			resultMap = MapUtil.newHashMap();
		}
		if (ObjectUtil.isNull(iterable)) {
			return resultMap;
		}

		for (T value : iterable) {
			resultMap.computeIfAbsent(keyMapper.apply(value), k -> new ArrayList<>()).add(valueMapper.apply(value));
		}

		return resultMap;
	}

    /**
     * [将列表转成HashMap](Convert list to HashMap)
     * @description zh - 将列表转成HashMap
     * @description en - Convert list to HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-17 07:50:54
     * @param iterable 值列表
     * @param keyMapper Map的键映射
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> toMap(Iterable<V> iterable, Function<V, K> keyMapper) {
		return toMap(iterable, keyMapper, v -> v);
	}

    /**
     * [将列表转成HashMap](Convert list to HashMap)
     * @description zh - 将列表转成HashMap
     * @description en - Convert list to HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-17 07:52:20
     * @param iterable 值列表
     * @param keyMapper Map的键映射
     * @param valueMapper Map的值映射
     * @return java.util.Map<K, V>
     */
    public static <T, K, V> Map<K, V> toMap(Iterable<T> iterable, Function<T, K> keyMapper, Function<T, V> valueMapper) {
		return toMap(MapUtil.newHashMap(), iterable, keyMapper, valueMapper);
	}

    /**
     * [将列表转成Map](Convert list to map)
     * @description zh - 将列表转成Map
     * @description en - Convert list to map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-17 07:55:17
     * @param resultMap 结果Map，通过传入map对象决定结果的Map类型
     * @param iterable 值列表
     * @param keyMapper Map的键映射
     * @param valueMapper Map的值映射
     * @return java.util.Map<K, V>
     */
    public static <T, K, V> Map<K, V> toMap(Map<K, V> resultMap, Iterable<T> iterable, Function<T, K> keyMapper, Function<T, V> valueMapper) {
		if (Constant.NULL == resultMap) {
			resultMap = MapUtil.newHashMap();
		}
		if (ObjectUtil.isNull(iterable)) {
			return resultMap;
		}

		for (T value : iterable) {
			resultMap.put(keyMapper.apply(value), valueMapper.apply(value));
		}

		return resultMap;
	}

  /**
   * [Iterator转List](Iterator to list)
   * @description zh - Iterator转List
   * @description en - Iterator to list
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-18 22:14:00
   * @param iter Iterator
   * @return java.util.List<E>
   */
  public static <E> List<E> toList(Iterable<E> iter) {
    return Constant.NULL == iter ? null : toList(iter.iterator());
	}

  /**
   * [Iterator转List](Iterator to list)
   * @description zh - Iterator转List
   * @description en - Iterator to list
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-18 22:14:58
   * @param iter Iterator
   * @return java.util.List<E>
   */
  public static <E> List<E> toList(Iterator<E> iter) {
		final List<E> list = new ArrayList<>();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}

  /* Iterator 转为 Iterable -------------------------------------------------------------- Iterator to iteratable */ 
    
  /**
   * [Enumeration转换为Iterator](Convert enumeration to iterator)
   * @description zh - Enumeration转换为Iterator
   * @description en - Convert enumeration to iterator
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:20:09
   * @param enumeration Enumeration
   * @return java.util.Iterator<E>
   */
  public static <E> Iterator<E> asIterator(Enumeration<E> enumeration) {
		return new EnumerationIter<>(enumeration);
	}

  /**
   * [Iterator 转为 Iterable](Iterator to iteratable)
   * @description zh - Iterator 转为 Iterable
   * @description en - Iterator to iteratable
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:23:18
   * @param iterator Iterator
   * @return java.lang.Iterable<E>
   */
  public static <E> Iterable<E> asIterable(final Iterator<E> iterator) {
		return () -> iterator;
	}

  /* 获取第一个元素 -------------------------------------------------------------- get first */ 

  /**
   * [获取集合的第一个元素](Gets the first element of the collection)
   * @description zh - 获取集合的第一个元素
   * @description en - Gets the first element of the collection
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:26:40
   * @param iterable Iterable
   * @return T
   */
  public static <T> T getFirst(Iterable<T> iterable) {
    return Constant.NULL == iterable ? null : getFirst(iterable.iterator());
	}

  /**
   * [获取集合的第一个元素](Gets the first element of the collection)
   * @description zh - 获取集合的第一个元素
   * @description en - Gets the first element of the collection
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:28:34
   * @param iterator Iterator
   * @return T
   */
  public static <T> T getFirst(Iterator<T> iterator) {
    return Constant.NULL != iterator && iterator.hasNext() ? iterator.next() : null;
	}

  /* 元素类型 -------------------------------------------------------------- get type */ 

  /**
   * [获得 Iterable 对象的元素类型](Gets the element type of the iteratable object)
   * @description zh - 获得 Iterable 对象的元素类型
   * @description en - Gets the element type of the iteratable object
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:39:18
   * @param iterable Iterable
   * @return java.lang.Class<?>
   */
	public static Class<?> getElementType(Iterable<?> iterable) {
    return Constant.NULL != iterable ? getElementType(iterable.iterator()) : null; 
	}

  /**
   * [获得 Iterable 对象的元素类型](Gets the element type of the iteratable object)
   * @description zh - 获得 Iterable 对象的元素类型
   * @description en - Gets the element type of the iteratable object
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:41:22
   * @param iterator Iterator
   * @retuen java.lang.Class<?>
   */
  public static Class<?> getElementType(Iterator<?> iterator) {
		final Iterator<?> iter2 = new CopiedIter<>(iterator);
		if (iter2.hasNext()) {
			final Object t = iter2.next();
			if (Constant.NULL != t) {
				return t.getClass();
			}
		}
		return null;
	}

  /* 过滤 -------------------------------------------------------------- filter */ 

  /**
   * [过滤集合](Filter collection)
   * @description zh - 过滤集合
   * @description en - Filter collection
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:42:40
   * @param iter 集合对象
   * @param filter 过滤接口
   * @return T
   */
  public static <T extends Iterable<E>, E> T filter(T iter, Filter<E> filter) {
		if (Constant.NULL == iter) {
			return null;
		}

		filter(iter.iterator(), filter);

		return iter;
	}

  /**
   * [过滤集合](Filter collection)
   * @description zh - 过滤集合
   * @description en - Filter collection
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:44:06
   * @param iter 集合对象
   * @param filter 过滤接口
   * @return java.util.Iterator<E>
   */
  public static <E> Iterator<E> filter(Iterator<E> iter, Filter<E> filter) {
		if (Constant.NULL == iter || Constant.NULL == filter) {
			return iter;
		}

		while (iter.hasNext()) {
			if (Constant.FALSE == filter.accept(iter.next())) {
				iter.remove();
			}
		}
		return iter;
	}

  /* 转为map -------------------------------------------------------------- to map */   

  /**
   * [Iterator转换为Map](Convert iterator to map)
   * @description zh - Iterator转换为Map
   * @description en - Convert iterator to map
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:49:03
   * @param iterator 数据列表
   * @param map map
   * @param keyFunc 生成key的函数
   * @return java.util.Map<K, V>
   */
  public static <K, V> Map<K, V> toMap(Iterator<V> iterator, Map<K, V> map, Func1<V, K> keyFunc) {
		return toMap(iterator, map, keyFunc, (value) -> value);
	}

  /**
   * [Iterator转换为Map](Convert iterator to map)
   * @description zh - Iterator转换为Map
   * @description en - Convert iterator to map
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:51:17
   * @param iterator 数据列表
   * @param map map
   * @param keyFunc 生成key的函数
   * @param valueFunc 生成值的策略函数
   * @return java.util.Map<K, V>
   */
  public static <K, V, E> Map<K, V> toMap(Iterator<E> iterator, Map<K, V> map, Func1<E, K> keyFunc, Func1<E, V> valueFunc) {
		if (Constant.NULL == iterator) {
			return map;
		}

		if (Constant.NULL == map) {
			map = MapUtil.newHashMap(true);
		}

		E element;
		while (iterator.hasNext()) {
			element = iterator.next();
			try {
				map.put(keyFunc.call(element), valueFunc.call(element));
			} catch (Exception e) {
				throw new UtilException(e);
			}
		}
		return map;
	}

  /* 其他 -------------------------------------------------------------- other */     

  /**
   * [返回一个空Iterator](Returns an empty iterator)
   * @description zh - 返回一个空Iterator
   * @description en - Returns an empty iterator
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:52:45
   * @return java.util.Iterator<T>
   */
  public static <T> Iterator<T> empty() {
		return Collections.emptyIterator();
	}

  /**
   * [转换 Iterator 为另一种类型的 Iterator](Convert iterator to another type of iterator)
   * @description zh - 转换 Iterator 为另一种类型的 Iterator
   * @description en - Convert iterator to another type of iterator
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:54:37
   * @param iterator Iterator
   * @param function 转换函数
   * @return java.util.Iterator<T>
   */
  public static <F, T> Iterator<T> trans(Iterator<F> iterator, Function<? super F, ? extends T> function) {
		return new TransIter<>(iterator, function);
	}

  /**
   * [返回 Iterable 对象的元素数量](Returns the number of elements of an iteratable object)
   * @description zh - 返回 Iterable 对象的元素数量
   * @description en - Returns the number of elements of an iteratable object
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-19 21:56:44
   * @param iterable Iterable
   * @return int
   */
  public static int size(final Iterable<?> iterable) {
    return Constant.NULL == iterable ? Constant.ZERO :
           iterable instanceof Collection<?> ? ((Collection<?>) iterable).size() : 
           size(iterable.iterator());
	}
}
