package com.xiaoTools.date.timer.groupTimeInterval;

import com.xiaoTools.date.dateUnit.DateUnit;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.dateUtil.DateUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * [分组计时器](Group timer)
 * @description: zh - 分组计时器
 * @description: en - Group timer
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/23 3:44 下午
*/
public class GroupTimeInterval implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final boolean isNano;

    protected final Map<String, Long> groupMap;

    /**
     * [有参构造](Parametric structure)
     * @description: zh - 有参构造
     * @description: en - Parametric structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:45 下午
     * @param isNano: 是否使用纳秒计数，false则使用毫秒
    */
    public GroupTimeInterval(boolean isNano) {
        this.isNano = isNano;
        groupMap = new ConcurrentHashMap<>();
    }

    /**
     * [获取时间的毫秒或纳秒数，纳秒非时间戳](Gets the number of milliseconds or nanoseconds of the time. Nanoseconds are not timestamps)
     * @description: zh - 获取时间的毫秒或纳秒数，纳秒非时间戳
     * @description: en - Gets the number of milliseconds or nanoseconds of the time. Nanoseconds are not timestamps
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:57 下午
     * @return long
    */
    private long getTime() {
        return this.isNano ? System.nanoTime() : System.currentTimeMillis();
    }

    /**
     * [清空所有定时记录](Clear all timing records)
     * @description: zh - 清空所有定时记录
     * @description: en - Clear all timing records
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:47 下午
     * @return com.xiaoTools.date.timer.groupTimeInterval.GroupTimeInterval
    */
    public GroupTimeInterval clear(){
        this.groupMap.clear();
        return this;
    }

    /**
     * [开始计时并返回当前时间](Start timing and return to current time)
     * @description: zh - 开始计时并返回当前时间
     * @description: en - Start timing and return to current time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:48 下午
     * @param id: 分组ID
     * @return long
    */
    public long start(String id) {
        final long time = getTime();
        this.groupMap.put(id, time);
        return time;
    }

    /**
     * [重新计时并返回从开始到当前的持续时间秒,如果此分组下没有记录，则返回0;](Re time and return the duration seconds from the beginning to the current. If there is no record under this group, return 0;)
     * @description: zh - 重新计时并返回从开始到当前的持续时间秒,如果此分组下没有记录，则返回0;
     * @description: en - Re time and return the duration seconds from the beginning to the current. If there is no record under this group, return 0;
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:49 下午
     * @param id: 分组ID
     * @return long
    */
    public long intervalRestart(String id) {
        final long now = getTime();
        return now - ObjectUtil.defaultIfNull(this.groupMap.put(id, now), now);
    }

    /**
     * [从开始到当前的间隔时间（毫秒数）](Interval from start to current (milliseconds))
     * @description: zh - 从开始到当前的间隔时间（毫秒数）
     * @description: en - Interval from start to current (milliseconds)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:50 下午
     * @param id: 分组ID
     * @return long
    */
    public long interval(String id) {
        final Long lastTime = this.groupMap.get(id);
        return Constant.NULL == lastTime ? Constant.ZERO : getTime() - lastTime;
    }

    /**
     * [Interval time from start to current](Interval time from start to current)
     * @description: zh - 从开始到当前的间隔时间
     * @description: en - Interval time from start to current
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:54 下午
     * @param id: 分组ID
     * @param dateUnit: 时间单位
     * @return long
    */
    public long interval(String id, DateUnit dateUnit) {
        final long intervalMs = isNano ? interval(id) / 1000000L : interval(id);
        return DateUnit.MS == dateUnit ? intervalMs : intervalMs / dateUnit.getMillis();
    }

    /**
     * [从开始到当前的间隔时间（毫秒数）](Interval from start to current (milliseconds))
     * @description: zh - 从开始到当前的间隔时间（毫秒数）
     * @description: en - Interval from start to current (milliseconds)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 3:58 下午
     * @param id: 分组ID
     * @return long
    */
    public long intervalMs(String id) {
        return interval(id, DateUnit.MS);
    }

    /**
     * [从开始到当前的间隔秒数，取绝对值](The number of seconds between the beginning and the current, taking the absolute value)
     * @description: zh - 从开始到当前的间隔秒数，取绝对值
     * @description: en - The number of seconds between the beginning and the current, taking the absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:00 下午
     * @param id: 分组ID
     * @return long
    */
    public long intervalSecond(String id) {
        return interval(id, DateUnit.SECOND);
    }

    /**
     * [从开始到当前的间隔分钟数，取绝对值](The number of minutes between the beginning and the current, in absolute value)
     * @description: zh - 从开始到当前的间隔分钟数，取绝对值
     * @description: en - The number of minutes between the beginning and the current, in absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:00 下午
     * @param id: 分组ID
     * @return long
    */
    public long intervalMinute(String id) {
        return interval(id, DateUnit.MINUTE);
    }

    /**
     * [从开始到当前的间隔小时数，取绝对值](The number of hours between the beginning and the current, in absolute value)
     * @description: zh - 从开始到当前的间隔小时数，取绝对值
     * @description: en - The number of hours between the beginning and the current, in absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:01 下午
     * @param id: 分组ID
     * @return long
    */
    public long intervalHour(String id) {
        return interval(id, DateUnit.HOUR);
    }

    /**
     * [从开始到当前的间隔天数，取绝对值](The number of days between the beginning and the current, in absolute value)
     * @description: zh - 从开始到当前的间隔天数，取绝对值
     * @description: en - The number of days between the beginning and the current, in absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:02 下午
     * @param id: 分组ID
     * @return long
    */
    public long intervalDay(String id) {
        return interval(id, DateUnit.DAY);
    }

    /**
     * [从开始到当前的间隔周数，取绝对值](The number of weeks between the beginning and the current, in absolute value)
     * @description: zh - 从开始到当前的间隔周数，取绝对值
     * @description: en - The number of weeks between the beginning and the current, in absolute value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:03 下午
     * @param id: 分组ID
     * @return long
    */
    public long intervalWeek(String id) {
        return interval(id, DateUnit.WEEK);
    }

    /**
     * [从开始到当前的间隔时间（毫秒数），返回XX天XX小时XX分XX秒XX毫秒](The interval time (milliseconds) from the beginning to the current, which returns XX days XX hours XX minutes XX seconds XX milliseconds)
     * @description: zh - 从开始到当前的间隔时间（毫秒数），返回XX天XX小时XX分XX秒XX毫秒
     * @description: en - The interval time (milliseconds) from the beginning to the current, which returns XX days XX hours XX minutes XX seconds XX milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/23 4:05 下午
     * @param id: 分组ID
     * @return java.lang.String
    */
    public String intervalPretty(String id) {
        return DateUtil.formatBetween(intervalMs(id));
    }

}
