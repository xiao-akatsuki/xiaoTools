package com.xiaoTools.util.dateUtil;

import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.date.month.Month;
import com.xiaoTools.date.quarter.Quarter;
import com.xiaoTools.date.week.Week;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.calendarUtil.CalendarUtil;

import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;

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
}
