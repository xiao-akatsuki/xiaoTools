package com.xiaoTools.core.getter.optNullBasicTypeGetter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.xiaoTools.core.getter.basicTypeGetter.BasicTypeGetter;
import com.xiaoTools.core.getter.optBasicTypeGetter.OptBasicTypeGetter;


/**
 * [基本类型的getter接口抽象实现](Abstract implementation of getter interface of basic type)
 * @description zh - 基本类型的getter接口抽象实现
 * @description en - Abstract implementation of getter interface of basic type
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-08 07:41:50
 */
public interface OptNullBasicTypeGetter<K> extends BasicTypeGetter<K>, OptBasicTypeGetter<K> {
    @Override
	default Object getObj(K key) {
		return getObj(key, null);
	}
	
    /** 
     * [获取字符串型属性值](Get string property value)
     * @description zh - 获取字符串型属性值
     * @description en - Get string property value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-12 09:15:23
     * @param key 键
     * @return java.lang.String
     */
	@Override
	default String getStr(K key){
		return this.getStr(key, null);
	}
	
	/**
     * [获取int型属性值](Get int type attribute value)
     * @description zh - 获取int型属性值
     * @description en - Get int type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 10:59:36
     * @param key 键
     * @return java.lang.Integer
     */
	@Override
	default Integer getInt(K key) {
		return this.getInt(key, null);
	}
	
	/**
     * [获取short型属性值](Get short type attribute value)
     * @description zh - 获取short型属性值
     * @description en - Get short type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:01:28
     * @param key 键
     * @return java.lang.Short
     */
	@Override
	default Short getShort(K key){
		return this.getShort(key, null);
	}
	
	/**
     * [获取Boolean型属性值](Get Boolean type attribute value)
     * @description zh - 获取Boolean型属性值
     * @description en - Get Boolean type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:02:15
     * @param key 键
     * @return java.lang.Boolean
     */
	@Override
	default Boolean getBool(K key){
		return this.getBool(key, null);
	}
	
	/**
     * [获取Long型属性值](Get Long type attribute value)
     * @description zh - 获取Long型属性值
     * @description en - Get Long type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:03:24
     * @param key 键
     * @return java.lang.Long
     */
	@Override
	default Long getLong(K key){
		return this.getLong(key, null);
	}
	
	/**
     * [获取Character型属性值](Get Character type attribute value)
     * @description zh - 获取Character型属性值
     * @description en - Get Character type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:04:10
     * @param key 键
     * @return java.lang.Character
     */
	@Override
	default Character getChar(K key){
		return this.getChar(key, null);
	}
	
	/**
     * [获取Float型属性值](Get Float type attribute value)
     * @description zh - 获取Float型属性值
     * @description en - Get Float type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:04:54
     * @param key 键
     * @return java.lang.Float
     */
	@Override
	default Float getFloat(K key){
		return this.getFloat(key, null);
	}
	
	/**
     * [获取Double型属性值](Get Double type attribute value)
     * @description zh - 获取Double型属性值
     * @description en - Get Double type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:05:41
     * @param key 键
     * @return java.lang.Double
     */
	@Override
	default Double getDouble(K key){
		return this.getDouble(key, null);
	}

	/**
     * [获取Byte型属性值](Get Byte type attribute value)
     * @description zh - 获取Byte型属性值
     * @description en - Get Byte type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:06:18
     * @param key 键
     * @return java.lang.Byte
     */
	@Override
	default Byte getByte(K key){
		return this.getByte(key, null);
	}
	
	/**
     * [获取BigDecimal型属性值](Get BigDecimal type attribute value)
     * @description zh - 获取BigDecimal型属性值
     * @description en - Get BigDecimal type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:07:04
     * @param key 键
     * @return java.math.BigDecimal
     */
	@Override
	default BigDecimal getBigDecimal(K key){
		return this.getBigDecimal(key, null);
	}
	
	/**
     * [获取BigInteger型属性值](Get BigInteger type attribute value)
     * @description zh - 获取BigInteger型属性值
     * @description en - Get BigInteger type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:07:57
     * @param key 键
     * @return java.math.BigInteger
     */
	@Override
	default BigInteger getBigInteger(K key){
		return this.getBigInteger(key, null);
	}
	
	/**
     * [获取Enum型属性值](Get Enum type attribute value)
     * @description zh - 获取Enum型属性值
     * @description en - Get Enum type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:08:34
     * @param clazz Enum的Class 
     * @param key 键
     * @return E
     */
	@Override
	default <E extends Enum<E>> E getEnum(Class<E> clazz, K key) {
		return this.getEnum(clazz, key, null);
	}
	
	/**
     * [获取Date类型值](Get date type value)
     * @description zh - 获取Date类型值
     * @description en - Get date type value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-14 11:09:35
     * @param key 键
     * @return java.util.Date
     */
	@Override
	default Date getDate(K key) {
		return this.getDate(key, null);
	}
}
