package com.xiaoTools.core.map.mapWrapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * [Map包装类](Map wrapper class)
 * @description zh - Map包装类
 * @description en - Map wrapper class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-30 14:54:54
 */
public class MapWrapper<K, V> implements Map<K, V>, Iterable<Map.Entry<K, V>>, Serializable, Cloneable {

    private static final long serialVersionUID = -7524578042008586382L;

    /**
	 *Default growth factor
	 */
	protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
	/**
	 * Default initial size
	 */
	protected static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 

	private final Map<K, V> raw;
    
}
