package com.xiaoTools.util.temporalAccessorUtil;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

/**
 * [Temporal 工具类封装](Encapsulation of temporary tool class)
 * @description: zh - Temporal 工具类封装
 * @description: en - Encapsulation of temporary tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 11:02 上午
*/
public class TemporalUtil {

    /**
     * [获取两个日期的差，如果结束时间早于开始时间，获取结果为负。](Gets the difference between two dates. If the end time is earlier than the start time, the result is negative.)
     * @description: zh - 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * @description: en - Gets the difference between two dates. If the end time is earlier than the start time, the result is negative.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 11:03 上午
     * @param startTimeInclude: 开始时间（包含）
     * @param endTimeExclude: 结束时间（不包含）
     * @return java.time.Duration
    */
    public static Duration between(Temporal startTimeInclude, Temporal endTimeExclude) {
        return Duration.between(startTimeInclude, endTimeExclude);
    }

    /**
     * [获取两个日期的差，如果结束时间早于开始时间，获取结果为负。](Gets the difference between two dates. If the end time is earlier than the start time, the result is negative.)
     * @description: zh - 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * @description: en - Gets the difference between two dates. If the end time is earlier than the start time, the result is negative.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 11:02 上午
     * @param startTimeInclude: 开始时间（包括）
     * @param endTimeExclude: 结束时间（不包括）
     * @param unit: 时间差单位
     * @return long
    */
    public static long between(Temporal startTimeInclude, Temporal endTimeExclude, ChronoUnit unit) {
        return unit.between(startTimeInclude, endTimeExclude);
    }

}
