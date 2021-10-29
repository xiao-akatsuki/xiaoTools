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

    /**
     * [比较两个字符是否相同](Compare two characters to see if they are the same)
     * @description: zh - 比较两个字符是否相同
     * @description: en - Compare two characters to see if they are the same
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:12 上午
     * @param c1: 字符1
     * @param c2: 字符2
     * @param ignoreCase: 是否忽略大小写
     * @return boolean
    */
    public static boolean equals(char c1, char c2, boolean ignoreCase) {
        if (ignoreCase) {
            return Character.toLowerCase(c1) == Character.toLowerCase(c2);
        }
        return c1 == c2;
    }

	/**
	 * [是否为可见ASCII字符](Is it a visible ASCII character)
	 * @description zh - 是否为可见ASCII字符
	 * @description en - Is it a visible ASCII character
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 20:47:26
	 * @param ch 被检查的字符
	 * @return boolean
	 */
	public static boolean isAsciiPrintable(char ch) {
		return ch >= 32 && ch < 127;
	}

	/**
	 * [获取给定字符的16进制数值](Gets the hexadecimal value of the given character)
	 * @description zh - 获取给定字符的16进制数值
	 * @description en - Gets the hexadecimal value of the given character
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-29 20:15:04
	 * @param b 字符
	 * @return int
	 */
	public static int digit16(int b) {
		return Character.digit(b, 16);
	}
}
