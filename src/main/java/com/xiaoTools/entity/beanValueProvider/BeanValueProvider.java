package com.xiaoTools.entity.beanValueProvider;

import java.util.Map;
import java.lang.reflect.Type;

import com.xiaoTools.entity.propDesc.PropDesc;
import com.xiaoTools.entity.valueProvider.ValueProvider;
import com.xiaoTools.util.beanUtil.BeanUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [Bean的值提供者](Value provider for bean)
 * @description zh - Bean的值提供者
 * @description en - Value provider for bean
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-27 18:58:06
 */
public class BeanValueProvider implements ValueProvider<String>  {

	private final Object source;
	private final boolean ignoreError;
	final Map<String, PropDesc> sourcePdMap;

	/**
	 * [构造](structure)
	 * @description zh - 构造
	 * @description en - structure
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:59:36
	 * @param bean Bean
	 * @param ignoreCase 是否忽略字段大小写
	 * @param ignoreError 是否忽略字段值读取错误
	 */
	public BeanValueProvider(Object bean, boolean ignoreCase, boolean ignoreError) {
		this.source = bean;
		this.ignoreError = ignoreError;
		sourcePdMap = BeanUtil.getBeanDesc(source.getClass()).getPropMap(ignoreCase);
	}

	@Override
	public Object value(String key, Type valueType) {
		final PropDesc sourcePd = getPropDesc(key, valueType);

		Object result = null;
		if (null != sourcePd) {
			result = sourcePd.getValue(this.source, valueType, this.ignoreError);
		}
		return result;
	}

	@Override
	public boolean containsKey(String key) {
		final PropDesc sourcePd = getPropDesc(key, null);
		return null != sourcePd && sourcePd.isReadable(false);
	}

	/**
	 * [获得属性描述](Get attribute description)
	 * @description zh - 获得属性描述
	 * @description en - Get attribute description
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 19:01:32
	 * @param key 字段名
	 * @param valueType 值类型
	 * @return com.xiaoTools.entity.propDesc.PropDesc
	 */
	private PropDesc getPropDesc(String key, Type valueType) {
		PropDesc sourcePd = sourcePdMap.get(key);
		if (null == sourcePd && (null == valueType || Boolean.class == valueType || boolean.class == valueType)) {
			sourcePd = sourcePdMap.get(StrUtil.upperFirstAndAddPre(key, "is"));
		}

		return sourcePd;
	}

}
