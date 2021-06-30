package com.xiaoTools.util.hexUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.strUtil.StrUtil;

import java.nio.charset.Charset;

/**
 * [16进制工具](Hex tool)
 * @author XiaoXunYao
 * @since 2021/5/15 12:10 下午
*/
public class HexUtil {
    /**
     * zh - 十六进制的单个字符串
     * en - Single string in hexadecimal
     */
    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String HEXADECIMAL_STRING= "0123456789abcdef";

    /**
     * [初始化项目](Initialize project)
     * @author XiaoXunYao
     * @since 2021/5/15 12:11 下午
    */
    public HexUtil() { }

    /*判断给定字符串是否为16进制数-----------------------------------------------------------is Hex Number*/

    /**
     * [判断给定字符串是否为16进制数](Determine whether the given string is a hexadecimal number)
     * @description: zh - 判断给定字符串是否为16进制数
     * @description: en - Determine whether the given string is a hexadecimal number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/30 7:55 上午
     * @param value: 值
     * @return boolean
    */
    public static boolean isHexNumber(String value) {
        final int index = (value.startsWith(Constant.DASH) ? Constant.ONE : Constant.ZERO);
        if (value.startsWith(Constant.STRING_ZERO_X, index) || value.startsWith(Constant.STRING_ZERO_X, index) || value.startsWith(Constant.STRING_WELL_NUMBER, index)) {
            try {
                //方法联合去噪结果
                Long.decode(value);
            } catch (NumberFormatException e) {
                return Constant.FALSE;
            }
            return Constant.TRUE;
        }
        return Constant.FALSE;
    }

    /*编码-----------------------------------------------------------encode*/

    /**
     * [将字节数组转换为十六进制字符数组](Convert byte array to hexadecimal character array)
     * @description: zh - 将字节数组转换为十六进制字符数组
     * @description: en - Convert byte array to hexadecimal character array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/30 7:58 上午
     * @param data: byte[]
     * @return char[]
    */
    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    /**
     * [将字节数组转换为十六进制字符数组](Convert byte array to hexadecimal character array)
     * @description: zh - 将字节数组转换为十六进制字符数组
     * @description: en - Convert byte array to hexadecimal character array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/30 8:01 上午
     * @param str: 字符串
     * @param charset: 编码
     * @return char[]
    */
    public static char[] encodeHex(String str, Charset charset) {
        return encodeHex(StrUtil.bytes(str, charset), true);
    }

    /**
     * [将字节数组转换为十六进制字符数组](Convert byte array to hexadecimal character array)
     * @description: zh - 将字节数组转换为十六进制字符数组
     * @description: en - Convert byte array to hexadecimal character array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/30 8:02 上午
     * @param data: byte[]
     * @param toLowerCase: true 传换成小写格式 ， false 传换成大写格式
     * @return char[]
    */
    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * [将字节数组转换为十六进制字符数组](Convert byte array to hexadecimal character array)
     * @description: zh - 将字节数组转换为十六进制字符数组
     * @description: en - Convert byte array to hexadecimal character array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/30 8:05 上午
     * @param data: byte[]
     * @param toDigits: 用于控制输出的char[]
     * @return char[]
    */
    private static char[] encodeHex(byte[] data, char[] toDigits) {
        final int len = data.length;
        //len*2
        final char[] out = new char[len << Constant.ONE];
        //十六进制值中的两个字符。
        for (int i = Constant.ZERO, j = Constant.ZERO; i < len; i++) {
            // 高位
            out[j++] = toDigits[(Constant.ZERO_X_F_ZERO & data[i]) >>> Constant.FOUR];
            // 低位
            out[j++] = toDigits[Constant.ZERO_X_ZERO_F & data[i]];
        }
        return out;
    }

    /**
     * [将字符串转换为十六进制字符串，结果为小写](Converts a string to a hexadecimal string and the result is lowercase)
     * @description: zh - 将字符串转换为十六进制字符串，结果为小写
     * @description: en - Converts a string to a hexadecimal string and the result is lowercase
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/30 7:48 上午
     * @param data: 需要被编码的字符串
     * @param charset: 编码
     * @return java.lang.String
    */
    public static String encodeHexStr(String data, Charset charset) {
        return encodeHexStr(StrUtil.bytes(data, charset), true);
    }

    /**
     * [将字符串转换为十六进制字符串，结果为小写](Converts a string to a hexadecimal string and the result is lowercase)
     * @description: zh - 将字符串转换为十六进制字符串，结果为小写
     * @description: en - Converts a string to a hexadecimal string and the result is lowercase
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/30 9:33 下午
     * @param data: 被编码的字符串
     * @return java.lang.String
    */
    public static String encodeHexStr(String data) {
        return encodeHexStr(data, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * [将字节数组转换为十六进制字符串](Converts a byte array to a hexadecimal string)
     * @description: zh - 将字节数组转换为十六进制字符串
     * @description: en - Converts a byte array to a hexadecimal string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/30 9:34 下午
     * @param data: byte[]
     * @param toLowerCase: true 传换成小写格式 ， false 传换成大写格式
     * @return java.lang.String
    */
    public static String encodeHexStr(byte[] data, boolean toLowerCase) {
        return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /*解码-----------------------------------------------------------decode*/

    public static String decodeHexStr(String hexStr) {
        return decodeHexStr(hexStr, CharsetUtil.CHARSET_UTF_8);
    }


}
