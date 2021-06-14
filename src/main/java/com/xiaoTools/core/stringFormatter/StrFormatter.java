package com.xiaoTools.core.stringFormatter;

import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.strUtil.StrUtil;

public class StrFormatter {
    public static String format(final String strPattern, final Object... argArray) {
        if (StrUtil.isBlank(strPattern) || ArrayUtil.isEmpty(argArray)) {
            return strPattern;
        }
        final int strPatternLength = strPattern.length();
        // 初始化定义好的长度以获得更好的性能
        StringBuilder sbuf = new StringBuilder(strPatternLength + 50);
        // 记录已经处理到的位置
        int handledPosition = 0;
        int delimIndex;// 占位符所在位置
        for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
            delimIndex = strPattern.indexOf("{}", handledPosition);
            // 剩余部分无占位符
            if (delimIndex == -1) {
                // 不带占位符的模板直接返回
                if (handledPosition == 0) {
                    return strPattern;
                }
                // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                sbuf.append(strPattern, handledPosition, strPatternLength);
                return sbuf.toString();
            }

            // 转义符
            if (delimIndex > 0 && strPattern.charAt(delimIndex - 1) == '\\') {
                // 双转义符
                if (delimIndex > 1 && strPattern.charAt(delimIndex - 2) == '\\') {
                    // 转义符之前还有一个转义符，占位符依旧有效
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(StrUtil.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + 2;
                } else {
                    // 占位符被转义
                    argIndex--;
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append("{");
                    handledPosition = delimIndex + 1;
                }
            } else {
                // 正常占位符
                sbuf.append(strPattern, handledPosition, delimIndex);
                sbuf.append(StrUtil.utf8Str(argArray[argIndex]));
                handledPosition = delimIndex + 2;
            }
        }

        // append the characters following the last {} pair.
        // 加入最后一个占位符后所有的字符
        sbuf.append(strPattern, handledPosition, strPattern.length());

        return sbuf.toString();
    }
}
