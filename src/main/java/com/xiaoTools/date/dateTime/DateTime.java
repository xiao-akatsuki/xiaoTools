package com.xiaoTools.date.dateTime;

import com.xiaoTools.core.exception.dateException.DateException;
import com.xiaoTools.date.dateBetween.DateBetween;
import com.xiaoTools.date.dateField.DateField;
import com.xiaoTools.date.datePattern.DatePattern;
import com.xiaoTools.date.dateUnit.DateUnit;
import com.xiaoTools.date.format.dateParser.DateParser;
import com.xiaoTools.date.format.fastDateFormat.FastDateFormat;
import com.xiaoTools.date.leven.Level;
import com.xiaoTools.date.month.Month;
import com.xiaoTools.date.quarter.Quarter;
import com.xiaoTools.date.week.Week;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.dateUtil.DateUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

import java.io.Serial;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * [包装类](Packaging)
 * @description: zh - 包装类
 * @description: en - Packaging
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 11:27 上午
*/
public class DateTime extends Date {

    @Serial
    private static final long serialVersionUID = -5395712593979185936L;

    /**
     * 是否可变对象
     */
    private boolean mutable = true;
    /**
     * 一周的第一天，默认是周一， 在设置或获得 WEEK_OF_MONTH 或 WEEK_OF_YEAR 字段时，Calendar 必须确定一个月或一年的第一个星期，以此作为参考点。
     */
    private Week firstDayOfWeek = Week.MONDAY;

    /**
     * 时区
     */
    private TimeZone timeZone;

    /*构造函数--------------------------------------------------------------------Constructors*/

    /**
     * [当前时间](current time )
     * @description: zh - 当前时间
     * @description: en - current time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 12:52 下午
    */
    public DateTime() {
        this(TimeZone.getDefault());
    }

    /**
     * [当前时间](current time )
     * @description: zh - 当前时间
     * @description: en - current time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 12:53 下午
     * @param timeZone: 时区
    */
    public DateTime(TimeZone timeZone) {
        this(System.currentTimeMillis(), timeZone);
    }

    /**
     * [给定日期的构造](Construction of a given date)
     * @description: zh - 给定日期的构造
     * @description: en - Construction of a given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 1:00 下午
     * @param date: 日期
    */
    public DateTime(Date date) {
        this(date, (date instanceof DateTime) ? ((DateTime) date).timeZone : TimeZone.getDefault());
    }

    /**
     * [给定日期的构造](Construction of a given date)
     * @description: zh - 给定日期的构造
     * @description: en - Construction of a given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 1:41 下午
     * @param date: 日期
     * @param timeZone: 时区
    */
    public DateTime(Date date, TimeZone timeZone) {
        this(ObjectUtil.defaultIfNull(date, new Date()).getTime(), timeZone);
    }

    /**
     * [给定日期的构造](Construction of a given date)
     * @description: zh - 给定日期的构造
     * @description: en - Construction of a given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 1:49 下午
     * @param calendar:  Calendar日期
    */
    public DateTime(Calendar calendar) {
        this(calendar.getTime(), calendar.getTimeZone());
        this.setFirstDayOfWeek(Week.of(calendar.getFirstDayOfWeek()));
    }

    /**
     * [给定日期Instant的构造](Construction of instant with given date)
     * @description: zh - 给定日期Instant的构造
     * @description: en - Construction of instant with given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 1:49 下午
     * @param instant:  Instant 对象
    */
    public DateTime(Instant instant) {
        this(instant.toEpochMilli());
    }

    /**
     * [给定日期Instant的构造](Construction of instant with given date)
     * @description: zh - 给定日期Instant的构造
     * @description: en - Construction of instant with given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 1:52 下午
     * @param instant: Instant 对象
     * @param zoneId: 时区ID
    */
    public DateTime(Instant instant, ZoneId zoneId) {
        this(instant.toEpochMilli(), TimeZone.getTimeZone(ObjectUtil.defaultIfNull(zoneId, ZoneId.systemDefault())));
    }

    /**
     * [给定日期TemporalAccessor的构造](Construction of TemporalAccessor with given date)
     * @description: zh - 给定日期TemporalAccessor的构造
     * @description: en - Construction of TemporalAccessor with given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:00 下午
     * @param temporalAccessor: TemporalAccessor 对象
    */
    public DateTime(TemporalAccessor temporalAccessor) {
        this(DateUtil.toInstant(temporalAccessor));
    }

    /**
     * [给定日期ZonedDateTime的构造](Construction of ZonedDateTime with given date)
     * @description: zh - 给定日期ZonedDateTime的构造
     * @description: en - Construction of ZonedDateTime with given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:01 下午
     * @param zonedDateTime: ZonedDateTime 对象
    */
    public DateTime(ZonedDateTime zonedDateTime) {
        this(zonedDateTime.toInstant(), zonedDateTime.getZone());
    }

    /**
     * [给定日期毫秒数的构造](Construction of the number of milliseconds for a given date)
     * @description: zh - 给定日期毫秒数的构造
     * @description: en - Construction of the number of milliseconds for a given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 12:58 下午
     * @param timeMillis: 日期毫秒数
    */
    public DateTime(long timeMillis) {
        this(timeMillis, TimeZone.getDefault());
    }

    /**
     * [给定日期毫秒数的构造](Construction of the number of milliseconds for a given date)
     * @description: zh - 给定日期毫秒数的构造
     * @description: en - Construction of the number of milliseconds for a given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:02 下午
     * @param timeMillis: 日期毫秒数
     * @param timeZone: 时区
    */
    public DateTime(long timeMillis, TimeZone timeZone) {
        super(timeMillis);
        this.timeZone = ObjectUtil.defaultIfNull(timeZone, TimeZone.getDefault());
    }

    /**
     * [构造格式](Construction format)
     * @description: zh - 构造格式
     * @description: en - Construction format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:03 下午
     * @param dateStr: Date字符串
    */
    public DateTime(CharSequence dateStr) {
        this(DateUtil.parse(dateStr));
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:04 下午
     * @param dateStr: Date字符串
     * @param format: 格式
    */
    public DateTime(CharSequence dateStr, String format) {
        this(dateStr, DateUtil.newSimpleFormat(format));
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:05 下午
     * @param dateStr: Date字符串
     * @param dateFormat: 格式化器 SimpleDateFormat
    */
    public DateTime(CharSequence dateStr, DateFormat dateFormat) {
        this(parse(dateStr, dateFormat), dateFormat.getTimeZone());
    }

    /**
     * [构建DateTime对象](Building a DateTime object)
     * @description: zh - 构建DateTime对象
     * @description: en - Building a DateTime object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:05 下午
     * @param dateStr: Date字符串
     * @param formatter: 格式化器
    */
    public DateTime(CharSequence dateStr, DateTimeFormatter formatter) {
        this(Instant.from(formatter.parse(dateStr)), formatter.getZone());
    }

    /**
     * [构造函数](Constructors)
     * @description: zh - 构造函数
     * @description: en - Constructors
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:07 下午
     * @param dateStr: Date字符串
     * @param dateParser: 格式化器
    */
    public DateTime(CharSequence dateStr, DateParser dateParser) {
        this(parse(dateStr, dateParser), dateParser.getTimeZone());
    }

    /*转换--------------------------------------------------------------------transformation*/

    /**
     * [转换时间戳为 DateTime](Convert timestamp to DateTime)
     * @description: zh - 转换时间戳为 DateTime
     * @description: en - Convert timestamp to DateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:09 下午
     * @param timeMillis: 时间戳，毫秒数
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime of(long timeMillis) {
        return new DateTime(timeMillis);
    }

    /**
     * [转换JDK date为 DateTime](Convert JDK date to datetime)
     * @description: zh - 转换JDK date为 DateTime
     * @description: en - Convert JDK date to datetime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:12 下午
     * @param date: JDK Date
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime of(Date date) {
        return date instanceof DateTime ? (DateTime)date : new DateTime(date);
    }

    /**
     * [转换 Calendar 为 DateTime](Convert Calendar to DateTime)
     * @description: zh - 转换 Calendar 为 DateTime
     * @description: en - Convert Calendar to DateTime
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:13 下午
     * @param calendar:  Calendar对象
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime of(Calendar calendar) {
        return new DateTime(calendar);
    }

    /**
     * [使用构造函数](Using constructors)
     * @description: zh - 使用构造函数
     * @description: en - Using constructors
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:13 下午
     * @param dateStr: Date字符串
     * @param format: 格式
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime of(String dateStr, String format) {
        return new DateTime(dateStr, format);
    }

    /*获取现在的时间--------------------------------------------------------------------now*/

    /**
     * [获取现在的时间](Get the present time)
     * @description: zh - 获取现在的时间
     * @description: en - Get the present time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:14 下午
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public static DateTime now() {
        return new DateTime();
    }

    /*调整--------------------------------------------------------------------offset*/

    /**
     * [调整日期和时间](Adjust date and time)
     * @description: zh - 调整日期和时间
     * @description: en - Adjust date and time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:35 下午
     * @param datePart: 调整的部分 DateField
     * @param offset: 偏移量，正数为向后偏移，负数为向前偏移
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public DateTime offset(DateField datePart, int offset) {
        if (DateField.ERA == datePart) {
            throw new IllegalArgumentException("ERA is not support offset!");
        }

        final Calendar cal = toCalendar();
        //noinspection MagicConstant
        cal.add(datePart.getValue(), offset);

        DateTime dt = mutable ? this : ObjectUtil.clone(this);
        return dt.setTimeInternal(cal.getTimeInMillis());
    }

    /**
     * [调整日期和时间](Adjust date and time)
     * @description: zh - 调整日期和时间
     * @description: en - Adjust date and time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:37 下午
     * @param datePart: 调整的部分 DateField
     * @param offset: 偏移量，正数为向后偏移，负数为向前偏移
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public DateTime offsetNew(DateField datePart, int offset) {
        final Calendar cal = toCalendar();
        //noinspection MagicConstant
        cal.add(datePart.getValue(), offset);

        DateTime dt = ObjectUtil.clone(this);
        return dt.setTimeInternal(cal.getTimeInMillis());
    }

    /*设置 OR 获取日期 --------------------------------------------------------------------set OR get*/

    /**
     * [获得日期的某个部分](Get a part of the date)
     * @description: zh - 获得日期的某个部分
     * @description: en - Get a part of the date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:38 下午
     * @param field: 表示日期的哪个部分的枚举 DateField
     * @return int
    */
    public int getField(DateField field) {
        return getField(field.getValue());
    }

    /**
     * [获得日期的某个部分](Get a part of the date)
     * @description: zh - 获得日期的某个部分
     * @description: en - Get a part of the date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:43 下午
     * @param field: 表示日期的哪个部分的int值 Calendar
     * @return int
    */
    public int getField(int field) {
        return toCalendar().get(field);
    }

    /**
     * [设置日期的某个部分](Set a part of the date)
     * @description: zh - 设置日期的某个部分
     * @description: en - Set a part of the date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:44 下午
     * @param field: 表示日期的哪个部分的枚举 DateField
     * @param value: 值
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public DateTime setField(DateField field, int value) {
        return setField(field.getValue(), value);
    }

    /**
     * [设置日期的某个部分](Set a part of the date)
     * @description: zh - 设置日期的某个部分
     * @description: en - Set a part of the date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:44 下午
     * @param field: 表示日期的哪个部分的int值 Calendar
     * @param value: 值
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public DateTime setField(int field, int value) {
        final Calendar calendar = toCalendar();
        calendar.set(field, value);
        DateTime dt = this;
        if (!mutable) {
            dt = ObjectUtil.clone(this);
        }
        return dt.setTimeInternal(calendar.getTimeInMillis());
    }

    /*重写 --------------------------------------------------------------------Override*/

    @Override
    public void setTime(long time) {
        if (mutable) {
            super.setTime(time);
        } else {
            throw new DateException("This is not a mutable object !");
        }
    }

    /*年月日 --------------------------------------------------------------------year OR month OR day*/

    /**
     * [获得年的部分](Get part of the year)
     * @description: zh - 获得年的部分
     * @description: en - Get part of the year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:57 下午
     * @return int
    */
    public int year() { return getField(DateField.YEAR); }

    /**
     * [获得当前日期所属季度，从1开始计数](Gets the quarter of the current date, counting from 1)
     * @description: zh - 获得当前日期所属季度，从1开始计数
     * @description: en - Gets the quarter of the current date, counting from 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:07 下午
     * @return int
    */
    public int quarter() { return month() / Constant.THREE + Constant.ONE; }

    /**
     * [获取月份](Get month)
     * @description: zh - 获取月份
     * @description: en - Get month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:08 下午
     * @return int
    */
    public int month() { return getField(DateField.MONTH); }

    /**
     * [获得当前日期所属季度](Gets the quarter of the current date)
     * @description: zh - 获得当前日期所属季度
     * @description: en - Gets the quarter of the current date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:13 下午
     * @return com.xiaoTools.date.quarter.Quarter
    */
    public Quarter quarterEnum() { return Quarter.of(quarter()); }

    /**
     * [获取月，从1开始计数](Gets the month, counting from 1)
     * @description: zh - 获取月，从1开始计数
     * @description: en - Gets the month, counting from 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:15 下午
     * @return int
    */
    public int monthBaseOne() { return month() + Constant.ONE; }

    /**
     * [获得月份，从1开始计数](Get the month, counting from 1)
     * @description: zh - 获得月份，从1开始计数
     * @description: en - Get the month, counting from 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:16 下午
     * @return int
    */
    public int monthStartFromOne() { return month() + Constant.ONE; }

    /**
     * [获得月份](Month of acquisition)
     * @description: zh - 获得月份
     * @description: en - Month of acquisition
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:32 下午
     * @return com.xiaoTools.date.month.Month
    */
    public Month monthEnum() { return Month.of(month()); }

    /**
     * [获得指定日期是所在年份的第几周](Gets the week of the year in which the specified date is located)
     * @description: zh - 获得指定日期是所在年份的第几周
     * @description: en - Gets the week of the year in which the specified date is located
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:34 下午
     * @return int
    */
    public int weekOfYear() { return getField(DateField.WEEK_OF_YEAR); }

    /**
     * [获得指定日期是所在月份的第几周](Gets the week of the month in which the specified date is located)
     * @description: zh - 获得指定日期是所在月份的第几周
     * @description: en - Gets the week of the month in which the specified date is located
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:35 下午
     * @return int
    */
    public int weekOfMonth() { return getField(DateField.WEEK_OF_MONTH); }

    /**
     * [获得指定日期是这个日期所在月份的第几天，从1开始](Gets the day of the month in which the specified date occurs, starting from 1)
     * @description: zh - 获得指定日期是这个日期所在月份的第几天，从1开始
     * @description: en - Gets the day of the month in which the specified date occurs, starting from 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:39 下午
     * @return int
    */
    public int dayOfMonth() { return getField(DateField.DAY_OF_MONTH); }

    /**
     * [获得指定日期是这个日期所在年份的第几天，从1开始](Gets the day of the year in which the specified date occurs, starting from 1)
     * @description: zh - 获得指定日期是这个日期所在年份的第几天，从1开始
     * @description: en - Gets the day of the year in which the specified date occurs, starting from 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:42 下午
     * @return int
    */
    public int dayOfYear() { return getField(DateField.DAY_OF_YEAR); }

    /**
     * [获得指定日期是星期几，1表示周日，2表示周一](Get the specified day of the week, 1 for Sunday, 2 for Monday)
     * @description: zh - 获得指定日期是星期几，1表示周日，2表示周一
     * @description: en - Get the specified day of the week, 1 for Sunday, 2 for Monday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:44 下午
     * @return int
    */
    public int dayOfWeek() { return getField(DateField.DAY_OF_WEEK); }

    /**
     * [获得天所在的周是这个月的第几周](What is the week of the month in which you get the day)
     * @description: zh - 获得天所在的周是这个月的第几周
     * @description: en - What is the week of the month in which you get the day
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:45 下午
     * @return int
    */
    public int dayOfWeekInMonth() { return getField(DateField.DAY_OF_WEEK_IN_MONTH); }

    /**
     * [获得指定日期是星期几](What day of the week is the date given)
     * @description: zh - 获得指定日期是星期几
     * @description: en - What day of the week is the date given
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:45 下午
     * @return com.xiaoTools.date.week.Week
    */
    public Week dayOfWeekEnum() { return Week.of(dayOfWeek()); }

    /**
     * [获得指定日期的小时数部分](Gets the hours part of the specified date)
     * @description: zh - 获得指定日期的小时数部分
     * @description: en - Gets the hours part of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:46 下午
     * @param is24Hour: 是否24小时制
     * @return int
    */
    public int hour(boolean is24Hour) { return getField(is24Hour ? DateField.HOUR_OF_DAY : DateField.HOUR); }

    /**
     * [获得指定日期的分钟数部分](Gets the minutes part of the specified date)
     * @description: zh - 获得指定日期的分钟数部分
     * @description: en - Gets the minutes part of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:47 下午
     * @return int
    */
    public int minute() {
        return getField(DateField.MINUTE);
    }

    /**
     * [获得指定日期的秒数部分](Gets the seconds part of the specified date)
     * @description: zh - 获得指定日期的秒数部分
     * @description: en - Gets the seconds part of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:49 下午
     * @return int
    */
    public int second() {
        return getField(DateField.SECOND);
    }

    /**
     * [获得指定日期的毫秒数部分](Gets the millisecond part of the specified date)
     * @description: zh - 获得指定日期的毫秒数部分
     * @description: en - Gets the millisecond part of the specified date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:49 下午
     * @return int
    */
    public int millisecond() {
        return getField(DateField.MILLISECOND);
    }

    /**
     * [是否为上午](Is it morning)
     * @description: zh - 是否为上午
     * @description: en - Is it morning
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:50 下午
     * @return boolean
    */
    public boolean isAM() {
        return Calendar.AM == getField(DateField.AM_PM);
    }

    /**
     * [是否为下午](Is it afternoon)
     * @description: zh - 是否为下午
     * @description: en - Is it afternoon
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:51 下午
     * @return boolean
    */
    public boolean isPM() {
        return Calendar.PM == getField(DateField.AM_PM);
    }

    /**
     * [是否为周末，周末指周六或者周日](Is it a weekend? Weekend means Saturday or Sunday)
     * @description: zh - 是否为周末，周末指周六或者周日
     * @description: en - Is it a weekend? Weekend means Saturday or Sunday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:51 下午
     * @return boolean
    */
    public boolean isWeekend() {
        final int dayOfWeek = dayOfWeek();
        return Calendar.SATURDAY == dayOfWeek || Calendar.SUNDAY == dayOfWeek;
    }

    /*其他 --------------------------------------------------------------------other*/

    /**
     * [判断是否是闰年](Judge whether it's a leap year)
     * @description: zh - 判断是否是闰年
     * @description: en - Judge whether it's a leap year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:55 下午
     * @return boolean
    */
    public boolean isLeapYear() {
        return DateUtil.isLeapYear(year());
    }

    /**
     * [转换为Calendar, 默认 Locale](Convert to calendar, default locale)
     * @description: zh - 转换为Calendar, 默认 Locale
     * @description: en - Convert to calendar, default locale
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:56 下午
     * @return java.util.Calendar
    */
    public Calendar toCalendar() {
        return toCalendar(Locale.getDefault(Locale.Category.FORMAT));
    }

    /**
     * [转换为Calendar](Convert to calendar)
     * @description: zh - 转换为Calendar
     * @description: en - Convert to calendar
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:58 下午
     * @param locale: 地域 Locale
     * @return java.util.Calendar
    */
    public Calendar toCalendar(Locale locale) {
        return toCalendar(this.timeZone, locale);
    }

    /**
     * [转换为Calendar](Convert to calendar)
     * @description: zh - 转换为Calendar
     * @description: en - Convert to calendar
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:59 下午
     * @param zone: 时区 TimeZone
     * @return java.util.Calendar
    */
    public Calendar toCalendar(TimeZone zone) {
        return toCalendar(zone, Locale.getDefault(Locale.Category.FORMAT));
    }

    /**
     * [转换为Calendar](Convert to calendar)
     * @description: zh - 转换为Calendar
     * @description: en - Convert to calendar
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:05 下午
     * @param zone: 时区 TimeZone
     * @param locale: 地域 Locale
     * @return java.util.Calendar
    */
    public Calendar toCalendar(TimeZone zone, Locale locale) {
        if (Constant.NULL == locale) {
            locale = Locale.getDefault(Locale.Category.FORMAT);
        }
        final Calendar cal = (Constant.NULL != zone) ? Calendar.getInstance(zone, locale) : Calendar.getInstance(locale);
        cal.setFirstDayOfWeek(firstDayOfWeek.getValue());
        cal.setTime(this);
        return cal;
    }

    /**
     * [转换成为JDK的Date](Date converted to JDK)
     * @description: zh - 转换成为JDK的Date
     * @description: en - Date converted to JDK
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:08 下午
     * @return java.util.Date
    */
    public Date toJdkDate() {
        return new Date(this.getTime());
    }

    /**
     * [转为Timestamp](Turn to timestamp)
     * @description: zh - 转为Timestamp
     * @description: en - Turn to timestamp
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:10 下午
     * @return java.sql.Timestamp
    */
    public Timestamp toTimestamp() { return new Timestamp(this.getTime()); }

    /**
     * [转换成为sql.date的Date](Date converted to sql.date)
     * @description: zh - 转换成为sql.date的Date
     * @description: en - Date converted to sql.date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:11 下午
     * @return java.sql.Date
    */
    public java.sql.Date toSqlDate() {
        return new java.sql.Date(getTime());
    }

    /**
     * [计算相差时长](Calculate the time difference)
     * @description: zh - 计算相差时长
     * @description: en - Calculate the time difference
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 7:06 下午
     * @param date: 对比的日期
     * @return com.xiaoTools.date.dateBetween.DateBetween
    */
    public DateBetween between(Date date) {
        return new DateBetween(this, date);
    }

    /**
     * [计算相差时长](Calculate the time difference)
     * @description: zh - 计算相差时长
     * @description: en - Calculate the time difference
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 7:15 下午
     * @param date: 对比的日期
     * @param unit: 单位 DateUnit
     * @return long
    */
    public long between(Date date, DateUnit unit) {
        return new DateBetween(this, date).between(unit);
    }

    /**
     * [计算相差时长](Calculate the time difference)
     * @description: zh - 计算相差时长
     * @description: en - Calculate the time difference
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 7:17 下午
     * @param date: 对比的日期
     * @param unit: 单位 DateUnit
     * @param formatLevel: 格式化级别
     * @return java.lang.String
    */
    public String between(Date date, DateUnit unit, Level formatLevel) {
        return new DateBetween(this, date).toString(formatLevel);
    }

    /**
     * [当前日期是否在日期指定范围内](Is the current date within the specified range)
     * @description: zh - 当前日期是否在日期指定范围内
     * @description: en - Is the current date within the specified range
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 7:19 下午
     * @param beginDate: 起始日期
     * @param endDate: 结束日期
     * @return boolean
    */
    public boolean isIn(Date beginDate, Date endDate) {
        long beginMills = beginDate.getTime();
        long endMills = endDate.getTime();
        long thisMills = this.getTime();
        return thisMills >= Math.min(beginMills, endMills) && thisMills <= Math.max(beginMills, endMills);
    }
    /**
     * [是否在给定日期之前](Before the given date)
     * @description: zh - 是否在给定日期之前
     * @description: en - Before the given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 7:32 下午
     * @param date: 日期
     * @return boolean
    */
    public boolean isBefore(Date date) {
        if (Constant.NULL == date) {
            throw new NullPointerException("Date to compare is null !");
        }
        return compareTo(date) < Constant.ZERO;
    }

    /**
     * [是否在给定日期之前或与给定日期相等](Is it before or equal to the given date)
     * @description: zh - 是否在给定日期之前或与给定日期相等
     * @description: en - Is it before or equal to the given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 7:38 下午
     * @param date: 日期
     * @return boolean
    */
    public boolean isBeforeOrEquals(Date date) {
        if (Constant.NULL == date) {
            throw new NullPointerException("Date to compare is null !");
        }
        return compareTo(date) <= Constant.ZERO;
    }

    /**
     * [是否在给定日期之后](After a given date)
     * @description: zh - 是否在给定日期之后
     * @description: en - After a given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:53 下午
     * @param date: 日期
     * @return boolean
    */
    public boolean isAfter(Date date) {
        if (null == date) {
            throw new NullPointerException("Date to compare is null !");
        }
        return compareTo(date) > Constant.ZERO;
    }

    /**
     * [是否在给定日期之后或与给定日期相等](Is it after or equal to the given date)
     * @description: zh - 是否在给定日期之后或与给定日期相等
     * @description: en - Is it after or equal to the given date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:54 下午
     * @param date: 日期
     * @return boolean
    */
    public boolean isAfterOrEquals(Date date) {
        if (null == date) {
            throw new NullPointerException("Date to compare is null !");
        }
        return compareTo(date) >= Constant.ZERO;
    }

    /**
     * [对象是否可变](Is the object mutable)
     * @description: zh - 对象是否可变
     * @description: en - Is the object mutable
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:55 下午
     * @return boolean
    */
    public boolean isMutable() {
        return mutable;
    }

    /**
     * [设置对象是否可变 如果为不可变对象](Sets whether the object is mutable, if it is immutable)
     * @description: zh - 设置对象是否可变 如果为不可变对象
     * @description: en - Sets whether the object is mutable, if it is immutable
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:55 下午
     * @param mutable: 是否可变
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public DateTime setMutable(boolean mutable) {
        this.mutable = mutable;
        return this;
    }

    /**
     * [获得一周的第一天，默认为周一](Get the first day of the week, default to Monday)
     * @description: zh - 获得一周的第一天，默认为周一
     * @description: en - Get the first day of the week, default to Monday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:56 下午
     * @return com.xiaoTools.date.week.Week
    */
    public Week getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    /**
     * [设置一周的第一天](Set the first day of the week)
     * @description: zh - 设置一周的第一天
     * @description: en - Set the first day of the week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:56 下午
     * @param firstDayOfWeek: 一周的第一天
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public DateTime setFirstDayOfWeek(Week firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
        return this;
    }

    /**
     * [获取时区](Get time zone)
     * @description: zh - 获取时区
     * @description: en - Get time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:57 下午
     * @return java.util.TimeZone
    */
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    /**
     * [获取时区ID](Get time zone ID)
     * @description: zh - 获取时区ID
     * @description: en - Get time zone ID
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:58 下午
     * @return java.time.ZoneId
    */
    public ZoneId getZoneId() {
        return this.timeZone.toZoneId();
    }

    /**
     * [设置时区](Set time zone)
     * @description: zh - 设置时区
     * @description: en - Set time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 8:58 下午
     * @param timeZone: 时区
     * @return com.xiaoTools.date.dateTime.DateTime
    */
    public DateTime setTimeZone(TimeZone timeZone) {
        this.timeZone = ObjectUtil.defaultIfNull(timeZone, TimeZone.getDefault());
        return this;
    }

    /*输出 --------------------------------------------------------------------toString*/

    /**
     * [转为"yyyy-MM-dd HH:mm:ss" 格式字符串](Convert to "yyyy MM DD HH: mm: SS" format string)
     * @description: zh - 转为"yyyy-MM-dd HH:mm:ss" 格式字符串
     * @description: en - Convert to "yyyy MM DD HH: mm: SS" format string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 9:02 下午
     * @return java.lang.String
    */
    @Override
    public String toString() {
        return toString(this.timeZone);
    }

    /**
     * [转为"yyyy-MM-dd HH:mm:ss" 格式字符串](Convert to "yyyy MM DD HH: mm: SS" format string)
     * @description: zh - 转为"yyyy-MM-dd HH:mm:ss" 格式字符串
     * @description: en - Convert to "yyyy MM DD HH: mm: SS" format string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 9:02 下午
     * @return java.lang.String
    */
    public String toStringDefaultTimeZone() {
        return toString(TimeZone.getDefault());
    }

    /**
     * [转为"yyyy-MM-dd HH:mm:ss" 格式字符串](Convert to "yyyy MM DD HH: mm: SS" format string)
     * @description: zh - 转为"yyyy-MM-dd HH:mm:ss" 格式字符串
     * @description: en - Convert to "yyyy MM DD HH: mm: SS" format string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:31 上午
     * @param timeZone: 时区
     * @return java.lang.String
    */
    public String toString(TimeZone timeZone) {
        if (Constant.NULL != timeZone) {
            return toString(DateUtil.newSimpleFormat(DatePattern.NORM_DATETIME_PATTERN, Constant.NULL, timeZone));
        }
        return toString(DatePattern.NORM_DATETIME_FORMAT);
    }

    /**
     * [转为"yyyy-MM-dd" 格式字符串](Convert to "yyyy MM DD" format string)
     * @description: zh - 转为"yyyy-MM-dd" 格式字符串
     * @description: en - Convert to "yyyy MM DD" format string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:32 上午
     * @return java.lang.String
    */
    public String toDateStr() {
        if (Constant.NULL != this.timeZone) {
            return toString(DateUtil.newSimpleFormat(DatePattern.NORM_DATE_PATTERN, Constant.NULL, timeZone));
        }
        return toString(DatePattern.NORM_DATE_FORMAT);
    }

    /**
     * [转为"HH:mm:ss" 格式字符串](Convert to "HH: mm: SS" format string)
     * @description: zh - 转为"HH:mm:ss" 格式字符串
     * @description: en - Convert to "HH: mm: SS" format string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:32 上午
     * @return java.lang.String
    */
    public String toTimeStr() {
        if (Constant.NULL != this.timeZone) {
            return toString(DateUtil.newSimpleFormat(DatePattern.NORM_TIME_PATTERN, Constant.NULL, timeZone));
        }
        return toString(DatePattern.NORM_TIME_FORMAT);
    }

    /**
     * [转为字符串](Convert to string)
     * @description: zh - 转为字符串
     * @description: en - Convert to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:35 上午
     * @param format: 日期格式
     * @return java.lang.String
    */
    public String toString(String format) {
        if (Constant.NULL != this.timeZone) {
            return toString(DateUtil.newSimpleFormat(format, Constant.NULL, timeZone));
        }
        return toString(FastDateFormat.getInstance(format));
    }

    /**
     * [转为字符串](Convert to string)
     * @description: zh - 转为字符串
     * @description: en - Convert to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:36 上午
     * @param format: DatePrinter 或 FastDateFormat
     * @return java.lang.String
    */
    public String toString(DatePrinter format) {
        return format.format(this);
    }

    /**
     * [转为字符串](Convert to string)
     * @description: zh - 转为字符串
     * @description: en - Convert to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:36 上午
     * @param format: SimpleDateFormat
     * @return java.lang.String
    */
    public String toString(DateFormat format) {
        return format.format(this);
    }

    /**
     * [输出精确到毫秒的标准日期形式Constant.NULLtput the standard date form in milliseconds)
     * @description: zh - 输出精确到毫秒的标准日期形式
     * @description: en - Output the standard date form in milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:37 上午
     * @return java.lang.String
    */
    public String toMsStr() {
        return toString(DatePattern.NORM_DATETIME_MS_FORMAT);
    }


}
