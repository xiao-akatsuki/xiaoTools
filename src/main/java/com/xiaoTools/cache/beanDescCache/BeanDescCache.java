package com.xiaoTools.cache.beanDescCache;

import com.xiaoTools.cache.simple.SimpleCache;
import com.xiaoTools.cache.simple.method.CacheFun;
import com.xiaoTools.entity.beanDesc.BeanDesc;

/**
 * [Bean属性缓存](Bean attribute cache)
 * @description zh - Bean属性缓存
 * @description en - Bean attribute cache
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-26 09:56:40
 */
public enum BeanDescCache {

	INSTANCE;

	private final SimpleCache<Class<?>, BeanDesc> bdCache = new SimpleCache<>();

	/**
	 * [获得属性名和 BeanDesc Map映射](Get the property name and beandesc map mapping)
	 * @description zh - 获得属性名和 BeanDesc Map映射
	 * @description en - Get the property name and beandesc map mapping
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:35:53
	 * @param beanClass Bean的类
	 * @param supplier 对象不存在时创建对象的函数
	 * @return com.xiaoTools.entity.beanDesc.BeanDesc
	 */
	public BeanDesc getBeanDesc(Class<?> beanClass, CacheFun<BeanDesc> supplier){
		return bdCache.get(beanClass, supplier);
	}

}
