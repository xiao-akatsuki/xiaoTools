package com.xiaoTools.date.dateField;

import com.xiaoTools.lang.constant.Constant;

import java.util.Calendar;

/**
 * [日期各个部分的枚举](Enumeration of parts of the date)
 * @description: zh - 日期各个部分的枚举
 * @description: en - Enumeration of parts of the date
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 2:28 下午
*/
public enum DateField {

    /*枚举对象--------------------------------------------------------------------enumerable object */

    /**
     * 世纪
     */
    ERA(Constant.ERA),
    /**
     * 年
     */
    YEAR(Constant.YEAR),
    /**
     * 月
     */
    MONTH(Constant.MONTH),
    /**
     * 一年中第几周
     */
    WEEK_OF_YEAR(Constant.WEEK_OF_YEAR),
    /**
     * 一月中第几周
     */
    WEEK_OF_MONTH(Constant.WEEK_OF_MONTH),
    /**
     * 一月中的第几天
     */
    DAY_OF_MONTH(Constant.DAY_OF_MONTH),
    /**
     * 一年中的第几天
     */
    DAY_OF_YEAR(Constant.DAY_OF_YEAR),
    /**
     * 周几，1表示周日，2表示周一
     */
    DAY_OF_WEEK(Constant.DAY_OF_WEEK),
    /**
     * 天所在的周是这个月的第几周
     */
    DAY_OF_WEEK_IN_MONTH(Constant.DAY_OF_WEEK_IN_MONTH),
    /**
     * 上午或者下午
     */
    AM_PM(Constant.AM_PM),
    /**
     * 小时，用于12小时制
     */
    HOUR(Constant.HOUR),
    /**
     * 小时，用于24小时制
     */
    HOUR_OF_DAY(Constant.HOUR_OF_DAY),
    /**
     * 分钟
     */
    MINUTE(Constant.MINUTE),
    /**
     * 秒
     */
    SECOND(Constant.SECOND),
    /**
     * 毫秒
     */
    MILLISECOND(Constant.MILLISECOND);

    private final int value;

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:32 下午
     * @param value: 传入的值
    */
    DateField(int value) {
        this.value = value;
    }

    /**
     * [传递的值](Value passed)
     * @description: zh - 传递的值
     * @description: en - Value passed
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:33 下午
     * @return int
    */
    public int getValue() {
        return this.value;
    }

    /**
     * [将 Calendar相关值转换为DatePart枚举对象](Converts calendar related values to DatePart enumeration objects)
     * @description: zh - 将 Calendar相关值转换为DatePart枚举对象
     * @description: en - Converts calendar related values to DatePart enumeration objects
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 2:33 下午
     * @param calendarPartIntValue: Calendar中关于Week的int值
     * @return com.xiaoTools.date.dateField.DateField
    */
    public static DateField of(int calendarPartIntValue) {
        return switch (calendarPartIntValue) {
            case Calendar.ERA -> ERA;
            case Calendar.YEAR -> YEAR;
            case Calendar.MONTH -> MONTH;
            case Calendar.WEEK_OF_YEAR -> WEEK_OF_YEAR;
            case Calendar.WEEK_OF_MONTH -> WEEK_OF_MONTH;
            case Calendar.DAY_OF_MONTH -> DAY_OF_MONTH;
            case Calendar.DAY_OF_YEAR -> DAY_OF_YEAR;
            case Calendar.DAY_OF_WEEK -> DAY_OF_WEEK;
            case Calendar.DAY_OF_WEEK_IN_MONTH -> DAY_OF_WEEK_IN_MONTH;
            case Calendar.AM_PM -> AM_PM;
            case Calendar.HOUR -> HOUR;
            case Calendar.HOUR_OF_DAY -> HOUR_OF_DAY;
            case Calendar.MINUTE -> MINUTE;
            case Calendar.SECOND -> SECOND;
            case Calendar.MILLISECOND -> MILLISECOND;
            default -> null;
        };
    }
}
