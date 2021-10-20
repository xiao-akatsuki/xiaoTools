package com.xiaoTools.core.convert.periodConverter;

import java.time.Period;
import java.time.temporal.TemporalAmount;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [Period对象转换器](Period Object Converter)
 * @description zh - Period对象转换器
 * @description en - Period Object Converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:28:00
 */
public class PeriodConverter extends AbstractConverter<Period> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Period convertInternal(Object value) {
		return value instanceof TemporalAmount ? Period.from((TemporalAmount) value) :
			value instanceof Integer ? Period.ofDays((Integer) value) :
			Period.parse(convertToStr(value));
	}

}
