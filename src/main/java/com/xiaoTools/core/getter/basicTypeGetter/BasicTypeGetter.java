package com.xiaoTools.core.getter.basicTypeGetter;

import java.math.BigDecimal;

/**
 * [基本类型的getter接口](Getter interface of basic type)
 * @description zh - 基本类型的getter接口
 * @description en - Getter interface of basic type
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-08 07:43:31
 */
public interface BasicTypeGetter<K> {

    /**
     * [获取Object属性值](Get object property value)
     * @description zh - 获取Object属性值
     * @description en - Get object property value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-12 09:13:58
     * @param key 键
     * @return java.lang.Object
     */
    Object getObj(K key);

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
    String getStr(K key);

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
    Integer getInt(K key);

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
    Short getShort(K key);

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
    Boolean getBool(K key);

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
    Long getLong(K key);

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
    Character getChar(K key);

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
    Float getFloat(K key);

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
    Double getDouble(K key);

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
    Byte getByte(K key);

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
    BigDecimal getBigDecimal(K key);
}
