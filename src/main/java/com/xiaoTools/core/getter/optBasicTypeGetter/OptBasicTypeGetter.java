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
}
