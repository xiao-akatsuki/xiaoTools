package com.xiaoTools.cache.format;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.lang.tuple.Tuple;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * [日期格式化器缓存](Date formatter cache)
 * @description: zh - 日期格式化器缓存
 * @description: en - Date formatter cache
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 9:33 上午
*/
public abstract class FormatCache<F extends Format> {

    /**
     * 没有日期或时间。在与DateFormat.SHORT或DateFormat.LONG相同的参数中使用
     */
    static final int NONE = Constant.NEGATIVE_ONE;

    private final ConcurrentMap<Tuple, F> cInstanceCache = new ConcurrentHashMap<>(Constant.SEVEN);

    private static final ConcurrentMap<Tuple, String> C_DATE_TIME_INSTANCE_CACHE = new ConcurrentHashMap<>(Constant.SEVEN);

    /**
     * [创建格式化器](Create formatter)
     * @description: zh - 创建格式化器
     * @description: en - Create formatter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:46 上午
     * @param pattern: 非空日期格式，使用与 SimpleDateFormat相同格式
     * @param timeZone: 时区，默认当前时区
     * @param locale: 地区，默认使用当前地区
     * @return F
    */
    abstract protected F createInstance(String pattern, TimeZone timeZone, Locale locale);

    /**
     * [使用默认的pattern、timezone和locale获得缓存中的实例](Use the default pattern, timezone, and locale to get the instance in the cache)
     * @description: zh - 使用默认的pattern、timezone和locale获得缓存中的实例
     * @description: en - Use the default pattern, timezone, and locale to get the instance in the cache
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:46 上午
     * @return F
    */
    public F getInstance() {
        return getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Constant.TIME_ZONE_NULL, Constant.LOCALE_NULL);
    }

    /**
     * [使用 pattern, time zone and locale 获得对应的 格式化器](Use pattern, time zone and locale to get the corresponding formatter)
     * @description: zh - 使用 pattern, time zone and locale 获得对应的 格式化器
     * @description: en - Use pattern, time zone and locale to get the corresponding formatter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:43 上午
     * @param pattern: 非空日期格式，使用与 SimpleDateFormat相同格式
     * @param timeZone: 时区，默认当前时区
     * @param locale: 地区，默认使用当前地区
     * @return F
    */
    public F getInstance(final String pattern, TimeZone timeZone, Locale locale) {
        Assertion.notBlank(pattern, "pattern must not be blank") ;
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        final Tuple key = new Tuple(pattern, timeZone, locale);
        F format = cInstanceCache.get(key);
        if (format == null) {
            format = createInstance(pattern, timeZone, locale);
            final F previousValue = cInstanceCache.putIfAbsent(key, format);
            if (previousValue != null) {
                // another thread snuck in and did the same work
                // we should return the instance that is in ConcurrentMap
                format = previousValue;
            }
        }
        return format;
    }

    /**
     * [使用指定的样式、时区和区域设置获取日期/时间格式化程序实例。](Gets an instance of the date / time formatter using the specified style, time zone, and locale.)
     * @description: zh - 使用指定的样式、时区和区域设置获取日期/时间格式化程序实例。
     * @description: en - Gets an instance of the date / time formatter using the specified style, time zone, and locale.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:41 上午
     * @param dateStyle: 日期样式：FULL、LONG、MEDIUM或SHORT，null表示格式中没有日期
     * @param timeStyle: 时间样式：FULL、LONG、MEDIUM或SHORT，null表示格式中没有时间
     * @param timeZone: 时区，重写格式化日期的时区，零表示使用默认区域设置
     * @param locale: 可选区域设置，替代系统区域设置
     * @return F
    */
    private F getDateTimeInstance(final Integer dateStyle, final Integer timeStyle, final TimeZone timeZone, Locale locale) {
        if (locale == Constant.NULL) {
            locale = Locale.getDefault();
        }
        final String pattern = getPatternForStyle(dateStyle, timeStyle, locale);
        return getInstance(pattern, timeZone, locale);
    }

    /**
     * [使用指定的样式、时区和区域设置获取日期/时间格式化程序实例。](Gets an instance of the date / time formatter using the specified style, time zone, and locale.)
     * @description: zh - 使用指定的样式、时区和区域设置获取日期/时间格式化程序实例。
     * @description: en - Gets an instance of the date / time formatter using the specified style, time zone, and locale.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:39 上午
     * @param dateStyle: 日期样式：全、长、中、短
     * @param timeStyle: 时间样式：完整、长、中或短
     * @param timeZone: 时区，重写格式化日期的时区，零表示使用默认区域设置
     * @param locale: 可选区域设置，替代系统区域设置
     * @return F
    */
    public F getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone, final Locale locale) {
        return getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), timeZone, locale);
    }

    /**
     * [获取使用指定样式、时区和区域设置的日期格式化程序实例。](Gets an instance of a date formatter that uses the specified style, time zone, and locale.)
     * @description: zh - 获取使用指定样式、时区和区域设置的日期格式化程序实例。
     * @description: en - Gets an instance of a date formatter that uses the specified style, time zone, and locale.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:38 上午
     * @param dateStyle: 日期样式：全、长、中、短
     * @param timeZone: 时区，重写格式化日期的时区，零表示使用默认区域设置
     * @param locale: 可选区域设置，替代系统区域设置
     * @return F
    */
    public F getDateInstance(final int dateStyle, final TimeZone timeZone, final Locale locale) {
        return getDateTimeInstance(dateStyle, null, timeZone, locale);
    }

    /**
     * [获取使用指定样式、时区和区域设置的时间格式化程序实例。](Gets an instance of a time formatter that uses the specified style, time zone, and locale.)
     * @description: zh - 获取使用指定样式、时区和区域设置的时间格式化程序实例。
     * @description: en - Gets an instance of a time formatter that uses the specified style, time zone, and locale.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:35 上午
     * @param timeStyle: 时间样式：完整、长、中或短
     * @param timeZone: 时区，重写格式化日期的时区，零表示使用默认区域设置
     * @param locale: 可选区域设置，替代系统区域设置
     * @return F
    */
    public F getTimeInstance(final int timeStyle, final TimeZone timeZone, final Locale locale) {
        return getDateTimeInstance(null, timeStyle, timeZone, locale);
    }

    /**
     * [获取指定样式和区域设置的日期/时间格式。](Gets the date / time format for the specified style and locale.)
     * @description: zh - 获取指定样式和区域设置的日期/时间格式。
     * @description: en - Gets the date / time format for the specified style and locale.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 10:27 上午
     * @param dateStyle: 日期样式：FULL、LONG、MEDIUM或SHORT，null表示格式中没有日期
     * @param timeStyle: 时间样式：FULL、LONG、MEDIUM或SHORT，null表示格式中没有时间
     * @param locale: 所需格式的非空区域设置
     * @return java.lang.String
    */
    public static String getPatternForStyle(final Integer dateStyle, final Integer timeStyle, final Locale locale) {
        final Tuple key = new Tuple(dateStyle, timeStyle, locale);
        String pattern = C_DATE_TIME_INSTANCE_CACHE.get(key);
        if (pattern == Constant.NULL) {
            try {
                DateFormat formatter = dateStyle == Constant.NULL ? DateFormat.getTimeInstance(timeStyle, locale) : timeStyle == Constant.NULL ? DateFormat.getDateInstance(dateStyle, locale) : DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
                pattern = ((SimpleDateFormat) formatter).toPattern();
                final String previous = C_DATE_TIME_INSTANCE_CACHE.putIfAbsent(key, pattern);
                if (previous != Constant.NULL) {
                    pattern = previous;
                }
            } catch (final ClassCastException ex) {
                throw new IllegalArgumentException("No date time pattern for locale: " + locale);
            }
        }
        return pattern;
    }
}
