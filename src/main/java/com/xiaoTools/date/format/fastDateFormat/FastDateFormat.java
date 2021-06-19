package com.xiaoTools.date.format.fastDateFormat;

import com.xiaoTools.cache.format.FormatCache;
import com.xiaoTools.date.format.dateParser.DateParser;
import com.xiaoTools.date.format.datePrinter.DatePrinter;
import com.xiaoTools.date.format.fastDateParser.FastDateParser;
import com.xiaoTools.date.format.fastDatePrinter.FastDatePrinter;
import com.xiaoTools.lang.constant.Constant;

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
     * [获得 FastDateFormat 实例，使用默认格式和地区](Get the FastDateFormat instance, using the default format and region)
     * @description: zh -  获得 FastDateFormat 实例，使用默认格式和地区
     * @description: en -  Get the FastDateFormat instance, using the default format and region
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 4:37 下午
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getInstance() {
        return CACHE.getInstance();
    }

    /**
     * [获得 FastDateFormat 实例，使用默认地区](Get the FastDateFormat instance and use the default region)
     * @description: zh - 获得 FastDateFormat 实例，使用默认地区
     * @description: en - Get the FastDateFormat instance and use the default region
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 4:39 下午
     * @param pattern: 使用 SimpleDateFormat 相同的日期格式
     * @throws IllegalArgumentException 日期格式问题
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getInstance(final String pattern) {
        return CACHE.getInstance(pattern, Constant.TIME_ZONE_NULL, Constant.LOCALE_NULL);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 4:42 下午
     * @param pattern: 使用 SimpleDateFormat 相同的日期格式
     * @param timeZone: 时区
     * @throws IllegalArgumentException 日期格式问题
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getInstance(final String pattern, final TimeZone timeZone) {
        return CACHE.getInstance(pattern, timeZone, Constant.LOCALE_NULL);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 4:42 下午
     * @param pattern: 使用 SimpleDateFormat 相同的日期格式
     * @param locale: 日期地理位置
     * @throws IllegalArgumentException 日期格式问题
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getInstance(final String pattern, final Locale locale) {
        return CACHE.getInstance(pattern, Constant.TIME_ZONE_NULL, locale);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:43 下午
     * @param pattern: 使用 SimpleDateFormat 相同的日期格式
     * @param timeZone: 时区 TimeZone
     * @param locale: Locale 日期地理位置
     * @throws IllegalArgumentException 日期格式问题
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getInstance(final String pattern, final TimeZone timeZone, final Locale locale) {
        return CACHE.getInstance(pattern, timeZone, locale);
    }

    // -----------------------------------------------------------------------

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:45 下午
     * @param style: 日期样式：全、长、中、短
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getDateInstance(final int style) {
        return CACHE.getDateInstance(style, null, null);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:46 下午
     * @param style: 日期样式：全、长、中、短
     * @param locale: 日期地理位置
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getDateInstance(final int style, final Locale locale) {
        return CACHE.getDateInstance(style, null, locale);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:48 下午
     * @param style: 日期样式：全、长、中、短
     * @param timeZone: 时区
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getDateInstance(final int style, final TimeZone timeZone) {
        return CACHE.getDateInstance(style, timeZone, null);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:49 下午
     * @param style: 日期样式：全、长、中、短
     * @param timeZone: 时区
     * @param locale: 日期地理位置
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getDateInstance(final int style, final TimeZone timeZone, final Locale locale) {
        return CACHE.getDateInstance(style, timeZone, locale);
    }

    // -----------------------------------------------------------------------

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:50 下午
     * @param style: 时间样式：完整、长、中或短
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getTimeInstance(final int style) {
        return CACHE.getTimeInstance(style, null, null);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:51 下午
     * @param style: 时间样式：完整、长、中或短
     * @param locale: 日期地理位置
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getTimeInstance(final int style, final Locale locale) {
        return CACHE.getTimeInstance(style, null, locale);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:52 下午
     * @param style: 时间样式：完整、长、中或短
     * @param timeZone: 可选时区，覆盖格式化时间的时区
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
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
    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:53 下午
     * @param style: 时间样式：完整、长、中或短
     * @param timeZone: 可选时区，覆盖格式化时间的时区
     * @param locale: 日期地理位置
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getTimeInstance(final int style, final TimeZone timeZone, final Locale locale) {
        return CACHE.getTimeInstance(style, timeZone, locale);
    }

    // -----------------------------------------------------------------------

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:53 下午
     * @param dateStyle: 日期样式：全、长、中、短
     * @param timeStyle: 时间样式：完整、长、中或短
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle) {
        return CACHE.getDateTimeInstance(dateStyle, timeStyle, null, null);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:54 下午
     * @param dateStyle: 日期样式：全、长、中、短
     * @param timeStyle: 时间样式：完整、长、中或短
     * @param locale: 日期地理位置
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final Locale locale) {
        return CACHE.getDateTimeInstance(dateStyle, timeStyle, null, locale);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:55 下午
     * @param dateStyle: 日期样式：全、长、中、短
     * @param timeStyle: 时间样式：完整、长、中或短
     * @param timeZone: 时区
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone) {
        return getDateTimeInstance(dateStyle, timeStyle, timeZone, null);
    }

    /**
     * [获得 FastDateFormat 实例](Get FastDateFormat instance)
     * @description: zh - 获得 FastDateFormat 实例
     * @description: en - Get FastDateFormat instance
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:56 下午
     * @param dateStyle: 日期样式：全、长、中、短
     * @param timeStyle: 时间样式：完整、长、中或短
     * @param timeZone: 时区
     * @param locale: 日期地理位置
     * @return com.xiaoTools.date.format.fastDateFormat.FastDateFormat
    */
    public static FastDateFormat getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone, final Locale locale) {
        return CACHE.getDateTimeInstance(dateStyle, timeStyle, timeZone, locale);
    }

    // 构造函数----------------------------------------------------------------------- Constructor

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:56 下午
     * @param pattern: 使用 SimpleDateFormat 相同的日期格式
     * @param timeZone: 非空时区 TimeZone
     * @param locale: Locale 日期地理位置
     * @throws NullPointerException if pattern, timeZone, or locale is null.
    */
    protected FastDateFormat(final String pattern, final TimeZone timeZone, final Locale locale) {
        this(pattern, timeZone, locale, null);
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 5:58 下午
     * @param pattern: 使用 SimpleDateFormat 相同的日期格式
     * @param timeZone: 非空时区
     * @param locale: 日期地理位置
     * @param centuryStart: 用作两位数年份解析的“默认世纪”的100年周期的开始。如果centuryStart为空，则默认为现在-80年
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
