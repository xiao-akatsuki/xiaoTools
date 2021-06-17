package com.xiaoTools.cache.asciiStrCache;

import com.xiaoTools.lang.constant.Constant;

/**
 * [ASCII字符对应的字符串缓存](String cache for ASCII characters)
 * @description: zh - ASCII字符对应的字符串缓存
 * @description: en - String cache for ASCII characters
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 3:06 下午
*/
public class AsciiStrCache {

    private static final String[] CACHE = new String[Constant.ONE_TWO_EIGHT];

    static {
        for (char c = 0; c < Constant.ONE_TWO_EIGHT; c++) {
            CACHE[c] = String.valueOf(c);
        }
    }

    /**
     * [字符转为字符串,如果为ASCII字符，使用缓存](The character is converted to a string. If it is an ASCII character, the cache is used)
     * @description: zh - 字符转为字符串,如果为ASCII字符，使用缓存
     * @description: en - The character is converted to a string. If it is an ASCII character, the cache is used
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:07 下午
     * @param c: 字符
     * @return java.lang.String
    */
    public static String toString(char c) {
        return c < Constant.ONE_TWO_EIGHT ? CACHE[c] : String.valueOf(c);
    }

}
