package com.xiaoTools.core.convert.uUIDConverter;

import java.util.UUID;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [UUID对象转换器转换器](UUID Object Converter)
 * @description zh - UUID对象转换器转换器
 * @description en - UUID Object Converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 17:03:55
 */
public class UUIDConverter extends AbstractConverter<UUID> {
	private static final long serialVersionUID = 1L;

	@Override
	protected UUID convertInternal(Object value) {
		return UUID.fromString(convertToStr(value));
	}

}
