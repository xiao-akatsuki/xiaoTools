package com.xiaoTools.core.convert.temporalAccessorConverter;

import java.time.temporal.TemporalAccessor;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.util.dateUtil.DateUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [time包对象解析转换器](Time package object parsing converter)
 * @description zh - time包对象解析转换器
 * @description en - Time package object parsing converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 12:56:20
 */
public class TemporalAccessorConverter extends AbstractConverter<TemporalAccessor> {
	private static final long serialVersionUID = 1L;

	private final Class<?> targetType;

	/**
	 * 日期格式化
	 */
	private String format;

	/**
	 * [构造函数](Constructor)
	 * @description zh - 构造函数
	 * @description en - Constructor
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:56:54
	 * @param targetType 目标类型
	 */
	public TemporalAccessorConverter(Class<?> targetType) {
		this(targetType, null);
	}

	/**
	 * [构造函数](Constructor)
	 * @description zh - 构造函数
	 * @description en - Constructor
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:57:40
	 * @param targetType 目标类型
	 * @param format 日期格式
	 */
	public TemporalAccessorConverter(Class<?> targetType, String format) {
		this.targetType = targetType;
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	protected TemporalAccessor convertInternal(Object value) {
		if (value instanceof Long) {
			return parseFromLong((Long) value);
		} else if (value instanceof TemporalAccessor) {
			return parseFromTemporalAccessor((TemporalAccessor) value);
		} else if (value instanceof Date) {
			final DateTime dateTime = DateUtil.date((Date) value);
			return parseFromInstant(dateTime.toInstant(), dateTime.getZoneId());
		}else if (value instanceof Calendar) {
			final Calendar calendar = (Calendar) value;
			return parseFromInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
		} else {
			return parseFromCharSequence(convertToStr(value));
		}
	}

	/**
	 * [通过反射从字符串转time中的对象](Convert from string to object in time by reflection)
	 * @description zh - 通过反射从字符串转time中的对象
	 * @description en - Convert from string to object in time by reflection
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:58:43
	 * @param value 字符串值
	 * @return java.time.temporal.TemporalAccessor
	 */
	private TemporalAccessor parseFromCharSequence(CharSequence value) {
		if(StrUtil.isBlank(value)){
			return null;
		}

		final Instant instant;
		ZoneId zoneId;
		if (null != this.format) {
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.format);
			instant = formatter.parse(value, Instant::from);
			zoneId = formatter.getZone();
		} else {
			final DateTime dateTime = DateUtil.parse(value);
			instant = Objects.requireNonNull(dateTime).toInstant();
			zoneId = dateTime.getZoneId();
		}
		return parseFromInstant(instant, zoneId);
	}

	/**
	 * [将Long型时间戳转换为time中的对象](Converts a long timestamp to an object in time)
	 * @description zh - 将Long型时间戳转换为time中的对象
	 * @description en - Converts a long timestamp to an object in time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 13:04:18
	 * @param time 时间戳
	 * @return java.time.temporal.TemporalAccessor
	 */
	private TemporalAccessor parseFromLong(Long time) {
		return parseFromInstant(Instant.ofEpochMilli(time), null);
	}

	/**
	 * [将TemporalAccessor型时间戳转换为time中的对象](Converts a temporalaccessor timestamp to an object in time)
	 * @description zh - 将TemporalAccessor型时间戳转换为time中的对象
	 * @description en - Converts a temporalaccessor timestamp to an object in time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 13:02:39
	 * @param temporalAccessor TemporalAccessor对象
	 * @return java.time.temporal.TemporalAccessor
	 */
	private TemporalAccessor parseFromTemporalAccessor(TemporalAccessor temporalAccessor) {
		TemporalAccessor result = null;
		if(temporalAccessor instanceof LocalDateTime){
			result = parseFromLocalDateTime((LocalDateTime) temporalAccessor);
		} else if(temporalAccessor instanceof ZonedDateTime){
			result = parseFromZonedDateTime((ZonedDateTime) temporalAccessor);
		}

		if(null == result){
			result = parseFromInstant(DateUtil.toInstant(temporalAccessor), null);
		}

		return result;
	}

	/**
	 * [将TemporalAccessor型时间戳转换为time中的对象](Converts a temporalaccessor timestamp to an object in time)
	 * @description zh - 将TemporalAccessor型时间戳转换为time中的对象
	 * @description en - Converts a temporalaccessor timestamp to an object in time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 13:05:42
	 * @param localDateTime LocalDateTime对象
	 * @return java.time.temporal.TemporalAccessor
	 */
	private TemporalAccessor parseFromLocalDateTime(LocalDateTime localDateTime) {
		if(Instant.class.equals(this.targetType)){
			return DateUtil.toInstant(localDateTime);
		}
		if(LocalDate.class.equals(this.targetType)){
			return localDateTime.toLocalDate();
		}
		if(LocalTime.class.equals(this.targetType)){
			return localDateTime.toLocalTime();
		}
		if(ZonedDateTime.class.equals(this.targetType)){
			return localDateTime.atZone(ZoneId.systemDefault());
		}
		if(OffsetDateTime.class.equals(this.targetType)){
			return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
		}
		if(OffsetTime.class.equals(this.targetType)){
			return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime().toOffsetTime();
		}

		return null;
	}

	/**
	 * [将TemporalAccessor型时间戳转换为time中的对象](Converts a temporalaccessor timestamp to an object in time)
	 * @description zh - 将TemporalAccessor型时间戳转换为time中的对象
	 * @description en - Converts a temporalaccessor timestamp to an object in time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 13:01:40
	 * @param zonedDateTime ZonedDateTime对象
	 * @return java.time.temporal.TemporalAccessor
	 */
	private TemporalAccessor parseFromZonedDateTime(ZonedDateTime zonedDateTime) {
		if(Instant.class.equals(this.targetType)){
			return DateUtil.toInstant(zonedDateTime);
		}
		if(LocalDateTime.class.equals(this.targetType)){
			return zonedDateTime.toLocalDateTime();
		}
		if(LocalDate.class.equals(this.targetType)){
			return zonedDateTime.toLocalDate();
		}
		if(LocalTime.class.equals(this.targetType)){
			return zonedDateTime.toLocalTime();
		}
		if(OffsetDateTime.class.equals(this.targetType)){
			return zonedDateTime.toOffsetDateTime();
		}
		if(OffsetTime.class.equals(this.targetType)){
			return zonedDateTime.toOffsetDateTime().toOffsetTime();
		}

		return null;
	}

	/**
	 * [将TemporalAccessor型时间戳转换为time中的对象](Converts a temporalaccessor timestamp to an object in time)
	 * @description zh - 将TemporalAccessor型时间戳转换为time中的对象
	 * @description en - Converts a temporalaccessor timestamp to an object in time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-20 12:59:37
	 * @param instant Instant对象
	 * @param zoneId 时区ID
	 * @return java.time.temporal.TemporalAccessor
	 */
	private TemporalAccessor parseFromInstant(Instant instant, ZoneId zoneId) {
		if(Instant.class.equals(this.targetType)){
			return instant;
		}

		zoneId = ObjectUtil.defaultIfNull(zoneId, ZoneId.systemDefault());

		TemporalAccessor result = null;
		if (LocalDateTime.class.equals(this.targetType)) {
			result = LocalDateTime.ofInstant(instant, zoneId);
		} else if (LocalDate.class.equals(this.targetType)) {
			result = instant.atZone(zoneId).toLocalDate();
		} else if (LocalTime.class.equals(this.targetType)) {
			result = instant.atZone(zoneId).toLocalTime();
		} else if (ZonedDateTime.class.equals(this.targetType)) {
			result = instant.atZone(zoneId);
		} else if (OffsetDateTime.class.equals(this.targetType)) {
			result = OffsetDateTime.ofInstant(instant, zoneId);
		} else if (OffsetTime.class.equals(this.targetType)) {
			result = OffsetTime.ofInstant(instant, zoneId);
		}
		return result;
	}
}
