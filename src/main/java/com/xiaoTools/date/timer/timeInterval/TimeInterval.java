package com.xiaoTools.date.timer.timeInterval;

import com.xiaoTools.date.timer.groupTimeInterval.GroupTimeInterval;
import com.xiaoTools.lang.constant.Constant;

import java.io.Serial;

public class TimeInterval extends GroupTimeInterval {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ID = Constant.EMPTY;

    /*构造-----------------------------------------------------------structure*/

    /**
     * [无参构造](Nonparametric structure)
     * @description: zh - 无参构造
     * @description: en - Nonparametric structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:08 下午
    */
    public TimeInterval() {
        this(Constant.FALSE);
    }

    /**
     * [有参](You Shen)
     * @description: zh - 有参
     * @description: en - You Shen
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:14 下午
     * @param isNano: 是否使用纳秒计数，false则使用毫秒
    */
    public TimeInterval(boolean isNano) {
        super(isNano);
        start();
    }

    public long start() {
        return start(DEFAULT_ID);
    }

    //间隔----------------------------------------------------------- Interval

    /**
     * [从开始到当前的间隔时间（毫秒数）](Interval from start to current (milliseconds))
     * @description: zh - 从开始到当前的间隔时间（毫秒数）
     * @description: en - Interval from start to current (milliseconds)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:51 下午
     * @return long
    */
    public long interval() {
        return interval(DEFAULT_ID);
    }

    /**
     * [从开始到当前的间隔时间（毫秒数），返回XX天XX小时XX分XX秒XX毫秒](The interval time (milliseconds) from the beginning to the current, which returns XX days XX hours XX minutes XX seconds XX milliseconds)
     * @description: zh - 从开始到当前的间隔时间（毫秒数），返回XX天XX小时XX分XX秒XX毫秒
     * @description: en - The interval time (milliseconds) from the beginning to the current, which returns XX days XX hours XX minutes XX seconds XX milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:54 下午
     * @return java.lang.String
    */
    public String intervalPretty() {
        return intervalPretty(DEFAULT_ID);
    }

    /**
     * [从开始到当前的间隔时间（毫秒数）](Interval from start to current (milliseconds))
     * @description: zh - 从开始到当前的间隔时间（毫秒数）
     * @description: en - Interval from start to current (milliseconds)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:56 下午
     * @return long
    */
    public long intervalMs() {
        return intervalMs(DEFAULT_ID);
    }

    /**
     * [从开始到当前的间隔秒数，取绝对值](The number of seconds between the beginning and the current, taking the absolute value)
     * @description: zh - 从开始到当前的间隔秒数，取绝对值
     * @description: en - The number of seconds between the beginning and the current, taking the absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:59 下午
     * @return long
    */
    public long intervalSecond() {
        return intervalSecond(DEFAULT_ID);
    }

    /**
     * [从开始到当前的间隔分钟数，取绝对值](The number of minutes between the beginning and the current, in absolute value)
     * @description: zh - 从开始到当前的间隔分钟数，取绝对值
     * @description: en - The number of minutes between the beginning and the current, in absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:59 下午
     * @return long
    */
    public long intervalMinute() {
        return intervalMinute(DEFAULT_ID);
    }

    /**
     * [从开始到当前的间隔小时数，取绝对值](The number of hours between the beginning and the current, in absolute value)
     * @description: zh -  从开始到当前的间隔小时数，取绝对值
     * @description: en -  The number of hours between the beginning and the current, in absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 5:00 下午
     * @return long
    */
    public long intervalHour() {
        return intervalHour(DEFAULT_ID);
    }

    /**
     * [从开始到当前的间隔天数，取绝对值](The number of days between the beginning and the current, in absolute value)
     * @description: zh - 从开始到当前的间隔天数，取绝对值
     * @description: en - The number of days between the beginning and the current, in absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:58 下午
     * @return long
    */
    public long intervalDay() {
        return intervalDay(DEFAULT_ID);
    }

    /**
     * [从开始到当前的间隔周数，取绝对值](The number of weeks between the beginning and the current, in absolute value)
     * @description: zh - 从开始到当前的间隔周数，取绝对值
     * @description: en - The number of weeks between the beginning and the current, in absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:57 下午
     * @return long
    */
    public long intervalWeek() {
        return intervalWeek(DEFAULT_ID);
    }
}
