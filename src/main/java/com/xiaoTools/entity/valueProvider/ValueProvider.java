package com.xiaoTools.entity.valueProvider;

import java.lang.reflect.Type;

/**
 * [值提供者](Value provider)
 * @description zh - 值提供者
 * @description en - Value provider
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-27 18:07:47
 */
public interface ValueProvider<T> {

	/**
	 * [获取值](Get value)
	 * @description zh - 获取值
	 * @description en - Get value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:08:13
	 * @param key Bean对象中参数名
	 * @param valueType 被注入的值的类型
	 * @return java.lang.Object
	 */
	Object value(T key, Type valueType);

	/**
	 * [是否包含指定KEY](Whether to include the specified key)
	 * @description zh - 是否包含指定KEY
	 * @description en - Whether to include the specified key
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:08:50
	 * @param key Bean对象中参数名
	 * @return boolean
	 */
	boolean containsKey(T key);

}
