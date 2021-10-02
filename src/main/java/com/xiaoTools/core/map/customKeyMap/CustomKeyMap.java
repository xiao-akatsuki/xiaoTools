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

    @Override
	public V get(Object key) {
		return super.get(customKey(key));
	}

	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		return super.put((K) customKey(key), value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		m.forEach(this::put);
	}

	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(customKey(key));
	}

	@Override
	public V remove(Object key) {
		return super.remove(customKey(key));
	}

	@Override
	public boolean remove(Object key, Object value) {
		return super.remove(customKey(key), value);
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue) {
		return super.replace((K) customKey(key), oldValue, newValue);
	}

	@Override
	public V replace(K key, V value) {
		return super.replace((K) customKey(key), value);
	}

    /**
     * [自定义键](Custom key)
     * @description zh - 自定义键
     * @description en - Custom key
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-02 10:07:54
     * @param key 键
     * @return java.lang.Object
     */
    protected abstract Object customKey(Object key);
}
