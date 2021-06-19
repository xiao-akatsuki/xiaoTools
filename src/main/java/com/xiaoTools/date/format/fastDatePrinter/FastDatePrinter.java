package com.xiaoTools.date.format.fastDatePrinter;

import com.xiaoTools.date.format.abstractDateBasic.AbstractDateBasic;
import com.xiaoTools.date.format.datePrinter.DatePrinter;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.*;

/**
 * [java.text.SimpleDateFormat 的线程安全版本，用于将 Date 格式化输出](java.text.SimpleDateFormat The thread safe version of, used to format the date output)
 * @description: zh - java.text.SimpleDateFormat 的线程安全版本，用于将 Date 格式化输出
 * @description: en - java.text.SimpleDateFormat The thread safe version of, used to format the date output
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 11:02 上午
*/
public class FastDatePrinter extends AbstractDateBasic implements DatePrinter {

    private static final long serialVersionUID = -6305750172255764887L;

    /**
     * 规则列表
     */
    private transient Rule[] rules;

    /**
     * [设置规则](Set rules)
     * @description: zh - 设置规则
     * @description: en - Set rules
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 11:08 上午
    */
    private interface Rule {

        /**
         * [返回结果的估计长度。](Returns the estimated length of the result.)
         * @description: zh - 返回结果的估计长度。
         * @description: en - Returns the estimated length of the result.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 11:08 上午
         * @return int
        */
        int estimateLength();

        /**
         * [根据规则实现将指定日历的值附加到输出缓冲区。](Appends the value of the specified calendar to the output buffer based on the rule implementation.)
         * @description: zh - 根据规则实现将指定日历的值附加到输出缓冲区。
         * @description: en - Appends the value of the specified calendar to the output buffer based on the rule implementation.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 11:12 上午
         * @param buf: 输出缓冲区
         * @param calendar: 要附加的日历
         * @throws IOException
        */
        void appendTo(Appendable buf, Calendar calendar) throws IOException;
    }

    /**
     * 估算最大长度
     */
    private transient int mMaxLengthEstimate;

    /*初始化--------------------------------------------------------------------initialization*/

    /**
     * [内部使用的构造函数](Constructors used internally)
     * @description: zh - 内部使用的构造函数
     * @description: en - Constructors used internally
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 11:04 上午
     * @param pattern: 使用java.text.SimpleDateFormat 相同的日期格式
     * @param timeZone: 非空时区TimeZone
     * @param locale: 非空Locale 日期地理位置
    */
    public FastDatePrinter(final String pattern, final TimeZone timeZone, final Locale locale) {
        super(pattern, timeZone, locale);
        init();
    }

    /**
     * [初始化方法](Initialization method)
     * @description: zh - 初始化方法
     * @description: en - Initialization method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 11:05 上午
    */
    private void init() {
        final List<Rule> rulesList = parsePattern();
        rules = rulesList.toArray(new Rule[0]);
        int len = 0;
        for (int i = rules.length; --i >= 0;) {
            len += rules[i].estimateLength();
        }
        mMaxLengthEstimate = len;
    }

    /*分析格式--------------------------------------------------------------------Parse the pattern*/

    protected List<Rule> parsePattern() {
        final DateFormatSymbols symbols = new DateFormatSymbols(locale);
        final List<Rule> rules = new ArrayList<>();

        final String[] ERAs = symbols.getEras();
        final String[] months = symbols.getMonths();
        final String[] shortMonths = symbols.getShortMonths();
        final String[] weekdays = symbols.getWeekdays();
        final String[] shortWeekdays = symbols.getShortWeekdays();
        final String[] AmPmStrings = symbols.getAmPmStrings();

        final int length = pattern.length();
        final int[] indexRef = new int[1];

        for (int i = 0; i < length; i++) {
            indexRef[0] = i;
            final String token = parseToken(pattern, indexRef);
            i = indexRef[0];

            final int tokenLen = token.length();
            if (tokenLen == 0) {
                break;
            }

            Rule rule;
            final char c = token.charAt(0);

            switch (c) {
                case 'G':
                    rule = new TextField(Calendar.ERA, ERAs);
                    break;
                case 'y':
                case 'Y':
                    if (tokenLen == 2) {
                        rule = TwoDigitYearField.INSTANCE;
                    } else {
                        rule = selectNumberRule(Calendar.YEAR, Math.max(tokenLen, 4));
                    }
                    if (c == 'Y') {
                        rule = new WeekYear((NumberRule) rule);
                    }
                    break;
                case 'M':
                    if (tokenLen >= 4) {
                        rule = new TextField(Calendar.MONTH, months);
                    } else if (tokenLen == 3) {
                        rule = new TextField(Calendar.MONTH, shortMonths);
                    } else if (tokenLen == 2) {
                        rule = TwoDigitMonthField.INSTANCE;
                    } else {
                        rule = UnpaddedMonthField.INSTANCE;
                    }
                    break;
                case 'd':
                    rule = selectNumberRule(Calendar.DAY_OF_MONTH, tokenLen);
                    break;
                case 'h':
                    rule = new TwelveHourField(selectNumberRule(Calendar.HOUR, tokenLen));
                    break;
                case 'H':
                    rule = selectNumberRule(Calendar.HOUR_OF_DAY, tokenLen);
                    break;
                case 'm':
                    rule = selectNumberRule(Calendar.MINUTE, tokenLen);
                    break;
                case 's':
                    rule = selectNumberRule(Calendar.SECOND, tokenLen);
                    break;
                case 'S':
                    rule = selectNumberRule(Calendar.MILLISECOND, tokenLen);
                    break;
                case 'E':
                    rule = new TextField(Calendar.DAY_OF_WEEK, tokenLen < 4 ? shortWeekdays : weekdays);
                    break;
                case 'u':
                    rule = new DayInWeekField(selectNumberRule(Calendar.DAY_OF_WEEK, tokenLen));
                    break;
                case 'D':
                    rule = selectNumberRule(Calendar.DAY_OF_YEAR, tokenLen);
                    break;
                case 'F':
                    rule = selectNumberRule(Calendar.DAY_OF_WEEK_IN_MONTH, tokenLen);
                    break;
                case 'w':
                    rule = selectNumberRule(Calendar.WEEK_OF_YEAR, tokenLen);
                    break;
                case 'W':
                    rule = selectNumberRule(Calendar.WEEK_OF_MONTH, tokenLen);
                    break;
                case 'a':
                    rule = new TextField(Calendar.AM_PM, AmPmStrings);
                    break;
                case 'k':
                    rule = new TwentyFourHourField(selectNumberRule(Calendar.HOUR_OF_DAY, tokenLen));
                    break;
                case 'K':
                    rule = selectNumberRule(Calendar.HOUR, tokenLen);
                    break;
                case 'X':
                    rule = Iso8601_Rule.getRule(tokenLen);
                    break;
                case 'z':
                    if (tokenLen >= 4) {
                        rule = new TimeZoneNameRule(timeZone, locale, TimeZone.LONG);
                    } else {
                        rule = new TimeZoneNameRule(timeZone, locale, TimeZone.SHORT);
                    }
                    break;
                case 'Z':
                    if (tokenLen == 1) {
                        rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
                    } else if (tokenLen == 2) {
                        rule = Iso8601_Rule.ISO8601_HOURS_COLON_MINUTES;
                    } else {
                        rule = TimeZoneNumberRule.INSTANCE_COLON;
                    }
                    break;
                case '\'':
                    final String sub = token.substring(1);
                    if (sub.length() == 1) {
                        rule = new CharacterLiteral(sub.charAt(0));
                    } else {
                        rule = new StringLiteral(sub);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Illegal pattern component: " + token);
            }

            rules.add(rule);
        }

        return rules;
    }

    private static class TextField implements Rule {
        private final int mField;
        private final String[] mValues;

        /**
         * [用指定的字段和值构造TextField的实例。](Constructs an instance of TextField with the specified field and values.)
         * @description: zh - 用指定的字段和值构造TextField的实例。
         * @description: en - Constructs an instance of TextField with the specified field and values.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 11:42 上午
         * @param field: 字段
         * @param values: 字段值
        */
        TextField(final int field, final String[] values) {
            mField = field;
            mValues = values;
        }

        @Override
        public int estimateLength() {
            int max = 0;
            for (int i = mValues.length; --i >= 0;) {
                final int len = mValues[i].length();
                if (len > max) {
                    max = len;
                }
            }
            return max;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            buffer.append(mValues[calendar.get(mField)]);
        }
    }
}
