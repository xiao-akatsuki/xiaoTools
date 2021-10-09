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
}
