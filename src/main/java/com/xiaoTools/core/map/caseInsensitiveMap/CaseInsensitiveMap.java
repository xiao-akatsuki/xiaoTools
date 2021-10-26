package com.xiaoTools.core.map.caseInsensitiveMap;

import java.util.HashMap;
import java.util.Map;

import com.xiaoTools.core.map.customKeyMap.CustomKeyMap;

/**
 * [忽略大小写的Map](Ignore uppercase and lowercase maps)
 * @description zh - 忽略大小写的Map
 * @description en - Ignore uppercase and lowercase maps
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-26 20:13:15
 */
public class CaseInsensitiveMap<K, V> extends CustomKeyMap<K, V> {

	private static final long serialVersionUID = 4043263744224569870L;

	public CaseInsensitiveMap() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public CaseInsensitiveMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	public CaseInsensitiveMap(Map<? extends K, ? extends V> m) {
		this(DEFAULT_LOAD_FACTOR, m);
	}

	public CaseInsensitiveMap(float loadFactor, Map<? extends K, ? extends V> m) {
		this(m.size(), loadFactor);
		this.putAll(m);
	}

	public CaseInsensitiveMap(int initialCapacity, float loadFactor) {
		super(new HashMap<>(initialCapacity, loadFactor));
	}

	@Override
	protected Object customKey(Object key) {
		if (key instanceof CharSequence) {
			key = key.toString().toLowerCase();
		}
		return key;
	}

}
