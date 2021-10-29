package com.xiaoTools.core.map.tableMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.listUtil.ListUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

/**
 * [可重复键和值的Map](Map of repeatable keys and values)
 * @description zh - 可重复键和值的Map
 * @description en - Map of repeatable keys and values
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-29 20:03:16
 */
public class TableMap<K, V> implements Map<K, V>, Iterable<Map.Entry<K, V>>, Serializable {

	private static final long serialVersionUID = 1L;

	private final List<K> keys;
	private final List<V> values;

	public TableMap(int size) {
		this.keys = new ArrayList<>(size);
		this.values = new ArrayList<>(size);
	}

	public TableMap(K[] keys, V[] values) {
		this.keys = CollUtil.toList(keys);
		this.values = CollUtil.toList(values);
	}

	/**
	 * [根据value获得对应的key](Obtain the corresponding key according to value)
	 * @description zh - 根据value获得对应的key
	 * @description en - Obtain the corresponding key according to value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:07:17
	 * @param value 值
	 * @return K
	 */
	public K getKey(V value){
		final int index = values.indexOf(value);
		if (index > -1 && index < keys.size()) {
			return keys.get(index);
		}
		return null;
	}

	/**
	 * [获取指定key对应的所有值](Gets all the values corresponding to the specified key)
	 * @description zh - 获取指定key对应的所有值
	 * @description en - Gets all the values corresponding to the specified key
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:06:32
	 * @param key 键
	 * @return java.util.List<V>
	 */
	public List<V> getValues(K key) {
		return CollUtil.getAny(
				this.values,
				ListUtil.indexOfAll(this.keys, (ele) -> ObjectUtil.equal(ele, key))
		);
	}

	/**
	 * [获取指定value对应的所有key](Gets all keys corresponding to the specified value)
	 * @description zh - 获取指定value对应的所有key
	 * @description en - Gets all keys corresponding to the specified value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:05:46
	 * @param value 值
	 * @return java.util.List<K>
	 */
	public List<K> getKeys(V value) {
		return CollUtil.getAny(
				this.keys,
				ListUtil.indexOfAll(this.values, (ele) -> ObjectUtil.equal(ele, value))
		);
	}

	@Override
	public int size() {
		return keys.size();
	}

	@Override
	public boolean isEmpty() {
		return CollUtil.isEmpty(keys);
	}

	@Override
	public boolean containsKey(Object key) {
		return keys.contains(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values.contains(value);
	}

	@Override
	public V get(Object key) {
		final int index = keys.indexOf(key);
		if (index > -1 && index < values.size()) {
			return values.get(index);
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		keys.add(key);
		values.add(value);
		return null;
	}

	@Override
	public V remove(Object key) {
		int index = keys.indexOf(key);
		if (index > -1) {
			keys.remove(index);
			if (index < values.size()) {
				values.remove(index);
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
			this.put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		keys.clear();
		values.clear();
	}

	@Override
	public Set<K> keySet() {
		return new HashSet<>(keys);
	}

	@Override
	public Collection<V> values() {
		return Collections.unmodifiableList(this.values);
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		final Set<Map.Entry<K, V>> hashSet = new LinkedHashSet<>();
		for (int i = 0; i < size(); i++) {
			hashSet.add(new Entry<>(keys.get(i), values.get(i)));
		}
		return hashSet;
	}

	@Override
	public Iterator<Map.Entry<K, V>> iterator() {
		return new Iterator<Map.Entry<K, V>>() {
			private final Iterator<K> keysIter = keys.iterator();
			private final Iterator<V> valuesIter = values.iterator();

			@Override
			public boolean hasNext() {
				return keysIter.hasNext() && valuesIter.hasNext();
			}

			@Override
			public Map.Entry<K, V> next() {
				return new Entry<>(keysIter.next(), valuesIter.next());
			}

			@Override
			public void remove() {
				keysIter.remove();
				valuesIter.remove();
			}
		};
	}

	@Override
	public String toString() {
		return "TableMap{" +
				"keys=" + keys +
				", values=" + values +
				'}';
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {

		private final K key;
		private final V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			throw new UnsupportedOperationException("setValue not supported.");
		}

		@Override
		public final boolean equals(Object o) {
			if (o == this)
				return true;
			if (o instanceof Map.Entry) {
				Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
				return Objects.equals(key, e.getKey()) &&
						Objects.equals(value, e.getValue());
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}
	}

}
