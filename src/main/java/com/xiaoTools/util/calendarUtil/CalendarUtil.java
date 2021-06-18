package com.xiaoTools.util.calendarUtil;

import com.xiaoTools.date.dateTime.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * [针对Calendar 对象封装工具类](Encapsulating tool classes for calendar objects)
 * @description: zh - 针对Calendar 对象封装工具类
 * @description: en - Encapsulating tool classes for calendar objects
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 11:22 上午
*/
public class CalendarUtil {

    /**
     * [创建Calendar对象，时间为默认时区的当前时间](Create a calendar object with the current time in the default time zone)
     * @description: zh - 创建Calendar对象，时间为默认时区的当前时间
     * @description: en - Create a calendar object with the current time in the default time zone
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 11:23 上午
     * @return java.util.Calendar
    */
    public static Calendar calendar() { return Calendar.getInstance(); }

    /**
     * [转换为Calendar对象](Convert to calendar object)
     * @description: zh - 转换为Calendar对象
     * @description: en - Convert to calendar object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:59 下午
     * @param date: 日期对象
     * @return java.util.Calendar
    */
    public static Calendar calendar(Date date) {
        if (date instanceof DateTime) {
            return ((DateTime) date).toCalendar();
        } else {
            return calendar(date.getTime());
        }
    }

    /**
     * [转换为Calendar对象](Convert to calendar object)
     * @description: zh - 转换为Calendar对象
     * @description: en - Convert to calendar object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 7:00 下午
     * @param millis: 时间戳
     * @return java.util.Calendar
    */
    public static Calendar calendar(long millis) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal;
    }
}
