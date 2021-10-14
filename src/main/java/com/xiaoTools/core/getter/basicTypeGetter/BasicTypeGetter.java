package com.xiaoTools.core.getter.basicTypeGetter;

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
}
