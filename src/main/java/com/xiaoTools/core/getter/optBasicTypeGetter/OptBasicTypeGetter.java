package com.xiaoTools.core.getter.optBasicTypeGetter;

/**
 * [可选默认值的基本类型的getter接口](Getter interface of basic type of optional default value)
 * @description zh - 可选默认值的基本类型的getter接口
 * @description en - Getter interface of basic type of optional default value
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-08 07:44:25
 */
public interface OptBasicTypeGetter<K> {
    
    /**
     * [获取Object属性值](Get object property value)
     * @description zh - 获取Object属性值
     * @description en - Get object property value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-09 08:45:05
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Object
     */
	Object getObj(K key, Object defaultValue);

    /**
     * [获取字符串型属性值](Get string property value)
     * @description zh - 获取字符串型属性值
     * @description en - Get string property value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-09 08:52:57
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.String
     */
    String getStr(K key, String defaultValue);

    /**
     * [获取int型属性值](Get int type attribute value)
     * @description zh - 获取int型属性值
     * @description en - Get int type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-09 08:55:40
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Integer
     */
    Integer getInt(K key, Integer defaultValue);

    /**
     * [获取short型属性值](Gets the value of a short attribute)
     * @description zh - 获取short型属性值
     * @description en - Gets the value of a short attribute
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-09 08:56:25
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Short
     */
    Short getShort(K key, Short defaultValue);

    /**
     * [获取boolean型属性值](Get Boolean attribute value)
     * @description zh - 获取boolean型属性值
     * @description en - Get Boolean attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-09 08:57:15
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Boolean
     */
    Boolean getBool(K key, Boolean defaultValue);

    /**
     * [获取Long型属性值](Gets the value of the long attribute)
     * @description zh - 获取Long型属性值
     * @description en - Gets the value of the long attribute
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-11 08:41:35
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Long
     */
    Long getLong(K key, Long defaultValue);

    /**
     * [获取char型属性值](Get char type attribute value)
     * @description zh - 获取char型属性值
     * @description en - Get char type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-11 08:42:27
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Character
     */
    Character getChar(K key, Character defaultValue);

    /**
     * [获取float型属性值](Get float type attribute value)
     * @description zh - 获取float型属性值
     * @description en - Get float type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-11 08:44:20
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Float
     */
    Float getFloat(K key, Float defaultValue);

    /**
     * [获取double型属性值](Get double type attribute value)
     * @description zh - 获取double型属性值
     * @description en - Get double type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-11 08:45:15
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Double
     */
    Double getDouble(K key, Double defaultValue);

    /**
     * [获取byte型属性值](Get byte type attribute value)
     * @description zh - 获取byte型属性值
     * @description en - Get byte type attribute value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-10-11 08:46:06
     * @param key 键
     * @param defaultValue 默认值
     * @return java.lang.Byte
     */
    Byte getByte(K key, Byte defaultValue);
}
