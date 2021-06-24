package com.xiaoTools.date.dateRange;

import com.xiaoTools.date.dateField.DateField;
import com.xiaoTools.date.dateTime.DateTime;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.lang.range.Range;
import com.xiaoTools.util.dateUtil.DateUtil;

import java.io.Serial;
import java.util.Date;

/**
 * [日期范围](Date range)
 * @description: zh - 日期范围
 * @description: en - Date range
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/24 1:27 下午
*/
public class DateRange extends Range<DateTime> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * [构造，包含开始和结束日期时间](Construct, including start and end dates and times)
     * @description: zh - 构造，包含开始和结束日期时间
     * @description: en - Construct, including start and end dates and times
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 2:37 下午
     * @param start: 起始日期时间
     * @param end: 结束日期时间
     * @param unit: 步进单位
    */
    public DateRange(Date start, Date end, final DateField unit) {
        this(start, end, unit, Constant.ONE);
    }

    /**
     * [构造，包含开始和结束日期时间](Construct, including start and end dates and times)
     * @description: zh - 构造，包含开始和结束日期时间
     * @description: en - Construct, including start and end dates and times
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 2:38 下午
     * @param start: 起始日期时间
     * @param end: 结束日期时间
     * @param unit: 步进单位
     * @param step: 步进数
    */
    public DateRange(Date start, Date end, final DateField unit, final int step) {
        this(start, end, unit, step, true, true);
    }

    /**
     * [构造，包含开始和结束日期时间](Construct, including start and end dates and times)
     * @description: zh - 构造，包含开始和结束日期时间
     * @description: en - Construct, including start and end dates and times
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 2:46 下午
     * @param start:  起始日期时间
     * @param end: 结束日期时间
     * @param unit: 步进单位
     * @param step: 步进数
     * @param isIncludeStart: 是否包含开始的时间
     * @param isIncludeEnd: 是否包含结束的时间
    */
    public DateRange(Date start, Date end, final DateField unit, final int step, boolean isIncludeStart, boolean isIncludeEnd) {
        super(DateUtil.date(start), DateUtil.date(end), (current, end1, index) -> {
            DateTime dt = current.offsetNew(unit, step);
            if (dt.isAfter(end1)) {
                return null;
            }
            return current.offsetNew(unit, step);
        }, isIncludeStart, isIncludeEnd);
    }
}
