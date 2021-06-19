package com.xiaoTools.date.format.datePrinter;

import com.xiaoTools.date.format.basic.DateBasic;

import java.util.Calendar;
import java.util.Date;

/**
 * [日期格式化输出接口](Date format output interface)
 * @description: zh - 日期格式化输出接口
 * @description: en - Date format output interface
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 9:11 上午
*/
public interface DatePrinter extends DateBasic {

    /**
     * [格式化日期表示的毫秒数](The number of milliseconds represented by the formatted date)
     * @description: zh - 格式化日期表示的毫秒数
     * @description: en - The number of milliseconds represented by the formatted date
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:19 上午
     * @param millis: 日期毫秒数
     * @return java.lang.String
    */
    String format(long millis);

    /**
     * [使用 GregorianCalendar 格式化 Date](Format date with Gregorian calendar)
     * @description: zh - 使用 GregorianCalendar 格式化 Date
     * @description: en - Format date with Gregorian calendar
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:19 上午
     * @param date: 日期
     * @return java.lang.String
    */
    String format(Date date);

    /**
     * [格式化 Calendar](Formats a Calendar object)
     * @description: zh - 格式化 Calendar
     * @description: en - Formats a Calendar object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:17 上午
     * @param calendar:  Calendar
     * @return java.lang.String
    */
    String format(Calendar calendar);

    /**
     *
     * @description: zh - 将毫秒长的值格式化为提供的可追加项。
     * @description: en - Formats a millisecond long value into the supplied Appendable.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:16 上午
     * @param millis: 要格式化的毫秒值
     * @param buf: 要格式化为的缓冲区
     * @return B
    */
    <B extends Appendable> B format(long millis, B buf);

    /**
     * [使用GregorianCalendar将aDate对象格式化为提供的Appendable。](Formats aDate object into the supplied Appendable using a GregorianCalendar.)
     * @description: zh - 使用GregorianCalendar将aDate对象格式化为提供的Appendable。
     * @description: en - Formats aDate object into the supplied Appendable using a GregorianCalendar.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:13 上午
     * @param date: 要格式化的日期
     * @param buf: 要格式化为的缓冲区
     * @return B
    */
    <B extends Appendable> B format(Date date, B buf);

    /**
     * [将日历对象格式化为所提供的可追加项。](Formats a Calendar object into the supplied Appendable.)
     * @description: zh - 将日历对象格式化为所提供的可追加项。
     * @description: en - Formats a Calendar object into the supplied Appendable.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:12 上午
     * @param calendar: 要格式化的日历
     * @param buf: 要格式化为的缓冲区
     * @return B
    */
    <B extends Appendable> B format(Calendar calendar, B buf);
}
