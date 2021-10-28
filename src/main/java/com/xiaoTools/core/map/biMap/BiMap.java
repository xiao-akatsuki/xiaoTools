package com.xiaoTools.core.map.biMap;

import java.util.Map;

import com.xiaoTools.core.map.mapWrapper.MapWrapper;
import com.xiaoTools.util.mapUtil.MapUtil;

/**
 * [双向Map](Bidirectional map)
 * @description zh - 双向Map
 * @description en - Bidirectional map
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 19:13:52
 */
public class BiMap<K, V> extends MapWrapper<K, V> {

	private static final long serialVersionUID = 1L;

	private Map<V, K> inverse;

	public BiMap(Map<K, V> raw) {
		super(raw);
	}

	@Override
	public V put(K key, V value) {
		if (null != this.inverse) {
			this.inverse.put(value, key);
		}
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		super.putAll(m);
		if (null != this.inverse) {
			m.forEach((key, value) -> this.inverse.put(value, key));
		}
	}

	@Override
	public void clear() {
		super.clear();
		this.inverse = null;
	}

	/**
	 * [获取反向Map](Get reverse map)
	 * @description zh - 获取反向Map
	 * @description en - Get reverse map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:15:53
	 * @return java.util.Map<K,V>
	 */
	public Map<V, K> getInverse() {
		if (null == this.inverse) {
			inverse = MapUtil.inverse(getRaw());
		}
		return this.inverse;
	}

	/**
	 * [根据值获得键](Get key from value)
	 * @description zh - 根据值获得键
	 * @description en - Get key from value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:16:23
	 * @param value 值
	 * @return K
	 */
	public K getKey(V value) {
		return getInverse().get(value);
	}

}
