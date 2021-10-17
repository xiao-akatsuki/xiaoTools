package com.xiaoTools.util.mapUtil;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.convert.typeReference.TypeReference;
import com.xiaoTools.core.editor.Editor;
import com.xiaoTools.core.filter.Filter;
import com.xiaoTools.core.map.camelCaseLinkedMap.CamelCaseLinkedMap;
import com.xiaoTools.core.map.camelCaseMap.CamelCaseMap;
import com.xiaoTools.core.map.mapBuilder.MapBuilder;
import com.xiaoTools.core.map.mapProxy.MapProxy;
import com.xiaoTools.core.map.mapWrapper.MapWrapper;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.lang.pair.Pair;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

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

    /**
     * [新建一个HashMap](Create a new HashMap)
     * @description zh - 新建一个HashMap
     * @description en - Create a new HashMap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 08:34:41
     * @param isOrder Map的Key是否有序
     * @return java.util.HashMap<K, V>
     */
    public static <K, V> HashMap<K, V> newHashMap(boolean isOrder) {
		return newHashMap(DEFAULT_INITIAL_CAPACITY, isOrder);
	}

    /**
     * [新建TreeMap，Key有序的Map](Create a new treemap, and the key is an ordered map)
     * @description zh - 新建TreeMap，Key有序的Map
     * @description en - Create a new treemap, and the key is an ordered map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 21:31:16
     * @param comparator Key比较器
     * @return java.util.TreeMap<K, V>
     */
    public static <K, V> TreeMap<K, V> newTreeMap(Comparator<? super K> comparator) {
		return new TreeMap<>(comparator);
	}

    /**
     * [新建TreeMap，Key有序的Map](Create a new treemap, and the key is an ordered map)
     * @description zh - 新建TreeMap，Key有序的Map
     * @description en - Create a new treemap, and the key is an ordered map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 21:33:08
     * @param map Map
     * @param comparator Key比较器
     * @return java.util.TreeMap<K, V>
     */
    public static <K, V> TreeMap<K, V> newTreeMap(Map<K, V> map, Comparator<? super K> comparator) {
		final TreeMap<K, V> treeMap = new TreeMap<>(comparator);
		if (Constant.FALSE == isEmpty(map)) {
			treeMap.putAll(map);
		}
		return treeMap;
	}

    /**
     * [创建键不重复Map](Create a map without duplicate keys)
     * @description zh - 创建键不重复Map
     * @description en - Create a map without duplicate keys
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 21:34:00
     * @param size 大小
     * @return java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> newIdentityMap(int size) {
		return new IdentityHashMap<>(size);
	}

    /**
     * [新建一个初始容量为 DEFAULT_INITIAL_CAPACITY 的 ConcurrentHashMap](Create a new one with an initial capacity of default_INITIAL_Concurrent HashMap of capability)
     * @description zh - 新建一个初始容量为 DEFAULT_INITIAL_CAPACITY 的 ConcurrentHashMap
     * @description en - Create a new one with an initial capacity of default_INITIAL_Concurrent HashMap of capability
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 21:35:58
     * @return java.util.concurrent.ConcurrentHashMap<K, V>
     */
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap() {
		return new ConcurrentHashMap<>(DEFAULT_INITIAL_CAPACITY);
	}

    /**
     * [新建一个ConcurrentHashMap](Create a new concurrenthashmap)
     * @description zh - 新建一个ConcurrentHashMap
     * @description en - Create a new concurrenthashmap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 21:37:10
     * @param size 大小
     * @return java.util.concurrent.ConcurrentHashMap<K, V>
     */
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap(int size) {
		return new ConcurrentHashMap<>(size <= 0 ? DEFAULT_INITIAL_CAPACITY : size);
	}

    /**
     * [传入一个Map将其转化为ConcurrentHashMap类型](Pass in a map and convert it to type concurrenthashmap)
     * @description zh - 传入一个Map将其转化为ConcurrentHashMap类型
     * @description en - Pass in a map and convert it to type concurrenthashmap
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 21:38:17
     * @param map 集合
     * @return java.util.concurrent.ConcurrentHashMap<K, V>
     */
    public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap(Map<K, V> map) {
        return isEmpty(map) ? new ConcurrentHashMap<>(DEFAULT_INITIAL_CAPACITY) : new ConcurrentHashMap<>(map);
	}

    /**
     * [创建Map](Create map)
     * @description zh - 创建Map
     * @description en - Create map
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-22 21:39:59
     * @param mapType map类型
     * @return java.util.Map<K, V>
     */
    @SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> createMap(Class<?> mapType) {
        return mapType.isAssignableFrom(AbstractMap.class) ? new HashMap<>() : (Map<K, V>) ReflectUtil.newInstance(mapType);
	}

    /* 值对转换 ----------------------------------------------------------- value of */

    /**
     * [将单一键值对转换为Map](Convert single key value pairs to maps)
     * @description zh - 将单一键值对转换为Map
     * @description en - Convert single key value pairs to maps
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-24 15:49:45
     * @param key 键
     * @param value 值
     * @return java.util.HashMap<K, V>
     */
    public static <K, V> HashMap<K, V> of(K key, V value) {
		return of(key, value, Constant.FALSE);
	}

    /**
     * [将单一键值对转换为Map](Convert single key value pairs to maps)
     * @description zh - 将单一键值对转换为Map
     * @description en - Convert single key value pairs to maps
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-24 16:12:19
     * @param key 键
     * @param value 值
     * @param isOrder 是否有序
     * @return java.util.HashMap<K, V>
     */
    public static <K, V> HashMap<K, V> of(K key, V value, boolean isOrder) {
		final HashMap<K, V> map = newHashMap(isOrder);
		map.put(key, value);
		return map;
	}

  /**
   * [根据给定的Pair数组创建Map对象](Creates a map object based on the given pair array)
   * @description zh - 根据给定的Pair数组创建Map对象
   * @description en - Creates a map object based on the given pair array
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-25 19:52:11
   */
  @SafeVarargs
	public static <K, V> Map<K, V> of(Pair<K, V>... pairs) {
		final Map<K, V> map = new HashMap<>();
		for (Pair<K, V> pair : pairs) {
			map.put(pair.getKey(), pair.getValue());
		}
		return map;
	}

  /**
   * [将数组转换为Map](Convert array to map)
   * @description zh - 将数组转换为Map
   * @description en - Convert array to map
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-26 12:17:35
   * @param array 数组
   * @return java.util.HashMap<Object, Object>
   */
  @SuppressWarnings("rawtypes")
	public static HashMap<Object, Object> of(Object[] array) {
		if (array == Constant.NULL) {
			return null;
		}
		final HashMap<Object, Object> map = new HashMap<>((int) (array.length * 1.5));
		for (int i = Constant.ZERO; i < array.length; i++) {
			final Object object = array[i];
			if (object instanceof Map.Entry) {
				Map.Entry entry = (Map.Entry) object;
				map.put(entry.getKey(), entry.getValue());
			} else if (object instanceof Object[]) {
				final Object[] entry = (Object[]) object;
				if (entry.length > Constant.ONE) {
					map.put(entry[Constant.ZERO], entry[Constant.ONE]);
				}
			} else if (object instanceof Iterable) {
				final Iterator iter = ((Iterable) object).iterator();
				if (iter.hasNext()) {
					final Object key = iter.next();
					if (iter.hasNext()) {
						final Object value = iter.next();
						map.put(key, value);
					}
				}
			} else if (object instanceof Iterator) {
				final Iterator iter = ((Iterator) object);
				if (iter.hasNext()) {
					final Object key = iter.next();
					if (iter.hasNext()) {
						final Object value = iter.next();
						map.put(key, value);
					}
				}
			} else {
				throw new IllegalArgumentException(StrUtil.format("Array element {}, '{}', is not type of Map.Entry or Array or Iterable or Iterator", i, object));
			}
		}
		return map;
	}

  /* 列转行 ----------------------------------------------------------- to map */

  /**
   * [行转列，合并相同的键，值合并为列表](Row to column, merge the same keys, and merge the values into a list)
   * @description zh - 行转列，合并相同的键，值合并为列表
   * @description en - Row to column, merge the same keys, and merge the values into a list
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-26 12:20:00
   * @return mapList Map列表
   * @return java.util.Map<K, List<V>>
   */
  public static <K, V> Map<K, List<V>> toListMap(Iterable<? extends Map<K, V>> mapList) {
		final HashMap<K, List<V>> resultMap = new HashMap<>();
		if (CollUtil.isEmpty(mapList)) {
			return resultMap;
		}

		Set<Entry<K, V>> entrySet;
		for (Map<K, V> map : mapList) {
			entrySet = map.entrySet();
			K key;
			List<V> valueList;
			for (Entry<K, V> entry : entrySet) {
				key = entry.getKey();
				valueList = resultMap.get(key);
				if (Constant.NULL == valueList) {
					valueList = CollUtil.newArrayList(entry.getValue());
					resultMap.put(key, valueList);
				} else {
					valueList.add(entry.getValue());
				}
			}
		}

		return resultMap;
	}

  /**
   * [列转行](Column to row)
   * @description zh - 列转行
   * @description en - Column to row
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-26 12:23:57
   * @param listMap 列表Map
   * @return java.util.List<java.util.Map<K, V>>
   */
  public static <K, V> List<Map<K, V>> toMapList(Map<K, ? extends Iterable<V>> listMap) {
		final List<Map<K, V>> resultList = new ArrayList<>();
		if (isEmpty(listMap)) {
			return resultList;
		}
    // 是否结束。标准是元素列表已耗尽
		boolean isEnd;
    // 值索引
		int index = Constant.ZERO;
		Map<K, V> map;
		do {
			isEnd = Constant.TRUE;
			map = new HashMap<>();
			List<V> vList;
			int vListSize;
			for (Entry<K, ? extends Iterable<V>> entry : listMap.entrySet()) {
				vList = CollUtil.newArrayList(entry.getValue());
				vListSize = vList.size();
				if (index < vListSize) {
					map.put(entry.getKey(), vList.get(index));
					if (index != vListSize - Constant.ONE) {
						// 当值列表中还有更多值（非最后一个），继续循环
						isEnd = Constant.FALSE;
					}
				}
			}
			if (Constant.FALSE == map.isEmpty()) {
				resultList.add(map);
			}
			index++;
		} while (Constant.FALSE == isEnd);

		return resultList;
	}

  /**
   * [将已知Map转换为key为驼峰风格的Map](Convert a known map into a hump style map)
   * @description zh - 将已知Map转换为key为驼峰风格的Map
   * @description en - Convert a known map into a hump style map
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-26 12:26:17
   * @return map 集合
   * @return java.util.Map<K, V>
   */
  public static <K, V> Map<K, V> toCamelCaseMap(Map<K, V> map) {
		return (map instanceof LinkedHashMap) ? new CamelCaseLinkedMap<>(map) : new CamelCaseMap<>(map);
	}

  /**
   * [将键值对转换为二维数组](Convert key value pairs to two-dimensional arrays)
   * @description zh - 将键值对转换为二维数组
   * @description en - Convert key value pairs to two-dimensional arrays
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-26 12:27:54
   * @rparam map 集合
   * @return java.lang.Object[][]
   */
  public static Object[][] toObjectArray(Map<?, ?> map) {
		if (map == Constant.NULL) {
			return null;
		}
		final Object[][] result = new Object[map.size()][2];
		if (map.isEmpty()) {
			return result;
		}
		int index = Constant.ZERO;
		for (Entry<?, ?> entry : map.entrySet()) {
			result[index][Constant.ZERO] = entry.getKey();
			result[index][Constant.ONE] = entry.getValue();
			index++;
		}
		return result;
	}

  /* 分页 ----------------------------------------------------------- join */

  /**
   * [将map转成字符串](Convert map to string)
   * @description zh - 将map转成字符串
   * @description en - Convert map to string
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-27 17:43:15
   * @param map Map
   * @param separator entry之间的连接符
   * @param keyValueSeparator kv之间的连接符
   * @param otherParams 其它附加参数字符串
   * @return java.lang.String
   */
  public static <K, V> String join(Map<K, V> map, String separator, String keyValueSeparator, String... otherParams) {
		return join(map, separator, keyValueSeparator, false, otherParams);
	}

  /**
   * [根据参数排序后拼接为字符串](Concatenate into strings after sorting according to parameters)
   * @description zh - 根据参数排序后拼接为字符串
   * @description en - Concatenate into strings after sorting according to parameters
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-28 22:48:42
   * @param params 参数
   * @param separator entry之间的连接符
   * @param keyValueSeparator kv之间的连接符
   * @param isIgnoreNull 是否忽略null的键和值
   * @param otherParams 其它附加参数字符串
   * @return java.lang.String
   */
  public static String sortJoin(Map<?, ?> params, String separator, String keyValueSeparator, boolean isIgnoreNull, String... otherParams) {
		return join(sort(params), separator, keyValueSeparator, isIgnoreNull, otherParams);
	}

  /**
   * [将map转成字符串，忽略null的键和值](Convert the map to a string, ignoring null keys and values)
   * @description zh - 将map转成字符串，忽略null的键和值
   * @description en - Convert the map to a string, ignoring null keys and values
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-29 19:47:24
   * @param map Map
   * @param separator entry之间的连接符
   * @param keyValueSeparator kv之间的连接符
   * @param otherParams 其它附加参数字符串
   * @return java.lang.String
   */
  public static <K, V> String joinIgnoreNull(Map<K, V> map, String separator, String keyValueSeparator, String... otherParams) {
		return join(map, separator, keyValueSeparator, true, otherParams);
	}

  /**
   * [将map转成字符串](Convert map to string)
   * @description zh - 将map转成字符串
   * @description en - Convert map to string
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-09-29 20:00:05
   * @param separator entry之间的连接符
   * @param keyValueSeparator kv之间的连接符
   * @param isIgnoreNull 是否忽略null的键和值
   * @param otherParams 其它附加参数字符串
   * @return java.lang.String
   */
  public static <K, V> String join(Map<K, V> map, String separator, String keyValueSeparator, boolean isIgnoreNull, String... otherParams) {
		final StringBuilder strBuilder = StrUtil.builder();
		boolean isFirst = Constant.TRUE;
		if (isNotEmpty(map)) {
			for (Entry<K, V> entry : map.entrySet()) {
				if (Constant.FALSE == isIgnoreNull || entry.getKey() != Constant.NULL && entry.getValue() != Constant.NULL) {
					if (isFirst) {
						isFirst = Constant.FALSE;
					} else {
						strBuilder.append(separator);
					}
					strBuilder.append(Convert.toStr(entry.getKey())).append(keyValueSeparator).append(Convert.toStr(entry.getValue()));
				}
			}
		}
		// 补充其它字符串到末尾，默认无分隔符
		if (ArrayUtil.isNotEmpty(otherParams)) {
			for (String otherParam : otherParams) {
				strBuilder.append(otherParam);
			}
		}
		return strBuilder.toString();
	}

  /* 过滤 ----------------------------------------------------------- filter */

  /**
   * [过滤](filter)
   * @description zh - 过滤
   * @description en - filter
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-05 22:35:08
   * @param map 集合
   * @param editor 编辑器接口
   * @return java.util.Map<K, V>
   */
  public static <K, V> Map<K, V> filter(Map<K, V> map, Editor<Entry<K, V>> editor) {
		if (Constant.NULL == map || Constant.NULL == editor) {
			return map;
		}

		final Map<K, V> map2 = ObjectUtil.clone(map);
		if (isEmpty(map2)) {
			return map2;
		}

		map2.clear();
		Entry<K, V> modified;
		for (Entry<K, V> entry : map.entrySet()) {
			modified = editor.edit(entry);
			if (Constant.NULL != modified) {
				map2.put(modified.getKey(), modified.getValue());
			}
		}
		return map2;
	}

  /**
   * [过滤](filter)
   * @description zh - 过滤
   * @description en - filter
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-06 08:54:32
   * @param map 集合
   * @param filter 编辑器接口
   * @return java.util.Map<K, V>
   */
  public static <K, V> Map<K, V> filter(Map<K, V> map, Filter<Entry<K, V>> filter) {
		if (Constant.NULL == map || Constant.NULL == filter) {
			return map;
		}

		final Map<K, V> map2 = ObjectUtil.clone(map);
		if (isEmpty(map2)) {
			return map2;
		}

		map2.clear();
		for (Entry<K, V> entry : map.entrySet()) {
			if (filter.accept(entry)) {
				map2.put(entry.getKey(), entry.getValue());
			}
		}
		return map2;
	}

  /**
   * [过滤Map保留指定键值对](Filter the map to retain the specified key value pair)
   * @description zh - 过滤Map保留指定键值对
   * @description en - Filter the map to retain the specified key value pair
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-06 08:55:48
   * @param map 集合
   * @param keys 键
   * @return java.util.Map<K, V>
   */
  @SuppressWarnings("unchecked")
  public static <K, V> Map<K, V> filter(Map<K, V> map, K... keys) {
		final Map<K, V> map2 = ObjectUtil.clone(map);
		if (isEmpty(map2)) {
			return map2;
		}

		map2.clear();
		for (K key : keys) {
			if (map.containsKey(key)) {
				map2.put(key, map.get(key));
			}
		}
		return map2;
	}

  /* 互换 ----------------------------------------------------------- reverse */

  /**
   * [Map的键和值互换](The keys and values of map are interchanged)
   * @description zh - Map的键和值互换
   * @description en - The keys and values of map are interchanged
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-06 09:06:32
   * @param map 集合
   * @return java.util.Map<T, T>
   */
  public static <T> Map<T, T> reverse(Map<T, T> map) {
		return filter(map, (Editor<Entry<T, T>>) t -> new Entry<T, T>() {

			@Override
			public T getKey() {
				return t.getValue();
			}

			@Override
			public T getValue() {
				return t.getKey();
			}

			@Override
			public T setValue(T value) {
				throw new UnsupportedOperationException("Unsupported setValue method !");
			}
		});
	}

  /**
   * [Map的键和值互换](The keys and values of map are interchanged)
   * @description zh - Map的键和值互换
   * @description en - The keys and values of map are interchanged
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-06 22:30:56
   * @param map 集合
   * @return java.util.Map<T, T>
   */
  public static <K, V> Map<V, K> inverse(Map<K, V> map) {
		final Map<V, K> result = createMap(map.getClass());
		map.forEach((key, value) -> result.put(value, key));
		return result;
	}

  /* 排序 ----------------------------------------------------------- sort */

  /**
   * [排序已有Map](Sort existing maps)
   * @description zh - 排序已有Map
   * @description en - Sort existing maps
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-07 08:51:52
   * @param map 集合
   * @return java.util.TreeMap<K, V>
   */
  public static <K, V> TreeMap<K, V> sort(Map<K, V> map) {
		return sort(map, null);
	}

  /**
   * [排序已有Map](Sort existing maps)
   * @description zh - 排序已有Map
   * @description en - Sort existing maps
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-07 08:53:47
   * @param map 集合
   * @param comparator Key比较器
   * @return java.util.TreeMap<K, V>
   */
  public static <K, V> TreeMap<K, V> sort(Map<K, V> map, Comparator<? super K> comparator) {
		if (Constant.NULL == map) {
			return null;
		}

		TreeMap<K, V> result;
		if (map instanceof TreeMap) {
			// 已经是可排序Map，此时只有比较器一致才返回原map
			result = (TreeMap<K, V>) map;
			if (Constant.NULL == comparator || comparator.equals(result.comparator())) {
				return result;
			}
		} else {
			result = newTreeMap(map, comparator);
		}

		return result;
	}

  /**
   * [按照值排序，可选是否倒序](Sort by value. Reverse order is optional)
   * @description zh - 按照值排序，可选是否倒序
   * @description en - Sort by value. Reverse order is optional
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-07 08:54:45
   * @param map 集合
   * @param isDesc 是否倒序
   * @return java.util.Map<K, V>
   */
  public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean isDesc) {
		Map<K, V> result = new LinkedHashMap<>();
		Comparator<Entry<K, V>> entryComparator = Entry.comparingByValue();
		if(isDesc){
			entryComparator = entryComparator.reversed();
		}
		map.entrySet().stream().sorted(entryComparator).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
		return result;
	}

  /* 创建 ----------------------------------------------------------- create */

  /**
   * [创建代理Map](Create proxy map)
   * @description zh - 创建代理Map
   * @description en - Create proxy map
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-08 07:36:12
   * @param map
   * @return com.xiaoTools.core.map.mapProxy
   */
  public static MapProxy createProxy(Map<?, ?> map) {
		return MapProxy.create(map);
	}

  /**
   * [创建Map包装类MapWrapper](Create Map wrapper class MapWrapper)
   * @description zh - 创建Map包装类MapWrapper
   * @description en - Create Map wrapper class MapWrapper
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-15 07:51:04
   * @param map 集合
   * @return com.xiaoTools.core.map.mapWrapper.MapWrapper<K, V>
   */
  public static <K, V> MapWrapper<K, V> wrap(Map<K, V> map) {
		return new MapWrapper<>(map);
	}

  /**
   * [将对应Map转换为不可修改的Map](Convert the corresponding map to a non modifiable map)
   * @description zh - 将对应Map转换为不可修改的Map
   * @description en - Convert the corresponding map to a non modifiable map
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-15 07:53:12
   * @param map 集合
   * @return java.util.Map<K, V>
   */
  public static <K, V> Map<K, V> unmodifiable(Map<K, V> map) {
		return Collections.unmodifiableMap(map);
	}

  /* 构造 ----------------------------------------------------------- builder */

  /**
   * [创建链接调用map](Create link call map)
   * @description zh - 创建链接调用map
   * @description en - Create link call map
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-15 07:54:28
   */
  public static <K, V> MapBuilder<K, V> builder() {
		return builder(new HashMap<>());
	}

  /**
   * [创建链接调用map](Create link call map)
   * @description zh - 创建链接调用map
   * @description en - Create link call map
   * @version V1.0
   * @author XiaoXunYao
   * @since 2021-10-15 08:10:47
   * @param map 集合
   * @return com.xiaoTools.core.map.mapBuilder.MapBuilder<K, V>
   */
  public static <K, V> MapBuilder<K, V> builder(Map<K, V> map) {
		return new MapBuilder<>(map);
	}

	/**
   	 * [创建链接调用map](Create link call map)
   	 * @description zh - 创建链接调用map
     * @description en - Create link call map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-16 13:15:54
	 */
	public static <K, V> MapBuilder<K, V> builder(K key, V value) {
		return (builder(new HashMap<K, V>())).put(key, value);
	}

	/**
	 * [获取Map的部分key生成新的Map](Get some keys of the map to generate a new map)
	 * @description zh - 获取Map的部分key生成新的Map
	 * @description en - Get some keys of the map to generate a new map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:35:28
	 * @param map 集合
	 * @param keys 键列表
	 * @return java.util.Map<K, V>
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> getAny(Map<K, V> map, final K... keys) {
		return filter(map, (Filter<Entry<K, V>>) entry -> ArrayUtil.contains(keys, entry.getKey()));
	}

	/**
	 * [去掉Map中指定key的键值对，修改原Map](Remove the key value pair of the specified key in the map and modify the original map)
	 * @description zh - 去掉Map中指定key的键值对，修改原Map
	 * @description en - Remove the key value pair of the specified key in the map and modify the original map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:36:46
	 * @param map 集合
	 * @param keys 键列表
	 * @return java.util.Map<K, V>
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> removeAny(Map<K, V> map, final K... keys) {
		for (K key : keys) {
			map.remove(key);
		}
		return map;
	}

	/**
	 * [获取Map指定key的值，并转换为字符串](Get the value of the key specified in the map and convert it to a string)
	 * @description zh - 获取Map指定key的值，并转换为字符串
	 * @description en - Get the value of the key specified in the map and convert it to a string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:38:26
	 * @param map 集合
	 * @param key 键
	 * @return java.lang.String
	 */
	public static String getStr(Map<?, ?> map, Object key) {
		return get(map, key, String.class);
	}

	/**
	 * [获取Map指定key的值，并转换为字符串](Get the value of the key specified in the map and convert it to a string)
	 * @description zh - 获取Map指定key的值，并转换为字符串
	 * @description en - Get the value of the key specified in the map and convert it to a string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:39:11
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.lang.String
	 */
	public static String getStr(Map<?, ?> map, Object key, String defaultValue) {
		return get(map, key, String.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为Integer](Get the value of the key specified in the map and convert it to integer)
	 * @description zh - 获取Map指定key的值，并转换为Integer
	 * @description en - Get the value of the key specified in the map and convert it to integer
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:40:18
	 * @param map 集合
	 * @param key 键
	 * @return java.lang.Integer
	 */
	public static Integer getInt(Map<?, ?> map, Object key) {
		return get(map, key, Integer.class);
	}

	/**
	 * [获取Map指定key的值，并转换为Integer](Get the value of the key specified in the map and convert it to integer)
	 * @description zh - 获取Map指定key的值，并转换为Integer
	 * @description en - Get the value of the key specified in the map and convert it to integer
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:41:08
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.lang.Integer
	 */
	public static Integer getInt(Map<?, ?> map, Object key, Integer defaultValue) {
		return get(map, key, Integer.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为Double](Get the value of the key specified in the map and convert it to double)
	 * @description zh - 获取Map指定key的值，并转换为Double
	 * @description en - Get the value of the key specified in the map and convert it to double
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:41:57
	 * @param map 集合
	 * @param key 键
	 * @return java.lang.Double
	 */
	public static Double getDouble(Map<?, ?> map, Object key) {
		return get(map, key, Double.class);
	}

	/**
	 * [获取Map指定key的值，并转换为Double](Get the value of the key specified in the map and convert it to double)
	 * @description zh - 获取Map指定key的值，并转换为Double
	 * @description en - Get the value of the key specified in the map and convert it to double
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:42:34
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.lang.Double
	 */
	public static Double getDouble(Map<?, ?> map, Object key, Double defaultValue) {
		return get(map, key, Double.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为Float](Get the value of the key specified in the map and convert it to float)
	 * @description zh - 获取Map指定key的值，并转换为Float
	 * @description en - Get the value of the key specified in the map and convert it to float
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:43:32
	 * @param map 集合
	 * @param key 键
	 * @return java.lang.Float
	 */
	public static Float getFloat(Map<?, ?> map, Object key) {
		return get(map, key, Float.class);
	}

	/**
	 * [获取Map指定key的值，并转换为Float](Get the value of the key specified in the map and convert it to float)
	 * @description zh - 获取Map指定key的值，并转换为Float
	 * @description en - Get the value of the key specified in the map and convert it to float
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:44:17
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.lang.Float
	 */
	public static Float getFloat(Map<?, ?> map, Object key, Float defaultValue) {
		return get(map, key, Float.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为Short](Get the value of the key specified in the map and convert it to short)
	 * @description zh - 获取Map指定key的值，并转换为Short
	 * @description en - Get the value of the key specified in the map and convert it to short
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:44:59
	 * @param map 集合
	 * @param key 键
	 * @return java.lang.Short
	 */
	public static Short getShort(Map<?, ?> map, Object key) {
		return get(map, key, Short.class);
	}

	/**
	 * [获取Map指定key的值，并转换为Short](Get the value of the key specified in the map and convert it to short)
	 * @description zh - 获取Map指定key的值，并转换为Short
	 * @description en - Get the value of the key specified in the map and convert it to short
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:45:46
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.lang.Short
	 */
	public static Short getShort(Map<?, ?> map, Object key, Short defaultValue) {
		return get(map, key, Short.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为Bool](Get the value of the key specified in the map and convert it to boolean)
	 * @description zh - 获取Map指定key的值，并转换为Bool
	 * @description en - Get the value of the key specified in the map and convert it to boolean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:47:08
	 * @param map 集合
	 * @param key 键
	 * @return java.lang.Boolean
	 */
	public static Boolean getBool(Map<?, ?> map, Object key) {
		return get(map, key, Boolean.class);
	}

	/**
	 * [获取Map指定key的值，并转换为Bool](Get the value of the key specified in the map and convert it to boolean)
	 * @description zh - 获取Map指定key的值，并转换为Bool
	 * @description en - Get the value of the key specified in the map and convert it to boolean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:47:51
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.lang.Boolean
	 */
	public static Boolean getBool(Map<?, ?> map, Object key, Boolean defaultValue) {
		return get(map, key, Boolean.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为Character](Get the value of the key specified in the map and convert it to character)
	 * @description zh - 获取Map指定key的值，并转换为Character
	 * @description en - Get the value of the key specified in the map and convert it to character
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:48:29
	 * @param map 集合
	 * @param key 键
	 * @return java.lang.Character
	 */
	public static Character getChar(Map<?, ?> map, Object key) {
		return get(map, key, Character.class);
	}

	/**
	 * [获取Map指定key的值，并转换为Character](Get the value of the key specified in the map and convert it to character)
	 * @description zh - 获取Map指定key的值，并转换为Character
	 * @description en - Get the value of the key specified in the map and convert it to character
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:49:07
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.lang.Character
	 */
	public static Character getChar(Map<?, ?> map, Object key, Character defaultValue) {
		return get(map, key, Character.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为Long](Get the value of the key specified in the map and convert it to long)
	 * @description zh - 获取Map指定key的值，并转换为Long
	 * @description en - Get the value of the key specified in the map and convert it to long
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:49:53
	 * @param map 集合
	 * @param key 键
	 * @return java.lang.Long
	 */
	public static Long getLong(Map<?, ?> map, Object key) {
		return get(map, key, Long.class);
	}

	/**
	 * [获取Map指定key的值，并转换为Long](Get the value of the key specified in the map and convert it to long)
	 * @description zh - 获取Map指定key的值，并转换为Long
	 * @description en - Get the value of the key specified in the map and convert it to long
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:50:28
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.lang.Long
	 */
	public static Long getLong(Map<?, ?> map, Object key, Long defaultValue) {
		return get(map, key, Long.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为 Date](Get the value of the key specified in the map and convert it to date)
	 * @description zh - 获取Map指定key的值，并转换为 Date
	 * @description en - Get the value of the key specified in the map and convert it to date
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:50:51
	 * @param map 集合
	 * @param key 键
	 * @return java.util.Date
	 */
	public static Date getDate(Map<?, ?> map, Object key) {
		return get(map, key, Date.class);
	}

	/**
	 * [获取Map指定key的值，并转换为 Date](Get the value of the key specified in the map and convert it to date)
	 * @description zh - 获取Map指定key的值，并转换为 Date
	 * @description en - Get the value of the key specified in the map and convert it to date
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:51:38
	 * @param map 集合
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return java.util.Date
	 */
	public static Date getDate(Map<?, ?> map, Object key, Date defaultValue) {
		return get(map, key, Date.class, defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为指定类型](Gets the value of the key specified in the map and converts it to the specified type)
	 * @description zh - 获取Map指定key的值，并转换为指定类型
	 * @description en - Gets the value of the key specified in the map and converts it to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:52:45
	 * @param map  Map
	 * @param key  键
	 * @param type 值类型
	 * @return T
	 */
	public static <T> T get(Map<?, ?> map, Object key, Class<T> type) {
		return get(map, key, type, null);
	}

	/**
	 * [获取Map指定key的值，并转换为指定类型](Gets the value of the key specified in the map and converts it to the specified type)
	 * @description zh - 获取Map指定key的值，并转换为指定类型
	 * @description en - Gets the value of the key specified in the map and converts it to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:53:26
	 * @param map Map
	 * @param key 键
	 * @param type 值类型
	 * @param defaultValue 默认值
	 * @return T
	 */
	public static <T> T get(Map<?, ?> map, Object key, Class<T> type, T defaultValue) {
		return null == map ? null : Convert.convert(type, map.get(key), defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为指定类型](Gets the value of the key specified in the map and converts it to the specified type)
	 * @description zh - 获取Map指定key的值，并转换为指定类型
	 * @description en - Gets the value of the key specified in the map and converts it to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:54:11
	 * @param map Map
	 * @param key 键
	 * @param type 值类型
	 * @param defaultValue 默认值
	 * @return T
	 */
	public static <T> T getQuietly(Map<?, ?> map, Object key, Class<T> type, T defaultValue) {
		return null == map ? null : Convert.convertQuietly(type, map.get(key), defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为指定类型](Gets the value of the key specified in the map and converts it to the specified type)
	 * @description zh - 获取Map指定key的值，并转换为指定类型
	 * @description en - Gets the value of the key specified in the map and converts it to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:54:41
	 * @param map  Map
	 * @param key  键
	 * @param type 值类型
	 * @return T
	 */
	public static <T> T get(Map<?, ?> map, Object key, TypeReference<T> type) {
		return get(map, key, type, null);
	}

	/**
	 * [获取Map指定key的值，并转换为指定类型](Gets the value of the key specified in the map and converts it to the specified type)
	 * @description zh - 获取Map指定key的值，并转换为指定类型
	 * @description en - Gets the value of the key specified in the map and converts it to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:56:50
	 * @param map Map
	 * @param key 键
	 * @param type 值类型
	 * @param defaultValue 默认值
	 * @return T
	 */
	public static <T> T get(Map<?, ?> map, Object key, TypeReference<T> type, T defaultValue) {
		return null == map ? null : Convert.convert(type, map.get(key), defaultValue);
	}

	/**
	 * [获取Map指定key的值，并转换为指定类型](Gets the value of the key specified in the map and converts it to the specified type)
	 * @description zh - 获取Map指定key的值，并转换为指定类型
	 * @description en - Gets the value of the key specified in the map and converts it to the specified type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:57:32
	 * @param map Map
	 * @param key 键
	 * @param type 值类型
	 * @param defaultValue 默认值
	 * @return T
	 */
	public static <T> T getQuietly(Map<?, ?> map, Object key, TypeReference<T> type, T defaultValue) {
		return null == map ? null : Convert.convertQuietly(type, map.get(key), defaultValue);
	}

	/**
	 * [重命名](rename)
	 * @description zh - 重命名
	 * @description en - rename
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 09:58:20
	 * @param map 集合
	 * @param oldKey 原键
	 * @param newKey 新键
	 * @return java.util.Map<K, V>
	 * @throws java.lang.IllegalArgumentException.IllegalArgumentException
	 */
	public static <K, V> Map<K, V> renameKey(Map<K, V> map, K oldKey, K newKey) {
		if (isNotEmpty(map) && map.containsKey(oldKey)) {
			if (map.containsKey(newKey)) {
				throw new IllegalArgumentException(StrUtil.format("The key '{}' exist !", newKey));
			}
			map.put(newKey, map.remove(oldKey));
		}
		return map;
	}

	/**
	 * [去除Map中值为 null 的键值对](Remove null key value pairs in the map)
	 * @description zh - 去除Map中值为 null 的键值对
	 * @description en - Remove null key value pairs in the map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 10:00:12
	 * @param map 集合
	 * @return java.util.Map<K, V>
	 */
	public static <K, V> Map<K, V> removeNullValue(Map<K, V> map) {
		if (isEmpty(map)) {
			return map;
		}

		final Iterator<Entry<K, V>> iter = map.entrySet().iterator();
		Entry<K, V> entry;
		while (iter.hasNext()) {
			entry = iter.next();
			if (null == entry.getValue()) {
				iter.remove();
			}
		}

		return map;
	}

	/**
	 * [返回一个空Map](Returns an empty map)
	 * @description zh - 返回一个空Map
	 * @description en - Returns an empty map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 10:01:10
	 * @return java.util.Map<K, V>
	 */
	public static <K, V> Map<K, V> empty() {
		return Collections.emptyMap();
	}

	/**
	 * [根据传入的Map类型不同，返回对应类型的空Map](Empty map of corresponding type is returned according to different types of incoming map)
	 * @description zh - 根据传入的Map类型不同，返回对应类型的空Map
	 * @description en - Empty map of corresponding type is returned according to different types of incoming map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 10:02:03
	 * @param mapClass Map类型
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <K, V, T extends Map<K, V>> T empty(Class<?> mapClass) {
		if (null == mapClass) {
			return (T) Collections.emptyMap();
		}
		if (NavigableMap.class == mapClass) {
			return (T) Collections.emptyNavigableMap();
		} else if (SortedMap.class == mapClass) {
			return (T) Collections.emptySortedMap();
		} else if (Map.class == mapClass) {
			return (T) Collections.emptyMap();
		}

		// 不支持空集合的集合类型
		throw new IllegalArgumentException(StrUtil.format("[{}] is not support to get empty!", mapClass));
	}

	/**
	 *
	 * @description zh - 清除一个或多个Map集合内的元素
	 * @description en - Clears elements within one or more map collections
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-17 10:03:03
	 * @param maps 一个或多个Map
	 */
	public static void clear(Map<?, ?>... maps) {
		for (Map<?, ?> map : maps) {
			if (isNotEmpty(map)) {
				map.clear();
			}
		}
	}


}
