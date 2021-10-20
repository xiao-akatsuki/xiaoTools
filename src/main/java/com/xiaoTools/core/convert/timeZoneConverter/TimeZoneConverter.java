package com.xiaoTools.core.convert.timeZoneConverter;

import java.util.TimeZone;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [TimeZone转换器](Timezone converter)
 * @description zh - TimeZone转换器
 * @description en - Timezone converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:49:24
 */
public class TimeZoneConverter extends AbstractConverter<TimeZone>{
	private static final long serialVersionUID = 1L;

	@Override
	protected TimeZone convertInternal(Object value) {
		return TimeZone.getTimeZone(convertToStr(value));
	}

}
