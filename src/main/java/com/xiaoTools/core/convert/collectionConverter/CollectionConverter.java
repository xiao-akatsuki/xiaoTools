package com.xiaoTools.core.convert.collectionConverter;

import java.lang.reflect.Type;
import java.util.Collection;

import com.xiaoTools.core.convert.converter.Converter;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;

/**
 * [各种集合类转换器](Various collection class converters)
 * @description zh - 各种集合类转换器
 * @description en - Various collection class converters
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-25 08:28:41
 */
public class CollectionConverter implements Converter<Collection<?>> {

	/**
	 * 集合类型
	 */
	private final Type collectionType;

	/**
	 * 集合元素类型
	 */
	private final Type elementType;

	public CollectionConverter(Type collectionType) {
		this(collectionType, TypeUtil.getTypeArgument(collectionType));
	}

	public CollectionConverter(Class<?> collectionType) {
		this(collectionType, TypeUtil.getTypeArgument(collectionType));
	}

	public CollectionConverter(Type collectionType, Type elementType) {
		this.collectionType = collectionType;
		this.elementType = elementType;
	}

	@Override
	public Collection<?> convert(Object value, Collection<?> defaultValue) throws IllegalArgumentException {
		final Collection<?> result = convertInternal(value);
		return ObjectUtil.defaultIfNull(result, defaultValue);
	}

	protected Collection<?> convertInternal(Object value) {
		final Collection<Object> collection = CollUtil.create(TypeUtil.getClass(this.collectionType));
		return CollUtil.addAll(collection, value, this.elementType);
	}

}
