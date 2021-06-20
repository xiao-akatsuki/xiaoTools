package com.xiaoTools.date.format.fastDateParser;

import com.xiaoTools.date.format.abstractDateBasic.AbstractDateBasic;
import com.xiaoTools.date.format.dateParser.DateParser;
import com.xiaoTools.lang.constant.Constant;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastDateParser extends AbstractDateBasic implements DateParser {

    @Serial
    private static final long serialVersionUID = -3199383897950947498L;

    static final Locale JAPANESE_IMPERIAL = new Locale(Constant.STRING_JA, Constant.STRING_JP, Constant.STRING_JP);

    private final int century;

    private final int startYear;

    /**
     * 衍生字段
     */
    private transient List<StrategyAndWidth> patterns;

    /**
     * 长首小写
     */
    private static final Comparator<String> LONGER_FIRST_LOWERCASE = Comparator.reverseOrder();

    /**
     * 缓存
     */
    private static final ConcurrentMap<Locale, Strategy>[] CACHES = new ConcurrentMap[Calendar.FIELD_COUNT];

    /*初始化--------------------------------------------------------------------init*/

    public FastDateParser(String pattern, TimeZone timeZone, Locale locale) {
        this(pattern, timeZone, locale, null);
    }

    public FastDateParser(final String pattern, final TimeZone timeZone, final Locale locale, final Date centuryStart) {
        super(pattern, timeZone, locale);
        final Calendar definingCalendar = Calendar.getInstance(timeZone, locale);

        int centuryStartYear;
        if (centuryStart != Constant.NULL) {
            definingCalendar.setTime(centuryStart);
            centuryStartYear = definingCalendar.get(Calendar.YEAR);
        } else if (locale.equals(JAPANESE_IMPERIAL)) {
            centuryStartYear = Constant.ZERO;
        } else {
            // from 80 years ago to 20 years from now
            definingCalendar.setTime(new Date());
            centuryStartYear = definingCalendar.get(Calendar.YEAR) - Constant.EIGHTY;
        }
        century = centuryStartYear / Constant.HUNDRED * Constant.HUNDRED;
        startYear = centuryStartYear - century;

        init(definingCalendar);
    }

    /**
     * [从定义字段初始化派生字段。这是从构造函数和readObject调用的（反序列化）](Initialize derived fields from defining fields. This is called from constructor and from readObject (de-serialization))
     * @description: zh - 从定义字段初始化派生字段。这是从构造函数和readObject调用的（反序列化）
     * @description: en - Initialize derived fields from defining fields. This is called from constructor and from readObject (de-serialization)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 6:51 下午
     * @param definingCalendar: 用于初始化此FastDateParser的日历实例
    */
    private void init(final Calendar definingCalendar) {
        patterns = new ArrayList<>();
        final StrategyParser fm = new StrategyParser(definingCalendar);
        for (; ; ) {
            final StrategyAndWidth field = fm.getNextStrategy();
            if (field == Constant.NULL) {
                break;
            }
            patterns.add(field);
        }
    }

    private static class StrategyAndWidth {
        final Strategy strategy;
        final int width;

        StrategyAndWidth(final Strategy strategy, final int width) {
            this.strategy = strategy;
            this.width = width;
        }

        int getMaxWidth(final ListIterator<StrategyAndWidth> lt) {
            if (!strategy.isNumber() || !lt.hasNext()) {
                return Constant.ZERO;
            }
            final Strategy nextStrategy = lt.next().strategy;
            lt.previous();
            return nextStrategy.isNumber() ? width : Constant.ZERO;
        }
    }

    private class StrategyParser {
        final private Calendar definingCalendar;
        private int currentIdx;

        StrategyParser(final Calendar definingCalendar) {
            this.definingCalendar = definingCalendar;
        }

        StrategyAndWidth getNextStrategy() {
            if (currentIdx >= pattern.length()) {
                return (StrategyAndWidth) Constant.NULL;
            }

            final char c = pattern.charAt(currentIdx);
            if (isFormatLetter(c)) {
                return letterPattern(c);
            }
            return literal();
        }

        private StrategyAndWidth letterPattern(final char c) {
            final int begin = currentIdx;
            while (++currentIdx < pattern.length()) {
                if (pattern.charAt(currentIdx) != c) {
                    break;
                }
            }

            final int width = currentIdx - begin;
            return new StrategyAndWidth(getStrategy(c, width, definingCalendar), width);
        }

        private StrategyAndWidth literal() {
            boolean activeQuote = Constant.FALSE;

            final StringBuilder sb = new StringBuilder();
            while (currentIdx < pattern.length()) {
                final char c = pattern.charAt(currentIdx);
                if (!activeQuote && isFormatLetter(c)) {
                    break;
                } else if (c == Constant.CHAR_SLASH_SPOT && (++currentIdx == pattern.length() || pattern.charAt(currentIdx) != Constant.CHAR_SLASH_SPOT)) {
                    activeQuote = !activeQuote;
                    continue;
                }
                ++currentIdx;
                sb.append(c);
            }

            if (activeQuote) {
                throw new IllegalArgumentException("Unterminated quote");
            }

            final String formatField = sb.toString();
            return new StrategyAndWidth(new CopyQuotedStrategy(formatField), formatField.length());
        }
    }

    /**
     * [判断是否是格式字母](Determine whether it is a format letter)
     * @description: zh - 判断是否是格式字母
     * @description: en - Determine whether it is a format letter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:47 下午
     * @param c: 判断字母
     * @return boolean
    */
    private static boolean isFormatLetter(final char c) {
        return c >= Constant.CHAR_UP_A && c <= Constant.CHAR_UP_Z || c >= Constant.CHAR_DOWN_A && c <= Constant.CHAR_DOWN_Z;
    }

    //序列化----------------------------------------------------------------------- Serializing

    /**
     * [序列化后创建对象。此实现将重新初始化瞬态属性。](Create the object after serialization. This implementation reinitializes the transient properties.)
     * @description: zh - 序列化后创建对象。此实现将重新初始化瞬态属性。
     * @description: en - Create the object after serialization. This implementation reinitializes the transient properties.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 7:51 下午
     * @param in: 正在从中反序列化对象的ObjectInputStream。
     * @throws IOException 如果有IO问题。
     * @throws ClassNotFoundException if a class cannot be found.
    */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        final Calendar definingCalendar = Calendar.getInstance(timeZone, locale);
        init(definingCalendar);
    }

    //重写----------------------------------------------------------------------- Override

    @Override
    public Object parseObject(final String source) throws ParseException {
        return parse(source);
    }

    @Override
    public Date parse(final String source) throws ParseException {
        final ParsePosition pp = new ParsePosition(Constant.ZERO);
        final Date date = parse(source, pp);
        if (date == Constant.NULL) {
            // Add a note re supported date range
            if (locale.equals(JAPANESE_IMPERIAL)) {
                throw new ParseException("(The " + locale + " locale does not support dates before 1868 AD)\n" + "Unparseable date: \"" + source, pp.getErrorIndex());
            }
            throw new ParseException("Unparseable date: " + source, pp.getErrorIndex());
        }
        return date;
    }

    @Override
    public Object parseObject(final String source, final ParsePosition pos) {
        return parse(source, pos);
    }

    @Override
    public Date parse(final String source, final ParsePosition pos) {
        final Calendar cal = Calendar.getInstance(timeZone, locale);
        cal.clear();
        return parse(source, pos, cal) ? cal.getTime() : Constant.DATE_NULL;
    }

    @Override
    public boolean parse(final String source, final ParsePosition pos, final Calendar calendar) {
        final ListIterator<StrategyAndWidth> lt = patterns.listIterator();
        while (lt.hasNext()) {
            final StrategyAndWidth strategyAndWidth = lt.next();
            final int maxWidth = strategyAndWidth.getMaxWidth(lt);
            if (!strategyAndWidth.strategy.parse(this, calendar, source, pos, maxWidth)) {
                return Constant.FALSE;
            }
        }
        return Constant.TRUE;
    }

    private static StringBuilder simpleQuote(final StringBuilder sb, final String value) {
        for (int i = Constant.ZERO; i < value.length(); ++i) {
            final char c = value.charAt(i);
            switch (c) {
                case Constant.DOUBLE_CHAR_SLASH:
                case Constant.CHAR_POWER:
                case Constant.CHAR_DOLLAR:
                case Constant.CHAR_SPOT:
                case Constant.CHAR_VERTICAL_LINE:
                case Constant.CHAR_QUESTION_MARK:
                case Constant.CHAR_STAR:
                case Constant.CHAR_PLUS:
                case Constant.CHAR_LEFT_SMALL_BRACKETS:
                case Constant.CHAR_RIGHT_SMALL_BRACKETS:
                case Constant.CHAR_LEFT_MIDDLE_BRACKETS:
                case Constant.CHAR_LEFT_BIG_BRACKETS:
                    sb.append(Constant.DOUBLE_CHAR_SLASH);
                default:
                    sb.append(c);
            }
        }
        return sb;
    }

    /**
     * [获取字段显示的短值和长值](Get the short and long values displayed for a field)
     * @description: zh - 获取字段显示的短值和长值
     * @description: en - Get the short and long values displayed for a field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 9:30 上午
     * @param cal: 获取长短值的日历
     * @param locale: 显示名称的区域设置
     * @param field: 兴趣领域
     * @param regex: 要生成的正则表达式
     * @return java.util.Map<java.lang.String,java.lang.Integer>
    */
    private static Map<String, Integer> appendDisplayNames(final Calendar cal, final Locale locale, final int field, final StringBuilder regex) {
        final Map<String, Integer> values = new HashMap<>();

        final Map<String, Integer> displayNames = cal.getDisplayNames(field, Calendar.ALL_STYLES, locale);
        final TreeSet<String> sorted = new TreeSet<>(LONGER_FIRST_LOWERCASE);
        for (final Map.Entry<String, Integer> displayName : displayNames.entrySet()) {
            final String key = displayName.getKey().toLowerCase(locale);
            if (sorted.add(key)) {
                values.put(key, displayName.getValue());
            }
        }
        for (final String symbol : sorted) {
            simpleQuote(regex, symbol).append(Constant.CHAR_VERTICAL_LINE);
        }
        return values;
    }

    /**
     * [使用当前的世纪调整两位数年份为四位数年份](Use the current century to adjust a two digit year to a four digit year)
     * @description: zh - 使用当前的世纪调整两位数年份为四位数年份
     * @description: en - Use the current century to adjust a two digit year to a four digit year
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 9:36 上午
     * @param twoDigitYear: 两位数的年份
     * @return int
    */
    private int adjustYear(final int twoDigitYear) {
        final int trial = century + twoDigitYear;
        return twoDigitYear >= startYear ? trial : trial + Constant.HUNDRED;
    }

    /**
     * [从解析模式解析单个字段的策略](strategy of parsing single field from parsing mode)
     * @description: zh - 从解析模式解析单个字段的策略
     * @description: en - strategy of parsing single field from parsing mode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 9:43 上午
    */
    private static abstract class PatternStrategy extends Strategy {

        private Pattern pattern;

        void createPattern(final StringBuilder regex) {
            createPattern(regex.toString());
        }

        void createPattern(final String regex) {
            this.pattern = Pattern.compile(regex);
        }

        /**
         * Is this field a number? The default implementation returns false.
         *
         * @return true, if field is a number
         */
        @Override
        boolean isNumber() {
            return false;
        }

        @Override
        boolean parse(final FastDateParser parser, final Calendar calendar, final String source, final ParsePosition pos, final int maxWidth) {
            final Matcher matcher = pattern.matcher(source.substring(pos.getIndex()));
            if (!matcher.lookingAt()) {
                pos.setErrorIndex(pos.getIndex());
                return Constant.FALSE;
            }
            pos.setIndex(pos.getIndex() + matcher.end(Constant.ONE));
            setCalendar(parser, calendar, matcher.group(Constant.ONE));
            return Constant.TRUE;
        }

        abstract void setCalendar(FastDateParser parser, Calendar cal, String value);
    }

    /**
     * [从simpleDataFormat模式中获取给定字段的策略](Obtain a Strategy given a field from a SimpleDateFormat pattern)
     * @description: zh - 从simpleDataFormat模式中获取给定字段的策略
     * @description: en - Obtain a Strategy given a field from a SimpleDateFormat pattern
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 9:58 上午
     * @param f: 格式
     * @param width: 长度
     * @param definingCalendar: 获取长短值的日历
     * @return com.xiaoTools.date.format.fastDateParser.FastDateParser.Strategy
    */
    private Strategy getStrategy(final char f, final int width, final Calendar definingCalendar) {
        switch (f) {
            default:
                throw new IllegalArgumentException("Format '" + f + "' not supported");
            case Constant.CHAR_UP_D:
                return DAY_OF_YEAR_STRATEGY;
            case Constant.CHAR_UP_E:
                return getLocaleSpecificStrategy(Calendar.DAY_OF_WEEK, definingCalendar);
            case Constant.CHAR_UP_F:
                return DAY_OF_WEEK_IN_MONTH_STRATEGY;
            case Constant.CHAR_UP_G:
                return getLocaleSpecificStrategy(Calendar.ERA, definingCalendar);
            case Constant.CHAR_UP_H:
                // Hour in day (0-23)
                return HOUR_OF_DAY_STRATEGY;
            case Constant.CHAR_UP_K:
                // Hour in am/pm (0-11)
                return HOUR_STRATEGY;
            case Constant.CHAR_UP_M:
                return width >= Constant.THREE ? getLocaleSpecificStrategy(Calendar.MONTH, definingCalendar) : NUMBER_MONTH_STRATEGY;
            case Constant.CHAR_UP_S:
                return MILLISECOND_STRATEGY;
            case Constant.CHAR_UP_W:
                return WEEK_OF_MONTH_STRATEGY;
            case Constant.CHAR_DOWN_A:
                return getLocaleSpecificStrategy(Calendar.AM_PM, definingCalendar);
            case Constant.CHAR_DOWN_D:
                return DAY_OF_MONTH_STRATEGY;
            case Constant.CHAR_DOWN_H:
                // Hour in am/pm (1-12), i.e. midday/midnight is 12, not 0
                return HOUR12_STRATEGY;
            case Constant.CHAR_DOWN_K:
                // Hour in day (1-24), i.e. midnight is 24, not 0
                return HOUR24_OF_DAY_STRATEGY;
            case Constant.CHAR_DOWN_M:
                return MINUTE_STRATEGY;
            case Constant.CHAR_DOWN_S:
                return SECOND_STRATEGY;
            case Constant.CHAR_DOWN_U:
                return DAY_OF_WEEK_STRATEGY;
            case Constant.CHAR_DOWN_W:
                return WEEK_OF_YEAR_STRATEGY;
            case Constant.CHAR_DOWN_Y:
            case Constant.CHAR_UP_Y:
                return width > Constant.TWO ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
            case Constant.CHAR_UP_X:
                return ISO8601TimeZoneStrategy.getStrategy(width);
            case Constant.CHAR_UP_Z:
                if (width == Constant.TWO) {
                    return ISO8601TimeZoneStrategy.ISO_8601_3_STRATEGY;
                }
                //$FALL-THROUGH$
            case Constant.CHAR_DOWN_Z:
                return getLocaleSpecificStrategy(Calendar.ZONE_OFFSET, definingCalendar);
        }
    }

    /**
     * [获取特定领域的策略缓存](Get a cache of Strategies for a particular field)
     * @description: zh - 获取特定领域的策略缓存
     * @description: en - Get a cache of Strategies for a particular field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 10:04 上午
     * @param field: 日历字段
     * @return java.util.concurrent.ConcurrentMap<java.util.Locale,com.xiaoTools.date.format.fastDateParser.FastDateParser.Strategy>
    */
    private static ConcurrentMap<Locale, Strategy> getCache(final int field) {
        synchronized (CACHES) {
            if (CACHES[field] == Constant.NULL) {
                CACHES[field] = new ConcurrentHashMap<>(Constant.THREE);
            }
            return CACHES[field];
        }
    }

    /**
     * [构造一个解析文本字段的策略](Construct a Strategy that parses a Text field)
     * @description: zh - 构造一个解析文本字段的策略
     * @description: en - Construct a Strategy that parses a Text field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 10:08 上午
     * @param field: 日历字段
     * @param definingCalendar: 获取长短值的日历
     * @return com.xiaoTools.date.format.fastDateParser.FastDateParser.Strategy
    */
    private Strategy getLocaleSpecificStrategy(final int field, final Calendar definingCalendar) {
        final ConcurrentMap<Locale, Strategy> cache = getCache(field);
        Strategy strategy = cache.get(locale);
        if (strategy == Constant.NULL) {
            strategy = field == Calendar.ZONE_OFFSET ? new TimeZoneStrategy(locale) : new CaseInsensitiveTextStrategy(field, definingCalendar, locale);
            final Strategy inCache = cache.putIfAbsent(locale, strategy);
            if (inCache != Constant.NULL) {
                return inCache;
            }
        }
        return strategy;
    }

    /**
     * [单个日期字段的分析策略](Analysis strategy of single date field)
     * @description: zh - 单个日期字段的分析策略
     * @description: en - Analysis strategy of single date field
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 6:47 下午
    */
    private static abstract class Strategy {
        /**
         * [这个字段是数字吗？默认实现返回false。](Is this field a number? The default implementation returns false.)
         * @description: zh - 这个字段是数字吗？默认实现返回false。
         * @description: en - Is this field a number? The default implementation returns false.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 6:10 下午
         * @return boolean
        */
        boolean isNumber() {
            return Constant.FALSE;
        }

        /**
         * [解析](analysis)
         * @description: zh - 解析
         * @description: en - analysis
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/19 6:42 下午
         * @param parser: 快速日期分析器
         * @param calendar: 日历
         * @param source: 源位置
         * @param pos: 解析位置
         * @param maxWidth: 最大宽度
         * @return boolean
        */
        abstract boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth);
    }

    /**
     * [在解析模式中复制静态或引用字段的策略](A strategy that copies the static or quoted field in the parsing pattern)
     * @description: zh - 在解析模式中复制静态或引用字段的策略
     * @description: en - A strategy that copies the static or quoted field in the parsing pattern
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 10:20 上午
    */
    private static class CopyQuotedStrategy extends Strategy {

        final private String formatField;

        /**
         * [构造一个策略，确保formatField具有文本](Construct a Strategy that ensures the formatField has literal text)
         * @description: zh - 构造一个策略，确保formatField具有文本
         * @description: en - Construct a Strategy that ensures the formatField has literal text
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/20 10:17 上午
         * @param formatField: 要匹配的文本
        */
        CopyQuotedStrategy(final String formatField) {
            this.formatField = formatField;
        }

        @Override
        boolean isNumber() {
            return false;
        }

        @Override
        boolean parse(final FastDateParser parser, final Calendar calendar, final String source, final ParsePosition pos, final int maxWidth) {
            for (int idx = Constant.ZERO; idx < formatField.length(); ++idx) {
                final int sIdx = idx + pos.getIndex();
                if (sIdx == source.length()) {
                    pos.setErrorIndex(sIdx);
                    return Constant.FALSE;
                }
                if (formatField.charAt(idx) != source.charAt(sIdx)) {
                    pos.setErrorIndex(sIdx);
                    return Constant.FALSE;
                }
            }
            pos.setIndex(formatField.length() + pos.getIndex());
            return Constant.TRUE;
        }
    }

    /**
     * [在解析模式中处理文本字段的策略](A strategy that handles a text field in the parsing pattern)
     * @description: zh - 在解析模式中处理文本字段的策略
     * @description: en - A strategy that handles a text field in the parsing pattern
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 10:23 上午
    */
    private static class CaseInsensitiveTextStrategy extends PatternStrategy {
        private final int field;
        final Locale locale;
        private final Map<String, Integer> lKeyValues;

        /**
         * [构造一个解析文本字段的策略](Construct a Strategy that parses a Text field)
         * @description: zh - 构造一个解析文本字段的策略
         * @description: en - Construct a Strategy that parses a Text field
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/20 10:26 上午
         * @param field: 日历字段
         * @param definingCalendar: 要使用的日历
         * @param locale: 要使用的区域设置
        */
        CaseInsensitiveTextStrategy(final int field, final Calendar definingCalendar, final Locale locale) {
            this.field = field;
            this.locale = locale;

            final StringBuilder regex = new StringBuilder();
            regex.append(Constant.STRING_QUESTION_MARK_I_U);
            lKeyValues = appendDisplayNames(definingCalendar, locale, field, regex);
            regex.setLength(regex.length() - Constant.ONE);
            regex.append(Constant.STRING_SMALL_RIGHT_BRACKETS);
            createPattern(regex);
        }

        @Override
        void setCalendar(final FastDateParser parser, final Calendar cal, final String value) {
            final Integer iVal = lKeyValues.get(value.toLowerCase(locale));
            cal.set(field, iVal);
        }
    }

    private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(Calendar.YEAR) {
        @Override
        int modify(final FastDateParser parser, final int iValue) {
            return iValue < Constant.HUNDRED ? parser.adjustYear(iValue) : iValue;
        }
    };


    /**
     * [在解析模式中处理时区字段的策略](A strategy that handles a timezone field in the parsing pattern)
     * @description: zh - 在解析模式中处理时区字段的策略
     * @description: en - A strategy that handles a timezone field in the parsing pattern
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 10:42 上午
    */
    static class TimeZoneStrategy extends PatternStrategy {
        private static final String RFC_822_TIME_ZONE = Constant.RFC_822_TIME_ZONE;
        private static final String UTC_TIME_ZONE_WITH_OFFSET = Constant.UTC_TIME_ZONE_WITH_OFFSET;
        private static final String GMT_OPTION = Constant.GMT_OPTION;

        private final Locale locale;
        private final Map<String, TzInfo> tzNames = new HashMap<>();

        private static class TzInfo {
            TimeZone zone;
            int dstOffset;

            TzInfo(final TimeZone tz, final boolean useDst) {
                zone = tz;
                dstOffset = useDst ? tz.getDSTSavings() : Constant.ZERO;
            }
        }

        /**
         * 区域id索引
         */
        private static final int ID = Constant.ZERO;

        /**
         * [构造一个解析时区的策略](Construct a Strategy that parses a TimeZone)
         * @description: zh - 构造一个解析时区的策略
         * @description: en - Construct a Strategy that parses a TimeZone
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/20 10:51 上午
         * @param locale: 区域设置
        */
        TimeZoneStrategy(final Locale locale) {
            this.locale = locale;

            final StringBuilder sb = new StringBuilder();
            sb.append(Constant.STRING_QUESTION_MARK_I_U + RFC_822_TIME_ZONE + Constant.STRING_VERTICAL_LINE + UTC_TIME_ZONE_WITH_OFFSET + Constant.STRING_VERTICAL_LINE + GMT_OPTION);

            final Set<String> sorted = new TreeSet<>(LONGER_FIRST_LOWERCASE);

            final String[][] zones = DateFormatSymbols.getInstance(locale).getZoneStrings();
            for (final String[] zoneNames : zones) {
                final String tzId = zoneNames[ID];
                if (Constant.GMT.equalsIgnoreCase(tzId)) {
                    continue;
                }
                final TimeZone tz = TimeZone.getTimeZone(tzId);
                final TzInfo standard = new TzInfo(tz, Constant.FALSE);
                TzInfo tzInfo = standard;
                for (int i = Constant.ONE; i < zoneNames.length; ++i) {
                    switch (i) {
                        case Constant.THREE -> tzInfo = new TzInfo(tz, Constant.TRUE);
                        case Constant.FIVE -> tzInfo = standard;
                    }
                    if (zoneNames[i] != Constant.NULL) {
                        final String key = zoneNames[i].toLowerCase(locale);
                        if (sorted.add(key)) {
                            tzNames.put(key, tzInfo);
                        }
                    }
                }
            }
            for (final String zoneName : sorted) {
                simpleQuote(sb.append(Constant.CHAR_VERTICAL_LINE), zoneName);
            }
            sb.append(Constant.STRING_SMALL_RIGHT_BRACKETS);
            createPattern(sb);
        }

        @Override
        void setCalendar(final FastDateParser parser, final Calendar cal, final String value) {
            if (value.charAt(Constant.ZERO) == Constant.CHAR_PLUS || value.charAt(Constant.ZERO) == Constant.CHAR_DASH) {
                final TimeZone tz = TimeZone.getTimeZone(Constant.GMT + value);
                cal.setTimeZone(tz);
            } else if (value.regionMatches(Constant.TRUE, Constant.ZERO, Constant.GMT, Constant.ZERO, Constant.THREE)) {
                final TimeZone tz = TimeZone.getTimeZone(value.toUpperCase());
                cal.setTimeZone(tz);
            } else {
                final TzInfo tzInfo = tzNames.get(value.toLowerCase(locale));
                cal.set(Calendar.DST_OFFSET, tzInfo.dstOffset);
                cal.set(Calendar.ZONE_OFFSET, parser.getTimeZone().getRawOffset());
            }
        }
    }

    private static class ISO8601TimeZoneStrategy extends PatternStrategy {

        /**
         * [构造一个解析时区的策略](Construct a Strategy that parses a TimeZone)
         * @description: zh -  构造一个解析时区的策略
         * @description: en -  Construct a Strategy that parses a TimeZone
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/20 11:07 上午
         * @param pattern: The Pattern
        */
        ISO8601TimeZoneStrategy(final String pattern) {
            createPattern(pattern);
        }

        @Override
        void setCalendar(final FastDateParser parser, final Calendar cal, final String value) {
            if (Objects.equals(value, Constant.STRING_UP_Z)) {
                cal.setTimeZone(TimeZone.getTimeZone(Constant.UTC));
            } else {
                cal.setTimeZone(TimeZone.getTimeZone(Constant.GMT + value));
            }
        }

        private static final Strategy ISO_8601_1_STRATEGY = new ISO8601TimeZoneStrategy(Constant.ISO_8601_1_STRATEGY);
        private static final Strategy ISO_8601_2_STRATEGY = new ISO8601TimeZoneStrategy(Constant.ISO_8601_2_STRATEGY);
        private static final Strategy ISO_8601_3_STRATEGY = new ISO8601TimeZoneStrategy(Constant.ISO_8601_3_STRATEGY);

        /**
         * [ISO8601时区分类的工厂方法。](Factory method for ISO8601TimeZoneStrategies.)
         * @description: zh - ISO8601时区分类的工厂方法。
         * @description: en - Factory method for ISO8601TimeZoneStrategies.
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/20 11:11 上午
         * @param tokenLen:  表示要格式化的时区字符串长度的标记。
         * @return com.xiaoTools.date.format.fastDateParser.FastDateParser.Strategy
        */
        static Strategy getStrategy(final int tokenLen) {
            return switch (tokenLen) {
                case Constant.ONE -> ISO_8601_1_STRATEGY;
                case Constant.TWO -> ISO_8601_2_STRATEGY;
                case Constant.THREE -> ISO_8601_3_STRATEGY;
                default -> throw new IllegalArgumentException("invalid number of X");
            };
        }
    }

    private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(Calendar.MONTH) {
        @Override
        int modify(final FastDateParser parser, final int iValue) {
            return iValue - Constant.ONE;
        }
    };
    private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(Calendar.YEAR);
    private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(Calendar.WEEK_OF_YEAR);
    private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(Calendar.WEEK_OF_MONTH);
    private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(Calendar.DAY_OF_YEAR);
    private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(Calendar.DAY_OF_MONTH);
    private static final Strategy DAY_OF_WEEK_STRATEGY = new NumberStrategy(Calendar.DAY_OF_WEEK) {
        @Override
        int modify(final FastDateParser parser, final int iValue) {
            return iValue != Constant.SEVEN ? iValue + Constant.ONE : Calendar.SUNDAY;
        }
    };
    private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(Calendar.DAY_OF_WEEK_IN_MONTH);
    private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(Calendar.HOUR_OF_DAY);
    private static final Strategy HOUR24_OF_DAY_STRATEGY = new NumberStrategy(Calendar.HOUR_OF_DAY) {
        @Override
        int modify(final FastDateParser parser, final int iValue) {
            return iValue == Constant.TWENTY_FOUR ? Constant.ZERO : iValue;
        }
    };
    private static final Strategy HOUR12_STRATEGY = new NumberStrategy(Calendar.HOUR) {
        @Override
        int modify(final FastDateParser parser, final int iValue) {
            return iValue == Constant.TWELVE ? Constant.ZERO : iValue;
        }
    };
    private static final Strategy HOUR_STRATEGY = new NumberStrategy(Calendar.HOUR);
    private static final Strategy MINUTE_STRATEGY = new NumberStrategy(Calendar.MINUTE);
    private static final Strategy SECOND_STRATEGY = new NumberStrategy(Calendar.SECOND);
    private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(Calendar.MILLISECOND);

    /**
     * [在解析模式中处理数字字段的策略](A strategy that handles a number field in the parsing pattern)
     * @description: zh - 在解析模式中处理数字字段的策略
     * @description: en - A strategy that handles a number field in the parsing pattern
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/20 10:31 上午
    */
    private static class NumberStrategy extends Strategy {
        private final int field;

        /**
         * [构造一个解析数字字段的策略](Construct a Strategy that parses a Number field)
         * @description: zh - 构造一个解析数字字段的策略
         * @description: en - Construct a Strategy that parses a Number field
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/20 10:35 上午
         * @param field: 日历字段
        */
        NumberStrategy(final int field) {
            this.field = field;
        }

        @Override
        boolean isNumber() {
            return Constant.TRUE;
        }

        @Override
        boolean parse(final FastDateParser parser, final Calendar calendar, final String source, final ParsePosition pos, final int maxWidth) {
            int idx = pos.getIndex();
            int last = source.length();

            if (maxWidth == Constant.ZERO) {
                // if no maxWidth, strip leading white space
                for (; idx < last; ++idx) {
                    final char c = source.charAt(idx);
                    if (!Character.isWhitespace(c)) {
                        break;
                    }
                }
                pos.setIndex(idx);
            } else {
                final int end = idx + maxWidth;
                if (last > end) {
                    last = end;
                }
            }

            for (; idx < last; ++idx) {
                final char c = source.charAt(idx);
                if (!Character.isDigit(c)) {
                    break;
                }
            }

            if (pos.getIndex() == idx) {
                pos.setErrorIndex(idx);
                return Constant.FALSE;
            }

            final int value = Integer.parseInt(source.substring(pos.getIndex(), idx));
            pos.setIndex(idx);

            calendar.set(field, modify(parser, value));
            return Constant.TRUE;
        }

        /**
         * [对解析的整数进行任何修改](Make any modifications to parsed integer)
         * @description: zh - 对解析的整数进行任何修改
         * @description: en - Make any modifications to parsed integer
         * @version: V1.0
         * @author XiaoXunYao
         * @since 2021/6/20 10:34 上午
         * @param parser: 解析器
         * @param iValue: 解析的整数
         * @return int
        */
        int modify(final FastDateParser parser, final int iValue) {
            return iValue;
        }

    }
}
