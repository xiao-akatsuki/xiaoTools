package com.xiaoTools.core.map.customKeyMap;

import java.util.Map;

import com.xiaoTools.core.map.mapWrapper.MapWrapper;

public abstract class CustomKeyMap<K, V> extends MapWrapper<K, V> {
    private static final long serialVersionUID = 4043263744224569870L;

    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-02 10:06:20
     * @param map 集合
     */
    public CustomKeyMap(Map<K, V> map) {
		super(map);
	}
}
