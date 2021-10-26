package com.xiaoTools.cache.beanInfoCache;

import java.beans.PropertyDescriptor;
import java.util.Map;

import com.xiaoTools.cache.simple.SimpleCache;
import com.xiaoTools.cache.simple.method.CacheFun;

/**
 * [Bean属性缓存](Bean attribute cache)
 * @description zh - Bean属性缓存
 * @description en - Bean attribute cache
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-26 10:00:46
 */
public enum BeanInfoCache {
	INSTANCE;

	private final SimpleCache<Class<?>, Map<String, PropertyDescriptor>> pdCache = new SimpleCache<>();
	private final SimpleCache<Class<?>, Map<String, PropertyDescriptor>> ignoreCasePdCache = new SimpleCache<>();

	/**
	 * [获得属性名和 PropertyDescriptor Map 映射](Get property name and PropertyDescriptor Map mapping)
	 * @description zh - 获得属性名和 PropertyDescriptor Map 映射
	 * @description en - Get property name and PropertyDescriptor Map mapping
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 10:04:04
	 * @param beanClass
	 * @param ignoreCase
	 * @return java.util.Map<java.lang.String, java.beans.PropertyDescriptor>
	 */
	public Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> beanClass, boolean ignoreCase) {
		return getCache(ignoreCase).get(beanClass);
	}

	/**
	 * [获得属性名和 PropertyDescriptor Map 映射](Get property name and PropertyDescriptor Map mapping)
	 * @description zh - 获得属性名和 PropertyDescriptor Map 映射
	 * @description en - Get property name and PropertyDescriptor Map mapping
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 10:05:31
	 * @param beanClass Bean的类
	 * @param ignoreCase 是否忽略大小写
	 * @param supplier 缓存对象产生函数
	 * @return java.util.Map<java.lang.String, java.beans.PropertyDescriptor>
	 */
	public Map<String, PropertyDescriptor> getPropertyDescriptorMap(
			Class<?> beanClass,
			boolean ignoreCase,
			CacheFun<Map<String, PropertyDescriptor>> supplier) {
		return getCache(ignoreCase).get(beanClass, supplier);
	}

	/**
	 * 加入缓存
	 *
	 * @param beanClass                      Bean的类
	 * @param fieldNamePropertyDescriptorMap 属性名和{@link PropertyDescriptor}Map映射
	 * @param ignoreCase                     是否忽略大小写
	 */
	/**
	 * [加入缓存](Add cache)
	 * @description zh - 加入缓存
	 * @description en - Add cache
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 10:06:39
	 * @param beanClass Bean的类
	 * @param fieldNamePropertyDescriptorMap 属性名和 PropertyDescriptor Map 映射
	 * @param ignoreCase 是否忽略大小写
	 */
	public void putPropertyDescriptorMap(Class<?> beanClass, Map<String, PropertyDescriptor> fieldNamePropertyDescriptorMap, boolean ignoreCase) {
		getCache(ignoreCase).put(beanClass, fieldNamePropertyDescriptorMap);
	}

	/**
	 * [根据是否忽略字段名的大小写](Depending on whether the case of the field name is ignored)
	 * @description zh - 根据是否忽略字段名的大小写
	 * @description en - Depending on whether the case of the field name is ignored
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 10:07:19
	 * @param ignoreCase 是否忽略大小写
	 * @return com.xiaoTools.cache.simple.SimpleCache<java.lang.Class<?>, java.util.Map<java.lang.String, java.beans.PropertyDescriptor>>
	 */
	private SimpleCache<Class<?>, Map<String, PropertyDescriptor>> getCache(boolean ignoreCase) {
		return ignoreCase ? ignoreCasePdCache : pdCache;
	}

}
