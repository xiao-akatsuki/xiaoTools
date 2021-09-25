package com.xiaoTools.lang.pair;

import com.xiaoTools.lang.clone.cLoneSupport.CloneSupport;

import java.io.Serializable;
import java.util.Objects;

/**
 * [键值对对象](Key value pair object)
 * @description zh - 键值对对象
 * @description en - Key value pair object
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-25 19:54:42
 */
public class Pair<K, V> extends CloneSupport<Pair<K, V>> implements Serializable {

    private static final long serialVersionUID = 1L;

	private final K key;
	private final V value;

    /**
     * [构建 Pair 对象](Building pair objects)
     * @description zh - 构建 Pair 对象
     * @description en - Building pair objects
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-25 19:56:19
     * @param key 键
     * @param value 值
     * @return com.xiaoTools.lang.pair.Pair<K, V>
     */
    public static <K, V> Pair<K, V> of(K key, V value) {
		return new Pair<>(key, value);
	}

    /**
     * [构造](structure)
     * @description zh - 构造
     * @description en - structure
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-25 19:58:53
     * @param key 键
     * @param value 值
     */
    public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

    /**
     * [获取值](get value)
     * @description zh - 获取值
     * @description en - get Value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-25 20:01:10
     * @return V
     */
    public V getValue() {
		return this.value;
	}

    /**
     * [获取键](get key)
     * @description zh - 获取键
     * @description en - get key 
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-25 20:08:57
     */
    public K getKey() {
		return this.key;
	}


    @Override
	public String toString() {
		return "Pair [key=" + key + ", value=" + value + "]";
	}

    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof Pair) {
			Pair<?, ?> pair = (Pair<?, ?>) o;
			return Objects.equals(getKey(), pair.getKey()) &&
					Objects.equals(getValue(), pair.getValue());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(key) ^ Objects.hashCode(value);
	}


}
