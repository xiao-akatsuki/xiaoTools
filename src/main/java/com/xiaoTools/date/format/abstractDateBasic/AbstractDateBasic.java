package com.xiaoTools.date.format.abstractDateBasic;

import com.xiaoTools.date.format.basic.DateBasic;
import com.xiaoTools.date.format.fastDatePrinter.FastDatePrinter;
import com.xiaoTools.lang.constant.Constant;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

/**
 * [摘要日期基本](Summary date basic)
 * @description: zh - 摘要日期基本
 * @description: en - Summary date basic
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 11:21 上午
*/
public class AbstractDateBasic implements DateBasic, Serializable {

    @Serial
    private static final long serialVersionUID = 6333136319870641818L;

    /**
     * 模式
     */
    protected final  String pattern;

    /**
     * 时区
     */
    protected final TimeZone timeZone;

    /**
     * 区域设置
     */
    protected final Locale locale;

    /*构造函数--------------------------------------------------------------------Constructors*/

    protected AbstractDateBasic(final String pattern, final TimeZone timeZone, final Locale locale) {
        this.pattern = pattern;
        this.timeZone = timeZone;
        this.locale = locale;
    }

    /*访问器--------------------------------------------------------------------Accessors*/

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public TimeZone getTimeZone() {
        return timeZone;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    /*基础--------------------------------------------------------------------Basic*/

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof FastDatePrinter)) {
            return false;
        }
        final AbstractDateBasic other = (AbstractDateBasic) obj;
        return pattern.equals(other.pattern) && timeZone.equals(other.timeZone) && locale.equals(other.locale);
    }

    @Override
    public int hashCode() {
        return pattern.hashCode() + Constant.THIRTEEN * (timeZone.hashCode() + Constant.THIRTEEN * locale.hashCode());
    }

    @Override
    public String toString() {
        return "FastDatePrinter[" + pattern + "," + locale + "," + timeZone.getID() + "]";
    }
}
