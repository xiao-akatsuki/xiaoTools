package com.xiaoTools.util.calendarUtil;

import com.xiaoTools.date.dateField.DateField;
import com.xiaoTools.date.dateModifier.DateModifier;
import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.date.month.Month;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.dateUtil.DateUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * [针对Calendar 对象封装工具类](Encapsulating tool classes for calendar objects)
 * @description: zh - 针对Calendar 对象封装工具类
 * @description: en - Encapsulating tool classes for calendar objects
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 11:22 上午
*/
public class CalendarUtil {

    /**
     * [创建Calendar对象，时间为默认时区的当前时间](Create a calendar object with the current time in the default time zone)
     * @description: zh - 创建Calendar对象，时间为默认时区的当前时间
     * @description: en - Create a calendar object with the current time in the default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 11:23 上午
     * @return java.util.Calendar
    */
    public static Calendar calendar() { return Calendar.getInstance(); }

    /**
     * [转换为Calendar对象](Convert to calendar object)
     * @description: zh - 转换为Calendar对象
     * @description: en - Convert to calendar object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:59 下午
     * @param date: 日期对象
     * @return java.util.Calendar
    */
    public static Calendar calendar(Date date) {
        if (date instanceof DateTime) {
            return ((DateTime) date).toCalendar();
        } else {
            return calendar(date.getTime());
        }
    }

    /**
     * [转换为Calendar对象](Convert to calendar object)
     * @description: zh - 转换为Calendar对象
     * @description: en - Convert to calendar object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 7:00 下午
     * @param millis: 时间戳
     * @return java.util.Calendar
    */
    public static Calendar calendar(long millis) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal;
    }

    /**
     * [是否为上午](Is it morning)
     * @description: zh - 是否为上午
     * @description: en - Is it morning
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 5:09 下午
     * @param calendar:  Calendar
     * @return boolean
    */
    public static boolean isAM(Calendar calendar) {
        return Calendar.AM == calendar.get(Calendar.AM_PM);
    }

    /**
     * [是否为下午](Is it afternoon)
     * @description: zh - 是否为下午
     * @description: en - Is it afternoon
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 5:10 下午
     * @param calendar: Calendar
     * @return boolean
    */
    public static boolean isPM(Calendar calendar) {
        return Calendar.PM == calendar.get(Calendar.AM_PM);
    }

    /**
     * [修改日期为某个时间字段起始时间](The modification date is the start time of a time field)
     * @description: zh - 修改日期为某个时间字段起始时间
     * @description: en - The modification date is the start time of a time field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 5:54 下午
     * @param calendar: Calendar
     * @param dateField: 时间字段
     * @return java.util.Calendar
    */
    public static Calendar truncate(Calendar calendar, DateField dateField) {
        return DateModifier.modify(calendar, dateField.getValue(), DateModifier.ModifyType.TRUNCATE);
    }

    /**
     * [修改日期为某个时间字段结束时间](The modification date is the end time of a time field)
     * @description: zh - 修改日期为某个时间字段结束时间
     * @description: en - The modification date is the end time of a time field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 5:57 下午
     * @param calendar: Calendar
     * @param dateField: 时间字段
     * @return java.util.Calendar
    */
    public static Calendar round(Calendar calendar, DateField dateField) {
        return DateModifier.modify(calendar, dateField.getValue(), DateModifier.ModifyType.ROUND);
    }

    /**
     * [获取秒级别的开始时间，即忽略毫秒部分](Gets the start time of the second level, that is, the millisecond part is ignored)
     * @description: zh - 获取秒级别的开始时间，即忽略毫秒部分
     * @description: en - Gets the start time of the second level, that is, the millisecond part is ignored
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:14 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar beginOfSecond(Calendar calendar) {
        return truncate(calendar, DateField.SECOND);
    }

    /**
     * [获取秒级别的结束时间，即毫秒设置为999](Gets the end time of the second level, that is, the millisecond is set to 999)
     * @description: zh - 获取秒级别的结束时间，即毫秒设置为999
     * @description: en - Gets the end time of the second level, that is, the millisecond is set to 999
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:16 下午
     * @param calendar: 日期 Calendar 
     * @return java.util.Calendar
    */
    public static Calendar endOfSecond(Calendar calendar) {
        return ceiling(calendar, DateField.SECOND);
    }

    /**
     * [获取某小时的开始时间](Gets the start time of an hour)
     * @description: zh - 获取某小时的开始时间
     * @description: en - Gets the start time of an hour
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:19 下午
     * @param calendar:  日期 Calendar 
     * @return java.util.Calendar
    */
    public static Calendar beginOfHour(Calendar calendar) {
        return truncate(calendar, DateField.HOUR_OF_DAY);
    }

    /**
     * [获取某小时的结束时间](Gets the end time of an hour)
     * @description: zh - 获取某小时的结束时间
     * @description: en - Gets the end time of an hour
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:20 下午
     * @param calendar: 日期 Calendar 
     * @return java.util.Calendar
    */
    public static Calendar endOfHour(Calendar calendar) {
        return ceiling(calendar, DateField.HOUR_OF_DAY);
    }

    /**
     * [获取某分钟的开始时间](Gets the start time of a minute)
     * @description: zh - 获取某分钟的开始时间 
     * @description: en - Gets the start time of a minute 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:24 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar beginOfMinute(Calendar calendar) {
        return truncate(calendar, DateField.MINUTE);
    }

    /**
     * [获取某分钟的结束时间](Gets the end time of a minute)
     * @description: zh - 获取某分钟的结束时间
     * @description: en - Gets the end time of a minute
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:26 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar endOfMinute(Calendar calendar) {
        return ceiling(calendar, DateField.MINUTE);
    }

    /**
     * [获取某天的开始时间](Get the start time of a day)
     * @description: zh - 获取某天的开始时间
     * @description: en - Get the start time of a day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:26 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar beginOfDay(Calendar calendar) {
        return truncate(calendar, DateField.DAY_OF_MONTH);
    }

    /**
     * [获取某天的结束时间](Gets the end time of a day)
     * @description: zh - 获取某天的结束时间
     * @description: en - Gets the end time of a day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:27 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar endOfDay(Calendar calendar) {
        return ceiling(calendar, DateField.DAY_OF_MONTH);
    }

    /**
     * [获取给定日期当前周的开始时间，周一定为一周的开始时间](Gets the start time of the current week for a given date. The week must be the start time of a week)
     * @description: zh - 获取给定日期当前周的开始时间，周一定为一周的开始时间
     * @description: en - Gets the start time of the current week for a given date. The week must be the start time of a week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:32 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar beginOfWeek(Calendar calendar) {
        return beginOfWeek(calendar, Constant.TRUE);
    }

    /**
     * [获取给定日期当前周的开始时间](Gets the start time of the current week for a given date)
     * @description: zh - 获取给定日期当前周的开始时间
     * @description: en - Gets the start time of the current week for a given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:33 下午
     * @param calendar: 日期 Calendar
     * @param isMondayAsFirstDay: 是否周一做为一周的第一天（false表示周日做为第一天）
     * @return java.util.Calendar
    */
    public static Calendar beginOfWeek(Calendar calendar, boolean isMondayAsFirstDay) {
        calendar.setFirstDayOfWeek(isMondayAsFirstDay ? Calendar.MONDAY : Calendar.SUNDAY);
        // WEEK_OF_MONTH为上限的字段（不包括），实际调整的为DAY_OF_MONTH
        return truncate(calendar, DateField.WEEK_OF_MONTH);
    }

    /**
     * [获取某周的结束时间，周日定为一周的结束](Get the end time of a week. Sunday is the end time of a week)
     * @description: zh - 获取某周的结束时间，周日定为一周的结束
     * @description: en - Get the end time of a week. Sunday is the end time of a week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:36 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar endOfWeek(Calendar calendar) {
        return endOfWeek(calendar, Constant.TRUE);
    }

    /**
     * [获取某周的结束时间](Gets the end time of a week)
     * @description: zh - 获取某周的结束时间
     * @description: en - Gets the end time of a week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:38 下午
     * @param calendar: 日期 Calendar
     * @param isSundayAsLastDay: 是否周日做为一周的最后一天（false表示周六做为最后一天）
     * @return java.util.Calendar
    */
    public static Calendar endOfWeek(Calendar calendar, boolean isSundayAsLastDay) {
        calendar.setFirstDayOfWeek(isSundayAsLastDay ? Calendar.MONDAY : Calendar.SUNDAY);
        // WEEK_OF_MONTH为上限的字段（不包括），实际调整的为DAY_OF_MONTH
        return ceiling(calendar, DateField.WEEK_OF_MONTH);
    }

    /**
     * [获取某月的开始时间](Gets the start time of a month)
     * @description: zh - 获取某月的开始时间
     * @description: en - Gets the start time of a month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:40 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar beginOfMonth(Calendar calendar) {
        return truncate(calendar, DateField.MONTH);
    }

    /**
     * [获取某月的结束时间](Gets the end time of a month)
     * @description: zh - 获取某月的结束时间
     * @description: en - Gets the end time of a month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:41 下午
     * @param calendar:  日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar endOfMonth(Calendar calendar) {
        return ceiling(calendar, DateField.MONTH);
    }

    /**
     * [获取某季度的开始时间](Gets the start time of a quarter)
     * @description: zh - 获取某季度的开始时间
     * @description: en - Gets the start time of a quarter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:44 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar beginOfQuarter(Calendar calendar) {
        calendar.set(Calendar.MONTH, calendar.get(DateField.MONTH.getValue()) / Constant.THREE * Constant.THREE);
        calendar.set(Calendar.DAY_OF_MONTH, Constant.ONE);
        return beginOfDay(calendar);
    }

    /**
     * [获取某季度的结束时间](Gets the end time of a quarter)
     * @description: zh - 获取某季度的结束时间
     * @description: en - Gets the end time of a quarter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:52 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar endOfQuarter(Calendar calendar) {
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(DateField.MONTH.getValue()) / Constant.THREE * Constant.THREE + Constant.TWO;
        final Calendar resultCal = Calendar.getInstance(calendar.getTimeZone());
        resultCal.set(year, month, Month.of(month).getLastDay(DateUtil.isLeapYear(year)));
        return endOfDay(resultCal);
    }

    /**
     * [获取某年的开始时间](Gets the start time of a year)
     * @description: zh - 获取某年的开始时间
     * @description: en - Gets the start time of a year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:54 下午
     * @param calendar:  日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar beginOfYear(Calendar calendar) {
        return truncate(calendar, DateField.YEAR);
    }

    /**
     * [获取某年的结束时间](Gets the end time of a year)
     * @description: zh - 获取某年的结束时间
     * @description: en - Gets the end time of a year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:55 下午
     * @param calendar: 日期 Calendar
     * @return java.util.Calendar
    */
    public static Calendar endOfYear(Calendar calendar) {
        return ceiling(calendar, DateField.YEAR);
    }

    /**
     * [比较两个日期是否为同一天](Compare two dates for the same day)
     * @description: zh - 比较两个日期是否为同一天
     * @description: en - Compare two dates for the same day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:56 下午
     * @param cal1: 日期1
     * @param cal2: 日期2
     * @return boolean
    */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == Constant.NULL || cal2 == Constant.NULL) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA);
    }

    /**
     * [比较两个日期是否为同一月](Compare two dates for the same month)
     * @description: zh - 比较两个日期是否为同一月
     * @description: en - Compare two dates for the same month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 6:58 下午
     * @param cal1: 日期1
     * @param cal2: 日期2
     * @return boolean
    */
    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        if (cal1 == Constant.NULL || cal2 == Constant.NULL) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    }

    /**
     * [检查两个Calendar时间戳是否相同。](Check that the two calendar timestamps are the same.)
     * @description: zh - 检查两个Calendar时间戳是否相同。
     * @description: en - Check that the two calendar timestamps are the same.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:35 上午
     * @param date1: 时间1
     * @param date2: 时间2
     * @return boolean
    */
    public static boolean isSameInstant(Calendar date1, Calendar date2) {
        return Constant.NULL == date1 ? Constant.NULL == date2 : Constant.NULL == date2 ? Constant.FALSE : date1.getTimeInMillis() == date2.getTimeInMillis();
    }

    /**
     * [获得指定日期区间内的年份和季度](Gets the year and quarter in the specified date range)
     * @description: zh - 获得指定日期区间内的年份和季度
     * @description: en - Gets the year and quarter in the specified date range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:38 上午
     * @param startDate: 起始日期（包含）
     * @param endDate: 结束日期（包含）
     * @return java.util.LinkedHashSet<java.lang.String>
    */
    public static LinkedHashSet<String> yearAndQuarter(long startDate, long endDate) {
        LinkedHashSet<String> quarters = new LinkedHashSet<>();
        final Calendar cal = calendar(startDate);
        while (startDate <= endDate) {
            // 如果开始时间超出结束时间，让结束时间为开始时间，处理完后结束循环
            quarters.add(yearAndQuarter(cal));
            cal.add(Calendar.MONTH, Constant.THREE);
            startDate = cal.getTimeInMillis();
        }
        return quarters;
    }

    /**
     * [获得指定日期年份和季度](Get the specified date, year and quarter)
     * @description: zh - 获得指定日期年份和季度
     * @description: en - Get the specified date, year and quarter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:43 上午
     * @param cal: 日期
     * @return java.lang.String
    */
    public static String yearAndQuarter(Calendar cal) {
        return StrUtil.builder().append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH) / Constant.THREE + Constant.ONE).toString();
    }

    /**
     * [获取指定日期字段的最小值，例如分钟的最小值是0](Gets the minimum value of the specified date field. For example, the minimum value of minute is 0)
     * @description: zh - 获取指定日期字段的最小值，例如分钟的最小值是0
     * @description: en - Gets the minimum value of the specified date field. For example, the minimum value of minute is 0
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:46 上午
     * @param calendar: Calendar
     * @param dateField:  DateField
     * @return int
    */
    public static int getBeginValue(Calendar calendar, DateField dateField) {
        return getBeginValue(calendar, dateField.getValue());
    }

    /**
     * [获取指定日期字段的最小值，例如分钟的最小值是0](Gets the minimum value of the specified date field. For example, the minimum value of minute is 0)
     * @description: zh - 获取指定日期字段的最小值，例如分钟的最小值是0
     * @description: en - Gets the minimum value of the specified date field. For example, the minimum value of minute is 0
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:47 上午
     * @param calendar: Calendar
     * @param dateField: DateField
     * @return int
    */
    public static int getBeginValue(Calendar calendar, int dateField) {
        return Calendar.DAY_OF_WEEK == dateField ? calendar.getFirstDayOfWeek() : calendar.getActualMinimum(dateField);
    }

    /**
     * [获取指定日期字段的最大值](Gets the maximum value of the specified date field)
     * @description: zh - 获取指定日期字段的最大值
     * @description: en - Gets the maximum value of the specified date field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:51 上午
     * @param calendar: Calendar
     * @param dateField: DateField
     * @return int
    */
    public static int getEndValue(Calendar calendar, DateField dateField) {
        return getEndValue(calendar, dateField.getValue());
    }

    /**
     * [获取指定日期字段的最大值](Gets the maximum value of the specified date field)
     * @description: zh - 获取指定日期字段的最大值
     * @description: en - Gets the maximum value of the specified date field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:52 上午
     * @param calendar: Calendar
     * @param dateField: DateField
     * @return int
    */
    public static int getEndValue(Calendar calendar, int dateField) {
        return Calendar.DAY_OF_WEEK == dateField ? (calendar.getFirstDayOfWeek() + Constant.SIX) % Constant.SEVEN : calendar.getActualMaximum(dateField);
    }

    /**
     * [CalendarInstant 对象](CalendarInstant object)
     * @description: zh - CalendarInstant 对象
     * @description: en - CalendarInstant object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:57 上午
     * @param calendar: Date对象
     * @return java.time.Instant
    */
    public static Instant toInstant(Calendar calendar) {
        return Constant.NULL == calendar ? Constant.INSTANT_NULL : calendar.toInstant();
    }

    /**
     * [Calendar 转换为 LocalDateTime，使用系统默认时区](Calendar is converted to LocalDateTime and the system default time zone is used)
     * @description: zh - Calendar 转换为 LocalDateTime，使用系统默认时区
     * @description: en - Calendar is converted to LocalDateTime and the system default time zone is used
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 7:59 上午
     * @param calendar: Calendar
     * @return java.time.LocalDateTime
    */
    public static LocalDateTime toLocalDateTime(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
    }


}
