package com.xiaoTools.core.getter.optNullBasicTypeFromObjectGetter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.getter.optNullBasicTypeGetter.OptNullBasicTypeGetter;

/**
 * [基本类型的getter接口抽象实现](Abstract implementation of getter interface of basic type)
 * @description zh - 基本类型的getter接口抽象实现
 * @description en - Abstract implementation of getter interface of basic type
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-08 07:40:38
 */
public interface OptNullBasicTypeFromObjectGetter<K> extends OptNullBasicTypeGetter<K> {
    @Override
	default String getStr(K key, String defaultValue) {
		final Object obj = getObj(key);
		return null == obj ? defaultValue : Convert.toStr(obj, defaultValue);
	}

	@Override
	default Integer getInt(K key, Integer defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toInt(obj, defaultValue);
	}

	@Override
	default Short getShort(K key, Short defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toShort(obj, defaultValue);
	}

	@Override
	default Boolean getBool(K key, Boolean defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toBoolean(obj, defaultValue);
	}

	@Override
	default Long getLong(K key, Long defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toLong(obj, defaultValue);
	}

	@Override
	default Character getChar(K key, Character defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toChar(obj, defaultValue);
	}

	@Override
	default Float getFloat(K key, Float defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toFloat(obj, defaultValue);
	}

	@Override
	default Double getDouble(K key, Double defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toDouble(obj, defaultValue);
	}

	@Override
	default Byte getByte(K key, Byte defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toByte(obj, defaultValue);
	}

	@Override
	default BigDecimal getBigDecimal(K key, BigDecimal defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toBigDecimal(obj, defaultValue);
	}

	@Override
	default BigInteger getBigInteger(K key, BigInteger defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toBigInteger(obj, defaultValue);
	}

	@Override
	default <E extends Enum<E>> E getEnum(Class<E> clazz, K key, E defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toEnum(clazz, obj, defaultValue);
	}

	@Override
	default Date getDate(K key, Date defaultValue) {
		final Object obj = getObj(key);
		
		return null == obj ? defaultValue : Convert.toDate(obj, defaultValue);
	}
}
