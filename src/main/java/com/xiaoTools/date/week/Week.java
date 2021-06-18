package com.xiaoTools.date.week;

import com.xiaoTools.lang.constant.Constant;

import java.util.Calendar;

/**
 * [星期枚举](Week enumeration)
 * @description: zh - 星期枚举
 * @description: en - Week enumeration
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 12:10 下午
*/
public enum Week {

    /**
     * 周日
     */
    SUNDAY(Constant.SUNDAY),
    /**
     * 周一
     */
    MONDAY(Constant.MONDAY),
    /**
     * 周二
     */
    TUESDAY(Constant.TUESDAY),
    /**
     * 周三
     */
    WEDNESDAY(Constant.WEDNESDAY),
    /**
     * 周四
     */
    THURSDAY(Constant.THURSDAY),
    /**
     * 周五
     */
    FRIDAY(Constant.FRIDAY),
    /**
     * 周六
     */
    SATURDAY(Constant.SATURDAY);

    /**
     * 星期对应Calendar 中的Week值
     */
    private final int value;

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 12:10 下午
     * @param value: 星期对应Calendar 中的Week值
    */
    Week(int value) {
        this.value = value;
    }

    /**
     * [获得星期对应Calendar 中的Week值](Get the week value in calendar corresponding to the week)
     * @description: zh - 获得星期对应Calendar 中的Week值
     * @description: en - Get the week value in calendar corresponding to the week
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 12:11 下午
     * @return int
    */
    public int getValue() {
        return this.value;
    }

    /**
     * [转换为中文名](Convert to Chinese name)
     * @description: zh - 转换为中文名
     * @description: en - Convert to Chinese name
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 12:11 下午
     * @return java.lang.String
    */
    public String toChinese() {
        return toChinese(Constant.WEEK_NAME_PRE);
    }

    /**
     * [转换为中文名](Convert to Chinese name)
     * @description: zh - 转换为中文名
     * @description: en - Convert to Chinese name
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 12:11 下午
     * @param weekNamePre: 表示星期的前缀
     * @return java.lang.String
    */
    public String toChinese(String weekNamePre) {
        return switch (this) {
            case SUNDAY -> weekNamePre + Constant.WEEK_SUNDAY;
            case MONDAY -> weekNamePre + Constant.WEEK_ONE;
            case TUESDAY -> weekNamePre + Constant.WEEK_TWO;
            case WEDNESDAY -> weekNamePre + Constant.WEEK_THREE;
            case THURSDAY -> weekNamePre + Constant.WEEK_FOUR;
            case FRIDAY -> weekNamePre + Constant.WEEK_FIVE;
            case SATURDAY -> weekNamePre + Constant.WEEK_SIX;
            default -> Constant.STRING_NULL;
        };
    }

    /**
     * [将 Calendar星期相关值转换为Week枚举对象](Converts calendar week related values to week enumeration objects)
     * @description: zh - 将 Calendar星期相关值转换为Week枚举对象
     * @description: en - Converts calendar week related values to week enumeration objects
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 12:12 下午
     * @param calendarWeekIntValue: Calendar中关于Week的int值
     * @return com.xiaoTools.date.week.Week
    */
    public static Week of(int calendarWeekIntValue) {
        return switch (calendarWeekIntValue) {
            case Calendar.SUNDAY -> SUNDAY;
            case Calendar.MONDAY -> MONDAY;
            case Calendar.TUESDAY -> TUESDAY;
            case Calendar.WEDNESDAY -> WEDNESDAY;
            case Calendar.THURSDAY -> THURSDAY;
            case Calendar.FRIDAY -> FRIDAY;
            case Calendar.SATURDAY -> SATURDAY;
            default -> null;
        };
    }
}
