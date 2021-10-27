package com.xiaoTools.entity.dynaBeanValueProvider;

import java.lang.reflect.Type;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.entity.dynaBean.DynaBean;
import com.xiaoTools.entity.valueProvider.ValueProvider;

/**
 * [Bean的值提供者](Value provider for bean)
 * @description zh - Bean的值提供者
 * @description en - Value provider for bean
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-27 19:04:44
 */
public class DynaBeanValueProvider implements ValueProvider<String> {

	private final DynaBean dynaBean;
	private final boolean ignoreError;

	public DynaBeanValueProvider(DynaBean dynaBean, boolean ignoreError) {
		this.dynaBean = dynaBean;
		this.ignoreError = ignoreError;
	}

	@Override
	public Object value(String key, Type valueType) {
		final Object value = dynaBean.get(key);
		return Convert.convertWithCheck(valueType, value, null, this.ignoreError);
	}

	@Override
	public boolean containsKey(String key) {
		return dynaBean.containsProp(key);
	}

}
