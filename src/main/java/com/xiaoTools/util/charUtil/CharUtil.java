package com.xiaoTools.util.charUtil;

import com.xiaoTools.cache.asciiStrCache.AsciiStrCache;
import com.xiaoTools.lang.constant.Constant;

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
        return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == Constant.NOTHING_BOM || c == Constant.UTF8_BOM || c == Constant.ZERO;
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
        return Constant.SINGLE_CHAR_SLASH == value || Constant.DOUBLE_CHAR_SLASH == value;
    }

    /**
     * [给定对象对应的类是否为字符类](Is the class corresponding to the given object a character class)
     * @description: zh - 给定对象对应的类是否为字符类
     * @description: en - Is the class corresponding to the given object a character class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:00 下午
     * @param value: 被检查的对象
     * @return boolean
    */
    public static boolean isChar(Object value) {
        return value instanceof Character || value.getClass() == char.class;
    }

    /**
     * [字符转为字符串 如果为ASCII字符，使用缓存](The character is converted to a string. If it is an ASCII character, the cache is used)
     * @description: zh - 字符转为字符串 如果为ASCII字符，使用缓存
     * @description: en - The character is converted to a string. If it is an ASCII character, the cache is used
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:01 下午
     * @param c: 字符
     * @return java.lang.String
    */
    public static String toString(char c) {
        return AsciiStrCache.toString(c);
    }
}
