package com.xiaoTools.core.convert.beanConverter;

import java.util.Map;
import java.lang.reflect.Type;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.core.exception.convertException.ConvertException;
import com.xiaoTools.core.map.mapProxy.MapProxy;
import com.xiaoTools.entity.beanCopier.BeanCopier;
import com.xiaoTools.entity.copyOptions.CopyOptions;
import com.xiaoTools.entity.valueProvider.ValueProvider;
import com.xiaoTools.util.beanUtil.BeanUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;

/**
 * [Bean转换器](Bean converter)
 * @description zh - Bean转换器
 * @description en - Bean converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 10:05:23
 */
public class BeanConverter<T> extends AbstractConverter<T> {

	private static final long serialVersionUID = 1L;

	private final Type beanType;
	private final Class<T> beanClass;
	private final CopyOptions copyOptions;

	@SuppressWarnings("unchecked")
	public BeanConverter(Type beanType, CopyOptions copyOptions) {
		this.beanType = beanType;
		this.beanClass = (Class<T>) TypeUtil.getClass(beanType);
		this.copyOptions = copyOptions;
	}

	public BeanConverter(Class<T> beanClass) {
		this(beanClass, CopyOptions.create().setIgnoreError(true));
	}

	public BeanConverter(Type beanType) {
		this(beanType, CopyOptions.create().setIgnoreError(true));
	}

	@Override
	protected T convertInternal(Object value) {
		if(value instanceof Map ||
				value instanceof ValueProvider ||
				BeanUtil.isBean(value.getClass())) {
			if(value instanceof Map && this.beanClass.isInterface()) {
				return MapProxy.create((Map<?, ?>)value).toProxyBean(this.beanClass);
			}

			return BeanCopier.create(value, ReflectUtil.newInstanceIfPossible(this.beanClass), this.beanType, this.copyOptions).copy();
		} else if(value instanceof byte[]){
			return ObjectUtil.deserialize((byte[])value);
		}

		throw new ConvertException("Unsupported source type: {}", value.getClass());
	}

	@Override
	public Class<T> getTargetType() {
		return this.beanClass;
	}

}
