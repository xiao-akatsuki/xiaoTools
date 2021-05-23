package com.xiaoTools.core.charUtil;

/**
 * [char工具类](Char tool class)
 * @author HCY
 * @since 2021/5/14 4:43 下午
*/
public class CharUtil {
    /**
     * 判断char的字符值为空
     * @author HCY
     * @since 2021/5/14 4:44 下午
     * @param c: 判断的是否为空
     * @return boolean
    */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == 65279 || c == 8234 || c == 0;
    }

    /**
     * [输入单个字符串判断是否是文件的地址分隔符](Enter a single string to determine whether it is the address separator of the file)
     * @description: zh - 输入单个字符串判断是否是文件的地址分隔符
     * @description: en - Enter a single string to determine whether it is the address separator of the file
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/5/23 3:20 下午
     * @param value: [单个字符串](Single string)
     * @return boolean
    */
    public static boolean isFileSeparator(char value) {
        return '/' == value || '\\' == value;
    }
}
