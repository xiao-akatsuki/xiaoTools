package com.xiaoTools.core.map.mapBuilder;

import java.io.Serializable;
import java.util.Map;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.mapUtil.MapUtil;

/**
 * [Map创建类](Map create class)
 * @description zh - Map创建类
 * @description en - Map create class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-15 07:56:15
 */
public class MapBuilder<K, V> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<K, V> map;

    public MapBuilder(Map<K, V> map) {
		this.map = map;
	}

    /** 
     * [创建Builder，默认HashMap实现](Create a builder, which is implemented by HashMap by default)
     * @description zh - 创建Builder，默认HashMap实现
     * @description en - Create a builder, which is implemented by HashMap by default
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 07:58:45
     * @return com.xiaoTools.core.map.mapBuilder.MapBuilder<K, V>
     */
    public static <K, V> MapBuilder<K, V> create() {
		return create(Constant.FALSE);
	}

    /**
     * [创建Builder](Create builder)
     * @description zh - 创建Builder
     * @description en - Create builder
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:00:21
     * @param isLinked true创建LinkedHashMap，false创建HashMap
     * @return com.xiaoTools.core.map.mapBuilder.MapBuilder<K, V>
     */
    public static <K, V> MapBuilder<K, V> create(boolean isLinked) {
		return create(MapUtil.newHashMap(isLinked));
	}

    /**
     * [创建Builder](Create builder)
     * @description zh - 创建Builder
     * @description en - Create builder
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:01:37
     * @param map 集合
     * @return com.xiaoTools.core.map.mapBuilder.MapBuilder<K, V>
     */
    public static <K, V> MapBuilder<K, V> create(Map<K, V> map) {
		return new MapBuilder<>(map);
	}
    
    /**
     * [链式Map创建](Chain map creation)
     * @description zh - 链式Map创建
     * @description en - Chain map creation
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:03:49
     * @param key 键
     * @param value 值
     * @return com.xiaoTools.core.map.mapBuilder.MapBuilder<K, V>
     */
    public MapBuilder<K, V> put(K key, V value) {
		map.put(key, value);
		return this;
	}

    /**
     * [链式Map创建](Chain map creation)
     * @description zh - 链式Map创建
     * @description en - Chain map creation
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:05:29
     * @param map 集合
     * @return com.xiaoTools.core.map.mapBuilder.MapBuilder<K, V>
     */
	public MapBuilder<K, V> putAll(Map<K, V> map) {
		this.map.putAll(map);
		return this;
	}

    /**
     * [创建后的map](Map after creation)
     * @description zh - 创建后的map
     * @description en - Map after creation
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:07:35
     * @return java.util.Map<K, V>
     */
    public Map<K, V> map() {
		return map;
	}

    /**
     * [创建后的map](Map after creation)
     * @description zh - 创建后的map
     * @description en - Map after creation
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:08:08
     * @return java.util.Map<K, V>
     */
    public Map<K, V> build() {
		return map();
	}

    /**
     * [将map转成字符串](Convert map to string)
     * @description zh - 将map转成字符串
     * @description en - Convert map to string
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:08:44
	 * @param separator entry之间的连接符
	 * @param keyValueSeparator kv之间的连接符
     * @return java.lang.String
     */
	public String join(String separator, final String keyValueSeparator) {
		return MapUtil.join(this.map, separator, keyValueSeparator);
	}

    /**
     * [将map转成字符串](Convert map to string)
     * @description zh - 将map转成字符串
     * @description en - Convert map to string
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:09:24
	 * @param separator entry之间的连接符
	 * @param keyValueSeparator kv之间的连接符
     * @return java.lang.String
     */
	public String joinIgnoreNull(String separator, final String keyValueSeparator) {
		return MapUtil.joinIgnoreNull(this.map, separator, keyValueSeparator);
	}

    /**
     * [将map转成字符串](Convert map to string)
     * @description zh - 将map转成字符串
     * @description en - Convert map to string
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-15 08:09:43
	 * @param separator entry之间的连接符
	 * @param keyValueSeparator kv之间的连接符
	 * @param isIgnoreNull 是否忽略null的键和值
     * @return java.lang.String
     */
	public String join(String separator, final String keyValueSeparator, boolean isIgnoreNull) {
		return MapUtil.join(this.map, separator, keyValueSeparator, isIgnoreNull);
	}
}
