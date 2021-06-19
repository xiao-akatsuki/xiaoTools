package com.xiaoTools.date.format.dateParser;

import com.xiaoTools.date.format.basic.DateBasic;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

/**
 * [日期解析接口](Date resolution interface)
 * @description: zh - 日期解析接口
 * @description: en - Date resolution interface
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 7:57 上午
*/
public interface DateParser extends DateBasic {

    /**
     * [将日期字符串解析并转换为 Date 对象](Parses and converts a date string to a date object)
     * @description: zh - 将日期字符串解析并转换为 Date 对象
     * @description: en - Parses and converts a date string to a date object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:07 上午
     * @param source: 日期字符串
     * @return java.util.Date
    */
    Date parse(String source) throws ParseException;

    /**
     * [将日期字符串解析并转换为 Date 对象](Parses and converts a date string to a date object)
     * @description: zh - 将日期字符串解析并转换为 Date 对象
     * @description: en - Parses and converts a date string to a date object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:06 上午
     * @param source: 日期字符串
     * @param pos: ParsePosition
     * @return java.util.Date
    */
    Date parse(String source, ParsePosition pos);

    /**
     * [根据给定格式转换日期字符串](Converts the date string according to the given format)
     * @description: zh - 根据给定格式转换日期字符串
     * @description: en - Converts the date string according to the given format
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:05 上午
     * @param source: 被转换的日期字符串
     * @param pos: 定义开始转换的位置
     * @param calendar: 要在其中设置已解析字段的日历。
     * @return boolean
    */
    boolean parse(String source, ParsePosition pos, Calendar calendar);

    /**
     * [将日期字符串解析并转换为 Date 对象](Parses and converts a date string to a date object)
     * @description: zh - 将日期字符串解析并转换为 Date 对象
     * @description: en - Parses and converts a date string to a date object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:02 上午
     * @param source: 解析其开头。
     * @return java.lang.Object
    */
    Object parseObject(String source) throws ParseException;

    /**
     * [根据 ParsePosition 给定将日期字符串解析并转换为 Date 对象](Parse and convert date string to Date object according to ParsePosition)
     * @description: zh - 根据 ParsePosition 给定将日期字符串解析并转换为 Date 对象
     * @description: en - Parse and convert date string to Date object according to ParsePosition
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 8:08 上午
     * @param source: 解析其开头。
     * @param pos: 解析位置
     * @return java.lang.Object
    */
    Object parseObject(String source, ParsePosition pos);
}
