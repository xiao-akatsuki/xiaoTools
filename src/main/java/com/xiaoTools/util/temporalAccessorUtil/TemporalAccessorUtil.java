package com.xiaoTools.util.temporalAccessorUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.strUtil.StrUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * [TemporalAccessor 工具类封装](Encapsulation of TemporalAccessor tool class)
 * @description: zh - TemporalAccessor 工具类封装
 * @description: en - Encapsulation of TemporalAccessor tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 10:59 上午
*/
public class TemporalAccessorUtil extends TemporalUtil {

    /**
     * [安全获取时间的某个属性，属性不存在返回0](Get a property of the security time. The property does not exist. Return 0)
     * @description: zh - 安全获取时间的某个属性，属性不存在返回0
     * @description: en - Get a property of the security time. The property does not exist. Return 0
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 11:05 上午
     * @param temporalAccessor: 需要获取的时间对象
     * @param field: 需要获取的属性
     * @return int
    */
    public static int get(TemporalAccessor temporalAccessor, TemporalField field) {
        return temporalAccessor.isSupported(field) ? temporalAccessor.get(field) : (int)field.range().getMinimum();
    }

    /**
     * [格式化日期时间为指定格式](Format the date and time in the specified format)
     * @description: zh - 格式化日期时间为指定格式
     * @description: en - Format the date and time in the specified format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 11:09 上午
     * @param time: TemporalAccessor
     * @param formatter: 日期格式化器，预定义的格式见：DateTimeFormatter
     * @return java.lang.String
    */
    public static String format(TemporalAccessor time, DateTimeFormatter formatter) {
        if (Constant.NULL == time) { return Constant.STRING_NULL; }
        if(Constant.NULL == formatter){ formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; }
        try {
            return formatter.format(time);
        } catch (UnsupportedTemporalTypeException e){
            if(time instanceof LocalDate && e.getMessage().contains(Constant.STRING_HOUR_OF_DAY)){
                // 用户传入LocalDate，但是要求格式化带有时间部分，转换为LocalDateTime重试
                return formatter.format(((LocalDate) time).atStartOfDay());
            }else if(time instanceof LocalTime && e.getMessage().contains(Constant.STRING_YEAR_OF_ERA)){
                // 用户传入LocalTime，但是要求格式化带有日期部分，转换为LocalDateTime重试
                return formatter.format(((LocalTime) time).atDate(LocalDate.now()));
            }
            throw e;
        }
    }

    /**
     * [格式化日期时间为指定格式](Format the date and time in the specified format)
     * @description: zh - 格式化日期时间为指定格式
     * @description: en - Format the date and time in the specified format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 11:14 上午
     * @param time:  TemporalAccessor
     * @param format: 日期格式
     * @return java.lang.String
    */
    public static String format(TemporalAccessor time, String format) {
        if (Constant.NULL == time) { return Constant.STRING_NULL; }
        final DateTimeFormatter formatter = StrUtil.isBlank(format) ? Constant.DATE_TIME_FORMATTER_NULL : DateTimeFormatter.ofPattern(format);
        return format(time, formatter);
    }

    /**
     * [TemporalAccessor to timestamp (milliseconds from 1970-01-01t00:00:00z)](TemporalAccessor 转换为 时间戳（从1970-01-01T00:00:00Z开始的毫秒数)
     * @description: zh - TemporalAccessor 转换为 时间戳（从1970-01-01T00:00:00Z开始的毫秒数
     * @description: en - TemporalAccessor to timestamp (milliseconds from 1970-01-01t00:00:00z)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 11:40 上午
     * @param temporalAccessor: Date对象
     * @return long
    */
    public static long toEpochMilli(TemporalAccessor temporalAccessor) {
        return toInstant(temporalAccessor).toEpochMilli();
    }

    /**
     * [TemporalAccessor 转换为 Instant 对象](Convert TemporalAccessor to Instant object)
     * @description: zh - TemporalAccessor 转换为 Instant 对象
     * @description: en - Convert TemporalAccessor to Instant object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 12:09 下午
     * @param temporalAccessor: Date对象
     * @return java.time.Instant
    */
    public static Instant toInstant(TemporalAccessor temporalAccessor) {
        if (Constant.NULL == temporalAccessor) { return Constant.INSTANT_NULL; }
        Instant result;
        if (temporalAccessor instanceof Instant) {
            result = (Instant) temporalAccessor;
        } else if (temporalAccessor instanceof LocalDateTime) {
            result = ((LocalDateTime) temporalAccessor).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof ZonedDateTime) {
            result = ((ZonedDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof OffsetDateTime) {
            result = ((OffsetDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof LocalDate) {
            result = ((LocalDate) temporalAccessor).atStartOfDay(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof LocalTime) {
            // 指定本地时间转换 为Instant，取当天日期
            result = ((LocalTime) temporalAccessor).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof OffsetTime) {
            // 指定本地时间转换 为Instant，取当天日期
            result = ((OffsetTime) temporalAccessor).atDate(LocalDate.now()).toInstant();
        } else {
            result = Instant.from(temporalAccessor);
        }

        return result;
    }
}
