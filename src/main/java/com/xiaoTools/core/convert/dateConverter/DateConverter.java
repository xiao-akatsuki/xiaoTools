package com.xiaoTools.core.convert.dateConverter;

import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.util.dateUtil.DateUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [日期转换器](Date Converter)
 * @description zh - 日期转换器
 * @description en - Date Converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 12:22:45
 */
public class DateConverter extends AbstractConverter<java.util.Date>  {
	private static final long serialVersionUID = 1L;

	private final Class<? extends java.util.Date> targetType;

	/**
	 * [日期格式化](Date formatting)
	 * @description zh - 日期格式化
	 * @description en - Date formatting
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:23:22
	 */
	private String format;

	/**
	 * [构造函数](Constructor)
	 * @description zh - 构造函数
	 * @description en - Constructor
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:24:01
	 * @param targetType 目标类型
	 */
	public DateConverter(Class<? extends java.util.Date> targetType) {
		this.targetType = targetType;
	}

	/**
	 * [日期格式化](Date formatting)
	 * @description zh - 日期格式化
	 * @description en - Date formatting
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:24:36
	 * @param targetType 目标类型
	 * @param format 日期格式
	 */
	public DateConverter(Class<? extends java.util.Date> targetType, String format) {
		this.targetType = targetType;
		this.format = format;
	}

	/**
	 * [获取日期格式](Get date format)
	 * @description zh - 获取日期格式
	 * @description en - Get date format
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:25:11
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
	 * @since 2021-10-20 12:25:42
	 * @param format 日期格式
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	protected java.util.Date convertInternal(Object value) {
		if (value instanceof TemporalAccessor) {
			return wrap(DateUtil.date((TemporalAccessor) value));
		} else if (value instanceof Calendar) {
			return wrap(DateUtil.date((Calendar) value));
		} else if (value instanceof Number) {
			return wrap(((Number) value).longValue());
		} else {
			final String valueStr = convertToStr(value);
			final Date date = StrUtil.isBlank(this.format) //
					? DateUtil.parse(valueStr) //
					: DateUtil.parse(valueStr, this.format);
			if(null != date){
				return wrap(date);
			}
		}

		throw new UnsupportedOperationException(StrUtil.format("Unsupport Date type: {}", this.targetType.getName()));
	}

	/**
	 * [转换类型](Conversion type)
	 * @description zh - 转换类型
	 * @description en - Conversion type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:26:40
	 * @param date Date
	 * @return java.util.Date
	 */
	private java.util.Date wrap(java.util.Date date){
		if (java.util.Date.class == targetType) {
			return date;
		}
		if (DateTime.class == targetType) {
			return DateUtil.date(date);
		}
		if (java.sql.Date.class == targetType) {
			return new java.sql.Date(date.getTime());
		}
		if (java.sql.Time.class == targetType) {
			return new java.sql.Time(date.getTime());
		}
		if (java.sql.Timestamp.class == targetType) {
			return new java.sql.Timestamp(date.getTime());
		}

		throw new UnsupportedOperationException(StrUtil.format("Unsupport Date type: {}", this.targetType.getName()));
	}

	/**
	 * [转换类型](Conversion type)
	 * @description zh - 转换类型
	 * @description en - Conversion type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:27:26
	 * @param mills Date
	 * @return java.util.Date
	 */
	private java.util.Date wrap(long mills){
		if (java.util.Date.class == targetType) {
			return new Date(mills);
		}
		if (DateTime.class == targetType) {
			return DateUtil.date(mills);
		}
		if (java.sql.Date.class == targetType) {
			return new java.sql.Date(mills);
		}
		if (java.sql.Time.class == targetType) {
			return new java.sql.Time(mills);
		}
		if (java.sql.Timestamp.class == targetType) {
			return new java.sql.Timestamp(mills);
		}

		throw new UnsupportedOperationException(StrUtil.format("Unsupport Date type: {}", this.targetType.getName()));
	}

}
