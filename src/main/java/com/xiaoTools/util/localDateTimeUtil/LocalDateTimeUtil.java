package com.xiaoTools.util.localDateTimeUtil;

import com.xiaoTools.date.datePattern.DatePattern;
import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.strUtil.StrUtil;
import com.xiaoTools.util.temporalAccessorUtil.TemporalAccessorUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.TimeZone;

/**
 * [LocalDateTime 工具类封装](Encapsulation of LocalDateTime tool class)
 * @description: zh - LocalDateTime 工具类封装
 * @description: en - Encapsulation of LocalDateTime tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 10:13 上午
*/
public class LocalDateTimeUtil {

    /**
     * [当前时间，默认时区](Current time, default time zone)
     * @description: zh - 当前时间，默认时区
     * @description: en - Current time, default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:17 上午
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime now() { return LocalDateTime.now(); }

    /*转换-----------------------------------------------------------of*/

    /**
     * [Instant 转 LocalDateTime ，使用默认时区](Convert instant to LocalDateTime and use the default time zone)
     * @description: zh - Instant 转 LocalDateTime ，使用默认时区
     * @description: en - Convert instant to LocalDateTime and use the default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:19 上午
     * @param instant: Instant
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(Instant instant) { return of(instant, ZoneId.systemDefault()); }

    /**
     * [毫秒转 LocalDateTime，使用默认时区](MS to LocalDateTime, using the default time zone)
     * @description: zh - 毫秒转 LocalDateTime，使用默认时区
     * @description: en - MS to LocalDateTime, using the default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:30 上午
     * @param epochMilli: 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(long epochMilli) {
        return of(Instant.ofEpochMilli(epochMilli));
    }

    /**
     * [Instant 转 LocalDateTime](Instant to LocalDateTime)
     * @description: zh - Instant 转 LocalDateTime
     * @description: en - Instant to LocalDateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:25 上午
     * @param instant: Instant
     * @param zoneId: 时区
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(Instant instant, ZoneId zoneId) {
        return Constant.NULL == instant ? Constant.LOCAL_DATE_TIME_NULL : LocalDateTime.ofInstant(instant, ObjectUtil.defaultIfNull(zoneId, ZoneId.systemDefault()));
    }

    /**
     * [ZonedDateTime 转 LocalDateTime](ZonedDateTime turn LocalDateTime)
     * @description: zh - ZonedDateTime 转 LocalDateTime
     * @description: en - ZonedDateTime turn LocalDateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:23 上午
     * @param zonedDateTime: ZonedDateTime
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(ZonedDateTime zonedDateTime) {
        return Constant.NULL == zonedDateTime ? Constant.LOCAL_DATE_TIME_NULL : zonedDateTime.toLocalDateTime();
    }

    /**
     * [ Instant 转 LocalDateTime ]( Instant to LocalDateTime )
     * @description: zh - Instant 转 LocalDateTime
     * @description: en - Instant to LocalDateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:28 上午
     * @param instant: Instant
     * @param timeZone: 时区
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(Instant instant, TimeZone timeZone) {
        return Constant.NULL == instant ? Constant.LOCAL_DATE_TIME_NULL : of(instant, ObjectUtil.defaultIfNull(timeZone, TimeZone.getDefault()).toZoneId());
    }

    /**
     * [毫秒转 LocalDateTime，根据时区不同，结果会产生时间偏移](Milliseconds to LocalDateTime, according to the time zone, the result will produce time offset)
     * @description: zh - 毫秒转 LocalDateTime，根据时区不同，结果会产生时间偏移
     * @description: en - Milliseconds to LocalDateTime, according to the time zone, the result will produce time offset
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:38 上午
     * @param epochMilli: 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @param zoneId: 时区
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(long epochMilli, ZoneId zoneId) { return of(Instant.ofEpochMilli(epochMilli), zoneId); }

    /**
     * [毫秒转 LocalDateTime，结果会产生时间偏移](MS to LocalDateTime, resulting in time offset)
     * @description: zh - 毫秒转 LocalDateTime，结果会产生时间偏移
     * @description: en - MS to LocalDateTime, resulting in time offset
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:40 上午
     * @param epochMilli: 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @param timeZone: 时区
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(long epochMilli, TimeZone timeZone) { return of(Instant.ofEpochMilli(epochMilli), timeZone); }

    /**
     * [Date 转 LocalDateTime，使用默认时区](Convert Date to LocalDateTime and use the default time zone)
     * @description: zh - Date 转 LocalDateTime，使用默认时区
     * @description: en - Convert Date to LocalDateTime and use the default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:46 上午
     * @param date: Date对象
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(Date date) {
        return Constant.NULL == date ? Constant.LOCAL_DATE_TIME_NULL : date instanceof DateTime ? of(date.toInstant(), ((DateTime) date).getZoneId()) : of(date.toInstant());
    }

    /**
     * [TemporalAccessor 转 LocalDateTime，使用默认时区](Convert temporary accessor to local datetime and use the default time zone)
     * @description: zh - TemporalAccessor 转 LocalDateTime，使用默认时区
     * @description: en - Convert temporary accessor to local datetime and use the default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 10:50 上午
     * @param temporalAccessor: TemporalAccessor
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime of(TemporalAccessor temporalAccessor) {
        if (Constant.NULL == temporalAccessor) {
            return Constant.LOCAL_DATE_TIME_NULL;
        }
        if(temporalAccessor instanceof LocalDate){
            return ((LocalDate)temporalAccessor).atStartOfDay();
        }
        return LocalDateTime.of(
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.YEAR),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.DAY_OF_MONTH),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.HOUR_OF_DAY),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.MINUTE_OF_HOUR),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.SECOND_OF_MINUTE),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.NANO_OF_SECOND)
        );
    }

    /*转换UTC-----------------------------------------------------------of UTC*/

    /**
     * [Instant 转 LocalDateTime，使用UTC时区](Convert instant to LocalDateTime and use UTC time zone)
     * @description: zh - Instant 转 LocalDateTime，使用UTC时区
     * @description: en - Convert instant to LocalDateTime and use UTC time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 12:11 下午
     * @param instant: Instant 
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime ofUTC(Instant instant) { return of(instant, ZoneId.of(Constant.UTC)); }

    /**
     * [毫秒转 LocalDateTime，使用UTC时区](MS to LocalDateTime, using UTC time zone)
     * @description: zh - 毫秒转 LocalDateTime，使用UTC时区
     * @description: en - MS to LocalDateTime, using UTC time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 12:12 下午
     * @param epochMilli: 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime ofUTC(long epochMilli) {
        return ofUTC(Instant.ofEpochMilli(epochMilli));
    }

    /*转换Date-----------------------------------------------------------of Date*/

    /**
     * [TemporalAccessor 转 LocalDate，使用默认时区](Convert temporary accessor to local date and use the default time zone)
     * @description: zh - TemporalAccessor 转 LocalDate，使用默认时区
     * @description: en - Convert temporary accessor to local date and use the default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 1:00 下午
     * @param temporalAccessor: TemporalAccessor
     * @return java.time.LocalDate
    */
    public static LocalDate ofDate(TemporalAccessor temporalAccessor) {
        if (Constant.NULL == temporalAccessor) { return Constant.LOCAL_DATE_NULL; }
        if(temporalAccessor instanceof LocalDateTime){
            return ((LocalDateTime)temporalAccessor).toLocalDate();
        }
        return LocalDate.of(
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.YEAR),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
                TemporalAccessorUtil.get(temporalAccessor, ChronoField.DAY_OF_MONTH)
        );
    }

    /*解析-----------------------------------------------------------parse*/

    /**
     * [解析日期时间字符串为LocalDateTime，仅支持yyyy-MM-dd'T'HH:mm:ss格式](The parsing date time string is LocalDateTime, which only supports yyyy-mm-dd't'hh: mm: SS format)
     * @description: zh - 解析日期时间字符串为 LocalDateTime，仅支持yyyy-MM-dd'T'HH:mm:ss格式
     * @description: en - The parsing date time string is LocalDateTime, which only supports yyyy-mm-dd't'hh: mm: SS format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 5:26 下午
     * @param text: 日期时间字符串
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime parse(CharSequence text) {
        return parse(text, (DateTimeFormatter)Constant.NULL);
    }

    /**
     * [解析日期时间字符串为 LocalDateTime，格式支持日期时间、日期、时间](The parsing date time string is LocalDateTime, and the format supports date time, date and time)
     * @description: zh - 解析日期时间字符串为 LocalDateTime，格式支持日期时间、日期、时间
     * @description: en - The parsing date time string is LocalDateTime, and the format supports date time, date and time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 5:36 下午
     * @param text: 日期时间字符串
     * @param formatter: 日期格式化器
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter) {
        return Constant.NULL == text ? Constant.LOCAL_DATE_TIME_NULL : Constant.NULL == formatter ? LocalDateTime.parse(text) : of(formatter.parse(text));
    }

    public static LocalDateTime parse(CharSequence text, String format) {
        if (Constant.NULL == text) { return Constant.LOCAL_DATE_TIME_NULL; }
        DateTimeFormatter formatter = Constant.DATE_TIME_FORMATTER_NULL;
        if(StrUtil.isNotBlank(format)){
            if(StrUtil.startWithIgnoreEquals(format, Constant.PURE_DATETIME_PATTERN)){
                final String fraction = StrUtil.removePrefix(format, Constant.PURE_DATETIME_PATTERN);
                if(ReUtil.isMatch(Constant.STRING_S_ONE_TWO, fraction)){
                    text += StrUtil.repeat(Constant.CHAR_ZERO, Constant.THREE-fraction.length());
                }
                formatter = new DateTimeFormatterBuilder()
                        .appendPattern(Constant.PURE_DATETIME_PATTERN)
                        .appendValue(ChronoField.MILLI_OF_SECOND, Constant.THREE)
                        .toFormatter();
            } else{
                formatter = DateTimeFormatter.ofPattern(format);
            }
        }
        return parse(text, formatter);
    }

}
