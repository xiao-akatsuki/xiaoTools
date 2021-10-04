package com.xiaoTools.core.map.camelCaseLinkedMap;

import java.util.LinkedHashMap;
import java.util.Map;

import com.xiaoTools.core.map.customKeyMap.CustomKeyMap;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [驼峰Key风格的LinkedHashMap](Hump key style LinkedHashMap)
 * @description zh - 驼峰Key风格的LinkedHashMap
 * @description en - Hump key style LinkedHashMap
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-04 08:28:03
 */
public class CamelCaseLinkedMap<K,V> extends CustomKeyMap<K, V> {

    private static final long serialVersionUID = 4043263744224569870L;

    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-04 08:30:43
     */
    public CamelCaseLinkedMap() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-04 08:36:14
     * @param map 集合
     */
    public CamelCaseLinkedMap(Map<? extends K, ? extends V> map) {
		this(DEFAULT_LOAD_FACTOR, map);
	}

    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-04 08:31:24
     * @param initialCapacity 初始大小
     */
    public CamelCaseLinkedMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-04 08:32:01
     * @param loadFactor 加载因子
     * @param map 集合
     */
    public CamelCaseLinkedMap(float loadFactor, Map<? extends K, ? extends V> map) {
		this(map.size(), loadFactor);
		this.putAll(map);
	}
    
    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-04 08:33:05
     * @param initialCapacity 初始大小
     * @param loadFactor 加载因子
     */
    public CamelCaseLinkedMap(int initialCapacity, float loadFactor) {
		super(new LinkedHashMap<>(initialCapacity, loadFactor));
	}

    /**
     * [将Key转为驼峰风格](Change key to hump style)
     * @description zh - 将Key转为驼峰风格
     * @description en - Change key to hump style
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-04 08:34:14
     * @param key 键
     * @return java.lang.Object
     */
    @Override
	protected Object customKey(Object key) {
		if (key instanceof CharSequence) {
			key = StrUtil.toCamelCase(key.toString());
		}
		return key;
	}
}
