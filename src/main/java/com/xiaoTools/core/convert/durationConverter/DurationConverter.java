package com.xiaoTools.core.convert.durationConverter;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [Duration对象转换器](Duration Object Converter)
 * @description zh - Duration对象转换器
 * @description en - Duration Object Converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:29:10
 */
public class DurationConverter extends AbstractConverter<Duration> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Duration convertInternal(Object value) {
		return value instanceof TemporalAmount ? Duration.from((TemporalAmount) value) :
			value instanceof Long ? Duration.ofMillis((Long) value) :
			Duration.parse(convertToStr(value));

	}


}
