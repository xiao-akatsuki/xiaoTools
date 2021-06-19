package com.xiaoTools.date.format.fastDatePrinter;

import com.xiaoTools.core.exception.dateException.DateException;
import com.xiaoTools.date.format.abstractDateBasic.AbstractDateBasic;
import com.xiaoTools.date.format.datePrinter.DatePrinter;
import com.xiaoTools.lang.constant.Constant;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
     * 估算最大长度
     */
    private transient int mMaxLengthEstimate;

    private static final ConcurrentMap<TimeZoneDisplayKey, String> C_TIME_ZONE_DISPLAY_CACHE = new ConcurrentHashMap<>(7);

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

    /**
     * [返回给定模式的规则列表。](Returns a list of Rules given a pattern.)
     * @description: zh - 返回给定模式的规则列表。
     * @description: en - Returns a list of Rules given a pattern.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 12:51 下午
     * @return java.util.List<com.xiaoTools.date.format.fastDatePrinter.FastDatePrinter.Rule>
    */
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
        final int[] indexRef = new int[Constant.ONE];

        for (int i = Constant.ZERO; i < length; i++) {
            indexRef[Constant.ZERO] = i;
            final String token = parseToken(pattern, indexRef);
            i = indexRef[Constant.ZERO];

            final int tokenLen = token.length();
            if (tokenLen == Constant.ZERO) {
                break;
            }

            Rule rule;
            final char c = token.charAt(Constant.ZERO);

            switch (c) {
                case Constant.CHAR_UP_G:
                    rule = new TextField(Calendar.ERA, ERAs);
                    break;
                case Constant.CHAR_DOWN_Y:
                case Constant.CHAR_UP_Y:
                    rule = tokenLen == Constant.TWO ? TwoDigitYearField.INSTANCE : selectNumberRule(Calendar.YEAR, Math.max(tokenLen, Constant.FOUR));
                    if (c == Constant.CHAR_UP_Y) { rule = new WeekYear((NumberRule) rule); }
                    break;
                case Constant.CHAR_UP_M:
                    if (tokenLen >= Constant.FOUR) {
                        rule = new TextField(Calendar.MONTH, months);
                    } else if (tokenLen == Constant.THREE) {
                        rule = new TextField(Calendar.MONTH, shortMonths);
                    } else if (tokenLen == Constant.TWO) {
                        rule = TwoDigitMonthField.INSTANCE;
                    } else {
                        rule = UnpaddedMonthField.INSTANCE;
                    }
                    break;
                case Constant.CHAR_DOWN_D:
                    rule = selectNumberRule(Calendar.DAY_OF_MONTH, tokenLen);
                    break;
                case Constant.CHAR_DOWN_H:
                    rule = new TwelveHourField(selectNumberRule(Calendar.HOUR, tokenLen));
                    break;
                case Constant.CHAR_UP_H:
                    rule = selectNumberRule(Calendar.HOUR_OF_DAY, tokenLen);
                    break;
                case Constant.CHAR_DOWN_M:
                    rule = selectNumberRule(Calendar.MINUTE, tokenLen);
                    break;
                case Constant.CHAR_DOWN_S:
                    rule = selectNumberRule(Calendar.SECOND, tokenLen);
                    break;
                case Constant.CHAR_UP_S:
                    rule = selectNumberRule(Calendar.MILLISECOND, tokenLen);
                    break;
                case Constant.CHAR_UP_E:
                    rule = new TextField(Calendar.DAY_OF_WEEK, tokenLen < 4 ? shortWeekdays : weekdays);
                    break;
                case Constant.CHAR_DOWN_U:
                    rule = new DayInWeekField(selectNumberRule(Calendar.DAY_OF_WEEK, tokenLen));
                    break;
                case Constant.CHAR_UP_D:
                    rule = selectNumberRule(Calendar.DAY_OF_YEAR, tokenLen);
                    break;
                case Constant.CHAR_UP_F:
                    rule = selectNumberRule(Calendar.DAY_OF_WEEK_IN_MONTH, tokenLen);
                    break;
                case Constant.CHAR_DOWN_W:
                    rule = selectNumberRule(Calendar.WEEK_OF_YEAR, tokenLen);
                    break;
                case Constant.CHAR_UP_W:
                    rule = selectNumberRule(Calendar.WEEK_OF_MONTH, tokenLen);
                    break;
                case Constant.CHAR_DOWN_A:
                    rule = new TextField(Calendar.AM_PM, AmPmStrings);
                    break;
                case Constant.CHAR_DOWN_K:
                    rule = new TwentyFourHourField(selectNumberRule(Calendar.HOUR_OF_DAY, tokenLen));
                    break;
                case Constant.CHAR_UP_K:
                    rule = selectNumberRule(Calendar.HOUR, tokenLen);
                    break;
                case Constant.CHAR_UP_X:
                    rule = Iso8601_Rule.getRule(tokenLen);
                    break;
                case Constant.CHAR_DOWN_Z:
                    if (tokenLen >= Constant.FOUR) {
                        rule = new TimeZoneNameRule(timeZone, locale, TimeZone.LONG);
                    } else {
                        rule = new TimeZoneNameRule(timeZone, locale, TimeZone.SHORT);
                    }
                    break;
                case Constant.CHAR_UP_Z:
                    if (tokenLen == Constant.ONE) {
                        rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
                    } else if (tokenLen == Constant.TWO) {
                        rule = Iso8601_Rule.ISO8601_HOURS_COLON_MINUTES;
                    } else {
                        rule = TimeZoneNumberRule.INSTANCE_COLON;
                    }
                    break;
                case Constant.CHAR_SLASH_SPOT:
                    final String sub = token.substring(Constant.ONE);
                    if (sub.length() == Constant.ONE) {
                        rule = new CharacterLiteral(sub.charAt(Constant.ZERO));
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

    /**
     * [执行令牌解析。](Performs the parsing of tokens.)
     * @description: zh - 执行令牌解析。
     * @description: en - Performs the parsing of tokens.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:51 下午
     * @param pattern: 模式
     * @param indexRef: 索引引用
     * @return java.lang.String
    */
    protected String parseToken(final String pattern, final int[] indexRef) {
        final StringBuilder buf = new StringBuilder();

        int i = indexRef[Constant.ZERO];
        final int length = pattern.length();

        char c = pattern.charAt(i);
        if (c >= Constant.CHAR_UP_A && c <= Constant.CHAR_UP_Z || c >= Constant.CHAR_DOWN_A && c <= Constant.CHAR_DOWN_Z) {
            // Scan a run of the same character, which indicates a time
            // pattern.
            buf.append(c);

            while (i + Constant.ONE < length) {
                final char peek = pattern.charAt(i + Constant.ONE);
                if (peek == c) {
                    buf.append(c);
                    i++;
                } else {
                    break;
                }
            }
        } else {
            // This will identify token as text.
            buf.append(Constant.CHAR_SLASH_SPOT);

            boolean inLiteral = Constant.FALSE;

            for (; i < length; i++) {
                c = pattern.charAt(i);

                if (c == Constant.CHAR_SLASH_SPOT) {
                    if (i + Constant.ONE < length && pattern.charAt(i + Constant.ONE) == Constant.CHAR_SLASH_SPOT) {
                        // '' is treated as escaped '
                        i++;
                        buf.append(c);
                    } else {
                        inLiteral = !inLiteral;
                    }
                } else if (!inLiteral && (c >= Constant.CHAR_UP_A && c <= Constant.CHAR_UP_Z || c >= Constant.CHAR_DOWN_A && c <= Constant.CHAR_DOWN_Z)) {
                    i--;
                    break;
                } else {
                    buf.append(c);
                }
            }
        }

        indexRef[Constant.ZERO] = i;
        return buf.toString();
    }

    /**
     * [获取所需填充的适当规则。](Gets an appropriate rule for the padding required.)
     * @description: zh - 获取所需填充的适当规则。
     * @description: en - Gets an appropriate rule for the padding required.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:49 下午
     * @param field: 获取规则的字段
     * @param padding: 所需的填充
     * @return com.xiaoTools.date.format.fastDatePrinter.FastDatePrinter.NumberRule
    */
    protected NumberRule selectNumberRule(final int field, final int padding) {
        return switch (padding) {
            case Constant.ONE -> new UnpaddedNumberField(field);
            case Constant.TWO -> new TwoDigitNumberField(field);
            default -> new PaddedNumberField(field, padding);
        };
    }

    /*格式化方法--------------------------------------------------------------------Format methods*/

    /**
     * [设置日期、日历或长（毫秒）对象的格式。](Formats a Date, Calendar or Long (milliseconds) object.)
     * @description: zh - 设置日期、日历或长（毫秒）对象的格式。
     * @description: en - Formats a Date, Calendar or Long (milliseconds) object.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:53 下午
     * @param obj: 需要格式化的对象
     * @return java.lang.String
    */
    public String format(final Object obj) {
        if (obj instanceof Date) {
            return format((Date) obj);
        } else if (obj instanceof Calendar) {
            return format((Calendar) obj);
        } else if (obj instanceof Long) {
            return format(((Long) obj).longValue());
        } else {
            throw new IllegalArgumentException("Unknown class: " + (obj == null ? "<null>" : obj.getClass().getName()));
        }
    }


    @Override
    public String format(final long millis) {
        final Calendar c = Calendar.getInstance(timeZone, locale);
        c.setTimeInMillis(millis);
        return applyRulesToString(c);
    }

    @Override
    public String format(final Date date) {
        final Calendar c = Calendar.getInstance(timeZone, locale);
        c.setTime(date);
        return applyRulesToString(c);
    }

    @Override
    public String format(final Calendar calendar) {
        return format(calendar, new StringBuilder(mMaxLengthEstimate)).toString();
    }

    @Override
    public <B extends Appendable> B format(final long millis, final B buf) {
        final Calendar c = Calendar.getInstance(timeZone, locale);
        c.setTimeInMillis(millis);
        return applyRules(c, buf);
    }

    @Override
    public <B extends Appendable> B format(final Date date, final B buf) {
        final Calendar c = Calendar.getInstance(timeZone, locale);
        c.setTime(date);
        return applyRules(c, buf);
    }

    @Override
    public <B extends Appendable> B format(Calendar calendar, final B buf) {
        // do not pass in calendar directly, this will cause TimeZone of FastDatePrinter to be ignored
        if (!calendar.getTimeZone().equals(timeZone)) {
            calendar = (Calendar) calendar.clone();
            calendar.setTimeZone(timeZone);
        }
        return applyRules(calendar, buf);
    }

    /**
     * [通过对给定日历应用此打印机的规则来创建该日历的字符串表示形式。](Creates a String representation of the given Calendar by applying the rules of this printer to it.)
     * @description: zh - 通过对给定日历应用此打印机的规则来创建该日历的字符串表示形式。
     * @description: en - Creates a String representation of the given Calendar by applying the rules of this printer to it.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:55 下午
     * @param c: 应用规则的日历。
     * @return java.lang.String
    */
    private String applyRulesToString(final Calendar c) {
        return applyRules(c, new StringBuilder(mMaxLengthEstimate)).toString();
    }

    /**
     * [通过将规则应用于指定的日历来执行格式设置。](Performs the formatting by applying the rules to the specified calendar.)
     * @description: zh - 通过将规则应用于指定的日历来执行格式设置。
     * @description: en - Performs the formatting by applying the rules to the specified calendar.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:56 下午
     * @param calendar: 要格式化的日历
     * @param buf: 要格式化为的缓冲区
     * @return B
    */
    private <B extends Appendable> B applyRules(final Calendar calendar, final B buf) {
        try {
            for (final Rule rule : this.rules) {
                rule.appendTo(buf, calendar);
            }
        } catch (final IOException e) {
            throw new DateException(e);
        }
        return buf;
    }

    /**
     * [估算生成的日期字符串长度 实际生成的字符串长度小于或等于此值](Estimate the length of the generated date string. The actual length of the generated string is less than or equal to this value)
     * @description: zh - 估算生成的日期字符串长度 实际生成的字符串长度小于或等于此值
     * @description: en - Estimate the length of the generated date string. The actual length of the generated string is less than or equal to this value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:57 下午
     * @return int
    */
    public int getMaxLengthEstimate() {
        return mMaxLengthEstimate;
    }

    /*序列化--------------------------------------------------------------------Serializing*/

    /**
     * [序列化后创建对象。此实现将重新初始化瞬态属性。](Create the object after serialization. This implementation reinitializes the transient properties.)
     * @description: zh - 序列化后创建对象。此实现将重新初始化瞬态属性。
     * @description: en - Create the object after serialization. This implementation reinitializes the transient properties.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 3:00 下午
     * @param in: 正在从中反序列化对象的ObjectInputStream。
     * @throws IOException
     * @throws ClassNotFoundException
    */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        init();
    }

    /**
     * [将两个数字附加到给定的缓冲区。](Appends two digits to the given buffer.)
     * @description: zh - 将两个数字附加到给定的缓冲区。
     * @description: en - Appends two digits to the given buffer.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 3:02 下午
     * @param buffer: 要附加到的缓冲区。
     * @param value: 要从中追加数字的值。
     * @throws IOException
    */
    private static void appendDigits(final Appendable buffer, final int value) throws IOException {
        buffer.append((char) (value / Constant.TEN + Constant.CHAR_ZERO));
        buffer.append((char) (value % Constant.TEN + Constant.CHAR_ZERO));
    }

    /**
     * [将所有数字追加到给定的缓冲区。](Appends all digits to the given buffer.)
     * @description: zh - 将所有数字追加到给定的缓冲区。
     * @description: en - Appends all digits to the given buffer.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 3:08 下午
     * @param buffer: 要附加到的缓冲区。
     * @param value: 要从中追加数字的值。
     * @param minFieldWidth: 最小字段宽度
    */
    private static void appendFullDigits(final Appendable buffer, int value, int minFieldWidth) throws IOException {
        if (value < Constant.TEN_THOUSAND) {
            int nDigits = Constant.FOUR;
            if (value < Constant.ONE_THOUSAND) {
                --nDigits;
                if (value < Constant.HUNDRED) {
                    --nDigits;
                    if (value < Constant.TEN) {
                        --nDigits;
                    }
                }
            }
            // left zero pad
            for (int i = minFieldWidth - nDigits; i > Constant.ZERO; --i) {
                buffer.append(Constant.CHAR_ZERO);
            }

            switch (nDigits) {
                case Constant.FOUR:
                    buffer.append((char) (value / Constant.ONE_THOUSAND + Constant.CHAR_ZERO));
                    value %= Constant.ONE_THOUSAND;
                case Constant.THREE:
                    if (value >= Constant.HUNDRED) {
                        buffer.append((char) (value / Constant.HUNDRED + Constant.CHAR_ZERO));
                        value %= Constant.HUNDRED;
                    } else {
                        buffer.append(Constant.CHAR_ZERO);
                    }
                case Constant.TWO:
                    if (value >= Constant.TEN) {
                        buffer.append((char) (value / Constant.TEN + Constant.CHAR_ZERO));
                        value %= Constant.TEN;
                    } else {
                        buffer.append(Constant.CHAR_ZERO);
                    }
                case Constant.ONE:
                    buffer.append((char) (value + Constant.CHAR_ZERO));
            }
        } else {
            // more memory allocation path works for any digits

            // build up decimal representation in reverse
            final char[] work = new char[Constant.TEN];
            int digit = Constant.ZERO;
            while (value != Constant.ZERO) {
                work[digit++] = (char) (value % Constant.TEN + Constant.CHAR_ZERO);
                value = value / Constant.TEN;
            }

            // pad with zeros
            while (digit < minFieldWidth) {
                buffer.append(Constant.CHAR_ZERO);
                --minFieldWidth;
            }

            // reverse
            while (--digit >= Constant.ZERO) {
                buffer.append(work[digit]);
            }
        }
    }

    /*私有的--------------------------------------------------------------------private*/

    /**
     * [获取时区显示名称，使用缓存提高性能。](Gets the time zone display name, using a cache for performance.)
     * @description: zh - 获取时区显示名称，使用缓存提高性能。
     * @description: en - Gets the time zone display name, using a cache for performance.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 3:25 下午
     * @param tz: 要查询的区域
     * @param daylight: 如果是夏令时，则为真
     * @param style: 使用TimeZone.LONG或TimeZone.SHORT的样式
     * @param locale: 要使用的区域设置
     * @return java.lang.String
    */
    static String getTimeZoneDisplay(final TimeZone tz, final boolean daylight, final int style, final Locale locale) {
        final TimeZoneDisplayKey key = new TimeZoneDisplayKey(tz, daylight, style, locale);
        String value = C_TIME_ZONE_DISPLAY_CACHE.get(key);
        if (value == Constant.NULL) {
            // This is a very slow call, so cache the results.
            value = tz.getDisplayName(daylight, style, locale);
            final String prior = C_TIME_ZONE_DISPLAY_CACHE.putIfAbsent(key, value);
            if (prior != Constant.NULL) {
                value = prior;
            }
        }
        return value;
    }

    /**
     * [用于输出时区名称的内部类。](Inner class to output a time zone name.)
     * @description: zh - 用于输出时区名称的内部类。
     * @description: en - Inner class to output a time zone name.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 3:29 下午
    */
    private static class TimeZoneNameRule implements Rule {
        private final Locale mLocale;
        private final int mStyle;
        private final String mStandard;
        private final String mDaylight;

        /**
         * [构造具有指定属性的TimeZoneNameRole实例。](Constructs an instance of TimeZoneNameRule with the specified properties.)
         * @description: zh - 构造具有指定属性的TimeZoneNameRole实例。
         * @description: en - Constructs an instance of TimeZoneNameRule with the specified properties.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 3:28 下午
         * @param timeZone: 时区
         * @param locale: 你指定当地设置
         * @param style: 风格
        */
        TimeZoneNameRule(final TimeZone timeZone, final Locale locale, final int style) {
            mLocale = locale;
            mStyle = style;

            mStandard = getTimeZoneDisplay(timeZone, false, style, locale);
            mDaylight = getTimeZoneDisplay(timeZone, true, style, locale);
        }

        @Override
        public int estimateLength() {
            return Math.max(mStandard.length(), mDaylight.length());
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            final TimeZone zone = calendar.getTimeZone();
            if (calendar.get(Calendar.DST_OFFSET) != Constant.ZERO) {
                buffer.append(getTimeZoneDisplay(zone, true, mStyle, mLocale));
            } else {
                buffer.append(getTimeZoneDisplay(zone, false, mStyle, mLocale));
            }
        }
    }

    /**
     * [内部类以数字+/-HHMM或+/-HH:MM的形式输出时区。](Inner class to output a time zone as a number +/-HHMM or +/-HH:MM.)
     * @description: zh - 内部类以数字+/-HHMM或+/-HH:MM的形式输出时区。
     * @description: en - Inner class to output a time zone as a number +/-HHMM or +/-HH:MM.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 3:30 下午
    */
    private static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(Constant.TRUE);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(Constant.FALSE);

        final boolean mColon;

        /**
         *
         * @description: zh - 如果 true，在输出中的HH和MM之间添加冒号
         * @description: en - If true, add a colon between HH and mm in the output
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 3:32 下午
         * @param colon: 布尔值
        */
        TimeZoneNumberRule(final boolean colon) {
            mColon = colon;
        }

        @Override
        public int estimateLength() {
            return 5;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            int offset = calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET);
            if (offset < Constant.ZERO) {
                buffer.append(Constant.CHAR_DASH);
                offset = -offset;
            } else {
                buffer.append(Constant.CHAR_PLUS);
            }

            final int hours = offset / (Constant.SIXTY * Constant.SIXTY * Constant.ONE_THOUSAND);
            appendDigits(buffer, hours);

            if (mColon) {
                buffer.append(Constant.CHAR_COLON);
            }

            final int minutes = offset / (Constant.SIXTY * Constant.ONE_THOUSAND) - Constant.SIXTY * hours;
            appendDigits(buffer, minutes);
        }
    }

    /**
     * [内部类以数字+/-HHMM或+/-HH:MM的形式输出时区。](Inner class to output a time zone as a number +/-HHMM or +/-HH:MM.)
     * @description: zh - 内部类以数字+/-HHMM或+/-HH:MM的形式输出时区。
     * @description: en - Inner class to output a time zone as a number +/-HHMM or +/-HH:MM.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 3:31 下午
    */
    private static class Iso8601_Rule implements Rule {

        static final Iso8601_Rule ISO8601_HOURS = new Iso8601_Rule(Constant.THREE);

        static final Iso8601_Rule ISO8601_HOURS_MINUTES = new Iso8601_Rule(Constant.FIVE);

        static final Iso8601_Rule ISO8601_HOURS_COLON_MINUTES = new Iso8601_Rule(Constant.SIX);

        static Iso8601_Rule getRule(final int tokenLen) {
            return switch (tokenLen) {
                case Constant.ONE -> Iso8601_Rule.ISO8601_HOURS;
                case Constant.TWO -> Iso8601_Rule.ISO8601_HOURS_MINUTES;
                case Constant.THREE -> Iso8601_Rule.ISO8601_HOURS_COLON_MINUTES;
                default -> throw new IllegalArgumentException("invalid number of X");
            };
        }

        final int length;

        Iso8601_Rule(final int length) {
            this.length = length;
        }

        @Override
        public int estimateLength() {
            return length;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            int offset = calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET);
            if (offset == Constant.ZERO) {
                buffer.append(Constant.STRING_UP_Z);
                return;
            }

            if (offset < Constant.ZERO) {
                buffer.append(Constant.CHAR_DASH);
                offset = -offset;
            } else {
                buffer.append(Constant.CHAR_PLUS);
            }

            final int hours = offset / (Constant.SIXTY * Constant.SIXTY * Constant.ONE_THOUSAND);
            appendDigits(buffer, hours);

            if (length < Constant.FIVE) {
                return;
            }

            if (length == Constant.SIX) {
                buffer.append(Constant.CHAR_COLON);
            }

            final int minutes = offset / (Constant.SIXTY * Constant.ONE_THOUSAND) - Constant.SIXTY * hours;
            appendDigits(buffer, minutes);
        }
    }

    /*类--------------------------------------------------------------------class*/

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
     * [定义数字规则的内部类。](Inner class defining a numeric rule.)
     * @description: zh - 定义数字规则的内部类。
     * @description: en - Inner class defining a numeric rule.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 1:35 下午
     */
    private interface NumberRule extends Rule {
        /**
         * [根据规则实现将指定的值附加到输出缓冲区。](Appends the specified value to the output buffer based on the rule implementation.)
         * @description: zh -  根据规则实现将指定的值附加到输出缓冲区。
         * @description: en -  Appends the specified value to the output buffer based on the rule implementation.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 1:21 下午
         * @param buffer: 输出缓冲区
         * @param value: 要附加的值
         * @throws IOException
         */
        void appendTo(Appendable buffer, int value) throws IOException;
    }

    /**
     * [内部类输出一个常量单个字符。](Inner class to output a constant single character.)
     * @description: zh - 内部类输出一个常量单个字符。
     * @description: en - Inner class to output a constant single character.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 1:46 下午
    */
    private static class CharacterLiteral implements Rule {
        private final char mValue;

        /**
         * [构造CharacterLiteral的新实例以保存指定的值。](Constructs a new instance of CharacterLiteral to hold the specified value.)
         * @description: zh - 构造CharacterLiteral的新实例以保存指定的值。
         * @description: en - Constructs a new instance of CharacterLiteral to hold the specified value.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 2:32 下午
         * @param value: 字符文字
        */
        CharacterLiteral(final char value) {
            mValue = value;
        }

        @Override
        public int estimateLength() {
            return Constant.ONE;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            buffer.append(mValue);
        }
    }

    /**
     * [用于输出常量字符串的内部类。](Inner class to output a constant string.)
     * @description: zh - 用于输出常量字符串的内部类。
     * @description: en - Inner class to output a constant string.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:04 下午
    */
    private static class StringLiteral implements Rule {
        private final String mValue;

        /**
         * [构造StringLiteral的新实例以保存指定的值。](Constructs a new instance of StringLiteral to hold the specified value.)
         * @description: zh - 构造StringLiteral的新实例以保存指定的值。
         * @description: en - Constructs a new instance of StringLiteral to hold the specified value.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 2:33 下午
         * @param value: 字符串文本
        */
        StringLiteral(final String value) {
            mValue = value;
        }

        @Override
        public int estimateLength() {
            return mValue.length();
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            buffer.append(mValue);
        }
    }

    /**
     * [内部类输出一组值中的一个。](Inner class to output one of a set of values.)
     * @description: zh - 内部类输出一组值中的一个。
     * @description: en - Inner class to output one of a set of values.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 1:34 下午
    */
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
            int max = Constant.ZERO;
            for (int i = mValues.length; --i >= Constant.ZERO;) {
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

    /**
     * [内部类以输出未添加的数字。](Inner class to output an unpadded number.)
     * @description: zh - 内部类以输出未添加的数字。
     * @description: en - Inner class to output an unpadded number.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:21 下午
    */
    private static class UnpaddedNumberField implements NumberRule {
        private final int mField;

        /**
         * [用指定的字段构造UnpadedNumberField的实例。](Constructs an instance of UnpadedNumberField with the specified field.)
         * @description: zh -  用指定的字段构造UnpadedNumberField的实例。
         * @description: en - Constructs an instance of UnpadedNumberField with the specified field.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 2:34 下午
         * @param field: 字段
        */
        UnpaddedNumberField(final int field) {
            mField = field;
        }

        @Override
        public int estimateLength() {
            return Constant.FOUR;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(mField));
        }

        @Override
        public final void appendTo(final Appendable buffer, final int value) throws IOException {
            if (value < Constant.TEN) {
                buffer.append((char) (value + Constant.CHAR_ZERO));
            } else if (value < Constant.HUNDRED) {
                appendDigits(buffer, value);
            } else {
                appendFullDigits(buffer, value, Constant.ONE);
            }
        }
    }

    /**
     * [用于输出未添加月份的内部类。](Inner class to output an unpadded month.)
     * @description: zh - 用于输出未添加月份的内部类。
     * @description: en - Inner class to output an unpadded month.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:22 下午
    */
    private static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        UnpaddedMonthField() {
        }

        @Override
        public int estimateLength() {
            return Constant.TWO;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(Calendar.MONTH) + Constant.ONE);
        }

        @Override
        public final void appendTo(final Appendable buffer, final int value) throws IOException {
            if (value < Constant.TEN) {
                buffer.append((char) (value + Constant.CHAR_ZERO));
            } else {
                appendDigits(buffer, value);
            }
        }
    }

    /**
     * [内部类以输出填充数字。](Inner class to output a padded number.)
     * @description: zh - 内部类以输出填充数字。
     * @description: en - Inner class to output a padded number.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:22 下午
    */
    private static class PaddedNumberField implements NumberRule {
        private final int mField;
        private final int mSize;

        /**
         * [构造PaddedNumberField的实例。](Constructs an instance of PaddedNumberField.)
         * @description: zh - 构造PaddedNumberField的实例。
         * @description: en - Constructs an instance of PaddedNumberField.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 2:38 下午
         * @param field: 字段
         * @param size: 输出字段的大小
        */
        PaddedNumberField(final int field, final int size) {
            if (size < Constant.THREE) {
                throw new IllegalArgumentException();
            }
            mField = field;
            mSize = size;
        }

        @Override
        public int estimateLength() {
            return mSize;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(mField));
        }

        @Override
        public final void appendTo(final Appendable buffer, final int value) throws IOException {
            appendFullDigits(buffer, value, mSize);
        }
    }

    /**
     * [用于输出两位数的内部类。](Inner class to output a two digit number.)
     * @description: zh - 用于输出两位数的内部类。
     * @description: en - Inner class to output a two digit number.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:24 下午
    */
    private static class TwoDigitNumberField implements NumberRule {
        private final int mField;

        /**
         * [用指定的字段构造TwoDigitNumberField的实例。](Constructs an instance of TwoDigitNumberField with the specified field.)
         * @description: zh - 用指定的字段构造TwoDigitNumberField的实例。
         * @description: en - Constructs an instance of TwoDigitNumberField with the specified field.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 2:40 下午
         * @param field: 字段
        */
        TwoDigitNumberField(final int field) {
            mField = field;
        }

        @Override
        public int estimateLength() {
            return Constant.TWO;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(mField));
        }

        @Override
        public final void appendTo(final Appendable buffer, final int value) throws IOException {
            if (value < Constant.HUNDRED) {
                appendDigits(buffer, value);
            } else {
                appendFullDigits(buffer, value, Constant.TWO);
            }
        }
    }

    /**
     * [内部类输出两位数的年份。](Inner class to output a two digit year.)
     * @description: zh - 内部类输出两位数的年份。
     * @description: en - Inner class to output a two digit year.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:24 下午
    */
    private static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        TwoDigitYearField() {
        }

        @Override
        public int estimateLength() {
            return Constant.TWO;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(Calendar.YEAR) % Constant.HUNDRED);
        }

        @Override
        public final void appendTo(final Appendable buffer, final int value) throws IOException {
            appendDigits(buffer, value);
        }
    }

    /**
     * [内部类输出两位数的月份。](Inner class to output a two digit month.)
     * @description: zh - 内部类输出两位数的月份。
     * @description: en - Inner class to output a two digit month.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:25 下午
    */
    private static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        TwoDigitMonthField() {
        }

        @Override
        public int estimateLength() {
            return Constant.TWO;
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(Calendar.MONTH) + Constant.ONE);
        }

        @Override
        public final void appendTo(final Appendable buffer, final int value) throws IOException {
            appendDigits(buffer, value);
        }
    }

    /**
     * [内部类输出12小时字段。](Inner class to output the twelve hour field.)
     * @description: zh - 内部类输出12小时字段。
     * @description: en - Inner class to output the twelve hour field.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 1:36 下午
    */
    private static class TwelveHourField implements NumberRule {
        private final NumberRule mRule;

        /**
         * [用指定的NumberRule构造TwelveHourField的实例。](Constructs an instance of TwelveHourField with the specified NumberRule.)
         * @description: zh - 用指定的NumberRule构造TwelveHourField的实例。
         * @description: en - Constructs an instance of TwelveHourField with the specified NumberRule.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 1:26 下午
         * @param rule: 规则
        */
        TwelveHourField(final NumberRule rule) {
            mRule = rule;
        }

        @Override
        public int estimateLength() {
            return mRule.estimateLength();
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            int value = calendar.get(Calendar.HOUR);
            if (value == Constant.ZERO) {
                value = calendar.getLeastMaximum(Calendar.HOUR) + Constant.ONE;
            }
            mRule.appendTo(buffer, value);
        }

        @Override
        public void appendTo(final Appendable buffer, final int value) throws IOException {
            mRule.appendTo(buffer, value);
        }
    }

    /**
     * [内部类输出二十四小时字段。](内部类输出二十四小时字段。)
     * @description: zh - 内部类输出二十四小时字段。
     * @description: en - Inner class to output the twenty four hour field.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:27 下午
    */
    private static class TwentyFourHourField implements NumberRule {
        private final NumberRule mRule;

        TwentyFourHourField(final NumberRule rule) {
            mRule = rule;
        }

        @Override
        public int estimateLength() {
            return mRule.estimateLength();
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            int value = calendar.get(Calendar.HOUR_OF_DAY);
            if (value == Constant.ZERO) {
                value = calendar.getMaximum(Calendar.HOUR_OF_DAY) + Constant.ONE;
            }
            mRule.appendTo(buffer, value);
        }

        @Override
        public void appendTo(final Appendable buffer, final int value) throws IOException {
            mRule.appendTo(buffer, value);
        }
    }

    /**
     * [内部类以输出星期中的数字日。](Inner class to output the numeric day in week.)
     * @description: zh - 内部类以输出星期中的数字日。
     * @description: en - Inner class to output the numeric day in week.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:27 下午
    */
    private static class DayInWeekField implements NumberRule {
        private final NumberRule mRule;

        DayInWeekField(final NumberRule rule) {
            mRule = rule;
        }

        @Override
        public int estimateLength() {
            return mRule.estimateLength();
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            final int value = calendar.get(Calendar.DAY_OF_WEEK);
            mRule.appendTo(buffer, value != Calendar.SUNDAY ? value - Constant.ONE : Constant.SEVEN);
        }

        @Override
        public void appendTo(final Appendable buffer, final int value) throws IOException {
            mRule.appendTo(buffer, value);
        }
    }

    /**
     * [内部类以输出星期中的数字日。](Inner class to output the numeric day in week.)
     * @description: zh - 内部类以输出星期中的数字日。
     * @description: en - Inner class to output the numeric day in week.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 2:28 下午
    */
    private static class WeekYear implements NumberRule {
        private final NumberRule mRule;

        WeekYear(final NumberRule rule) {
            mRule = rule;
        }

        @Override
        public int estimateLength() {
            return mRule.estimateLength();
        }

        @Override
        public void appendTo(final Appendable buffer, final Calendar calendar) throws IOException {
            mRule.appendTo(buffer, calendar.getWeekYear());
        }

        @Override
        public void appendTo(final Appendable buffer, final int value) throws IOException {
            mRule.appendTo(buffer, value);
        }
    }

    /**
     * [充当时区名称复合键的内部类。](An inner class that acts as a composite key for the time zone name.)
     * @description: zh - 充当时区名称复合键的内部类。
     * @description: en - An inner class that acts as a composite key for the time zone name.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 3:15 下午
    */
    private static class TimeZoneDisplayKey {
        private final TimeZone mTimeZone;
        private final int mStyle;
        private final Locale mLocale;

        /**
         * Constructs an instance of {@code TimeZoneDisplayKey} with the specified properties.
         *
         * @param timeZone the time zone
         * @param daylight adjust the style for daylight saving time if {@code true}
         * @param style the timezone style
         * @param locale the timezone locale
         */
        TimeZoneDisplayKey(final TimeZone timeZone, final boolean daylight, final int style, final Locale locale) {
            mTimeZone = timeZone;
            if (daylight) {
                mStyle = style | 0x80000000;
            } else {
                mStyle = style;
            }
            mLocale = locale;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return (mStyle * 31 + mLocale.hashCode()) * 31 + mTimeZone.hashCode();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TimeZoneDisplayKey) {
                final TimeZoneDisplayKey other = (TimeZoneDisplayKey) obj;
                return mTimeZone.equals(other.mTimeZone) && mStyle == other.mStyle && mLocale.equals(other.mLocale);
            }
            return false;
        }
    }


}
