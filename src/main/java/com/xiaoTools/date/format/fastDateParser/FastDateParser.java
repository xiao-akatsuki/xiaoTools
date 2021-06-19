package com.xiaoTools.date.format.fastDateParser;

import com.xiaoTools.date.format.abstractDateBasic.AbstractDateBasic;
import com.xiaoTools.date.format.dateParser.DateParser;
import com.xiaoTools.lang.constant.Constant;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.*;

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
                case '$':
                case '.':
                case '|':
                case '?':
                case '*':
                case '+':
                case '(':
                case ')':
                case '[':
                case '{':
                    sb.append(Constant.DOUBLE_CHAR_SLASH);
                default:
                    sb.append(c);
            }
        }
        return sb;
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
}
