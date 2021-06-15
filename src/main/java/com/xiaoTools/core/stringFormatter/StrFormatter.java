package com.xiaoTools.core.stringFormatter;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [格式化字符串](format string )
 * @description: zh - 格式化字符串
 * @description: en - format string
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/15 6:46 下午
*/
public class StrFormatter {
    public static String format(final String strPattern, final Object... argArray) {
        if (StrUtil.isBlank(strPattern) || ArrayUtil.isEmpty(argArray)) {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();
        // 初始化定义好的长度以获得更好的性能
        StringBuilder sbuf = new StringBuilder(strPatternLength + Constant.FIFTY);
        // 记录已经处理到的位置
        int handledPosition = Constant.ZERO;
        int delimIndex;// 占位符所在位置
        for (int argIndex = Constant.ZERO; argIndex < argArray.length; argIndex++) {
            delimIndex = strPattern.indexOf(Constant.BRACKETS, handledPosition);
            // 剩余部分无占位符
            if (delimIndex == Constant.NEGATIVE_ONE) {
                // 不带占位符的模板直接返回
                if (handledPosition == Constant.ZERO) {
                    return strPattern;
                }
                // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                sbuf.append(strPattern, handledPosition, strPatternLength);
                return sbuf.toString();
            }

            // 转义符
            if (delimIndex > Constant.ZERO && strPattern.charAt(delimIndex - Constant.ONE) == Constant.DOUBLE_CHAR_SLASH) {
                // 双转义符
                if (delimIndex > Constant.ONE && strPattern.charAt(delimIndex - Constant.TWO) == Constant.DOUBLE_CHAR_SLASH) {
                    // 转义符之前还有一个转义符，占位符依旧有效
                    sbuf.append(strPattern, handledPosition, delimIndex - Constant.ONE);
                    sbuf.append(StrUtil.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + Constant.TWO;
                } else {
                    // 占位符被转义
                    argIndex--;
                    sbuf.append(strPattern, handledPosition, delimIndex - Constant.ONE);
                    sbuf.append(Constant.LEFT_BRACKETS);
                    handledPosition = delimIndex + Constant.ONE;
                }
            } else {
                // 正常占位符
                sbuf.append(strPattern, handledPosition, delimIndex);
                sbuf.append(StrUtil.utf8Str(argArray[argIndex]));
                handledPosition = delimIndex + Constant.TWO;
            }
        }
        // 加入最后一个占位符后所有的字符
        sbuf.append(strPattern, handledPosition, strPattern.length());

        return sbuf.toString();
    }
}
