package com.xiaoTools.date.format.fastDateFormat;

import com.xiaoTools.cache.format.FormatCache;
import com.xiaoTools.date.format.dateParser.DateParser;
import com.xiaoTools.date.format.datePrinter.DatePrinter;

import java.io.Serial;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * [FastDateFormat 是一个线程安全的 java.text.SimpleDateFormat 实现](FastDateFormat is a thread safe implementation of Java.Text.SimpleDateFormat)
 * @description: zh - FastDateFormat 是一个线程安全的 java.text.SimpleDateFormat 实现
 * @description: en - FastDateFormat is a thread safe implementation of Java.Text.SimpleDateFormat
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 8:06 上午
*/
public class FastDateFormat extends Format implements DateParser, DatePrinter {

    @Serial
    private static final long serialVersionUID = 8097890768636183236L;

    /**
     * 完全依赖于区域设置的日期或时间样式。
     */
    public static final int FULL = DateFormat.FULL;

    /**
     * 与区域设置相关的长日期或时间样式。
     */
    public static final int LONG = DateFormat.LONG;

    /**
     * 中等区域设置相关的日期或时间样式。
     */
    public static final int MEDIUM = DateFormat.MEDIUM;

    /**
     * 与区域设置相关的短日期或时间样式。
     */
    public static final int SHORT = DateFormat.SHORT;

    private static final FormatCache<FastDateFormat> CACHE = new FormatCache<FastDateFormat>(){
        @Override
        protected FastDateFormat createInstance(final String pattern, final TimeZone timeZone, final Locale locale) {
            return new FastDateFormat(pattern, timeZone, locale);
        }
    };

    private final FastDatePrinter printer;
    private final FastDateParser parser;

    // -----------------------------------------------------------------------

    /**
     * 获得 FastDateFormat实例，使用默认格式和地区
     *
     * @return FastDateFormat
     */
    public static FastDateFormat getInstance() {
        return CACHE.getInstance();
    }

    /**
     * 获得 FastDateFormat 实例，使用默认地区<br>
     * 支持缓存
     *
     * @param pattern 使用{@link java.text.SimpleDateFormat} 相同的日期格式
     * @return FastDateFormat
     * @throws IllegalArgumentException 日期格式问题
     */
    public static FastDateFormat getInstance(final String pattern) {
        return CACHE.getInstance(pattern, null, null);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param pattern 使用{@link java.text.SimpleDateFormat} 相同的日期格式
     * @param timeZone 时区{@link TimeZone}
     * @return FastDateFormat
     * @throws IllegalArgumentException 日期格式问题
     */
    public static FastDateFormat getInstance(final String pattern, final TimeZone timeZone) {
        return CACHE.getInstance(pattern, timeZone, null);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param pattern 使用{@link java.text.SimpleDateFormat} 相同的日期格式
     * @param locale {@link Locale} 日期地理位置
     * @return FastDateFormat
     * @throws IllegalArgumentException 日期格式问题
     */
    public static FastDateFormat getInstance(final String pattern, final Locale locale) {
        return CACHE.getInstance(pattern, null, locale);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param pattern 使用{@link java.text.SimpleDateFormat} 相同的日期格式
     * @param timeZone 时区{@link TimeZone}
     * @param locale {@link Locale} 日期地理位置
     * @return FastDateFormat
     * @throws IllegalArgumentException 日期格式问题
     */
    public static FastDateFormat getInstance(final String pattern, final TimeZone timeZone, final Locale locale) {
        return CACHE.getInstance(pattern, timeZone, locale);
    }

    // -----------------------------------------------------------------------
    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param style date style: FULL, LONG, MEDIUM, or SHORT
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getDateInstance(final int style) {
        return CACHE.getDateInstance(style, null, null);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param style date style: FULL, LONG, MEDIUM, or SHORT
     * @param locale {@link Locale} 日期地理位置
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getDateInstance(final int style, final Locale locale) {
        return CACHE.getDateInstance(style, null, locale);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param style date style: FULL, LONG, MEDIUM, or SHORT
     * @param timeZone 时区{@link TimeZone}
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getDateInstance(final int style, final TimeZone timeZone) {
        return CACHE.getDateInstance(style, timeZone, null);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param style date style: FULL, LONG, MEDIUM, or SHORT
     * @param timeZone 时区{@link TimeZone}
     * @param locale {@link Locale} 日期地理位置
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getDateInstance(final int style, final TimeZone timeZone, final Locale locale) {
        return CACHE.getDateInstance(style, timeZone, locale);
    }

    // -----------------------------------------------------------------------
    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param style time style: FULL, LONG, MEDIUM, or SHORT
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getTimeInstance(final int style) {
        return CACHE.getTimeInstance(style, null, null);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param style time style: FULL, LONG, MEDIUM, or SHORT
     * @param locale {@link Locale} 日期地理位置
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getTimeInstance(final int style, final Locale locale) {
        return CACHE.getTimeInstance(style, null, locale);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param style time style: FULL, LONG, MEDIUM, or SHORT
     * @param timeZone optional time zone, overrides time zone of formatted time
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getTimeInstance(final int style, final TimeZone timeZone) {
        return CACHE.getTimeInstance(style, timeZone, null);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param style time style: FULL, LONG, MEDIUM, or SHORT
     * @param timeZone optional time zone, overrides time zone of formatted time
     * @param locale {@link Locale} 日期地理位置
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getTimeInstance(final int style, final TimeZone timeZone, final Locale locale) {
        return CACHE.getTimeInstance(style, timeZone, locale);
    }

    // -----------------------------------------------------------------------
    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param dateStyle date style: FULL, LONG, MEDIUM, or SHORT
     * @param timeStyle time style: FULL, LONG, MEDIUM, or SHORT
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle) {
        return CACHE.getDateTimeInstance(dateStyle, timeStyle, null, null);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param dateStyle date style: FULL, LONG, MEDIUM, or SHORT
     * @param timeStyle time style: FULL, LONG, MEDIUM, or SHORT
     * @param locale {@link Locale} 日期地理位置
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final Locale locale) {
        return CACHE.getDateTimeInstance(dateStyle, timeStyle, null, locale);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param dateStyle date style: FULL, LONG, MEDIUM, or SHORT
     * @param timeStyle time style: FULL, LONG, MEDIUM, or SHORT
     * @param timeZone 时区{@link TimeZone}
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone) {
        return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
    }

    /**
     * 获得 FastDateFormat 实例<br>
     * 支持缓存
     *
     * @param dateStyle date style: FULL, LONG, MEDIUM, or SHORT
     * @param timeStyle time style: FULL, LONG, MEDIUM, or SHORT
     * @param timeZone 时区{@link TimeZone}
     * @param locale {@link Locale} 日期地理位置
     * @return 本地化 FastDateFormat
     */
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone, final Locale locale) {
        return CACHE.getDateTimeInstance(dateStyle, timeStyle, timeZone, locale);
    }

    // 构造函数----------------------------------------------------------------------- Constructor
    /**
     * 构造
     *
     * @param pattern 使用{@link java.text.SimpleDateFormat} 相同的日期格式
     * @param timeZone 非空时区 {@link TimeZone}
     * @param locale {@link Locale} 日期地理位置
     * @throws NullPointerException if pattern, timeZone, or locale is null.
     */
    protected FastDateFormat(final String pattern, final TimeZone timeZone, final Locale locale) {
        this(pattern, timeZone, locale, null);
    }

    /**
     * 构造
     *
     * @param pattern 使用{@link java.text.SimpleDateFormat} 相同的日期格式
     * @param timeZone 非空时区 {@link TimeZone}
     * @param locale {@link Locale} 日期地理位置
     * @param centuryStart The start of the 100 year period to use as the "default century" for 2 digit year parsing. If centuryStart is null, defaults to now - 80 years
     * @throws NullPointerException if pattern, timeZone, or locale is null.
     */
    protected FastDateFormat(final String pattern, final TimeZone timeZone, final Locale locale, final Date centuryStart) {
        printer = new FastDatePrinter(pattern, timeZone, locale);
        parser = new FastDateParser(pattern, timeZone, locale, centuryStart);
    }

    //格式化方法----------------------------------------------------------------------- Format methods

    @Override
    public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
        return toAppendTo.append(printer.format(obj));
    }

    @Override
    public String format(final long millis) {
        return printer.format(millis);
    }

    @Override
    public String format(final Date date) {
        return printer.format(date);
    }

    @Override
    public String format(final Calendar calendar) {
        return printer.format(calendar);
    }

    @Override
    public <B extends Appendable> B format(final long millis, final B buf) {
        return printer.format(millis, buf);
    }

    @Override
    public <B extends Appendable> B format(final Date date, final B buf) {
        return printer.format(date, buf);
    }

    @Override
    public <B extends Appendable> B format(final Calendar calendar, final B buf) {
        return printer.format(calendar, buf);
    }

    //解析----------------------------------------------------------------------- Parsing

    @Override
    public Date parse(final String source) throws ParseException {
        return parser.parse(source);
    }

    @Override
    public Date parse(final String source, final ParsePosition pos) {
        return parser.parse(source, pos);
    }

    @Override
    public boolean parse(final String source, final ParsePosition pos, final Calendar calendar) {
        return parser.parse(source, pos, calendar);
    }

    @Override
    public Object parseObject(final String source, final ParsePosition pos) {
        return parser.parseObject(source, pos);
    }

    // ----------------------------------------------------------------------- Accessors

    @Override
    public String getPattern() {
        return printer.getPattern();
    }

    @Override
    public TimeZone getTimeZone() {
        return printer.getTimeZone();
    }

    @Override
    public Locale getLocale() {
        return printer.getLocale();
    }

    /**
     *估算生成的日期字符串长度<br>
     * 实际生成的字符串长度小于或等于此值
     *
     * @return 日期字符串长度
     */
    public int getMaxLengthEstimate() {
        return printer.getMaxLengthEstimate();
    }

    // Basics
    // -----------------------------------------------------------------------

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof FastDateFormat)) {
            return false;
        }
        final FastDateFormat other = (FastDateFormat) obj;
        // no need to check parser, as it has same invariants as printer
        return printer.equals(other.printer);
    }

    @Override
    public int hashCode() {
        return printer.hashCode();
    }

    @Override
    public String toString() {
        return "FastDateFormat[" + printer.getPattern() + "," + printer.getLocale() + "," + printer.getTimeZone().getID() + "]";
    }
}
