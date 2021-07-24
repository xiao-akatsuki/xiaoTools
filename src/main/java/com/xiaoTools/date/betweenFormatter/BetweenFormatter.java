package com.xiaoTools.date.betweenFormatter;

import com.xiaoTools.date.dateUnit.DateUnit;
import com.xiaoTools.date.leven.Level;
import com.xiaoTools.lang.constant.Constant;

import java.io.Serial;
import java.io.Serializable;

/**
 * [时长格式化器，用于格式化输出两个日期相差的时长](Duration formatter, used to format the output of two dates of different duration)
 * @description: zh - 时长格式化器，用于格式化输出两个日期相差的时长
 * @description: en - Duration formatter, used to format the output of two dates of different duration
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 5:56 下午
*/
public class BetweenFormatter implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 时长毫秒数
     */
    private long betweenMs;
    /**
     * 格式化级别
     */
    private Level level;
    /**
     * 格式化级别的最大个数
     */
    private final int levelMaxCount;


    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:23 下午
     * @param betweenMs: 日期间隔
     * @param level: 级别
    */
    public BetweenFormatter(long betweenMs, Level level) {
        this(betweenMs, level, Constant.ZERO);
    }

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:24 下午
     * @param betweenMs: 日期间隔
     * @param level: 级别，按照天、小时、分、秒、毫秒分为5个等级，根据传入等级，格式化到相应级别
     * @param levelMaxCount: 格式化级别的最大个数，假如级别个数为1，但是级别到秒，那只显示一个级别
    */
    public BetweenFormatter(long betweenMs, Level level, int levelMaxCount) {
        this.betweenMs = betweenMs;
        this.level = level;
        this.levelMaxCount = levelMaxCount;
    }

    /**
     * [格式化日期间隔输出](Format date interval output)
     * @description: zh - 格式化日期间隔输出
     * @description: en - Format date interval output
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:40 下午
     * @return java.lang.String
    */
    public String format() {
        final StringBuilder sb = new StringBuilder();
        if (betweenMs > Constant.ZERO) {
            long day = betweenMs / DateUnit.DAY.getMillis();
            long hour = betweenMs / DateUnit.HOUR.getMillis() - day * Constant.TWENTY_FOUR;
            long minute = betweenMs / DateUnit.MINUTE.getMillis() - day * Constant.TWENTY_FOUR * Constant.SIXTY - hour * Constant.SIXTY;

            final long betweenOfSecond = ((day * Constant.TWENTY_FOUR + hour) * Constant.SIXTY + minute) * Constant.SIXTY;
            long second = betweenMs / DateUnit.SECOND.getMillis() - betweenOfSecond;
            long millisecond = betweenMs - (betweenOfSecond + second) * Constant.ONE_THOUSAND;

            final int level = this.level.ordinal();
            int levelCount = Constant.ZERO;

            if (isLevelCountValid(levelCount) && Constant.ZERO != day && level >= Level.DAY.ordinal()) {
                sb.append(day).append(Level.DAY.getName());
                levelCount++;
            }
            if (isLevelCountValid(levelCount) && Constant.ZERO != hour && level >= Level.HOUR.ordinal()) {
                sb.append(hour).append(Level.HOUR.getName());
                levelCount++;
            }
            if (isLevelCountValid(levelCount) && Constant.ZERO != minute && level >= Level.MINUTE.ordinal()) {
                sb.append(minute).append(Level.MINUTE.getName());
                levelCount++;
            }
            if (isLevelCountValid(levelCount) && Constant.ZERO != second && level >= Level.SECOND.ordinal()) {
                sb.append(second).append(Level.SECOND.getName());
                levelCount++;
            }
            if (isLevelCountValid(levelCount) && Constant.ZERO != millisecond && level >= Level.MILLISECOND.ordinal()) {
                sb.append(millisecond).append(Level.MILLISECOND.getName());
            }
        }
        if (StrUtil.isEmpty(sb)) {
            sb.append(Constant.ZERO).append(this.level.getName());
        }
        return sb.toString();
    }

    /**
     * [获得 时长毫秒数](Get duration milliseconds)
     * @description: zh - 获得 时长毫秒数
     * @description: en - Get duration milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:45 下午
     * @return long
    */
    public long getBetweenMs() {
        return betweenMs;
    }

    /**
     * [设置 时长毫秒数](Set duration milliseconds)
     * @description: zh - 设置 时长毫秒数
     * @description: en - Set duration milliseconds
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:46 下午
     * @param betweenMs: 时长毫秒数
    */
    public void setBetweenMs(long betweenMs) {
        this.betweenMs = betweenMs;
    }

    /**
     * [获得 格式化级别](Get formatting level)
     * @description: zh - 获得 格式化级别
     * @description: en - Get formatting level
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:49 下午
     * @return com.xiaoTools.date.leven.Level
    */
    public Level getLevel() {
        return level;
    }

    /**
     * [设置格式化级别](Set formatting level)
     * @description: zh - 设置格式化级别
     * @description: en - Set formatting level
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:50 下午
     * @param level: 格式化级别
    */
    public void setLevel(Level level) {
        this.level = level;
    }

    /* 重写--------------------------------------------------------------------Override*/

    @Override
    public String toString() {
        return format();
    }

    /* 私有--------------------------------------------------------------------private*/

    /**
     * [等级数量是否有效](Is the level quantity valid)
     * @description: zh - 等级数量是否有效
     * @description: en - Is the level quantity valid
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 6:40 下午
     * @param levelCount: 登记数量
     * @return boolean
    */
    private boolean isLevelCountValid(int levelCount) {
        return this.levelMaxCount <= Constant.ZERO || levelCount < this.levelMaxCount;
    }



}
