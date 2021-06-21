package com.xiaoTools.core.convert.formatter.numberChineseFormatter;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [数字转中文类](Digital to Chinese)
 * @description: zh - 数字转中文类
 * @description: en - Digital to Chinese
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 9:30 上午
*/
public class NumberChineseFormatter {

    /**
     * 简体中文形式
     **/
    private static final String[] SIMPLE_DIGITS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    /**
     * 繁体中文形式
     **/
    private static final String[] TRADITIONAL_DIGITS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    /**
     * 简体中文单位
     **/
    private static final String[] SIMPLE_UNITS = {"", "十", "百", "千"};

    /**
     * 繁体中文单位
     **/
    private static final String[] TRADITIONAL_UNITS = {"", "拾", "佰", "仟"};

    /**
     * 汉字转阿拉伯数字的
     */
    private static final ChineseNameValue[] CHINESE_NAME_VALUE = {
            new ChineseNameValue("十", Constant.TEN, Constant.FALSE),
            new ChineseNameValue("百", Constant.HUNDRED, Constant.FALSE),
            new ChineseNameValue("千", Constant.ONE_THOUSAND, Constant.FALSE),
            new ChineseNameValue("万", Constant.TEN_THOUSAND, Constant.TRUE),
            new ChineseNameValue("亿", Constant.ONE_HUNDRED_MILLION, Constant.TRUE),
    };

    /**
     * [阿拉伯数字转换成中文,小数点后四舍五入保留两位. 使用于整数、小数的转换.](Arabic numerals into Chinese, decimal point rounded to retain two digits. Used for integer, decimal conversion)
     * @description: zh - 阿拉伯数字转换成中文,小数点后四舍五入保留两位. 使用于整数、小数的转换.
     * @description: en - Arabic numerals into Chinese, decimal point rounded to retain two digits. Used for integer, decimal conversion
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:36 上午
     * @param amount: 数字
     * @param isUseTraditional: 是否使用繁体
     * @return java.lang.String
    */
    public static String format(double amount, boolean isUseTraditional) {
        return format(amount, isUseTraditional, Constant.FALSE);
    }

    /**
     * [阿拉伯数字转换成中文,小数点后四舍五入保留两位. 使用于整数、小数的转换.](Arabic numerals into Chinese, decimal point rounded to retain two digits. Used for integer, decimal conversion)
     * @description: zh - 阿拉伯数字转换成中文,小数点后四舍五入保留两位. 使用于整数、小数的转换.
     * @description: en - Arabic numerals into Chinese, decimal point rounded to retain two digits. Used for integer, decimal conversion
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:39 上午
     * @param amount: 数字
     * @param isUseTraditional: 是否使用繁体
     * @param isMoneyMode: 是否为金额模式
     * @return java.lang.String
    */
    public static String format(double amount, boolean isUseTraditional, boolean isMoneyMode) {
        final String[] numArray = isUseTraditional ? TRADITIONAL_DIGITS : SIMPLE_DIGITS;
        if (amount > Constant.NUMBER_SUPPORT_START || amount < Constant.NUMBER_SUPPORT_END) {
            throw new IllegalArgumentException("Number support only: (-99999999999999.99 ～ 99999999999999.99)！");
        }
        boolean negative = Constant.FALSE;
        if (amount < Constant.ZERO) {
            negative = Constant.TRUE;
            amount = -amount;
        }

        long temp = Math.round(amount * Constant.HUNDRED);
        int numFen = (int) (temp % Constant.TEN);
        temp = temp / Constant.TEN;
        int numJiao = (int) (temp % Constant.TEN);
        temp = temp / Constant.TEN;

        //将数字以万为单位分为多份
        int[] parts = new int[Constant.TWENTY];
        int numParts = Constant.ZERO;
        for (int i = Constant.ZERO; temp != Constant.ZERO; i++) {
            int part = (int) (temp % Constant.TEN_THOUSAND);
            parts[i] = part;
            numParts++;
            temp = temp / Constant.TEN_THOUSAND;
        }

        // 标志“万”下面一级是不是 0
        boolean beforeWanIsZero = Constant.TRUE;

        StringBuilder chineseStr = new StringBuilder();
        for (int i = Constant.ZERO; i < numParts; i++) {
            String partChinese = toChinese(parts[i], isUseTraditional);
            if (i % Constant.TWO == Constant.ZERO) {
                beforeWanIsZero = StrUtil.isEmpty(partChinese);
            }

            if (i != Constant.ZERO) {
                if (i % Constant.TWO == Constant.ZERO) {
                    chineseStr.insert(Constant.ZERO, "亿");
                } else {
                    if ("".equals(partChinese) && !beforeWanIsZero) {
                        // 如果“万”对应的 part 为 0，而“万”下面一级不为 0，则不加“万”，而加“零”
                        chineseStr.insert(Constant.ZERO, "零");
                    } else {
                        if (parts[i - Constant.ONE] < Constant.ONE_THOUSAND && parts[i - Constant.ONE] > Constant.ZERO) {
                            // 如果"万"的部分不为 0, 而"万"前面的部分小于 1000 大于 0， 则万后面应该跟“零”
                            chineseStr.insert(Constant.ZERO, "零");
                        }
                        if (parts[i] > Constant.ZERO) {
                            // 如果"万"的部分不为 0 则增加万
                            chineseStr.insert(Constant.ZERO, "万");
                        }
                    }
                }
            }
            chineseStr.insert(Constant.ZERO, partChinese);
        }

        // 整数部分为 0, 则表达为"零"
        if (Constant.EMPTY.equals(chineseStr.toString())) {
            chineseStr = new StringBuilder(numArray[Constant.ZERO]);
        }
        //负数
        if (negative) {
            // 整数部分不为 0
            chineseStr.insert(Constant.ZERO, "负");
        }

        // 小数部分
        if (numFen != Constant.ZERO || numJiao != Constant.ZERO) {
            if (numFen == Constant.ZERO) {
                chineseStr.append(isMoneyMode ? "元" : "点").append(numArray[numJiao]).append(isMoneyMode ? "角" : "");
            } else {
                // “分”数不为 0
                if (numJiao == Constant.ZERO) {
                    chineseStr.append(isMoneyMode ? "元零" : "点零").append(numArray[numFen]).append(isMoneyMode ? "分" : "");
                } else {
                    chineseStr.append(isMoneyMode ? "元" : "点").append(numArray[numJiao]).append(isMoneyMode ? "角" : "").append(numArray[numFen]).append(isMoneyMode ? "分" : "");
                }
            }
        } else if (isMoneyMode) {
            //无小数部分的金额结尾
            chineseStr.append("元整");
        }

        return chineseStr.toString();
    }

    /**
     * [数字字符转中文，非数字字符原样返回](Numeric characters are converted into Chinese, and non numeric characters are returned as they are)
     * @description: zh - 数字字符转中文，非数字字符原样返回
     * @description: en - Numeric characters are converted into Chinese, and non numeric characters are returned as they are
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:50 上午
     * @param c: 数字字符
     * @param isUseTraditional: 是否繁体
     * @return java.lang.String
    */
    public static String numberCharToChinese(char c, boolean isUseTraditional) {
        String[] numArray = isUseTraditional ? TRADITIONAL_DIGITS : SIMPLE_DIGITS;
        int index = c - Constant.FORTY_EIGHT;
        if (index < Constant.ZERO || index >= numArray.length) {
            return String.valueOf(c);
        }
        return numArray[index];
    }

    /**
     * [把一个 0~9999 之间的整数转换为汉字的字符串，如果是 0 则返回 ""](Convert an integer between 0 and 9999 to a Chinese character string. If it is 0, return ＂)
     * @description: zh - 把一个 0~9999 之间的整数转换为汉字的字符串，如果是 0 则返回 ""
     * @description: en - Convert an integer between 0 and 9999 to a Chinese character string. If it is 0, return ＂
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:53 上午
     * @param amountPart: 数字部分
     * @param isUseTraditional: 是否使用繁体单位
     * @return java.lang.String
    */
    private static String toChinese(int amountPart, boolean isUseTraditional) {
        String[] numArray = isUseTraditional ? TRADITIONAL_DIGITS : SIMPLE_DIGITS;
        String[] units = isUseTraditional ? TRADITIONAL_UNITS : SIMPLE_UNITS;
        int temp = amountPart;
        StringBuilder chineseStr = new StringBuilder();
        // 在从低位往高位循环时，记录上一位数字是不是 0
        boolean lastIsZero = Constant.TRUE;
        for (int i = Constant.ZERO; temp > Constant.ZERO; i++) {
            int digit = temp % Constant.TEN;
            if (digit == Constant.ZERO) {
                // 取到的数字为 0
                if (!lastIsZero) {
                    // 前一个数字不是 0，则在当前汉字串前加“零”字;
                    chineseStr.insert(Constant.ZERO, "零");
                }
                lastIsZero = Constant.TRUE;
            } else { // 取到的数字不是 0
                chineseStr.insert(Constant.ZERO, numArray[digit] + units[i]);
                lastIsZero = Constant.FALSE;
            }
            temp = temp / Constant.TEN;
        }
        return chineseStr.toString();
    }

    /**
     * [把中文转换为数字](Convert Chinese to numbers)
     * @description: zh - 把中文转换为数字
     * @description: en - Convert Chinese to numbers
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:56 上午
     * @param chinese: 中文字符
     * @return int
    */
    public static int chineseToNumber(String chinese) {
        int pos = Constant.ZERO;
        int rtn = Constant.ZERO;
        int section = Constant.ZERO;
        int number = Constant.ZERO;
        boolean secUnit = Constant.FALSE;
        final int length = chinese.length();
        while (pos < length) {
            int num = ArrayUtil.indexOf(SIMPLE_DIGITS, chinese.substring(pos, pos + Constant.ONE));
            if (num >= Constant.ZERO) {
                number = num;
                pos += Constant.ONE;
                if (pos >= length) {
                    section += number;
                    rtn += section;
                    break;
                }
            } else {
                int unit = Constant.ONE;
                int tmp = chineseToUnit(chinese.substring(pos, pos + Constant.ONE));
                if (tmp != Constant.NEGATIVE_ONE) {
                    unit = CHINESE_NAME_VALUE[tmp].value;
                    secUnit = CHINESE_NAME_VALUE[tmp].secUnit;
                }
                if (secUnit) {
                    section = (section + number) * unit;
                    rtn += section;
                    section = Constant.ZERO;
                } else {
                    section += (number * unit);
                }
                number = Constant.ZERO;
                pos += Constant.ONE;
                if (pos >= chinese.length()) {
                    rtn += section;
                    break;
                }
            }
        }
        return rtn;
    }

    /**
     * [查找对应的权](Find the corresponding weight)
     * @description: zh - 查找对应的权
     * @description: en - Find the corresponding weight
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:57 上午
     * @param chinese: 中文权位名
     * @return int
    */
    private static int chineseToUnit(String chinese) {
        for (int i = Constant.ZERO; i < CHINESE_NAME_VALUE.length; i++) {
            if (CHINESE_NAME_VALUE[i].name.equals(chinese)) {
                return i;
            }
        }
        return Constant.NEGATIVE_ONE;
    }

    /**
     * [权位](power and political position)
     * @description: zh - 权位
     * @description: en - power and political position
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 9:32 上午
    */
    private static class ChineseNameValue {
        /**
         * 中文权名称
         */
        private final String name;
        /**
         * 10的倍数值
         */
        private final int value;
        /**
         * 是否为节权位
         */
        private final boolean secUnit;

        public ChineseNameValue(String name, int value, boolean secUnit) {
            this.name = name;
            this.value = value;
            this.secUnit = secUnit;
        }
    }


}
