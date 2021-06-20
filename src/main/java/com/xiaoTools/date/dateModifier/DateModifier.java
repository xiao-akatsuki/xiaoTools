package com.xiaoTools.date.dateModifier;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.dateUtil.DateUtil;

import java.util.Calendar;

/**
 * [日期修改器](Date modifier)
 * @description: zh - 日期修改器
 * @description: en - Date modifier
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/20 5:19 下午
*/
public class DateModifier {

    /**
     * 忽略的计算的字段
     */
    private static final int[] IGNORE_FIELDS = new int[] {
            // 与HOUR同名
            Calendar.HOUR_OF_DAY,
            // 此字段单独处理，不参与计算起始和结束
            Calendar.AM_PM,
            // 不参与计算
            Calendar.DAY_OF_WEEK_IN_MONTH,
            // DAY_OF_MONTH体现
            Calendar.DAY_OF_YEAR,
            // 特殊处理
            Calendar.WEEK_OF_MONTH,
            // WEEK_OF_MONTH体现
            Calendar.WEEK_OF_YEAR
    };

    public static Calendar modify(Calendar calendar, int dateField, ModifyType modifyType) {
        // AM_PM上下午特殊处理
        if (Calendar.AM_PM == dateField) {
            boolean isAM = DateUtil.isAM(calendar);
            switch (modifyType) {
                case TRUNCATE ->calendar.set(Calendar.HOUR_OF_DAY, isAM ? Constant.ZERO : Constant.TWELVE);
                case CEILING -> calendar.set(Calendar.HOUR_OF_DAY, isAM ? Constant.ELEVEN : Constant.TWENTY_THREE);
                case ROUND -> {
                    int min = isAM ? Constant.ZERO : Constant.TWELVE;
                    int max = isAM ? Constant.ELEVEN : Constant.TWENTY_THREE;
                    int href = (max - min) / Constant.TWO + Constant.ONE;
                    int value = calendar.get(Calendar.HOUR_OF_DAY);
                    calendar.set(Calendar.HOUR_OF_DAY, (value < href) ? min : max);
                }
            }
            // 处理下一级别字段
            return modify(calendar, dateField + Constant.ONE, modifyType);
        }

        // 循环处理各级字段，精确到毫秒字段
        for (int i = dateField + Constant.ONE; i <= Calendar.MILLISECOND; i++) {
            if (ArrayUtil.contains(IGNORE_FIELDS, i)) {
                // 忽略无关字段（WEEK_OF_MONTH）始终不做修改
                continue;
            }
            // 在计算本周的起始和结束日时，月相关的字段忽略。
            if (Calendar.WEEK_OF_MONTH == dateField || Calendar.WEEK_OF_YEAR == dateField) {
                if (Calendar.DAY_OF_MONTH == i) {
                    continue;
                }
            } else {
                // 其它情况忽略周相关字段计算
                if (Calendar.DAY_OF_WEEK == i) {
                    continue;
                }
            }
            modifyField(calendar, i, modifyType);
        }
        return calendar;
    }

    private static void modifyField(Calendar calendar, int field, ModifyType modifyType) {
        if (Calendar.HOUR == field) {
            // 修正小时。HOUR为12小时制，上午的结束时间为12:00，此处改为HOUR_OF_DAY: 23:59
            field = Calendar.HOUR_OF_DAY;
        }
        switch (modifyType) {
            case TRUNCATE -> calendar.set(field, DateUtil.getBeginValue(calendar, field));
            case CEILING -> calendar.set(field, DateUtil.getEndValue(calendar, field));
            case ROUND -> {
                int min = DateUtil.getBeginValue(calendar, field);
                int max = DateUtil.getEndValue(calendar, field);
                int href;
                if (Calendar.DAY_OF_WEEK == field) {
                    // 星期特殊处理，假设周一是第一天，中间的为周四
                    href = (min + Constant.THREE) % Constant.SEVEN;
                } else {
                    href = (max - min) / Constant.TWO + Constant.ONE;
                }
                int value = calendar.get(field);
                calendar.set(field, (value < href) ? min : max);
            }
        }
    }

    /**
     * [修改类型](Modification type)
     * @description: zh - 修改类型
     * @description: en - Modification type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 5:47 下午
    */
    public enum ModifyType {

        /**
         * 取指定日期短的起始值.
         */
        TRUNCATE,

        /**
         * 指定日期属性按照四舍五入处理
         */
        ROUND,

        /**
         * 指定日期属性按照进一法处理
         */
        CEILING
    }
}
