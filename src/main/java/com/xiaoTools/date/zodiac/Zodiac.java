package com.xiaoTools.date.zodiac;

import com.xiaoTools.date.month.Month;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.dateUtil.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * [星座](constellation)
 * @description: zh - 星座
 * @description: en - constellation
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/24 4:36 下午
*/
public class Zodiac {
    /**
     * 星座分隔时间日
     */
    private static final int[] DAY_ARR = new int[] { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };

    private static final String[] ZODIACS = new String[] { "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座" };
    private static final String[] CHINESE_ZODIACS = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };

    /*计算星座-----------------------------------------------------------get Zodiac*/

    /**
     * [通过生日计算星座](Calculate constellation by birthday)
     * @description: zh - 通过生日计算星座
     * @description: en - Calculate constellation by birthday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 4:41 下午
     * @param date: 出生日期
     * @return java.lang.String
    */
    public static String getZodiac(Date date) {
        return getZodiac(DateUtil.calendar(date));
    }

    /**
     * [通过生日计算星座](Calculate constellation by birthday)
     * @description: zh - 通过生日计算星座
     * @description: en - Calculate constellation by birthday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 4:42 下午
     * @param calendar: 出生日期
     * @return java.lang.String
    */
    public static String getZodiac(Calendar calendar) {
        return Constant.NULL == calendar ? Constant.STRING_NULL : getZodiac(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * [通过生日计算星座](Calculate constellation by birthday)
     * @description: zh - 通过生日计算星座
     * @description: en - Calculate constellation by birthday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 4:56 下午
     * @param month: 月，从0开始计数
     * @param day: 天
     * @return java.lang.String
    */
    public static String getZodiac(Month month, int day) {
        return getZodiac(month.getValue(), day);
    }

    /**
     * [通过生日计算星座](Calculate constellation by birthday)
     * @description: zh - 通过生日计算星座
     * @description: en - Calculate constellation by birthday
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 4:56 下午
     * @param month: 月，从0开始计数
     * @param day: 天
     * @return java.lang.String
    */
    public static String getZodiac(int month, int day) {
        // 在分隔日前为前一个星座，否则为后一个星座
        return day < DAY_ARR[month] ? ZODIACS[month] : ZODIACS[month + Constant.ONE];
    }

    /*计算生肖-----------------------------------------------------------Chinese zodiac*/

    /**
     * [通过生日计算生肖，只计算1900年后出生的人](Calculate the zodiac by birthday, only people born after 1900 are counted)
     * @description: zh - 通过生日计算生肖，只计算1900年后出生的人
     * @description: en - Calculate the zodiac by birthday, only people born after 1900 are counted
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:13 下午
     * @param date: 出生日期（年需农历）
     * @return java.lang.String
    */
    public static String getChineseZodiac(Date date) {
        return getChineseZodiac(DateUtil.calendar(date));
    }

    /**
     * [通过生日计算生肖，只计算1900年后出生的人](Calculate the zodiac by birthday, only people born after 1900 are counted)
     * @description: zh - 通过生日计算生肖，只计算1900年后出生的人
     * @description: en - Calculate the zodiac by birthday, only people born after 1900 are counted
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:14 下午
     * @param calendar: 出生日期（年需农历）
     * @return java.lang.String
    */
    public static String getChineseZodiac(Calendar calendar) {
        return Constant.NULL == calendar ? Constant.STRING_NULL : getChineseZodiac(calendar.get(Calendar.YEAR));
    }

    /**
     * [计算生肖，只计算1900年后出生的人](Calculate the zodiac, only people born after 1900)
     * @description: zh - 计算生肖，只计算1900年后出生的人
     * @description: en - Calculate the zodiac, only people born after 1900
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 5:14 下午
     * @param year: 农历年
     * @return java.lang.String
    */
    public static String getChineseZodiac(int year) {
        if (year < Constant.ONE_NINE_DOUBLE_ZERO) {
            return null;
        }
        return CHINESE_ZODIACS[(year - Constant.ONE_NINE_DOUBLE_ZERO) % CHINESE_ZODIACS.length];
    }
}
