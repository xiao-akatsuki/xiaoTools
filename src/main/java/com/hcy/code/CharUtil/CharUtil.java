package com.hcy.code.CharUtil;

/**
 * char工具类
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
}
