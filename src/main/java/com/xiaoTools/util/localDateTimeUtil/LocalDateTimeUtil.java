package com.xiaoTools.util.localDateTimeUtil;

import com.xiaoTools.date.datePattern.DatePattern;
import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.regularUtil.RegularUtil;
import com.xiaoTools.util.temporalAccessorUtil.TemporalAccessorUtil;
import com.xiaoTools.util.temporalAccessorUtil.TemporalUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
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

    /**
     * [解析日期时间字符串为 LocalDateTime](Parse the date time string as LocalDateTime)
     * @description: zh - 解析日期时间字符串为 LocalDateTime
     * @description: en - Parse the date time string as LocalDateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:25 上午
     * @param text: 日期时间字符串
     * @param format: 日期格式
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime parse(CharSequence text, String format) {
        if (Constant.NULL == text) { return Constant.LOCAL_DATE_TIME_NULL; }
        DateTimeFormatter formatter = Constant.DATE_TIME_FORMATTER_NULL;
        if(StrUtil.isNotBlank(format)){
            if(StrUtil.startWithIgnoreEquals(format, Constant.PURE_DATETIME_PATTERN)){
                final String fraction = StrUtil.removePrefix(format, Constant.PURE_DATETIME_PATTERN);
                if(RegularUtil.isMatch(Constant.STRING_S_ONE_TWO, fraction)){
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

    /**
     * [解析日期时间字符串为 LocalDate](Parse the date time string as LocalDate)
     * @description: zh - 解析日期时间字符串为 LocalDate
     * @description: en - Parse the date time string as LocalDate
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:33 上午
     * @param text: 日期时间字符串
     * @return java.time.LocalDate
    */
    public static LocalDate parseDate(CharSequence text) {
        return parseDate(text, (DateTimeFormatter)Constant.NULL);
    }

    /**
     * [解析日期时间字符串为 LocalDate，格式支持日期](The parsing date time string is LocalDate, and the format supports date)
     * @description: zh - 解析日期时间字符串为 LocalDate，格式支持日期
     * @description: en - The parsing date time string is LocalDate, and the format supports date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:34 上午
     * @param text: 日期时间字符串
     * @param formatter: 日期格式化器
     * @return java.time.LocalDate
    */
    public static LocalDate parseDate(CharSequence text, DateTimeFormatter formatter) {
        return Constant.NULL == text ? Constant.LOCAL_DATE_NULL : Constant.NULL == formatter ? LocalDate.parse(text) : ofDate(formatter.parse(text));
    }

    /**
     * [解析日期字符串为 LocalDate](Parse the date string as LocalDate)
     * @description: zh - 解析日期字符串为 LocalDate
     * @description: en - Parse the date string as LocalDate
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:42 上午
     * @param text: 日期字符串
     * @param format: 日期格式
     * @return java.time.LocalDate
    */
    public static LocalDate parseDate(CharSequence text, String format) {
        return Constant.NULL == text ? Constant.LOCAL_DATE_NULL : parseDate(text, DateTimeFormatter.ofPattern(format));
    }

    /*格式-----------------------------------------------------------format*/

    /**
     * [格式化日期时间为yyyy-MM-dd HH:mm:ss格式](Format the date and time in yyyy MM DD HH: mm: SS format)
     * @description: zh - 格式化日期时间为yyyy-MM-dd HH:mm:ss格式
     * @description: en - Format the date and time in yyyy MM DD HH: mm: SS format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:54 上午
     * @param time: LocalDateTime
     * @return java.lang.String
    */
    public static String formatNormal(LocalDateTime time) {
        return format(time, DatePattern.NORM_DATETIME_FORMATTER);
    }

    /**
     * [格式化日期时间为指定格式](Format the date and time in the specified format)
     * @description: zh - 格式化日期时间为指定格式
     * @description: en - Format the date and time in the specified format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:54 上午
     * @param time: LocalDateTime
     * @param formatter: 日期格式化器
     * @return java.lang.String
    */
    public static String format(LocalDateTime time, DateTimeFormatter formatter) {
        return TemporalAccessorUtil.format(time, formatter);
    }

    /**
     * [格式化日期时间为指定格式](Format the date and time in the specified format)
     * @description: zh - 格式化日期时间为指定格式
     * @description: en - Format the date and time in the specified format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:56 上午
     * @param time: LocalDateTime
     * @param format: 日期格式
     * @return java.lang.String
    */
    public static String format(LocalDateTime time, String format) {
        return Constant.NULL == time ? Constant.STRING_NULL : format(time, DateTimeFormatter.ofPattern(format));
    }

    /**
     * [格式化日期时间为yyyy-MM-dd格式](Format the date and time in yyyy-MM-dd format)
     * @description: zh - 格式化日期时间为yyyy-MM-dd格式
     * @description: en - Format the date and time in yyyy-MM-dd format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:00 下午
     * @param date: LocalDate
     * @return java.lang.String
    */
    public static String formatNormal(LocalDate date) {
        return format(date, DatePattern.NORM_DATE_FORMATTER);
    }

    /**
     * [格式化日期时间为指定格式](Format the date and time in the specified format)
     * @description: zh - 格式化日期时间为指定格式
     * @description: en - Format the date and time in the specified format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:01 下午
     * @param date: LocalDate
     * @param formatter: 日期格式化器
     * @return java.lang.String
    */
    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return TemporalAccessorUtil.format(date, formatter);
    }

    /**
     * [格式化日期时间为指定格式](Format the date and time in the specified format)
     * @description: zh - 格式化日期时间为指定格式
     * @description: en - Format the date and time in the specified format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:18 下午
     * @param date: LocalDate
     * @param format: 日期格式，类似于yyyy-MM-dd
     * @return java.lang.String
    */
    public static String format(LocalDate date, String format) {
        return Constant.NULL == date ? Constant.STRING_NULL : format(date, DateTimeFormatter.ofPattern(format));
    }

    /*偏移-----------------------------------------------------------offset*/

    /**
     * [日期偏移,根据field不同加不同值（偏移会修改传入的对象）](Date offset, add different values according to different fields (offset will modify the incoming object))
     * @description: zh - 日期偏移,根据field不同加不同值（偏移会修改传入的对象）
     * @description: en - Date offset, add different values according to different fields (offset will modify the incoming object)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:29 下午
     * @param time: LocalDateTime
     * @param number: 偏移量，正数为向后偏移，负数为向前偏移
     * @param field: 偏移单位
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime offset(LocalDateTime time, long number, TemporalUnit field) {
        return Constant.NULL == time ? Constant.LOCAL_DATE_TIME_NULL : time.plus(number, field);
    }

    /*差距-----------------------------------------------------------between*/

    /**
     * [获取两个日期的差，如果结束时间早于开始时间，获取结果为负](Gets the difference between two dates. If the end time is earlier than the start time, the result is negative)
     * @description: zh - 获取两个日期的差，如果结束时间早于开始时间，获取结果为负
     * @description: en - Gets the difference between two dates. If the end time is earlier than the start time, the result is negative
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:32 下午
     * @param startTimeInclude: 开始时间
     * @param endTimeExclude: 结束时间
     * @return java.time.Duration
    */
    public static Duration between(LocalDateTime startTimeInclude, LocalDateTime endTimeExclude) {
        return TemporalUtil.between(startTimeInclude, endTimeExclude);
    }

    /**
     * [获取两个日期的差，如果结束时间早于开始时间，获取结果为负。](Gets the difference between two dates. If the end time is earlier than the start time, the result is negative.)
     * @description: zh - 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * @description: en - Gets the difference between two dates. If the end time is earlier than the start time, the result is negative.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:42 下午
     * @param startTimeInclude: 开始时间
     * @param endTimeExclude: 结束时间
     * @param unit: 时间差单位
     * @return long
    */
    public static long between(LocalDateTime startTimeInclude, LocalDateTime endTimeExclude, ChronoUnit unit) {
        return TemporalUtil.between(startTimeInclude, endTimeExclude, unit);
    }

    /**
     * [获取两个日期的表象时间差，如果结束时间早于开始时间，获取结果为负。](Gets the apparent time difference between two dates. If the end time is earlier than the start time, the result is negative.)
     * @description: zh - 获取两个日期的表象时间差，如果结束时间早于开始时间，获取结果为负。
     * @description: en - Gets the apparent time difference between two dates. If the end time is earlier than the start time, the result is negative.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:50 下午
     * @param startTimeInclude: 开始时间
     * @param endTimeExclude: 结束时间
     * @return java.time.Period
    */
    public static Period betweenPeriod(LocalDate startTimeInclude, LocalDate endTimeExclude) {
        return Period.between(startTimeInclude, endTimeExclude);
    }

    /**
     * [修改为一天的开始时间](Change to the start time of the day)
     * @description: zh - 修改为一天的开始时间
     * @description: en - Change to the start time of the day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:52 下午
     * @param time: 日期时间
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime beginOfDay(LocalDateTime time) {
        return time.with(LocalTime.MIN);
    }

    /**
     * [修改为一天的结束时间](Change to the end time of the day)
     * @description: zh - 修改为一天的结束时间
     * @description: en - Change to the end time of the day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:53 下午
     * @param time: 日期时间
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime endOfDay(LocalDateTime time) {
        return time.with(LocalTime.MAX);
    }

    /**
     * [TemporalAccessor 转换为 时间戳](TemporalAccessor to timestamp)
     * @description: zh - TemporalAccessor 转换为 时间戳
     * @description: en - TemporalAccessor to timestamp
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:54 下午
     * @param temporalAccessor: Date对象
     * @return long
    */
    public static long toEpochMilli(TemporalAccessor temporalAccessor) {
        return TemporalAccessorUtil.toEpochMilli(temporalAccessor);
    }
}
