package com.xiaoTools.core.convert.atomicBooleanConverter;

import java.util.concurrent.atomic.AtomicBoolean;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.util.booleanUtil.BooleanUtil;

/**
 * [AtomicBoolean转换器](Atomicboolean converter)
 * @description zh - AtomicBoolean转换器
 * @description en - Atomicboolean converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-19 07:28:17
 */
public class AtomicBooleanConverter extends AbstractConverter<AtomicBoolean> {

	private static final long serialVersionUID = 1L;

	@Override
	protected AtomicBoolean convertInternal(Object value) {
		if (value instanceof Boolean) {
			return new AtomicBoolean((Boolean) value);
		}
		final String valueStr = convertToStr(value);
		return new AtomicBoolean(BooleanUtil.toBoolean(valueStr));
	}

}
