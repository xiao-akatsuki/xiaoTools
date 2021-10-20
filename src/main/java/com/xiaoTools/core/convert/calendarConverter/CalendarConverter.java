package com.xiaoTools.core.convert.calendarConverter;

import java.util.Calendar;
import java.util.Date;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.util.dateUtil.DateUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [日期转换器](Date Converter)
 * @description zh - 日期转换器
 * @description en - Date Converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 07:38:05
 */
public class CalendarConverter extends AbstractConverter<Calendar> {
	private static final long serialVersionUID = 1L;

	/**
	 * [日期格式化](Date formatting)
	 * @description zh - 日期格式化
	 * @description en - Date formatting
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:20:46
	 */
	private String format;

	/**
	 * [获取日期格式](Get date format)
	 * @description zh - 获取日期格式
	 * @description en - Get date format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:21:15
	 * @return java.lang.String
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * [设置日期格式](Format date)
	 * @description zh - 设置日期格式
	 * @description en - Format date
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:21:58
	 * @param format 日期格式
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	protected Calendar convertInternal(Object value) {
		if (value instanceof Date) {
			return DateUtil.calendar((Date)value);
		}

		if (value instanceof Long) {
			return DateUtil.calendar((Long)value);
		}

		final String valueStr = convertToStr(value);
		return DateUtil.calendar(StrUtil.isBlank(format) ? DateUtil.parse(valueStr) : DateUtil.parse(valueStr, format));
	}
}
