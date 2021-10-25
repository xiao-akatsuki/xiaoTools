package com.xiaoTools.core.convert.mapConverter;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.core.convert.converterRegistry.ConverterRegistry;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.strUtil.StrUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;

/**
 * [Map 转换器](Map converter)
 * @description zh - Map 转换器
 * @description en - Map converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-25 08:33:29
 */
public class MapConverter extends AbstractConverter<Map<?, ?>> {
	private static final long serialVersionUID = 1L;

	/**
	 * Map类型
	 */
	private final Type mapType;

	/**
	 * 键类型
	 */
	private final Type keyType;

	/**
	 * 值类型
	 */
	private final Type valueType;

	public MapConverter(Type mapType) {
		this(mapType, TypeUtil.getTypeArgument(mapType, Constant.ZERO), TypeUtil.getTypeArgument(mapType, 1));
	}

	public MapConverter(Type mapType, Type keyType, Type valueType) {
		this.mapType = mapType;
		this.keyType = keyType;
		this.valueType = valueType;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map<?, ?> convertInternal(Object value) {
		Map map;
		if (value instanceof Map) {
			final Type[] typeArguments = TypeUtil.getTypeArguments(value.getClass());
			if (null != typeArguments //
					&& 2 == typeArguments.length//
					&& Objects.equals(this.keyType, typeArguments[0]) //
					&& Objects.equals(this.valueType, typeArguments[1])) {
				return (Map) value;
			}
			map = MapUtil.createMap(TypeUtil.getClass(this.mapType));
			convertMapToMap((Map) value, map);
		} else if (BeanUtil.isBean(value.getClass())) {
			map = BeanUtil.beanToMap(value);
			map = convertInternal(map);
		} else {
			throw new UnsupportedOperationException(StrUtil.format("Unsupport toMap value type: {}", value.getClass().getName()));
		}
		return map;
	}

	/**
	 * [Map转Map](Map to Map)
	 * @description zh - Map转Map
	 * @description en - Map to Map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:32:51
	 * @param srcMap 源Map
	 * @param targetMap 目标Map
	 */
	private void convertMapToMap(Map<?, ?> srcMap, Map<Object, Object> targetMap) {
		final ConverterRegistry convert = ConverterRegistry.getInstance();
		Object key;
		Object value;
		for (Entry<?, ?> entry : srcMap.entrySet()) {
			key = TypeUtil.isUnknown(this.keyType) ? entry.getKey() : convert.convert(this.keyType, entry.getKey());
			value = TypeUtil.isUnknown(this.valueType) ? entry.getValue() : convert.convert(this.valueType, entry.getValue());
			targetMap.put(key, value);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<Map<?, ?>> getTargetType() {
		return (Class<Map<?, ?>>) TypeUtil.getClass(this.mapType);
	}
}
