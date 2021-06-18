package com.xiaoTools.date.month;

import com.xiaoTools.lang.constant.Constant;

import java.util.Calendar;

/**
 * [月份枚举](Month enumeration)
 * @description: zh - 月份枚举
 * @description: en - Month enumeration
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 3:18 下午
*/
public enum Month {
    /**
     * 一月
     */
    JANUARY(Constant.JANUARY),
    /**
     * 二月
     */
    FEBRUARY(Constant.FEBRUARY),
    /**
     * 三月
     */
    MARCH(Constant.MARCH),
    /**
     * 四月
     */
    APRIL(Constant.APRIL),
    /**
     * 五月
     */
    MAY(Constant.MAY),
    /**
     * 六月
     */
    JUNE(Constant.JUNE),
    /**
     * 七月
     */
    JULY(Constant.JULY),
    /**
     * 八月
     */
    AUGUST(Constant.AUGUST),
    /**
     * 九月
     */
    SEPTEMBER(Constant.SEPTEMBER),
    /**
     * 十月
     */
    OCTOBER(Constant.OCTOBER),
    /**
     * 十一月
     */
    NOVEMBER(Constant.NOVEMBER),
    /**
     * 十二月
     */
    DECEMBER(Constant.DECEMBER),
    /**
     * 十三月，仅用于农历
     */
    UNDECIMBER(Constant.UNDECIMBER);

    private static final int[] DAYS_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, -1};

    /**
     * 对应值，见Calendar
     */
    private final int value;

    Month(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * [获取此月份最后一天的值，不支持的月份（例如UNDECIMBER）返回-1](Gets the value of the last day of the month, and - 1 is returned for unsupported months (for example, undercomber))
     * @description: zh - 获取此月份最后一天的值，不支持的月份（例如UNDECIMBER）返回-1
     * @description: en - Gets the value of the last day of the month, and - 1 is returned for unsupported months (for example, undercomber)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:30 下午
     * @param isLeapYear: 是否闰年
     * @return int
    */
    public int getLastDay(boolean isLeapYear) {
        return getLastDay(this.value, isLeapYear);
    }

    /**
     * [将 Calendar月份相关值转换为Month枚举对象](Converts calendar month related values to month enumeration objects)
     * @description: zh - 将 Calendar月份相关值转换为Month枚举对象
     * @description: en - Converts calendar month related values to month enumeration objects
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:30 下午
     * @param calendarMonthIntValue: Calendar中关于Month的int值
     * @return com.xiaoTools.date.month.Month
    */
    public static Month of(int calendarMonthIntValue) {
        return switch (calendarMonthIntValue) {
            case Calendar.JANUARY -> JANUARY;
            case Calendar.FEBRUARY -> FEBRUARY;
            case Calendar.MARCH -> MARCH;
            case Calendar.APRIL -> APRIL;
            case Calendar.MAY -> MAY;
            case Calendar.JUNE -> JUNE;
            case Calendar.JULY -> JULY;
            case Calendar.AUGUST -> AUGUST;
            case Calendar.SEPTEMBER -> SEPTEMBER;
            case Calendar.OCTOBER -> OCTOBER;
            case Calendar.NOVEMBER -> NOVEMBER;
            case Calendar.DECEMBER -> DECEMBER;
            case Calendar.UNDECIMBER -> UNDECIMBER;
            default -> null;
        };
    }

    /**
     * [获得指定月的最后一天](Gets the last day of the specified month)
     * @description: zh - 获得指定月的最后一天
     * @description: en - Gets the last day of the specified month
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 3:31 下午
     * @param month: 月份，从0开始
     * @param isLeapYear: 是否为闰年，闰年只对二月有影响
     * @return int
    */
    public static int getLastDay(int month, boolean isLeapYear){
        int lastDay = DAYS_OF_MONTH[month];
        if (isLeapYear && Calendar.FEBRUARY == month){
            // 二月
            lastDay += Constant.ONE;
        }
        return lastDay;
    }
}
