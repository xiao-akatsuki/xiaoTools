package com.xiaoTools.core.convert.formatter.numberChineseFormatter;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [将浮点数类型的number转换成英语的表达方式](Convert floating-point number into English expression)
 * @description: zh - 将浮点数类型的number转换成英语的表达方式
 * @description: en - Convert floating-point number into English expression
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/7/4 6:45 下午
*/
public class NumberWordFormatter {

    private static final String[] NUMBER = new String[]{"", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
            "EIGHT", "NINE"};

    private static final String[] NUMBER_TEEN = new String[]{"TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN",
            "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"};

    private static final String[] NUMBER_TEN = new String[]{"TEN", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY",
            "SEVENTY", "EIGHTY", "NINETY"};

    private static final String[] NUMBER_MORE = new String[]{"", "THOUSAND", "MILLION", "BILLION"};

    private static final String[] NUMBER_SUFFIX = new String[]{"k", "w", "", "m", "", "", "b", "", "", "t", "", "", "p", "", "", "e"};

    /**
     * [将阿拉伯数字转为英文表达式](Convert Arabic numerals to English expressions)
     * @description: zh - 将阿拉伯数字转为英文表达式
     * @description: en - Convert Arabic numerals to English expressions
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:47 下午
     * @param value: 阿拉伯数字，可以为Number对象，也可以是普通对象，最后会使用字符串方式处理
     * @return java.lang.String
    */
    public static String format(Object value) {
        return Constant.NULL != value ? format(value.toString()) : Constant.EMPTY;
    }

    /**
     * [将阿拉伯数字转化为简洁计数单位](Convert Arabic numerals to simple units of count)
     * @description: zh - 将阿拉伯数字转化为简洁计数单位
     * @description: en - Convert Arabic numerals to simple units of count
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:49 下午
     * @param value: 被格式化的数字
     * @return java.lang.String
    */
    public static String formatSimple(long value) {
        return formatSimple(value, Constant.TRUE);
    }

    /**
     * [将阿拉伯数字转化为简介计数单位](Convert Arabic numerals into counting units)
     * @description: zh - 将阿拉伯数字转化为简介计数单位
     * @description: en - Convert Arabic numerals into counting units
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 6:55 下午
     * @param value: 对应数字的值
     * @param isTwo: 控制是否为只为k、w
     * @return java.lang.String
    */
    public static String formatSimple(long value, boolean isTwo) {
        if (value < Constant.ONE_THOUSAND) {
            return String.valueOf(value);
        }
        int index = Constant.NEGATIVE_ONE;
        double res = value;
        while (res > Constant.TEN && (!isTwo || index < Constant.ONE)) {
            if (res > Constant.ONE_THOUSAND) {
                res = res / Constant.ONE_THOUSAND;
                index++;
            }
            if (res > Constant.TEN) {
                res = res / Constant.TEN;
                index++;
            }
        }
        return String.format(Constant.STRING_S_S, NumUtil.decimalFormat(Constant.STRING_WELL_WELL, res), NUMBER_SUFFIX[index]);
    }

    /**
     * [将阿拉伯数字转为英文表达式](Convert Arabic numerals to English expressions)
     * @description: zh - 将阿拉伯数字转为英文表达式
     * @description: en - Convert Arabic numerals to English expressions
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/4 7:03 下午
     * @param value: 阿拉伯数字字符串
     * @return java.lang.String
    */
    private static String format(String value) {
        // 取小数点位置
        int z = value.indexOf(Constant.SPOT);
        String lstr, rstr = Constant.EMPTY;
        // 看是否有小数，如果有，则分别取左边和右边
        if (z > Constant.NEGATIVE_ONE) {
            lstr = value.substring(Constant.ZERO, z);
            rstr = value.substring(z + Constant.ONE);
        } else {
            // 否则就是全部
            lstr = value;
        }
        // 对左边的字串取反
        String lstrrev = StrUtil.reverse(lstr);
        // 定义5个字串变量来存放解析出来的叁位一组的字串
        String[] a = new String[Constant.FIVE];

        switch (lstrrev.length() % Constant.THREE) {
            case Constant.ONE -> lstrrev += "00";
            case Constant.TWO -> lstrrev += "0";
        }
        // 用来存放转换后的整数部分
        StringBuilder lm = new StringBuilder();
        for (int i = Constant.ZERO; i < lstrrev.length() / Constant.THREE; i++) {
            // 截取第一个三位
            a[i] = StrUtil.reverse(lstrrev.substring(Constant.THREE * i, Constant.THREE * i + Constant.THREE));
            // 用来避免这种情况：1000000 = one million
            if (!"000".equals(a[i])) {
                // thousand only
                if (i != Constant.ZERO) {
                    // 加:
                    lm.insert(Constant.ZERO, transThree(a[i]) + " " + parseMore(i) + " ");
                    // thousand、million、billion
                } else {
                    // 防止i=0时， 在多加两个空格.
                    lm = new StringBuilder(transThree(a[i]));
                }
            } else {
                lm.append(transThree(a[i]));
            }
        }
        // 用来存放转换后小数部分
        String xs = "";
        if (z > Constant.NEGATIVE_ONE) {
            // 小数部分存在时转换小数
            xs = "AND CENTS " + transTwo(rstr) + " ";
        }

        return lm.toString().trim() + " " + xs + "ONLY";
    }

    private static String parseFirst(String s) {
        return NUMBER[Integer.parseInt(s.substring(s.length() - Constant.ONE))];
    }

    private static String parseTeen(String s) {
        return NUMBER_TEEN[Integer.parseInt(s) - Constant.TEN];
    }

    private static String parseTen(String s) {
        return NUMBER_TEN[Integer.parseInt(s.substring(Constant.ZERO, Constant.ONE)) - Constant.ONE];
    }

    private static String parseMore(int i) {
        return NUMBER_MORE[i];
    }

    private static String transTwo(String s) {
        String value;
        // 判断位数
        if (s.length() > Constant.TWO) {
            s = s.substring(Constant.ZERO, Constant.TWO);
        } else if (s.length() < Constant.TWO) {
            s = Constant.STRING_ZERO + s;
        }

        if (s.startsWith(Constant.STRING_ZERO)) {
            // 07 - seven 是否小於10
            value = parseFirst(s);
        } else if (s.startsWith(Constant.STRING_ONE)) {
            // 17 seventeen 是否在10和20之间
            value = parseTeen(s);
        } else if (s.endsWith(Constant.STRING_ZERO)) {
            // 是否在10与100之间的能被10整除的数
            value = parseTen(s);
        } else {
            value = parseTen(s) + " " + parseFirst(s);
        }
        return value;
    }

    private static String transThree(String s) {
        String value;
        if (s.startsWith(Constant.STRING_ZERO)) {
            // 是否小于100
            value = transTwo(s.substring(1));
        } else if ("00".equals(s.substring(1))) {
            // 是否被100整除
            value = parseFirst(s.substring(0, 1)) + " HUNDRED";
        } else {
            value = parseFirst(s.substring(0, 1)) + " HUNDRED AND " + transTwo(s.substring(1));
        }
        return value;
    }
}
