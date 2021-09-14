package com.xiaoTools.util.iterUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.strUtil.StrUtil;

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

}
