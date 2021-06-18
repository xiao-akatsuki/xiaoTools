package com.xiaoTools.date.dateUnit;

import com.xiaoTools.lang.constant.Constant;

import java.time.temporal.ChronoUnit;

/**
 * [日期时间单位，每个单位都是以毫秒为基数](Date time units, each in milliseconds)
 * @description: zh - 日期时间单位，每个单位都是以毫秒为基数
 * @description: en - Date time units, each in milliseconds
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/18 5:01 下午
*/
public enum DateUnit {
    /**
     * 一毫秒
     */
    MS(Constant.ONE),
    /**
     * 一秒的毫秒数
     */
    SECOND(Constant.ONE_THOUSAND),
    /**
     * 一分钟的毫秒数
     */
    MINUTE(SECOND.getMillis() * Constant.SIXTY),
    /**
     * 一小时的毫秒数
     */
    HOUR(MINUTE.getMillis() * Constant.SIXTY),
    /**
     * 一天的毫秒数
     */
    DAY(HOUR.getMillis() * Constant.TWENTY_FOUR),
    /**
     * 一周的毫秒数
     */
    WEEK(DAY.getMillis() * Constant.SEVEN);

    private final long millis;

    DateUnit(long millis) {
        this.millis = millis;
    }

    /**
     * [单位对应的毫秒数](The number of milliseconds corresponding to the unit)
     * @description: zh - 单位对应的毫秒数
     * @description: en - The number of milliseconds corresponding to the unit
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 5:41 下午
     * @return long
    */
    public long getMillis() { return this.millis; }

    public ChronoUnit toChronoUnit() {
        return DateUnit.toChronoUnit(this);
    }

    /**
     * [单位兼容转换，将ChronoUnit转换为对应的DateUnit](Unit compatible conversion: convert ChronoUnit to corresponding DateUnit)
     * @description: zh - 单位兼容转换，将ChronoUnit转换为对应的DateUnit
     * @description: en - Unit compatible conversion: convert ChronoUnit to corresponding DateUnit
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 5:46 下午
     * @param unit: ChronoUnit
     * @return com.xiaoTools.date.dateUnit.DateUnit
    */
    public static DateUnit of(ChronoUnit unit) {
        return switch (unit) {
            case MICROS -> DateUnit.MS;
            case SECONDS -> DateUnit.SECOND;
            case MINUTES -> DateUnit.MINUTE;
            case HOURS -> DateUnit.HOUR;
            case DAYS -> DateUnit.DAY;
            case WEEKS -> DateUnit.WEEK;
            default -> null;
        };
    }

    /**
     * [单位兼容转换，将DateUnit转换为对应的ChronoUnit](Unit compatible conversion, converting DateUnit to corresponding ChronoUnit)
     * @description: zh - 单位兼容转换，将DateUnit转换为对应的ChronoUnit
     * @description: en - Unit compatible conversion, converting DateUnit to corresponding ChronoUnit
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/18 5:47 下午
     * @param unit:  DateUnit
     * @return java.time.temporal.ChronoUnit
    */
    public static ChronoUnit toChronoUnit(DateUnit unit) {
        return switch (unit) {
            case MS -> ChronoUnit.MICROS;
            case SECOND -> ChronoUnit.SECONDS;
            case MINUTE -> ChronoUnit.MINUTES;
            case HOUR -> ChronoUnit.HOURS;
            case DAY -> ChronoUnit.DAYS;
            case WEEK -> ChronoUnit.WEEKS;
            default -> null;
        };
    }
}
