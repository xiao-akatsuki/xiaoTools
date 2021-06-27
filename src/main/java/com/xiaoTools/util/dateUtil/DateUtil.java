package com.xiaoTools.util.dateUtil;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.exception.dateException.DateException;
import com.xiaoTools.core.regular.patternPool.PatternPool;
import com.xiaoTools.date.betweenFormatter.BetweenFormatter;
import com.xiaoTools.date.dateBetween.DateBetween;
import com.xiaoTools.date.dateField.DateField;
import com.xiaoTools.date.datePattern.DatePattern;
import com.xiaoTools.date.dateRange.DateRange;
import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.date.dateUnit.DateUnit;
import com.xiaoTools.date.format.dateParser.DateParser;
import com.xiaoTools.date.format.datePrinter.DatePrinter;
import com.xiaoTools.date.leven.Level;
import com.xiaoTools.date.month.Month;
import com.xiaoTools.date.quarter.Quarter;
import com.xiaoTools.date.timer.stopWatch.StopWatch;
import com.xiaoTools.date.timer.timeInterval.TimeInterval;
import com.xiaoTools.date.week.Week;
import com.xiaoTools.date.zodiac.Zodiac;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.calendarUtil.CalendarUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.compareUtil.CompareUtil;
import com.xiaoTools.util.localDateTimeUtil.LocalDateTimeUtil;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.regularUtil.RegularUtil;
import com.xiaoTools.util.strUtil.StrUtil;
import com.xiaoTools.util.temporalAccessorUtil.TemporalAccessorUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

/**
 * [时间工具类](Time tools)
 * @description: zh - 时间工具类
 * @description: en - Time tools
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 11:12 上午
*/
public class DateUtil extends CalendarUtil {

    /**
     * java.util.Date EEE MMM zzz 缩写数组
     */
    private final static String[] wtb = {
        // 星期
        "sun", "mon", "tue", "wed", "thu", "fri", "sat",
        // 月份
        "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec",
        // 时间标准
        "gmt", "ut", "utc", "est", "edt", "cst", "cdt", "mst", "mdt", "pst", "pdt"
    };

    /**
     * [当前时间，转换为 DateTime 对象](Current time, converted to a DateTime object)
     * @description: zh - 当前时间，转换为 DateTime 对象
     * @description: en - Current time, converted to a DateTime object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 2:44 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime date() {
        return new DateTime();
    }

    /**
     * [当前时间，转换为 DateTime 对象，忽略毫秒部分](Current time, converted to DateTime object, ignoring the millisecond part)
     * @description: zh - 当前时间，转换为 DateTime 对象，忽略毫秒部分
     * @description: en - Current time, converted to DateTime object, ignoring the millisecond part
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 2:45 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime dateSecond() {
        return beginOfSecond(date());
    }

    /**
     * [Date 类型时间转为 DateTime](Change the Date type time to DateTime)
     * @description: zh - Date 类型时间转为 DateTime
     * @description: en - Change the Date type time to DateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 2:58 下午
     * @param date: Long类型Date（Unix时间戳）
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime date(Date date) {
        return date instanceof DateTime ? (DateTime) date : dateNew(date);
    }

    /**
     * [根据已有 Date 产生新的 DateTime 对象](Generate a new datetime object based on the existing date)
     * @description: zh - 根据已有 Date 产生新的 DateTime 对象
     * @description: en - Generate a new datetime object based on the existing date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:08 下午
     * @param date: DateTime 对象
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime dateNew(Date date) { return new DateTime(date); }

    /**
     * [Long 类型时间转为 DateTime](Long type time to DateTime)
     * @description: zh - Long 类型时间转为 DateTime
     * @description: en - Long type time to DateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:13 下午
     * @param date: long 类型的 date 对象
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime date(long date) { return new DateTime(date); }

    /**
     * [Calendar 类型时间转为 DateTime](Calendar type time to DateTime)
     * @description: zh - Calendar 类型时间转为 DateTime
     * @description: en - Calendar type time to DateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:20 下午
     * @param calendar: Calendar
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime date(Calendar calendar) { return new DateTime(calendar); }

    /**
     * [TemporalAccessor 类型时间转为 DateTime](The TemporalAccessor type time is changed to DateTime)
     * @description: zh - TemporalAccessor 类型时间转为 DateTime
     * @description: en - The TemporalAccessor type time is changed to DateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:26 下午
     * @param temporalAccessor: TemporalAccessor
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime date(TemporalAccessor temporalAccessor) { return new DateTime(temporalAccessor); }

    /**
     * [当前时间的时间戳](The timestamp of the current time)
     * @description: zh - 当前时间的时间戳
     * @description: en - The timestamp of the current time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:28 下午
     * @return long
    */
    public static long current() { return System.currentTimeMillis(); }

    /**
     * [当前时间的时间戳（秒）](Timestamp of the current time in seconds)
     * @description: zh - 当前时间的时间戳（秒）
     * @description: en - Timestamp of the current time in seconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:30 下午
     * @return long
    */
    public static long currentSeconds() { return System.currentTimeMillis() / Constant.ONE_THOUSAND; }

    /**
     * [当前时间，格式 yyyy-MM-dd HH:mm:ss](Current time, format yyyy MM DD HH: mm: SS)
     * @description: zh - 当前时间，格式 yyyy-MM-dd HH:mm:ss
     * @description: en - Current time, format yyyy MM DD HH: mm: SS
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:31 下午
     * @return java.lang.String
    */
    public static String now() { return formatDateTime(new DateTime()); }

    /**
     * [当前日期，格式 yyyy-MM-dd](Current date, format yyyy MM DD)
     * @description: zh - 当前日期，格式 yyyy-MM-dd
     * @description: en - Current date, format yyyy MM DD
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:33 下午
     * @return java.lang.String
    */
    public static String today() {
        return formatDate(new DateTime());
    }

    /*部分日期-----------------------------------------------------------Partial date*/

    /**
     * [获得年](Year of acquisition)
     * @description: zh - 获得年
     * @description: en - Year of acquisition
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:35 下午
     * @param date: 日期
     * @return int
    */
    public static int year(Date date) {
        return DateTime.of(date).year();
    }

    /**
     * [获得指定日期所属季度，从1开始计数](Gets the quarter of the specified date, counting from 1)
     * @description: zh - 获得指定日期所属季度，从1开始计数
     * @description: en - Gets the quarter of the specified date, counting from 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:36 下午
     * @param date: 日期
     * @return int
    */
    public static int quarter(Date date) {
        return DateTime.of(date).quarter();
    }

    /**
     * [获得指定日期所属季度](Gets the quarter of the specified date)
     * @description: zh - 获得指定日期所属季度
     * @description: en - Gets the quarter of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:38 下午
     * @param date: 日期
     * @return com.xiaoTools.date.quarter.Quarter
    */
    public static Quarter quarterEnum(Date date) {
        return DateTime.of(date).quarterEnum();
    }

    /**
     * [获得月份，从0开始计数](Get the month, counting from 0)
     * @description: zh - 获得月份，从0开始计数
     * @description: en - Get the month, counting from 0
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:42 下午
     * @param date: 日期
     * @return int
    */
    public static int month(Date date) {
        return DateTime.of(date).month();
    }

    /**
     * [获得月份](Month of acquisition)
     * @description: zh - 获得月份
     * @description: en - Month of acquisition
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:44 下午
     * @param date: 日期
     * @return com.xiaoTools.date.month.Month
    */
    public static Month monthEnum(Date date) {
        return DateTime.of(date).monthEnum();
    }

    /**
     * [获得指定日期是所在年份的第几周](Gets the week of the year in which the specified date is located)
     * @description: zh - 获得指定日期是所在年份的第几周
     * @description: en - Gets the week of the year in which the specified date is located
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:45 下午
     * @param date: 日期
     * @return int
    */
    public static int weekOfYear(Date date) {
        return DateTime.of(date).weekOfYear();
    }

    /**
     * [获得指定日期是所在月份的第几周](Gets the week of the month in which the specified date is located)
     * @description: zh - 获得指定日期是所在月份的第几周
     * @description: en - Gets the week of the month in which the specified date is located
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:46 下午
     * @param date: 日期
     * @return int
    */
    public static int weekOfMonth(Date date) {
        return DateTime.of(date).weekOfMonth();
    }

    /**
     * [获得指定日期是这个日期所在月份的第几天](Gets the day of the month in which the specified date is located)
     * @description: zh - 获得指定日期是这个日期所在月份的第几天
     * @description: en - Gets the day of the month in which the specified date is located
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:47 下午
     * @param date: 日期
     * @return int
    */
    public static int dayOfMonth(Date date) {
        return DateTime.of(date).dayOfMonth();
    }

    /**
     * [获得指定日期是这个日期所在年的第几天](Gets the day of the year in which the specified date is located)
     * @description: zh - 获得指定日期是这个日期所在年的第几天
     * @description: en - Gets the day of the year in which the specified date is located
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:48 下午
     * @param date: 日期
     * @return int
    */
    public static int dayOfYear(Date date) {
        return DateTime.of(date).dayOfYear();
    }

    /**
     * [获得指定日期是星期几，1表示周日，2表示周一](Get the specified day of the week, 1 for Sunday, 2 for Monday)
     * @description: zh - 获得指定日期是星期几，1表示周日，2表示周一
     * @description: en - Get the specified day of the week, 1 for Sunday, 2 for Monday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:49 下午
     * @param date: 日期
     * @return int
    */
    public static int dayOfWeek(Date date) {
        return DateTime.of(date).dayOfWeek();
    }

    /**
     * [获得指定日期是星期几](What day of the week is the date given)
     * @description: zh - 获得指定日期是星期几
     * @description: en - What day of the week is the date given
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 3:50 下午
     * @param date: 日期
     * @return com.xiaoTools.date.week.Week
    */
    public static Week dayOfWeekEnum(Date date) {
        return DateTime.of(date).dayOfWeekEnum();
    }

    /**
     * [获得指定日期的小时数部分](Gets the hours part of the specified date)
     * @description: zh - 获得指定日期的小时数部分
     * @description: en - Gets the hours part of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:08 下午
     * @param date: 日期
     * @param is24HourClock: 是否24小时制
     * @return int
    */
    public static int hour(Date date, boolean is24HourClock) { return DateTime.of(date).hour(is24HourClock); }

    /**
     * [获得指定日期的分钟数部分](Gets the minutes part of the specified date)
     * @description: zh - 获得指定日期的分钟数部分
     * @description: en - Gets the minutes part of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:10 下午
     * @param date: 日期
     * @return int
    */
    public static int minute(Date date) { return DateTime.of(date).minute(); }

    /**
     * [获得指定日期的秒数部分](Gets the seconds part of the specified date)
     * @description: zh - 获得指定日期的秒数部分
     * @description: en - Gets the seconds part of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:11 下午
     * @param date: 日期
     * @return int
    */
    public static int second(Date date) {
        return DateTime.of(date).second();
    }

    /**
     * [获得指定日期的毫秒数部分](Gets the millisecond part of the specified date)
     * @description: zh - 获得指定日期的毫秒数部分
     * @description: en - Gets the millisecond part of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:12 下午
     * @param date: 日期
     * @return int
    */
    public static int millisecond(Date date) {
        return DateTime.of(date).millisecond();
    }

    /**
     * [是否为上午](Is it morning)
     * @description: zh - 是否为上午
     * @description: en - Is it morning
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:14 下午
     * @param date: 日期
     * @return boolean
    */
    public static boolean isAM(Date date) {
        return DateTime.of(date).isAM();
    }

    /**
     * [是否为下午](Is it afternoon)
     * @description: zh - 是否为下午
     * @description: en - Is it afternoon
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:15 下午
     * @param date: 日期
     * @return boolean
    */
    public static boolean isPM(Date date) {
        return DateTime.of(date).isPM();
    }

    /**
     * [今年](this year)
     * @description: zh - 今年
     * @description: en - this year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:16 下午
     * @return int
    */
    public static int thisYear() {
        return year(date());
    }

    /**
     * [当前月份](Current month)
     * @description: zh - 当前月份
     * @description: en - Current month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:41 下午
     * @return int
    */
    public static int thisMonth() {
        return month(date());
    }

    /**
     * [当前月份 Month](Current month)
     * @description: zh - 当前月份 Month
     * @description: en - Current month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:45 下午
     * @return com.xiaoTools.date.month.Month
    */
    public static Month thisMonthEnum() {
        return monthEnum(date());
    }

    /**
     * [当前日期所在年份的第几周](What week of the year the current date is in)
     * @description: zh - 当前日期所在年份的第几周
     * @description: en - What week of the year the current date is in
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:46 下午
     * @return int
    */
    public static int thisWeekOfYear() {
        return weekOfYear(date());
    }

    /**
     * [当前日期所在月份的第几周](What week of the month the current date is in)
     * @description: zh - 当前日期所在月份的第几周
     * @description: en - What week of the month the current date is in
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:47 下午
     * @return int
    */
    public static int thisWeekOfMonth() {
        return weekOfMonth(date());
    }

    /**
     * [当前日期是这个日期所在月份的第几天](What day of the month is the current date)
     * @description: zh - 当前日期是这个日期所在月份的第几天
     * @description: en - What day of the month is the current date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:47 下午
     * @return int
    */
    public static int thisDayOfMonth() {
        return dayOfMonth(date());
    }

    /**
     * [当前日期是星期几](What day is the current date)
     * @description: zh - 当前日期是星期几
     * @description: en - What day is the current date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:52 下午
     * @return int
    */
    public static int thisDayOfWeek() {
        return dayOfWeek(date());
    }

    /**
     * [当前日期是星期几 Week](What day is the current date week)
     * @description: zh - 当前日期是星期几 Week
     * @description: en - What day is the current date week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:54 下午
     * @return com.xiaoTools.date.week.Week
    */
    public static Week thisDayOfWeekEnum() {
        return dayOfWeekEnum(date());
    }

    /**
     * [当前日期的小时数部分](The hours part of the current date)
     * @description: zh - 当前日期的小时数部分
     * @description: en - The hours part of the current date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:55 下午
     * @param is24HourClock: 是否24小时制
     * @return int
    */
    public static int thisHour(boolean is24HourClock) {
        return hour(date(), is24HourClock);
    }

    /**
     * [当前日期的分钟数部分](The minutes part of the current date)
     * @description: zh - 当前日期的分钟数部分
     * @description: en - The minutes part of the current date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:56 下午
     * @return int
    */
    public static int thisMinute() {
        return minute(date());
    }

    /**
     * [当前日期的秒数部分](The seconds part of the current date)
     * @description: zh - 当前日期的秒数部分
     * @description: en - The seconds part of the current date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:58 下午
     * @return int
    */
    public static int thisSecond() {
        return second(date());
    }

    /**
     * [当前日期的毫秒数部分](The millisecond part of the current date)
     * @description: zh - 当前日期的毫秒数部分
     * @description: en - The millisecond part of the current date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 4:59 下午
     * @return int
    */
    public static int thisMillisecond() {
        return millisecond(date());
    }

    /**
     * [获得指定日期区间内的年份和季节](Gets the year and season in the specified date range)
     * @description: zh - 获得指定日期区间内的年份和季节
     * @description: en - Gets the year and season in the specified date range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 5:01 下午
     * @param startDate: 起始日期（包含）
     * @param endDate: 结束日期（包含）
     * @return java.util.LinkedHashSet<java.lang.String>
    */
    public static LinkedHashSet<String> yearAndQuarter(Date startDate, Date endDate) {
        if (startDate == Constant.NULL || endDate == Constant.NULL) {
            return new LinkedHashSet<>(Constant.ZERO);
        }
        return yearAndQuarter(startDate.getTime(), endDate.getTime());
    }

    /*格式化-----------------------------------------------------------Format*/

    /**
     * [格式化日期时间](Format date time)
     * @description: zh - 格式化日期时间
     * @description: en - Format date time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 12:58 下午
     * @param localDateTime: 被格式化的日期
     * @return java.lang.String
    */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return LocalDateTimeUtil.formatNormal(localDateTime);
    }

    /**
     * [根据特定格式格式化日期](Format the date according to a specific format)
     * @description: zh - 根据特定格式格式化日期
     * @description: en - Format the date according to a specific format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 1:00 下午
     * @param localDateTime: 被格式化的日期
     * @param format: 日期格式
     * @return java.lang.String
    */
    public static String format(LocalDateTime localDateTime, String format) {
        return LocalDateTimeUtil.format(localDateTime, format);
    }

    /**
     * [根据特定格式格式化日期](Format the date according to a specific format)
     * @description: zh - 根据特定格式格式化日期
     * @description: en - Format the date according to a specific format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 1:04 下午
     * @param date: 被格式化的日期
     * @param format: 日期格式
     * @return java.lang.String
    */
    public static String format(Date date, String format) {
        if (Constant.NULL == date || StrUtil.isBlank(format)) {
            return Constant.STRING_NULL;
        }
        TimeZone timeZone = (TimeZone) Constant.NULL;
        if (date instanceof DateTime) {
            timeZone = ((DateTime) date).getTimeZone();
        }
        return format(date, newSimpleFormat(format, Constant.LOCALE_NULL, timeZone));
    }

    /**
     * [根据特定格式格式化日期](Format the date according to a specific format)
     * @description: zh - 根据特定格式格式化日期
     * @description: en - Format the date according to a specific format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 1:12 下午
     * @param date: 被格式化的日期
     * @param format: DatePrinter 或 FastDateFormat
     * @return java.lang.String
    */
    public static String format(Date date, DatePrinter format) {
        return Constant.NULL == format || Constant.NULL == date ? Constant.STRING_NULL : format.format(date);
    }

    /**
     * [根据特定格式格式化日期](Format the date according to a specific format)
     * @description: zh - 根据特定格式格式化日期
     * @description: en - Format the date according to a specific format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 1:15 下午
     * @param date: 被格式化的日期
     * @param format: SimpleDateFormat
     * @return java.lang.String
    */
    public static String format(Date date, DateFormat format) {
        return Constant.NULL == format || Constant.NULL == date ? Constant.STRING_NULL : format.format(date);
    }

    /**
     * [根据特定格式格式化日期](Format the date according to a specific format)
     * @description: zh - 根据特定格式格式化日期
     * @description: en - Format the date according to a specific format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 1:18 下午
     * @param date: 被格式化的日期
     * @param format: SimpleDateFormat
     * @return java.lang.String
    */
    public static String format(Date date, DateTimeFormatter format) {
        return Constant.NULL == format || Constant.NULL == date ? Constant.STRING_NULL : format.format(date.toInstant());
    }

    /**
     * [格式化日期时间](Format date time)
     * @description: zh - 格式化日期时间
     * @description: en - Format date time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 3:01 下午
     * @param date: 被格式化的日期
     * @return java.lang.String
    */
    public static String formatDateTime(Date date) {
        return Constant.NULL == date ? Constant.STRING_NULL : DatePattern.NORM_DATETIME_FORMAT.format(date);
    }

    /**
     * [格式化日期部分](Format date section)
     * @description: zh - 格式化日期部分
     * @description: en - Format date section
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 3:10 下午
     * @param date: 被格式化的日期
     * @return java.lang.String
    */
    public static String formatDate(Date date) {
        return Constant.NULL == date ? Constant.STRING_NULL : DatePattern.NORM_DATE_FORMAT.format(date);
    }

    /**
     * [格式化时间](Format time)
     * @description: zh - 格式化时间
     * @description: en - Format time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 3:13 下午
     * @param date: 被格式化的日期
     * @return java.lang.String
    */
    public static String formatTime(Date date) {
        return Constant.NULL == date ? Constant.STRING_NULL : DatePattern.NORM_TIME_FORMAT.format(date);
    }

    /**
     * [格式化为Http的标准日期格式](Standard date format formatted as http)
     * @description: zh - 格式化为Http的标准日期格式
     * @description: en - Standard date format formatted as http
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 3:17 下午
     * @param date: 被格式化的日期
     * @return java.lang.String
    */
    public static String formatHttpDate(Date date) {
        return Constant.NULL == date ? Constant.STRING_NULL : DatePattern.HTTP_DATETIME_FORMAT.format(date);
    }

    /**
     * [格式化为中文日期格式，如果isUppercase为false](Format to Chinese date format, if isUpperCase is false)
     * @description: zh - 格式化为中文日期格式，如果isUppercase为false
     * @description: en - Format to Chinese date format, if isUpperCase is false
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 3:26 下午
     * @param date: 被格式化的日期
     * @param isUppercase: 是否采用大写形式
     * @param withTime: 是否包含时间部分
     * @return java.lang.String
    */
    public static String formatChineseDate(Date date, boolean isUppercase, boolean withTime) {
        return Constant.NULL == date ? Constant.STRING_NULL : !isUppercase ? (withTime ? DatePattern.CHINESE_DATE_TIME_FORMAT : DatePattern.CHINESE_DATE_FORMAT).format(date) : CalendarUtil.formatChineseDate(CalendarUtil.calendar(date), withTime);
    }

    /*构造-----------------------------------------------------------Parse*/

    /**
     * [构建 LocalDateTime 对象](Building the LocalDateTime object)
     * @description: zh - 构建 LocalDateTime 对象
     * @description: en - Building the LocalDateTime object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 3:28 下午
     * @param dateStr: 时间字符串
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime parseLocalDateTime(CharSequence dateStr) {
        return parseLocalDateTime(dateStr, DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * [构建 LocalDateTime 对象](Building the LocalDateTime object)
     * @description: zh - 构建 LocalDateTime 对象
     * @description: en - Building the LocalDateTime object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 7:33 下午
     * @param dateStr: 时间字符串（带格式）
     * @param format: 使用DatePattern定义的格式
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime parseLocalDateTime(CharSequence dateStr, String format) {
        return LocalDateTimeUtil.parse(dateStr, format);
    }

    /**
     * [构建DateTime对象](Building a datetime object)
     * @description: zh - 构建DateTime对象
     * @description: en - Building a datetime object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:17 下午
     * @param dateStr: Date字符串
     * @param dateFormat: 格式化器
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parse(CharSequence dateStr, DateFormat dateFormat) {
        return new DateTime(dateStr, dateFormat);
    }

    /**
     * [Building a datetime object](Building a datetime object)
     * @description: zh - 构建DateTime对象
     * @description: en - Building a datetime object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:18 下午
     * @param dateStr: Date字符串
     * @param parser: 格式化器
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parse(CharSequence dateStr, DateParser parser) {
        return new DateTime(dateStr, parser);
    }

    /**
     * [构建DateTime对象](Building a datetime object)
     * @description: zh - 构建DateTime对象
     * @description: en - Building a datetime object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:20 下午
     * @param dateStr: Date字符串
     * @param formatter: 格式化器
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parse(CharSequence dateStr, DateTimeFormatter formatter) {
        return new DateTime(dateStr, formatter);
    }

    /**
     * [将特定格式的日期转换为Date对象](Converts a date in a specific format to a date object)
     * @description: zh - 将特定格式的日期转换为Date对象
     * @description: en - Converts a date in a specific format to a date object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:22 下午
     * @param dateStr: 特定格式的日期
     * @param format: 格式
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parse(CharSequence dateStr, String format) {
        return new DateTime(dateStr, format);
    }

    /**
     * [将特定格式的日期转换为Date对象](Converts a date in a specific format to a date object)
     * @description: zh - 将特定格式的日期转换为Date对象
     * @description: en - Converts a date in a specific format to a date object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:22 下午
     * @param dateStr: 特定格式的日期
     * @param format: 格式
     * @param locale: 区域信息
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parse(CharSequence dateStr, String format, Locale locale) {
        return new DateTime(dateStr, DateUtil.newSimpleFormat(format, locale, Constant.TIME_ZONE_NULL));
    }

    /**
     * [通过给定的日期格式解析日期时间字符串。](Parses the date time string through the given date format.)
     * @description: zh - 通过给定的日期格式解析日期时间字符串。
     * @description: en - Parses the date time string through the given date format.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:25 下午
     * @param str: 日期时间字符串，非空
     * @param parsePatterns: 需要尝试的日期时间格式数组
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parse(String str, String... parsePatterns) throws DateException {
        return new DateTime(CalendarUtil.parseByPatterns(str, parsePatterns));
    }

    /**
     * [解析日期时间字符串](Parsing date time string)
     * @description: zh - 解析日期时间字符串
     * @description: en - Parsing date time string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:26 下午
     * @param dateString: 标准形式的时间字符串
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parseDateTime(CharSequence dateString) {
        dateString = normalize(dateString);
        return parse(dateString, DatePattern.NORM_DATETIME_FORMAT);
    }

    /**
     * [解析日期字符串，忽略时分秒](Parsing date string, ignoring time, minute and second)
     * @description: zh - 解析日期字符串，忽略时分秒
     * @description: en - Parsing date string, ignoring time, minute and second
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:30 下午
     * @param dateString: 标准形式的日期字符串
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parseDate(CharSequence dateString) {
        dateString = normalize(dateString);
        return parse(dateString, DatePattern.NORM_DATE_FORMAT);
    }

    /**
     * [解析时间](Analysis time)
     * @description: zh - 解析时间
     * @description: en - Analysis time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:31 下午
     * @param timeString: 标准形式的日期字符串
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parseTime(CharSequence timeString) {
        timeString = normalize(timeString);
        return parse(timeString, DatePattern.NORM_TIME_FORMAT);
    }

    /**
     * [解析时间](Analysis time)
     * @description: zh - 解析时间
     * @description: en - Analysis time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 8:31 下午
     * @param timeString: 标准形式的日期字符串
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parseTimeToday(CharSequence timeString) {
        timeString = StrUtil.format(Constant.STRING_DOUBLE_BRACKETS, today(), timeString);
        return Constant.ONE == StrUtil.count(timeString, Constant.CHAR_COLON) ? parse(timeString, DatePattern.NORM_DATETIME_MINUTE_PATTERN) : parse(timeString, DatePattern.NORM_DATETIME_FORMAT);
    }

    /**
     * [解析UTC时间](Resolve UTC time)
     * @description: zh - 解析UTC时间
     * @description: en - Resolve UTC time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:48 上午
     * @param utcString: UTC时间
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parseUTC(String utcString) {
        if (utcString == Constant.NULL) { return Constant.DATE_TIME_NULL; }
        int length = utcString.length();
        if (StrUtil.contains(utcString, Constant.CHAR_UP_Z)) {
            if (length == DatePattern.UTC_PATTERN.length() - Constant.FOUR) {
                // 格式类似：2018-09-13T05:34:31Z，-4表示减去4个单引号的长度
                return parse(utcString, DatePattern.UTC_FORMAT);
            }
            final int patternLength = DatePattern.UTC_MS_PATTERN.length();
            // 格式类似：2018-09-13T05:34:31.999Z，-4表示减去4个单引号的长度
            // -4 ~ -6范围表示匹配毫秒1~3位的情况
            if (length <= patternLength - Constant.FOUR && length >= patternLength - Constant.SIX) {
                return parse(utcString, DatePattern.UTC_MS_FORMAT);
            }
        } else {
            if (length == DatePattern.UTC_WITH_ZONE_OFFSET_PATTERN.length() + Constant.TWO || length == DatePattern.UTC_WITH_ZONE_OFFSET_PATTERN.length() + Constant.THREE) {
                // 格式类似：2018-09-13T05:34:31+0800 或 2018-09-13T05:34:31+08:00
                return parse(utcString, DatePattern.UTC_WITH_ZONE_OFFSET_FORMAT);
            } else if (length == DatePattern.UTC_MS_WITH_ZONE_OFFSET_PATTERN.length() + Constant.TWO || length == DatePattern.UTC_MS_WITH_ZONE_OFFSET_PATTERN.length() + Constant.THREE) {
                // 格式类似：2018-09-13T05:34:31.999+0800 或 2018-09-13T05:34:31.999+08:00
                return parse(utcString, DatePattern.UTC_MS_WITH_ZONE_OFFSET_FORMAT);
            } else if (length == DatePattern.UTC_SIMPLE_PATTERN.length() - Constant.TWO) {
                // 格式类似：2018-09-13T05:34:31
                return parse(utcString, DatePattern.UTC_SIMPLE_FORMAT);
            } else if (StrUtil.contains(utcString, Constant.CHAR_SPOT)){
                // 可能为：  2021-03-17T06:31:33.99
                return parse(utcString, DatePattern.UTC_SIMPLE_MS_FORMAT);
            }
        }
        // 没有更多匹配的时间格式
        throw new DateException("No format fit for date String [{}] !", utcString);
    }

    /**
     * [解析CST时间](Analysis of CST time)
     * @description: zh - 解析CST时间
     * @description: en - Analysis of CST time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:51 上午
     * @param cstString: UTC时间
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parseCST(CharSequence cstString) {
        return cstString == Constant.NULL ? Constant.DATE_TIME_NULL : parse(cstString, DatePattern.JDK_DATETIME_FORMAT);
    }

    /**
     * [将日期字符串转换为 DateTime 对象](Converts a date string to a DateTime object)
     * @description: zh - 将日期字符串转换为 DateTime 对象
     * @description: en - Converts a date string to a DateTime object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 8:37 上午
     * @param dateCharSequence: 日期字符串
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime parse(CharSequence dateCharSequence) {
        if (StrUtil.isBlank(dateCharSequence)) { return Constant.DATE_TIME_NULL; }
        String dateStr = dateCharSequence.toString();
        // 去掉两边空格并去掉中文日期中的“日”和“秒”，以规范长度
        dateStr = StrUtil.removeAll(dateStr.trim(), Constant.CHAR_DAY, Constant.CHAR_SECOND);
        int length = dateStr.length();
        if (NumUtil.isNumber(dateStr)) {
            // 纯数字形式
            if (length == Constant.PURE_DATETIME_PATTERN.length()) {
                return parse(dateStr, DatePattern.PURE_DATETIME_FORMAT);
            } else if (length == DatePattern.PURE_DATETIME_MS_PATTERN.length()) {
                return parse(dateStr, DatePattern.PURE_DATETIME_MS_FORMAT);
            } else if (length == DatePattern.PURE_DATE_PATTERN.length()) {
                return parse(dateStr, DatePattern.PURE_DATE_FORMAT);
            } else if (length == DatePattern.PURE_TIME_PATTERN.length()) {
                return parse(dateStr, DatePattern.PURE_TIME_FORMAT);
            }
        } else if (RegularUtil.isMatch(PatternPool.TIME, dateStr)) {
            // HH:mm:ss 或者 HH:mm 时间格式匹配单独解析
            return parseTimeToday(dateStr);
        } else if (StrUtil.containsAnyIgnoreCase(dateStr, wtb)) {
            // JDK的Date对象toString默认格式，类似于：Tue Jun 4 16:25:15 +0800 2021
            return parseCST(dateStr);
        } else if (StrUtil.contains(dateStr, Constant.CHAR_UP_T)) {
            // UTC时间
            return parseUTC(dateStr);
        }
        //标准日期格式（包括单个数字的日期时间）
        dateStr = normalize(dateStr);
        final Matcher matcher = DatePattern.REGEX_NORM.matcher(dateStr);
        if (RegularUtil.isMatch(DatePattern.REGEX_NORM, dateStr)) {
            final int colonCount = StrUtil.count(dateStr, Constant.CHAR_COLON);
            switch (colonCount) {
                case Constant.ZERO:
                    // yyyy-MM-dd
                    return parse(dateStr, DatePattern.NORM_DATE_FORMAT);
                case Constant.ONE:
                    // yyyy-MM-dd HH:mm
                    return parse(dateStr, DatePattern.NORM_DATETIME_MINUTE_FORMAT);
                case Constant.TWO:
                    if (StrUtil.contains(dateStr, Constant.CHAR_SPOT)) {
                        // yyyy-MM-dd HH:mm:ss.SSS
                        return parse(dateStr, DatePattern.NORM_DATETIME_MS_FORMAT);
                    }
                    // yyyy-MM-dd HH:mm:ss
                    return parse(dateStr, DatePattern.NORM_DATETIME_FORMAT);
            }
        }
        // 没有更多匹配的时间格式
        throw new DateException("No format fit for date String [{}] !", dateStr);
    }

    /*修改-----------------------------------------------------------Offset*/

    /**
     * [修改日期为某个时间字段起始时间](The modification date is the start time of a time field)
     * @description: zh - 修改日期为某个时间字段起始时间
     * @description: en - The modification date is the start time of a time field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:24 下午
     * @param date: Date
     * @param dateField: 时间字段
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime truncate(Date date, DateField dateField) {
        return new DateTime(truncate(calendar(date), dateField));
    }

    /**
     * [修改日期为某个时间字段四舍五入时间](Modify the date to a time field and round the time)
     * @description: zh - 修改日期为某个时间字段四舍五入时间
     * @description: en - Modify the date to a time field and round the time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:26 下午
     * @param date: Date
     * @param dateField: 时间字段
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime round(Date date, DateField dateField) {
        return new DateTime(round(calendar(date), dateField));
    }

    /**
     * [修改日期为某个时间字段结束时间](The modification date is the end time of a time field)
     * @description: zh - 修改日期为某个时间字段结束时间
     * @description: en - The modification date is the end time of a time field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:27 下午
     * @param date: Date
     * @param dateField: 时间字段
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime ceiling(Date date, DateField dateField) {
        return new DateTime(ceiling(calendar(date), dateField));
    }

    /**
     * [获取秒级别的开始时间，即忽略毫秒部分](Gets the start time of the second level, that is, the millisecond part is ignored)
     * @description: zh - 获取秒级别的开始时间，即忽略毫秒部分
     * @description: en - Gets the start time of the second level, that is, the millisecond part is ignored
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:28 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfSecond(Date date) {
        return new DateTime(beginOfSecond(calendar(date)));
    }

    /**
     * [获取秒级别的结束时间，即毫秒设置为999](Gets the end time of the second level, that is, the millisecond is set to 999)
     * @description: zh - 获取秒级别的结束时间，即毫秒设置为999
     * @description: en - Gets the end time of the second level, that is, the millisecond is set to 999
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:28 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfSecond(Date date) {
        return new DateTime(endOfSecond(calendar(date)));
    }

    /**
     * [获取某小时的开始时间](Gets the start time of an hour)
     * @description: zh - 获取某小时的开始时间
     * @description: en - Gets the start time of an hour
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:29 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfHour(Date date) {
        return new DateTime(beginOfHour(calendar(date)));
    }

    /**
     * [获取某小时的结束时间](Gets the end time of an hour)
     * @description: zh - 获取某小时的结束时间
     * @description: en - Gets the end time of an hour
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:31 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfHour(Date date) {
        return new DateTime(endOfHour(calendar(date)));
    }

    /**
     * [获取某分钟的开始时间](Gets the start time of a minute)
     * @description: zh - 获取某分钟的开始时间
     * @description: en - Gets the start time of a minute 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:32 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfMinute(Date date) {
        return new DateTime(beginOfMinute(calendar(date)));
    }

    /**
     * [获取某分钟的结束时间](Gets the end time of a minute)
     * @description: zh - 获取某分钟的结束时间
     * @description: en - Gets the end time of a minute
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:33 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfMinute(Date date) {
        return new DateTime(endOfMinute(calendar(date)));
    }

    /**
     * [](Get the start time of a day)
     * @description: zh - 获取某天的开始时间
     * @description: en - Get the start time of a day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:34 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfDay(Date date) {
        return new DateTime(beginOfDay(calendar(date)));
    }

    /**
     * [获取某天的结束时间](Gets the end time of a day)
     * @description: zh - 获取某天的结束时间
     * @description: en - Gets the end time of a day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:36 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfDay(Date date) {
        return new DateTime(endOfDay(calendar(date)));
    }

    /**
     * [获取某周的开始时间，周一定为一周的开始时间](Get the start time of a week. The week must be the start time of a week)
     * @description: zh - 获取某周的开始时间，周一定为一周的开始时间
     * @description: en - Get the start time of a week. The week must be the start time of a week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:38 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfWeek(Date date) {
        return new DateTime(beginOfWeek(calendar(date)));
    }

    /**
     * [获取某周的开始时间](Gets the start time of a week)
     * @description: zh - 获取某周的开始时间
     * @description: en - Gets the start time of a week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:41 下午
     * @param date: 日期
     * @param isMondayAsFirstDay: 是否周一做为一周的第一天（false表示周日做为第一天）
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfWeek(Date date, boolean isMondayAsFirstDay) {
        return new DateTime(beginOfWeek(calendar(date), isMondayAsFirstDay));
    }

    /**
     * [获取某周的结束时间，周日定为一周的结束](Get the end time of a week. Sunday is the end time of a week)
     * @description: zh - 获取某周的结束时间，周日定为一周的结束
     * @description: en - Get the end time of a week. Sunday is the end time of a week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:42 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfWeek(Date date) {
        return new DateTime(endOfWeek(calendar(date)));
    }

    /**
     * [获取某周的结束时间](Gets the end time of a week)
     * @description: zh - 获取某周的结束时间
     * @description: en - Gets the end time of a week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:43 下午
     * @param date: 日期
     * @param isSundayAsLastDay: 是否周日做为一周的最后一天（false表示周六做为最后一天）
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfWeek(Date date, boolean isSundayAsLastDay) {
        return new DateTime(endOfWeek(calendar(date), isSundayAsLastDay));
    }

    /**
     * [获取某月的开始时间](Gets the start time of a month)
     * @description: zh - 获取某月的开始时间
     * @description: en - Gets the start time of a month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:45 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfMonth(Date date) {
        return new DateTime(beginOfMonth(calendar(date)));
    }

    /**
     * [获取某月的结束时间](Gets the end time of a month)
     * @description: zh - 获取某月的结束时间
     * @description: en - Gets the end time of a month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:50 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfMonth(Date date) {
        return new DateTime(endOfMonth(calendar(date)));
    }

    /**
     * [获取某季度的开始时间](Gets the start time of a quarter)
     * @description: zh - 获取某季度的开始时间
     * @description: en - Gets the start time of a quarter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:51 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfQuarter(Date date) {
        return new DateTime(beginOfQuarter(calendar(date)));
    }

    /**
     * [获取某季度的结束时间](Gets the end time of a quarter)
     * @description: zh - 获取某季度的结束时间
     * @description: en - Gets the end time of a quarter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:55 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfQuarter(Date date) {
        return new DateTime(endOfQuarter(calendar(date)));
    }

    /**
     * [获取某年的开始时间](Gets the start time of a year)
     * @description: zh - 获取某年的开始时间
     * @description: en - Gets the start time of a year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:57 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime beginOfYear(Date date) {
        return new DateTime(beginOfYear(calendar(date)));
    }

    /**
     * [获取某年的结束时间](Gets the end time of a year)
     * @description: zh - 获取某年的结束时间
     * @description: en - Gets the end time of a year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 1:58 下午
     * @param date: 日期
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime endOfYear(Date date) {
        return new DateTime(endOfYear(calendar(date)));
    }

    /*偏移-----------------------------------------------------------deviation*/

    /**
     * [获取昨天](Get yesterday)
     * @description: zh - 获取昨天
     * @description: en - Get yesterday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:03 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime yesterday() {
        return offsetDay(new DateTime(), Constant.NEGATIVE_ONE);
    }

    /**
     * [获取明天](Get tomorrow)
     * @description: zh - 获取明天
     * @description: en - Get tomorrow
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:05 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime tomorrow() {
        return offsetDay(new DateTime(), Constant.ONE);
    }

    /**
     * [上周](last week)
     * @description: zh - 上周
     * @description: en - last week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:06 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime lastWeek() {
        return offsetWeek(new DateTime(), Constant.NEGATIVE_ONE);
    }

    /**
     * [下周](next week)
     * @description: zh - 下周
     * @description: en - next week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:09 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime nextWeek() {
        return offsetWeek(new DateTime(), Constant.ONE);
    }

    /**
     * [上个月](last month)
     * @description: zh - 上个月
     * @description: en - last month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:10 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime lastMonth() {
        return offsetMonth(new DateTime(), Constant.NEGATIVE_ONE);
    }

    /**
     * [下个月](next month)
     * @description: zh - 下个月
     * @description: en - next month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:13 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime nextMonth() {
        return offsetMonth(new DateTime(), Constant.ONE);
    }

    /**
     * [偏移毫秒数](Offset milliseconds)
     * @description: zh - 偏移毫秒数
     * @description: en - Offset milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:13 下午
     * @param date: 日期
     * @param offset: 偏移毫秒数
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime offsetMillisecond(Date date, int offset) {
        return offset(date, DateField.MILLISECOND, offset);
    }

    /**
     * [偏移秒数](Offset seconds)
     * @description: zh - 偏移秒数
     * @description: en - Offset seconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:15 下午
     * @param date: 日期
     * @param offset: 偏移秒数，正数向未来偏移，负数向历史偏移
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime offsetSecond(Date date, int offset) {
        return offset(date, DateField.SECOND, offset);
    }

    /**
     * [偏移分钟](Offset minutes)
     * @description: zh - 偏移分钟
     * @description: en - Offset minutes
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:18 下午
     * @param date: 日期
     * @param offset: 偏移分钟
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime offsetMinute(Date date, int offset) {
        return offset(date, DateField.MINUTE, offset);
    }

    /**
     * [偏移分钟](Offset minutes)
     * @description: zh - 偏移分钟
     * @description: en - Offset minutes
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:21 下午
     * @param date: 日期
     * @param offset: 偏移分钟数
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime offsetHour(Date date, int offset) {
        return offset(date, DateField.HOUR_OF_DAY, offset);
    }

    /**
     * [偏移天](Offset day)
     * @description: zh - 偏移天
     * @description: en - Offset day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:22 下午
     * @param date: 日期
     * @param offset: 偏移天数
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime offsetDay(Date date, int offset) {
        return offset(date, DateField.DAY_OF_YEAR, offset);
    }

    /**
     * [偏移周](Offset cycle)
     * @description: zh - 偏移周
     * @description: en - Offset cycle
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:24 下午
     * @param date: 日期
     * @param offset: 偏移周数，正数向未来偏移，负数向历史偏移
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime offsetWeek(Date date, int offset) {
        return offset(date, DateField.WEEK_OF_YEAR, offset);
    }

    /**
     * [偏移月](Offset month)
     * @description: zh - 偏移月
     * @description: en - Offset month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:26 下午
     * @param date: 日期
     * @param offset: 偏移月数，正数向未来偏移，负数向历史偏移
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime offsetMonth(Date date, int offset) {
        return offset(date, DateField.MONTH, offset);
    }

    /**
     *
     * @description:
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:27 下午
     * @param date: 基准日期
     * @param dateField: 偏移的粒度大小（小时、天、月等）DateField
     * @param offset: 偏移量，正数为向后偏移，负数为向前偏移
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime offset(Date date, DateField dateField, int offset) {
        return dateNew(date).offset(dateField, offset);
    }

    /*比较-----------------------------------------------------------between*/

    /**
     * [判断两个日期相差的时长，只保留绝对值](Judge the duration of the difference between two dates, and only keep the absolute value)
     * @description: zh - 判断两个日期相差的时长，只保留绝对值
     * @description: en - Judge the duration of the difference between two dates, and only keep the absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:31 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @param unit: 相差的单位
     * @return long
    */
    public static long between(Date beginDate, Date endDate, DateUnit unit) {
        return between(beginDate, endDate, unit, Constant.TRUE);
    }

    /**
     * [判断两个日期相差的时长](Judge the time difference between two dates)
     * @description: zh - 判断两个日期相差的时长
     * @description: en - Judge the time difference between two dates
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:33 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @param unit: 相差的单位
     * @param isAbs: 日期间隔是否只保留绝对值正数
     * @return long
    */
    public static long between(Date beginDate, Date endDate, DateUnit unit, boolean isAbs) {
        return new DateBetween(beginDate, endDate, isAbs).between(unit);
    }

    /**
     * [判断两个日期相差的毫秒数](The number of milliseconds to determine the difference between two dates)
     * @description: zh - 判断两个日期相差的毫秒数
     * @description: en - The number of milliseconds to determine the difference between two dates
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:36 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @return long
    */
    public static long betweenMs(Date beginDate, Date endDate) {
        return new DateBetween(beginDate, endDate).between(DateUnit.MS);
    }

    /**
     * [判断两个日期相差的天数](Determine the number of days between two dates)
     * @description: zh - 判断两个日期相差的天数
     * @description: en - Determine the number of days between two dates
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 2:40 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @param isReset: 是否重置时间为起始时间
     * @return long
    */
    public static long betweenDay(Date beginDate, Date endDate, boolean isReset) {
        if (isReset) {
            beginDate = beginOfDay(beginDate);
            endDate = beginOfDay(endDate);
        }
        return between(beginDate, endDate, DateUnit.DAY);
    }

    /**
     * [计算指定指定时间区间内的周数](Calculates the number of weeks in a specified time interval)
     * @description: zh - 计算指定指定时间区间内的周数
     * @description: en - Calculates the number of weeks in a specified time interval
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:03 下午
     * @param beginDate: 开始时间
     * @param endDate: 结束时间
     * @param isReset: 是否重置时间为起始时间
     * @return long
    */
    public static long betweenWeek(Date beginDate, Date endDate, boolean isReset) {
        if (isReset) {
            beginDate = beginOfDay(beginDate);
            endDate = beginOfDay(endDate);
        }
        return between(beginDate, endDate, DateUnit.WEEK);
    }

    /**
     * [计算两个日期相差月数](Calculate the number of months between two dates)
     * @description: zh - 计算两个日期相差月数
     * @description: en - Calculate the number of months between two dates
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:03 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @param isReset: 是否重置时间为起始时间
     * @return long
    */
    public static long betweenMonth(Date beginDate, Date endDate, boolean isReset) {
        return new DateBetween(beginDate, endDate).betweenMonth(isReset);
    }

    /**
     * [计算两个日期相差年数](Calculate the number of years between two dates)
     * @description: zh - 计算两个日期相差年数
     * @description: en - Calculate the number of years between two dates
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:04 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @param isReset: 是否重置时间为起始时间
     * @return long
    */
    public static long betweenYear(Date beginDate, Date endDate, boolean isReset) {
        return new DateBetween(beginDate, endDate).betweenYear(isReset);
    }

    /**
     * [格式化日期间隔输出](Format date interval output)
     * @description: zh - 格式化日期间隔输出
     * @description: en - Format date interval output
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:06 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @param level: 级别
     * @return java.lang.String
    */
    public static String formatBetween(Date beginDate, Date endDate, Level level) {
        return formatBetween(between(beginDate, endDate, DateUnit.MS), level);
    }

    /**
     * [格式化日期间隔输出，精确到毫秒](Format date interval output, accurate to milliseconds)
     * @description: zh - 格式化日期间隔输出，精确到毫秒
     * @description: en - Format date interval output, accurate to milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:07 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @return java.lang.String
    */
    public static String formatBetween(Date beginDate, Date endDate) {
        return formatBetween(between(beginDate, endDate, DateUnit.MS));
    }

    /**
     * [格式化日期间隔输出](Format date interval output)
     * @description: zh - 格式化日期间隔输出
     * @description: en - Format date interval output
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:08 下午
     * @param betweenMs: 日期间隔
     * @param level: 级别，按照天、小时、分、秒、毫秒分为5个等级
     * @return java.lang.String
    */
    public static String formatBetween(long betweenMs, Level level) {
        return new BetweenFormatter(betweenMs, level).format();
    }

    /**
     * [格式化日期间隔输出，精确到毫秒](Format date interval output, accurate to milliseconds)
     * @description: zh - 格式化日期间隔输出，精确到毫秒
     * @description: en - Format date interval output, accurate to milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:10 下午
     * @param betweenMs: 日期间隔
     * @return java.lang.String
    */
    public static String formatBetween(long betweenMs) {
        return new BetweenFormatter(betweenMs, Level.MILLISECOND).format();
    }

    /**
     * [当前日期是否在日期指定范围内,起始日期和结束日期可以互换](Whether the current date is in the range specified by the date. The start date and end date can be interchanged)
     * @description: zh - 当前日期是否在日期指定范围内,起始日期和结束日期可以互换
     * @description: en - Whether the current date is in the range specified by the date. The start date and end date can be interchanged 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:12 下午
     * @param date: 被检查的日期
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @return boolean
    */
    public static boolean isIn(Date date, Date beginDate, Date endDate) {
        if (date instanceof DateTime) {
            return ((DateTime) date).isIn(beginDate, endDate);
        } else {
            return new DateTime(date).isIn(beginDate, endDate);
        }
    }

    /**
     * [是否为相同时间](Is it the same time)
     * @description: zh - 是否为相同时间
     * @description: en - Is it the same time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:14 下午
     * @param date1: 日期
     * @param date2: 日期
     * @return boolean
    */
    public static boolean isSameTime(Date date1, Date date2) {
        return date1.compareTo(date2) == Constant.ZERO;
    }

    /**
     * [比较两个日期是否为同一天](Compare two dates for the same day)
     * @description: zh - 比较两个日期是否为同一天
     * @description: en - Compare two dates for the same day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:18 下午
     * @param date1: 日期
     * @param date2: 日期
     * @return boolean
    */
    public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 == Constant.NULL || date2 == Constant.NULL) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return CalendarUtil.isSameDay(calendar(date1), calendar(date2));
    }

    /**
     * [比较两个日期是否为同一月](Compare two dates for the same month)
     * @description: zh - 比较两个日期是否为同一月
     * @description: en - Compare two dates for the same month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:23 下午
     * @param date1: 日期
     * @param date2: 日期
     * @return boolean
    */
    public static boolean isSameMonth(final Date date1, final Date date2) {
        if (date1 == Constant.NULL || date2 == Constant.NULL) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return CalendarUtil.isSameMonth(calendar(date1), calendar(date2));
    }

    /**
     * [计时，常用于记录某段代码的执行时间](Timing, often used to record the execution time of a piece of code)
     * @description: zh - 计时，常用于记录某段代码的执行时间
     * @description: en - Timing, often used to record the execution time of a piece of code
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:26 下午
     * @param preTime: 之前记录的时间
     * @return long
    */
    public static long spendNt(long preTime) {
        return System.nanoTime() - preTime;
    }

    /**
     * [计时，常用于记录某段代码的执行时间，单位：毫秒](Timing, often used to record the execution time of a piece of code, unit: milliseconds)
     * @description: zh - 计时，常用于记录某段代码的执行时间，单位：毫秒
     * @description: en - Timing, often used to record the execution time of a piece of code, unit: milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:28 下午
     * @param preTime: 之前记录的时间
     * @return long
    */
    public static long spendMs(long preTime) {
        return System.currentTimeMillis() - preTime;
    }

    /**
     * [格式化成 yyMMddHHmm 后转换为int型](Format yyMMddHHmm and convert it to int)
     * @description: zh - 格式化成 yyMMddHHmm 后转换为int型
     * @description: en - Format yyMMddHHmm and convert it to int
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:32 下午
     * @param date: 日期
     * @return int
    */
    public static int toIntSecond(Date date) {
        return Integer.parseInt(DateUtil.format(date, Constant.PURE_DATE_TIME_PATTERN));
    }

    /**
     * [计算某个过程花费的时间，精确到毫秒](Calculate the time taken by a process to the nearest millisecond)
     * @description: zh - 计算某个过程花费的时间，精确到毫秒
     * @description: en - Calculate the time taken by a process to the nearest millisecond
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 5:03 下午
     * @return com.xiaoTools.date.timer.timeInterval.TimeInterval
    */
    public static TimeInterval timer() {
        return new TimeInterval();
    }

    /**
     * [计时器](timer)
     * @description: zh - 计时器
     * @description: en - timer
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 5:04 下午
     * @param isNano: 是否使用纳秒计数，false则使用毫秒
     * @return com.xiaoTools.date.timer.timeInterval.TimeInterval
    */
    public static TimeInterval timer(boolean isNano) {
        return new TimeInterval(isNano);
    }

    /*StopWatch-----------------------------------------------------------StopWatch*/

    /**
     * [创建秒表StopWatch，用于对代码块的执行时间计数](Create stopwatch to count the execution time of the code block)
     * @description: zh - 创建秒表StopWatch，用于对代码块的执行时间计数
     * @description: en - Create stopwatch to count the execution time of the code block
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:58 下午
     * @return com.xiaoTools.date.timer.stopWatch.StopWatch
    */
    public static StopWatch createStopWatch() {
        return new StopWatch();
    }

    /**
     * [创建秒表StopWatch，用于对代码块的执行时间计数](Create stopwatch to count the execution time of the code block)
     * @description: zh - 创建秒表StopWatch，用于对代码块的执行时间计数
     * @description: en - Create stopwatch to count the execution time of the code block
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 7:58 下午
     * @param id: 用于标识秒表的唯一ID
     * @return com.xiaoTools.date.timer.stopWatch.StopWatch
    */
    public static StopWatch createStopWatch(String id) {
        return new StopWatch(id);
    }

    /**
     * [生日转为年龄，计算法定年龄](The birthday is converted to the age, and the legal age is calculated)
     * @description: zh - 生日转为年龄，计算法定年龄
     * @description: en - The birthday is converted to the age, and the legal age is calculated
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 8:23 下午
     * @param birthDay: 生日，标准日期字符串
     * @return int
    */
    public static int ageOfNow(String birthDay) {
        return ageOfNow(parse(birthDay));
    }

    /**
     * [生日转为年龄，计算法定年龄](The birthday is converted to the age, and the legal age is calculated)
     * @description: zh - 生日转为年龄，计算法定年龄
     * @description: en - The birthday is converted to the age, and the legal age is calculated
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 11:16 上午
     * @param birthDay: 生日
     * @return int
    */
    public static int ageOfNow(Date birthDay) {
        return age(birthDay, date());
    }

    /**
     * [是否闰年](Is it a leap year)
     * @description: zh - 是否闰年
     * @description: en - Is it a leap year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 11:17 上午
     * @param year: 年
     * @return boolean
    */
    public static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

    /**
     * [计算相对于 dateToCompare 的年龄，长用于计算指定生日在某年的年龄](Calculate the age relative to dateToCompare. Length is used to calculate the age of a specified birthday in a year)
     * @description: zh - 计算相对于 dateToCompare 的年龄，长用于计算指定生日在某年的年龄
     * @description: en - Calculate the age relative to dateToCompare. Length is used to calculate the age of a specified birthday in a year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 11:17 上午
     * @param birthday: 生日
     * @param dateToCompare: 需要对比的日期
     * @return int
    */
    public static int age(Date birthday, Date dateToCompare) {
        Assertion.notNull(birthday, "Birthday can not be null !");
        dateToCompare = Constant.NULL == dateToCompare ? date() : dateToCompare;
        return age(birthday.getTime(), dateToCompare.getTime());
    }

    /**
     * [HH:mm:ss 时间格式字符串转为秒数](HH:mm:SS time format string to seconds)
     * @description: zh - HH:mm:ss 时间格式字符串转为秒数
     * @description: en - HH:mm:SS time format string to seconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 12:10 下午
     * @param timeStr: 字符串时分秒(HH:mm:ss)格式
     * @return int
    */
    public static int timeToSecond(String timeStr) {
        if (StrUtil.isEmpty(timeStr)) { return Constant.ZERO; }
        final List<String> hms = StrUtil.splitTrim(timeStr, Constant.CHAR_COLON, Constant.THREE);
        int lastIndex = hms.size() - Constant.ONE;
        int result = Constant.ZERO;
        for (int i = lastIndex; i >= Constant.ZERO; i--) {
            result += Integer.parseInt(hms.get(i)) * Math.pow(Constant.SIXTY, (lastIndex - i));
        }
        return result;
    }

    /**
     * [秒数转为时间格式(HH:mm:ss)Constant.TENSeconds to time format (HH:mm:SS))
     * @description: zh - 秒数转为时间格式(HH:mm:ss)
     * @description: en - Seconds to time format (HH:mm:SS)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 12:19 下午
     * @param seconds: 需要转换的秒数
     * @return java.lang.String
    */
    public static String secondToTime(int seconds) {
        if (seconds < Constant.ZERO) {
            throw new IllegalArgumentException("Seconds must be a positive number!");
        }
        int hour = seconds / Constant.THREE_SIX_DOUBLE_ZERO;
        int other = seconds % Constant.THREE_SIX_DOUBLE_ZERO;
        int minute = other / Constant.SIXTY;
        int second = other % Constant.SIXTY;
        final StringBuilder sb = new StringBuilder();
        if (hour < Constant.TEN) {
            sb.append(Constant.STRING_ZERO);
        }
        sb.append(hour);
        sb.append(Constant.STRING_COLON);
        if (minute < Constant.TEN) {
            sb.append(Constant.STRING_ZERO);
        }
        sb.append(minute);
        sb.append(Constant.STRING_COLON);
        if (second < Constant.TEN) {
            sb.append(Constant.STRING_ZERO);
        }
        sb.append(second);
        return sb.toString();
    }

    /**
     * [创建日期范围生成器](Create date range generator)
     * @description: zh - 创建日期范围生成器 
     * @description: en - Create date range generator 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:10 下午
     * @param start: 起始日期时间
     * @param end: 结束日期时间 
     * @param unit: 步进单位 
     * @return com.xiaoTools.date.dateRange.DateRange
    */
    public static DateRange range(Date start, Date end, final DateField unit) {
        return new DateRange(start, end, unit);
    }

    /**
     * [创建日期范围生成器](Create date range generator)
     * @description: zh - 创建日期范围生成器 
     * @description: en - Create date range generator 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:15 下午
     * @param start: 起始日期时间
     * @param end: 结束日期时间 
     * @param unit: 步进单位 
     * @return java.util.List<com.xiaoTools.date.dateTime.DateTime>
    */
    public static List<DateTime> rangeToList(Date start, Date end, final DateField unit) {
        return CollUtil.newArrayList((Iterable<DateTime>) range(start, end, unit));
    }

    /**
     * [通过生日计算星座](Calculate constellation by birthday)
     * @description: zh - 通过生日计算星座 
     * @description: en - Calculate constellation by birthday 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:19 下午
     * @param month: 月，从0开始计数
     * @param day: 天 
     * @return java.lang.String
    */
    public static String getZodiac(int month, int day) {
        return Zodiac.getZodiac(month, day);
    }

    /**
     * [计算生肖，只计算1900年后出生的人](Calculate the zodiac, only people born after 1900)
     * @description: zh - 计算生肖，只计算1900年后出生的人
     * @description: en - Calculate the zodiac, only people born after 1900
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:23 下午
     * @param year: 农历年
     * @return java.lang.String
    */
    public static String getChineseZodiac(int year) {
        return Zodiac.getChineseZodiac(year);
    }

    /**
     * [null安全的日期比较，null对象排在末尾](Null is a safe date comparison, with null objects at the end)
     * @description: zh - null安全的日期比较，null对象排在末尾 
     * @description: en - Null is a safe date comparison, with null objects at the end 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:26 下午
     * @param date1: 日期1
     * @param date2: 日期2 
     * @return int
    */
    public static int compare(Date date1, Date date2) {
        return CompareUtil.compare(date1, date2);
    }

    /**
     * [纳秒转毫秒](Nanosecond to millisecond)
     * @description: zh - 纳秒转毫秒
     * @description: en - Nanosecond to millisecond
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:27 下午
     * @param duration: 时长 
     * @return long
    */
    public static long nanosToMillis(long duration) {
        return TimeUnit.NANOSECONDS.toMillis(duration);
    }

    /**
     * [纳秒转秒，保留小数](Nanosecond to second, decimal)
     * @description: zh - 纳秒转秒，保留小数
     * @description: en - Nanosecond to second, decimal
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:31 下午
     * @param duration: 时长 
     * @return double
    */
    public static double nanosToSeconds(long duration) {
        return duration / 1_000_000_000.0;
    }

    /**
     * [Date对象转换为Instant对象](Convert Date object to instant object)
     * @description: zh - Date对象转换为Instant对象
     * @description: en - Convert Date object to instant object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:31 下午
     * @param date: Date对象
     * @return java.time.Instant
    */
    public static Instant toInstant(Date date) {
        return Constant.NULL == date ? Constant.INSTANT_NULL : date.toInstant();
    }

    /**
     * [Date对象转换为Instant对象](Convert Date object to instant object)
     * @description: zh - Date对象转换为Instant对象
     * @description: en - Convert Date object to instant object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:33 下午
     * @param temporalAccessor: Date对象 
     * @return java.time.Instant
    */
    public static Instant toInstant(TemporalAccessor temporalAccessor) {
        return TemporalAccessorUtil.toInstant(temporalAccessor);
    }

    /**
     * [Instant 转换为 LocalDateTime，使用系统默认时区](Convert instant to LocalDateTime and use the system default time zone)
     * @description: zh - Instant 转换为 LocalDateTime，使用系统默认时区
     * @description: en - Convert instant to LocalDateTime and use the system default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:34 下午
     * @param instant: Instant
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTimeUtil.of(instant);
    }

    /**
     * [Date 转换为 LocalDateTime，使用系统默认时区](Date is converted to LocalDateTime and the system default time zone is used)
     * @description: zh - Date 转换为 LocalDateTime，使用系统默认时区 
     * @description: en - Date is converted to LocalDateTime and the system default time zone is used 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:34 下午
     * @param date: Date
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTimeUtil.of(date);
    }

    /**
     * [获得指定年份的总天数](Gets the total number of days in the specified year)
     * @description: zh - 获得指定年份的总天数
     * @description: en - Gets the total number of days in the specified year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:36 下午
     * @param year: 年份
     * @return int
    */
    public static int lengthOfYear(int year) {
        return Year.of(year).length();
    }

    /**
     * [获得指定月份的总天数](Gets the total number of days in the specified month)
     * @description: zh - 获得指定月份的总天数 
     * @description: en - Gets the total number of days in the specified month 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:36 下午
     * @param month: 年份
     * @param isLeapYear: 是否闰年 
     * @return int
    */
    public static int lengthOfMonth(int month, boolean isLeapYear) {
        return java.time.Month.of(month).length(isLeapYear);
    }

    /**
     * [创建 SimpleDateFormat，注意此对象非线程安全！](Create SimpleDateFormat, note that this object is not thread safe!)
     * @description: zh - 创建 SimpleDateFormat，注意此对象非线程安全！
     * @description: en - Create SimpleDateFormat, note that this object is not thread safe!
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:38 下午
     * @param pattern: 表达式
     * @return java.text.SimpleDateFormat
    */
    public static SimpleDateFormat newSimpleFormat(String pattern) {
        return newSimpleFormat(pattern, null, null);
    }

    /**
     * [创建 SimpleDateFormat，注意此对象非线程安全！](Create SimpleDateFormat, note that this object is not thread safe!)
     * @description: zh - 创建 SimpleDateFormat，注意此对象非线程安全！
     * @description: en - Create SimpleDateFormat, note that this object is not thread safe!
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 3:45 下午
     * @param pattern: 表达式
     * @param locale: Locale，null表示默认
     * @param timeZone: TimeZone，null表示默认
     * @return java.text.SimpleDateFormat
    */
    public static SimpleDateFormat newSimpleFormat(String pattern, Locale locale, TimeZone timeZone) {
        if (Constant.NULL == locale) {
            locale = Locale.getDefault(Locale.Category.FORMAT);
        }
        final SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        if (Constant.NULL != timeZone) {
            format.setTimeZone(timeZone);
        }
        format.setLenient(Constant.FALSE);
        return format;
    }

    /*私有-----------------------------------------------------------private*/

    private static String normalize(CharSequence dateStr) {
        if (StrUtil.isBlank(dateStr)) { return StrUtil.str(dateStr); }

        // 日期时间分开处理
        final List<String> dateAndTime = StrUtil.splitTrim(dateStr, ' ');
        final int size = dateAndTime.size();
        if (size < Constant.ONE || size > Constant.TWO) {
            // 非可被标准处理的格式
            return StrUtil.str(dateStr);
        }
        final StringBuilder builder = StrUtil.builder();
        // 日期部分（"\"、"/"、"."、"年"、"月"都替换为"-"）
        String datePart = dateAndTime.get(Constant.ZERO).replaceAll("[/.年月]", "-");
        datePart = StrUtil.removeSuffix(datePart, "日");
        builder.append(datePart);
        // 时间部分
        if (size == Constant.TWO) {
            builder.append(' ');
            String timePart = dateAndTime.get(Constant.ONE).replaceAll("[时分秒]", ":");
            timePart = StrUtil.removeSuffix(timePart, ":");
            //将ISO8601中的逗号替换为.
            timePart = timePart.replace(',', '.');
            builder.append(timePart);
        }

        return builder.toString();
    }
}
