package com.xiaoTools.core.convert.optionalConverter;

import java.util.Optional;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [Optional对象转换器](Optional object converter)
 * @description zh - Optional对象转换器
 * @description en - Optional object converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 17:07:02
 */
public class OptionalConverter extends AbstractConverter<Optional<?>> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Optional<?> convertInternal(Object value) {
		return Optional.ofNullable(value);
	}

}
