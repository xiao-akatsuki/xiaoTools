package com.xiaoTools.core.map.camelCaseLinkedMap;

import com.xiaoTools.core.map.customKeyMap.CustomKeyMap;

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

    
}
