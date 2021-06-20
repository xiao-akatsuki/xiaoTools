package com.xiaoTools.date.datePattern;

import com.xiaoTools.core.regular.patternPool.PatternPool;
import com.xiaoTools.date.format.fastDateFormat.FastDateFormat;
import com.xiaoTools.lang.constant.Constant;

import java.time.format.DateTimeFormatter;
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

}
