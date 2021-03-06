package com.xiaoTools.date.datePattern;

import com.xiaoTools.core.regular.patternPool.PatternPool;
import com.xiaoTools.date.format.fastDateFormat.FastDateFormat;
import com.xiaoTools.lang.constant.Constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * [日期格式化类，提供常用的日期格式化对象](The date format class provides common date format objects)
 * @description: zh - 日期格式化类，提供常用的日期格式化对象
 * @description: en - The date format class provides common date format objects
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 7:50 上午
*/
public class DatePattern {

    /**
     * 标准日期时间正则，每个字段支持单个数字或2个数字
     */
    public static final Pattern REGEX_NORM = PatternPool.REGEX_NORM;

    /**
     * 年月格式：yyyy-MM
     */
    public static final String NORM_MONTH_PATTERN = Constant.NORM_MONTH_PATTERN;

    /**
     * 年月格式 FastDateFormat：yyyy-MM
     */
    public static final FastDateFormat NORM_MONTH_FORMAT = FastDateFormat.getInstance(NORM_MONTH_PATTERN);

    /**
     * 年月格式 FastDateFormat：yyyy-MM
     */
    public static final DateTimeFormatter NORM_MONTH_FORMATTER = DateTimeFormatter.ofPattern(NORM_MONTH_PATTERN);

    /**
     * 简单年月格式：yyyyMM
     */
    public static final String SIMPLE_MONTH_PATTERN = Constant.SIMPLE_MONTH_PATTERN;
    /**
     * 简单年月格式 FastDateFormat yyyyMM
     */
    public static final FastDateFormat SIMPLE_MONTH_FORMAT = FastDateFormat.getInstance(SIMPLE_MONTH_PATTERN);

    /**
     * 简单年月格式 FastDateFormat yyyyMM
     */
    public static final DateTimeFormatter SIMPLE_MONTH_FORMATTER = DateTimeFormatter.ofPattern(SIMPLE_MONTH_PATTERN);

    /**
     * 标准日期模式 yyyy-MM-dd
     */
    public static final String NORM_DATE_PATTERN = Constant.NORM_DATE_PATTERN;

    /**
     * 标准日期格式 FastDateFormat yyyy-MM-dd
     */
    public static final FastDateFormat NORM_DATE_FORMAT = FastDateFormat.getInstance(NORM_DATE_PATTERN);

    /**
     * 标准日期格式 FastDateFormat yyyy-MM-dd
     */
    public static final DateTimeFormatter NORM_DATE_FORMATTER = DateTimeFormatter.ofPattern(NORM_DATE_PATTERN);

    /**
     * 标准时间格式：HH:mm:ss
     */
    public static final String NORM_TIME_PATTERN = Constant.NORM_TIME_PATTERN;
    /**
     * 标准时间格式 FastDateFormat HH:mm:ss
     */
    public static final FastDateFormat NORM_TIME_FORMAT = FastDateFormat.getInstance(NORM_TIME_PATTERN);

    /**
     * 标准日期时间格式，精确到分：yyyy-MM-dd HH:mm
     */
    public static final String NORM_DATETIME_MINUTE_PATTERN = Constant.NORM_DATETIME_MINUTE_PATTERN;

    /**
     * 标准日期时间格式，精确到分 FastDateFormat yyyy-MM-dd HH:mm
     */
    public static final FastDateFormat NORM_DATETIME_MINUTE_FORMAT = FastDateFormat.getInstance(NORM_DATETIME_MINUTE_PATTERN);

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final String NORM_DATETIME_PATTERN = Constant.NORM_DATETIME_PATTERN;

    /**
     * 标准日期时间格式，精确到秒 FastDateFormat yyyy-MM-dd HH:mm:ss
     */
    public static final FastDateFormat NORM_DATETIME_FORMAT = FastDateFormat.getInstance(NORM_DATETIME_PATTERN);

    /**
     * 标准日期时间格式，精确到秒 FastDateFormat yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter NORM_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN);

    /**
     * 标准日期格式：yyyyMMddHHmmss
     */
    public static final String PURE_DATETIME_PATTERN = Constant.PURE_DATETIME_PATTERN;

    /**
     * 标准日期格式 FastDateFormat：yyyyMMddHHmmss
     */
    public static final FastDateFormat PURE_DATETIME_FORMAT = FastDateFormat.getInstance(PURE_DATETIME_PATTERN);

    /**
     * 标准日期格式：yyyyMMddHHmmssSSS
     */
    public static final String PURE_DATETIME_MS_PATTERN = Constant.PURE_DATETIME_MS_PATTERN;

    /**
     * 标准日期格式 FastDateFormat：yyyyMMddHHmmssSSS
     */
    public static final FastDateFormat PURE_DATETIME_MS_FORMAT = FastDateFormat.getInstance(PURE_DATETIME_MS_PATTERN);

    /**
     * 标准日期格式：yyyyMMdd
     */
    public static final String PURE_DATE_PATTERN = Constant.PURE_DATE_PATTERN;

    /**
     * 标准日期格式 FastDateFormat：yyyyMMdd
     */
    public static final FastDateFormat PURE_DATE_FORMAT = FastDateFormat.getInstance(PURE_DATE_PATTERN);

    /**
     * 标准日期格式：HHmmss
     */
    public static final String PURE_TIME_PATTERN = Constant.PURE_TIME_PATTERN;

    /**
     * 标准日期格式 FastDateFormat：HHmmss
     */
    public static final FastDateFormat PURE_TIME_FORMAT = FastDateFormat.getInstance(PURE_TIME_PATTERN);

    /**
     * 标准日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String NORM_DATETIME_MS_PATTERN = Constant.NORM_DATETIME_MS_PATTERN;

    /**
     * 标准日期时间格式，精确到毫秒 FastDateFormat：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final FastDateFormat NORM_DATETIME_MS_FORMAT = FastDateFormat.getInstance(NORM_DATETIME_MS_PATTERN);

    /**
     * ISO8601日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss,SSS
     */
    public static final String ISO8601_PATTERN = Constant.ISO8601_PATTERN;

    /**
     * ISO8601日期时间格式，精确到毫秒 FastDateFormat：yyyy-MM-dd HH:mm:ss,SSS
     */
    public static final FastDateFormat ISO8601_FORMAT = FastDateFormat.getInstance(ISO8601_PATTERN);

    /**
     * 标准日期格式：yyyy年MM月dd日
     */
    public static final String CHINESE_DATE_PATTERN = Constant.CHINESE_DATE_PATTERN;

    /**
     * 标准日期格式 FastDateFormat：yyyy年MM月dd日
     */
    public static final FastDateFormat CHINESE_DATE_FORMAT = FastDateFormat.getInstance(CHINESE_DATE_PATTERN);

    /**
     * 标准日期格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String CHINESE_DATE_TIME_PATTERN = Constant.CHINESE_DATE_TIME_PATTERN;

    /**
     * 标准日期格式 FastDateFormat：yyyy年MM月dd日HH时mm分ss秒
     */
    public static final FastDateFormat CHINESE_DATE_TIME_FORMAT = FastDateFormat.getInstance(CHINESE_DATE_TIME_PATTERN);

    /**
     * HTTP头中日期时间格式：EEE, dd MMM yyyy HH:mm:ss z
     */
    public static final String HTTP_DATETIME_PATTERN = Constant.HTTP_DATETIME_PATTERN;
    /**
     * HTTP头中日期时间格式 FastDateFormat EEE, dd MMM yyyy HH:mm:ss z
     */
    public static final FastDateFormat HTTP_DATETIME_FORMAT = FastDateFormat.getInstance(HTTP_DATETIME_PATTERN, TimeZone.getTimeZone(Constant.GMT), Locale.US);

    /**
     * JDK中日期时间格式：EEE MMM dd HH:mm:ss zzz yyyy
     */
    public static final String JDK_DATETIME_PATTERN = Constant.JDK_DATETIME_PATTERN;
    /**
     * JDK中日期时间格式 FastDateFormat EEE MMM dd HH:mm:ss zzz yyyy
     */
    public static final FastDateFormat JDK_DATETIME_FORMAT = FastDateFormat.getInstance(JDK_DATETIME_PATTERN, Locale.US);

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss
     */
    public static final String UTC_SIMPLE_PATTERN = Constant.UTC_SIMPLE_PATTERN;
    /**
     * UTC时间 FastDateFormat yyyy-MM-dd'T'HH:mm:ss
     */
    public static final FastDateFormat UTC_SIMPLE_FORMAT = FastDateFormat.getInstance(UTC_SIMPLE_PATTERN, TimeZone.getTimeZone(Constant.UTC));

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    public static final String UTC_SIMPLE_MS_PATTERN = Constant.UTC_SIMPLE_MS_PATTERN;
    /**
     * UTC时间 FastDateFormat yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    public static final FastDateFormat UTC_SIMPLE_MS_FORMAT = FastDateFormat.getInstance(UTC_SIMPLE_MS_PATTERN, TimeZone.getTimeZone(Constant.UTC));

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss'Z'
     */
    public static final String UTC_PATTERN = Constant.UTC_PATTERN;
    /**
     * UTC时间 FastDateFormat yyyy-MM-dd'T'HH:mm:ss'Z'
     */
    public static final FastDateFormat UTC_FORMAT = FastDateFormat.getInstance(UTC_PATTERN, TimeZone.getTimeZone(Constant.UTC));

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ssZ
     */
    public static final String UTC_WITH_ZONE_OFFSET_PATTERN = Constant.UTC_WITH_ZONE_OFFSET_PATTERN;
    /**
     * UTC时间 FastDateFormat yyyy-MM-dd'T'HH:mm:ssZ
     */
    public static final FastDateFormat UTC_WITH_ZONE_OFFSET_FORMAT = FastDateFormat.getInstance(UTC_WITH_ZONE_OFFSET_PATTERN, TimeZone.getTimeZone(Constant.UTC));

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    public static final String UTC_MS_PATTERN = Constant.UTC_MS_PATTERN;
    /**
     * UTC时间 FastDateFormat yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    public static final FastDateFormat UTC_MS_FORMAT = FastDateFormat.getInstance(UTC_MS_PATTERN, TimeZone.getTimeZone(Constant.UTC));

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ssZ
     */
    public static final String UTC_MS_WITH_ZONE_OFFSET_PATTERN = Constant.UTC_MS_WITH_ZONE_OFFSET_PATTERN;
    /**
     * UTC时间 FastDateFormat yyyy-MM-dd'T'HH:mm:ssZ
     */
    public static final FastDateFormat UTC_MS_WITH_ZONE_OFFSET_FORMAT = FastDateFormat.getInstance(UTC_MS_WITH_ZONE_OFFSET_PATTERN, TimeZone.getTimeZone(Constant.UTC));

}
