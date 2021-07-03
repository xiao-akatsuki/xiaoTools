package com.xiaoTools.util.hexUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.awt.*;
import java.math.BigInteger;
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
     * @since 2021/7/3 8:03 上午
     * @param data: byte[]
     * @return java.lang.String
    */
    public static String encodeHexStr(byte[] data) {
        return encodeHexStr(data, true);
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

    /**
     * [将十六进制字符数组转换为字符串，默认编码UTF-8](Convert hexadecimal character array to string, default encoding UTF-8)
     * @description: zh - 将十六进制字符数组转换为字符串，默认编码UTF-8
     * @description: en - Convert hexadecimal character array to string, default encoding UTF-8
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 7:50 上午
     * @param hexStr: 十六进制String
     * @return java.lang.String
    */
    public static String decodeHexStr(String hexStr) {
        return decodeHexStr(hexStr, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * [将十六进制字符数组转换为字符串](Converts a hexadecimal character array to a string)
     * @description: zh - 将十六进制字符数组转换为字符串
     * @description: en - Converts a hexadecimal character array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 7:51 上午
     * @param hexStr: 十六进制String
     * @param charset: 编码
     * @return java.lang.String
    */
    public static String decodeHexStr(String hexStr, Charset charset) {
        return StrUtil.isEmpty(hexStr) ? hexStr : decodeHexStr(hexStr.toCharArray(), charset);
    }

    /**
     * [将十六进制字符数组转换为字符串](Converts a hexadecimal character array to a string)
     * @description: zh - 将十六进制字符数组转换为字符串
     * @description: en - Converts a hexadecimal character array to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 7:52 上午
     * @param hexData: 十六进制char[]
     * @param charset: 编码
     * @return java.lang.String
    */
    public static String decodeHexStr(char[] hexData, Charset charset) {
        return StrUtil.str(decodeHex(hexData), charset);
    }

    /**
     * [将十六进制字符数组转换为字节数组](Converts a hexadecimal character array to a byte array)
     * @description: zh - 将十六进制字符数组转换为字节数组
     * @description: en - Converts a hexadecimal character array to a byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 7:54 上午
     * @param hexData: 十六进制char[]
     * @return byte[]
    */
    public static byte[] decodeHex(char[] hexData) {
        int len = hexData.length;
        if ((len & Constant.ZERO_X_ZERO_ONE) != Constant.ZERO) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        // two characters form the hex value.
        for (int i = Constant.ZERO, j = Constant.ZERO; j < len; i++) {
            int f = toDigit(hexData[j], j) << Constant.FOUR;
            j++;
            f = f | toDigit(hexData[j], j);
            j++;
            out[i] = (byte) (f & Constant.ZERO_X_F_F);
        }
        return out;
    }

    /**
     * [将十六进制字符串解码为byte[]](Decode hexadecimal string to byte [])
     * @description: zh - 将十六进制字符串解码为byte[]
     * @description: en - Decode hexadecimal string to byte []
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 7:58 上午
     * @param hexStr: 十六进制String
     * @return byte[]
    */
    public static byte[] decodeHex(String hexStr) {
        if (StrUtil.isEmpty(hexStr)) {
            return null;
        }
        hexStr = StrUtil.cleanBlank(hexStr);
        return decodeHex(hexStr.toCharArray());
    }

    /*编码-----------------------------------------------------------Color*/

    /**
     * [将Color编码为Hex形式](Encode color to hex form)
     * @description: zh - 将Color编码为Hex形式
     * @description: en - Encode color to hex form
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 7:59 上午
     * @param color: Color
     * @return java.lang.String
    */
    public static String encodeColor(Color color) {
        return encodeColor(color, Constant.STRING_WELL_NUMBER);
    }

    /**
     * [将Color编码为Hex形式](Encode color to hex form)
     * @description: zh - 将Color编码为Hex形式
     * @description: en - Encode color to hex form
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 8:03 上午
     * @param color: Color
     * @param prefix: 前缀字符串，可以是#、0x等
     * @return java.lang.String
    */
    public static String encodeColor(Color color, String prefix) {
        final StringBuilder builder = new StringBuilder(prefix);
        String colorHex;
        colorHex = Integer.toHexString(color.getRed());
        if (Constant.ONE == colorHex.length()) {
            builder.append(Constant.CHAR_ZERO);
        }
        builder.append(colorHex);
        colorHex = Integer.toHexString(color.getGreen());
        if (Constant.ONE == colorHex.length()) {
            builder.append(Constant.CHAR_ZERO);
        }
        builder.append(colorHex);
        colorHex = Integer.toHexString(color.getBlue());
        if (Constant.ONE == colorHex.length()) {
            builder.append(Constant.CHAR_ZERO);
        }
        builder.append(colorHex);
        return builder.toString();
    }

    /**
     * [将Hex颜色值转为Color](Convert hex color value to color)
     * @description: zh - 将Hex颜色值转为Color
     * @description: en - Convert hex color value to color
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 8:04 上午
     * @param hexColor: 16进制颜色值，可以以#开头，也可以用0x开头
     * @return java.awt.Color
    */
    public static Color decodeColor(String hexColor) {
        return Color.decode(hexColor);
    }

    /**
     * [将指定int值转换为Unicode字符串形式](Converts the specified int value to a Unicode string)
     * @description: zh - 将指定int值转换为Unicode字符串形式
     * @description: en - Converts the specified int value to a Unicode string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/1 8:31 上午
     * @param value: int值，也可以是char
     * @return java.lang.String
    */
    public static String toUnicodeHex(int value) {
        final StringBuilder builder = new StringBuilder(Constant.SIX);
        builder.append(Constant.STRING_PATTERN_U);
        String hex = toHex(value);
        int len = hex.length();
        if (len < Constant.FOUR) {
            // 不足4位补0
            builder.append(Constant.STRING_FOURS_ZERO, Constant.ZERO, Constant.FOUR - len);
        }
        builder.append(hex);

        return builder.toString();
    }

    /**
     * [将指定char值转换为Unicode字符串形式](Converts the specified char value to unicode string form)
     * @description: zh - 将指定char值转换为Unicode字符串形式
     * @description: en - Converts the specified char value to unicode string form
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/3 7:48 上午
     * @param ch: char值
     * @return java.lang.String
    */
    public static String toUnicodeHex(char ch) {
        return Constant.STRING_PATTERN_U +
                DIGITS_LOWER[(ch >> Constant.TWELVE) & Constant.FIFTEEN] +
                DIGITS_LOWER[(ch >> Constant.EIGHT) & Constant.FIFTEEN] +
                DIGITS_LOWER[(ch >> Constant.FOUR) & Constant.FIFTEEN] +
                DIGITS_LOWER[(ch) & Constant.FIFTEEN];
    }

    /**
     * [转为16进制字符串](Convert to hexadecimal string)
     * @description: zh - 转为16进制字符串
     * @description: en - Convert to hexadecimal string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/3 7:50 上午
     * @param value: int value
     * @return java.lang.String
    */
    public static String toHex(int value) {
        return Integer.toHexString(value);
    }

    /**
     * [转为16进制字符串](Convert to hexadecimal string)
     * @description: zh - 转为16进制字符串
     * @description: en - Convert to hexadecimal string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/3 7:50 上午
     * @param value: long value
     * @return java.lang.String
    */
    public static String toHex(long value) {
        return Long.toHexString(value);
    }

    /**
     * [将byte值转为16进制并添加到StringBuilder中](Convert the byte value to hexadecimal and add it to StringBuilder)
     * @description: zh - 将byte值转为16进制并添加到StringBuilder中
     * @description: en - Convert the byte value to hexadecimal and add it to StringBuilder
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/3 7:54 上午
     * @param builder: StringBuilder
     * @param b: byte
     * @param toLowerCase: 是否使用小写
    */
    public static void appendHex(StringBuilder builder, byte b, boolean toLowerCase) {
        final char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;
        //计算高位
        int high = (b & Constant.ZERO_X_F_ZERO_DOWN) >>> Constant.FOUR;
        //计算低位
        int low = b & Constant.BYTE_ZERO;
        builder.append(toDigits[high]);
        builder.append(toDigits[low]);
    }

    /**
     * [Hex（16进制）字符串转为BigInteger](Hex (hexadecimal) string to BigInteger)
     * @description: zh - Hex（16进制）字符串转为BigInteger
     * @description: en - Hex (hexadecimal) string to BigInteger
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/3 7:56 上午
     * @param hexStr: Hex(16进制字符串)
     * @return java.math.BigInteger
    */
    public static BigInteger toBigInteger(String hexStr) {
        return Constant.NULL == hexStr ? null : new BigInteger(hexStr, Constant.SIXTEEN);
    }

    /**
     * [格式化Hex字符串](Format hex string)
     * @description: zh - 格式化Hex字符串
     * @description: en - Format hex string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/3 7:59 上午
     * @param hexStr: Hex字符串
     * @return java.lang.String
    */
    public static String format(String hexStr) {
        final int length = hexStr.length();
        final StringBuilder builder = StrUtil.builder(length + length / Constant.TWO);
        builder.append(hexStr.charAt(Constant.ZERO)).append(hexStr.charAt(Constant.ONE));
        for (int i = Constant.TWO; i < length - Constant.ONE; i += Constant.TWO) {
            builder.append(Constant.CHAR_SPACE).append(hexStr.charAt(i)).append(hexStr.charAt(i + Constant.ONE));
        }
        return builder.toString();
    }

    /**
     *
     * @description: zh - 将字节数组转换为十六进制字符串
     * @description: en - Converts a byte array to a hexadecimal string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/3 8:01 上午
     * @param data: byte[]
     * @param toDigits: 用于控制输出的char[]
     * @return java.lang.String
    */
    private static String encodeHexStr(byte[] data, char[] toDigits) {
        return new String(encodeHex(data, toDigits));
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
     * [将十六进制字符转换成一个整数](Converts a hexadecimal character to an integer)
     * @description: zh - 将十六进制字符转换成一个整数
     * @description: en - Converts a hexadecimal character to an integer
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/3 8:01 上午
     * @param ch: 十六进制char
     * @param index: 十六进制字符在字符数组中的位置
     * @return int
    */
    private static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }
}
