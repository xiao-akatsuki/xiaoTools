package com.xiaoTools.date.dateBetween;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.date.betweenFormatter.BetweenFormatter;
import com.xiaoTools.date.dateUnit.DateUnit;
import com.xiaoTools.date.leven.Level;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.dateUtil.DateUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * [日期间隔](Date interval)
 * @description: zh - 日期间隔
 * @description: en - Date interval
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 4:14 下午
*/
public class DateBetween implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 开始日期
     */
    private final Date begin;

    /**
     * 结束日期
     */
    private final Date end;

    /**
     * [在前的日期做为起始时间，在后的做为结束时间，间隔只保留绝对值正数](The previous date is used as the start time, and the later date is used as the end time. Only positive absolute value is reserved for the interval)
     * @description: zh - 在前的日期做为起始时间，在后的做为结束时间，间隔只保留绝对值正数
     * @description: en - The previous date is used as the start time, and the later date is used as the end time. Only positive absolute value is reserved for the interval
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:18 下午
     * @param begin: 起始时间
     * @param end: 结束时间
    */
    public DateBetween(Date begin, Date end) {
        this(begin, end, true);
    }

    /**
     * [在前的日期做为起始时间，在后的做为结束时间](The previous date is used as the start time, and the later date is used as the end time)
     * @description: zh - 在前的日期做为起始时间，在后的做为结束时间
     * @description: en - The previous date is used as the start time, and the later date is used as the end time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:19 下午
     * @param begin: 起始时间
     * @param end: 结束时间
     * @param isAbs: 日期间隔是否只保留绝对值正数
    */
    public DateBetween(Date begin, Date end, boolean isAbs) {
        Assertion.notNull(begin, "Begin date is null !");
        Assertion.notNull(end, "End date is null !");
        if (isAbs && begin.after(end)) {
            // 间隔只为正数的情况下，如果开始日期晚于结束日期，置换之
            this.begin = end;
            this.end = begin;
        } else {
            this.begin = begin;
            this.end = end;
        }
    }

    /**
     * [创建函数,在前的日期做为起始时间，在后的做为结束时间，间隔只保留绝对值正数](To create a function, the previous date is used as the start time, and the later as the end time. Only the positive absolute value of the interval is retained)
     * @description: zh - 创建函数,在前的日期做为起始时间，在后的做为结束时间，间隔只保留绝对值正数
     * @description: en - To create a function, the previous date is used as the start time, and the later as the end time. Only the positive absolute value of the interval is retained
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:20 下午
     * @param begin: 起始时间
     * @param end: 结束时间
     * @return com.xiaoTools.date.dateBetween.DateBetween
    */
    public static DateBetween create(Date begin, Date end) {
        return new DateBetween(begin, end);
    }

    /**
     * [创建,在前的日期做为起始时间，在后的做为结束时间，间隔只保留绝对值正数](When creating, the previous date is used as the start time, and the later date is used as the end time. Only the positive absolute value of the interval is retained)
     * @description: zh - 创建,在前的日期做为起始时间，在后的做为结束时间，间隔只保留绝对值正数
     * @description: en - When creating, the previous date is used as the start time, and the later date is used as the end time. Only the positive absolute value of the interval is retained
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:26 下午
     * @param begin: 起始时间
     * @param end: 结束时间
     * @param isAbs: 日期间隔是否只保留绝对值正数
     * @return com.xiaoTools.date.dateBetween.DateBetween
    */
    public static DateBetween create(Date begin, Date end, boolean isAbs) {
        return new DateBetween(begin, end, isAbs);
    }

    /**
     * [判断两个日期相差的时长](Judge the time difference between two dates)
     * @description: zh - 判断两个日期相差的时长
     * @description: en - Judge the time difference between two dates
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:31 下午
     * @param unit: 相差的单位：相差 天DateUnit.DAY、小时DateUnit.HOUR 等
     * @return long
    */
    public long between(DateUnit unit) {
        long diff = end.getTime() - begin.getTime();
        return diff / unit.getMillis();
    }

    /**
     * [计算两个日期相差月数](Calculate the number of months between two dates)
     * @description: zh - 计算两个日期相差月数
     * @description: en - Calculate the number of months between two dates
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:32 下午
     * @param isReset: 是否重置时间为起始时间（重置天时分秒）
     * @return long
    */
    public long betweenMonth(boolean isReset) {
        final Calendar beginCal = DateUtil.calendar(begin);
        final Calendar endCal = DateUtil.calendar(end);
        final int betweenYear = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
        final int betweenMonthOfYear = endCal.get(Calendar.MONTH) - beginCal.get(Calendar.MONTH);
        int result = betweenYear * Constant.TWELVE + betweenMonthOfYear;
        if (!isReset) {
            endCal.set(Calendar.YEAR, beginCal.get(Calendar.YEAR));
            endCal.set(Calendar.MONTH, beginCal.get(Calendar.MONTH));
            long between = endCal.getTimeInMillis() - beginCal.getTimeInMillis();
            if (between < Constant.ZERO) {
                return result - Constant.ONE;
            }
        }
        return result;
    }

    /**
     * [计算两个日期相差年数](Calculate the number of years between two dates)
     * @description: zh - 计算两个日期相差年数
     * @description: en - Calculate the number of years between two dates
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:45 下午
     * @param isReset:  是否重置时间为起始时间（重置月天时分秒）
     * @return long
    */
    public long betweenYear(boolean isReset) {
        final Calendar beginCal = DateUtil.calendar(begin);
        final Calendar endCal = DateUtil.calendar(end);
        int result = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
        if (!isReset) {
            // 考虑闰年的2月情况
            if(Calendar.FEBRUARY == beginCal.get(Calendar.MONTH) && Calendar.FEBRUARY == endCal.get(Calendar.MONTH)){
                if(beginCal.get(Calendar.DAY_OF_MONTH) == beginCal.getActualMaximum(Calendar.DAY_OF_MONTH)
                        && endCal.get(Calendar.DAY_OF_MONTH) == endCal.getActualMaximum(Calendar.DAY_OF_MONTH)){
                    // 两个日期都位于2月的最后一天，此时月数按照相等对待，此时都设置为1号
                    beginCal.set(Calendar.DAY_OF_MONTH, Constant.ONE);
                    endCal.set(Calendar.DAY_OF_MONTH, Constant.ONE);
                }
            }
            endCal.set(Calendar.YEAR, beginCal.get(Calendar.YEAR));
            long between = endCal.getTimeInMillis() - beginCal.getTimeInMillis();
            if (between < Constant.ZERO) {
                return result - Constant.ONE;
            }
        }
        return result;
    }

    /**
     * [格式化时间差](Format time difference)
     * @description: zh - 格式化时间差
     * @description: en - Format time difference
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:54 下午
     * @param level: 级别
     * @return java.lang.String
    */
    public String toString(Level level) {
        return DateUtil.formatBetween(between(DateUnit.MS), level);
    }
    
    /**
     * [重写输出语句](Rewrite output statement)
     * @description: zh - 重写输出语句 
     * @description: en - Rewrite output statement 
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 4:53 下午
     * @return java.lang.String
    */
    @Override
    public String toString() {
        return toString(Level.MILLISECOND);
    }
}
