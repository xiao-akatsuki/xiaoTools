package com.xiaoTools.util.strUtil;

import com.xiaoTools.core.text.stringBuilder.StrBuilder;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.charSequenceUtil.CharSequenceUtil;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;


/**
 * [字符串工具类](String tool class)
 * @author HCY
 * @since 2021/5/14 3:42 下午
*/
public class StrUtil extends CharSequenceUtil {

    /*判断字符串是否为空白的-----------------------------------------------------------blank*/

    /**
     * [如果对象是字符串是否为空白](If the object is a string, is it blank)
     * @description: zh - 如果对象是字符串是否为空白
     * @description: en - If the object is a string, is it blank
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 6:25 下午
     * @param value: 对象
     * @return boolean
    */
    public static boolean isBlankIfStr(Object value) {
        return Constant.NULL == value ? Constant.TRUE :
                value instanceof CharSequence ? isBlank((CharSequence) value) : Constant.FALSE;
    }

    /*判断字符串是否为空的-----------------------------------------------------------empty*/

    /**
     * [如果对象是字符串是否为空串](If the object is a string, is it an empty string)
     * @description: zh - 如果对象是字符串是否为空串
     * @description: en - If the object is a string, is it an empty string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 6:30 下午
     * @param value: 对象
     * @return boolean
    */
    public static boolean isEmptyIfStr(Object value) {
        return Constant.NULL == value ? Constant.TRUE :
                value instanceof CharSequence ? Constant.ZERO == ((CharSequence) value).length() : Constant.FALSE;
    }

    /*给定字符串数组全部做去首尾空格-----------------------------------------------------------trim*/

    /**
     * [给定字符串数组全部做去首尾空格](Remove all leading and trailing spaces from the given string array)
     * @description: zh - 给定字符串数组全部做去首尾空格
     * @description: en - Remove all leading and trailing spaces from the given string array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 6:33 下午
     * @param values: 字符串数组
    */
    public static void trim(String[] values) {
        if (Constant.NULL == values) { return; }
        String str;
        for (int i = Constant.ZERO; i < values.length; i++) {
            str = values[i];
            if (Constant.NULL != str) {
                values[i] = trim(str);
            }
        }
    }

    /*将对象转为字符串-----------------------------------------------------------string*/

    /**
     * [将对象转为字符串](Convert object to string)
     * @description: zh - 将对象转为字符串
     * @description: en - Convert object to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 6:37 下午
     * @param value: 对象
     * @return java.lang.String
    */
    public static String string(Object value) {
        return str(value, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * [将对象转为字符串](Convert object to string)
     * @description: zh - 将对象转为字符串
     * @description: en - Convert object to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 6:39 下午
     * @param value: 对象
     * @param charsetName: 字符集
     * @return java.lang.String
    */
    public static String string(Object value, String charsetName) {
        return string(value, Charset.forName(charsetName));
    }

    /**
     * [将对象转为字符串](Convert object to string)
     * @description: zh - 将对象转为字符串
     * @description: en - Convert object to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/23 6:40 下午
     * @param value: 对象
     * @param charset: 字符集
     * @return java.lang.String
    */
    public static String string(Object value, Charset charset) {
        if (Constant.NULL == value) {
            return Constant.STRING_NULL;
        }

        if (value instanceof String) {
            return (String) value;
        } else if (value instanceof byte[]) {
            return str((byte[]) value, charset);
        } else if (value instanceof Byte[]) {
            return str((Byte[]) value, charset);
        } else if (value instanceof ByteBuffer) {
            return str((ByteBuffer) value, charset);
        } else if (ArrayUtil.isArray(value)) {
            return ArrayUtil.toString(value);
        }

        return value.toString();
    }

    /**
     * [将byte数组转为字符串](Convert byte array to string)
     * @description: zh - 将byte数组转为字符串
     * @description: en - Convert byte array to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:16 上午
     * @param bytes: 字符串
     * @param charset: 字符集
     * @return java.lang.String
    */
    public static String str(byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * [解码字节码](Decode bytecode)
     * @description: zh - 解码字节码
     * @description: en - Decode bytecode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:19 上午
     * @param data: 字符串
     * @param charset: 字符集，如果此字段为空，则解码的结果取决于平台
     * @return java.lang.String
    */
    public static String str(byte[] data, Charset charset) {
        return data == Constant.NULL ? Constant.STRING_NULL :
                Constant.NULL == charset ? new String(data) : new String(data,charset);
    }

    /**
     * [将Byte数组转为字符串](Convert byte array to string)
     * @description: zh - 将Byte数组转为字符串
     * @description: en - Convert byte array to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:20 上午
     * @param bytes: byte数组
     * @param charset: 字符集
     * @return java.lang.String
    */
    public static String str(Byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * [解码字节码](Decode bytecode)
     * @description: zh - 解码字节码
     * @description: en - Decode bytecode
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:22 上午
     * @param data: 字符串
     * @param charset: 字符集，如果此字段为空，则解码的结果取决于平台
     * @return java.lang.String
    */
    public static String str(Byte[] data, Charset charset) {
        if (data == Constant.NULL) {
            return Constant.STRING_NULL;
        }

        byte[] bytes = new byte[data.length];
        Byte dataByte;
        for (int i = Constant.ZERO; i < data.length; i++) {
            dataByte = data[i];
            bytes[i] = (Constant.NULL == dataByte) ? Constant.NEGATIVE_ONE : dataByte;
        }

        return str(bytes, charset);
    }

    /**
     * [将编码的byteBuffer数据转换为字符串](Converts encoded ByteBuffer data to a string)
     * @description: zh - 将编码的byteBuffer数据转换为字符串
     * @description: en - Converts encoded ByteBuffer data to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:22 上午
     * @param data: 数据
     * @param charset: 字符集，如果为空使用当前系统字符集
     * @return java.lang.String
    */
    public static String str(ByteBuffer data, String charset) {
        return data == Constant.NULL ? Constant.STRING_NULL : str(data, Charset.forName(charset));
    }

    /**
     * [将编码的byteBuffer数据转换为字符串](Converts encoded ByteBuffer data to a string)
     * @description: zh - 将编码的byteBuffer数据转换为字符串
     * @description: en - Converts encoded ByteBuffer data to a string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 8:23 上午
     * @param data: 数据
     * @param charset: 字符集，如果为空使用当前系统字符集
     * @return java.lang.String
    */
    public static String str(ByteBuffer data, Charset charset) {
        if (Constant.NULL == charset) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }

    /*输出字符串------------------------------------------------------------ to String*/

    /**
     * [调用对象的toString方法，null会返回“null”](Call the toString method of the object, null will return "null")
     * @description: zh - 调用对象的toString方法，null会返回“null”
     * @description: en - Call the toString method of the object, null will return "null"
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 9:54 上午
     * @param value: 对象
     * @return java.lang.String
    */
    public static String toString(Object value) {
        return Constant.NULL == value ? Constant.STRING_NULL_OUT : value.toString();
    }

    /*创建StringBuilder对象------------------------------------------------------------ builder*/

    /**
     * [创建StringBuilder对象](Create a StringBuilder object)
     * @description: zh - 创建StringBuilder对象
     * @description: en - Create a StringBuilder object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 10:00 上午
     * @return java.lang.StringBuilder
    */
    public static StringBuilder builder() {
        return new StringBuilder();
    }

    /**
     *
     * @description: zh - 创建 StringBuilder 对象
     * @description: en - Create a StringBuilder object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 10:03 上午
     * @param size: 初始大小
     * @return java.lang.StringBuilder
     */
    public static StringBuilder builder(int size) {
        return new StringBuilder(size);
    }

    /**
     * [创建StrBuilder对象](Create StrBuilder object)
     * @description: zh - 创建StrBuilder对象
     * @description: en - Create StrBuilder object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 10:01 上午
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public static StrBuilder strBuilder() {
        return StrBuilder.create();
    }

    /**
     * [创建StrBuilder对象](Create StrBuilder object)
     * @description: zh - 创建StrBuilder对象
     * @description: en - Create StrBuilder object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 10:13 上午
     * @param size: 初始大小
     * @return com.xiaoTools.core.text.stringBuilder.StrBuilder
    */
    public static StrBuilder strBuilder(int size) {
        return StrBuilder.create(size);
    }

    /*获得StringReader 和 StringWriter------------------------------------------------------------ Reader and Writer*/

    /**
     * [获得 StringReader](Get StringReader)
     * @description: zh - 获得 StringReader
     * @description: en - Get StringReader
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 10:25 上午
     * @param value: 字符串
     * @return java.io.StringReader
    */
    public static StringReader getReader(CharSequence value) {
        return Constant.NULL == value ? Constant.STRING_READER_NULL : new StringReader(value.toString());
    }

    /**
     * [获得StringWriter](Get StringWriter)
     * @description: zh - 获得StringWriter
     * @description: en - Get StringWriter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 10:26 上午
     * @return java.io.StringWriter
    */
    public static StringWriter getWriter() {
        return new StringWriter();
    }

    /*反转字符串------------------------------------------------------------ reverse*/

    /**
     * [反转字符串](Reverse string)
     * @description: zh - 反转字符串
     * @description: en - Reverse string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 12:02 下午
     * @param value: 被反转的字符串
     * @return java.lang.String
    */
    public static String reverse(String value) {
        return new String(ArrayUtil.reverse(value.toCharArray()));
    }

    /*填充字符串------------------------------------------------------------ fill*/

    /**
     * [将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串](Fill the existing string with the specified length. If the existing string exceeds this length, the string will be returned)
     * @description: zh - 将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串
     * @description: en - Fill the existing string with the specified length. If the existing string exceeds this length, the string will be returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 5:30 下午
     * @param value: 被填充的字符串
     * @param filled: 填充的字符
     * @param len: 填充长度
     * @return java.lang.String
    */
    public static String fillBefore(String value, char filled, int len) {
        return fill(value, filled, len, Constant.TRUE);
    }

    /**
     * [将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串](Fill the existing string with the specified length. If the existing string exceeds this length, the string will be returned)
     * @description: zh - 将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串
     * @description: en - Fill the existing string with the specified length. If the existing string exceeds this length, the string will be returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 5:32 下午
     * @param value: 被填充的字符串
     * @param filled: 填充的字符
     * @param len: 填充长度
     * @return java.lang.String
    */
    public static String fillAfter(String value, char filled, int len) {
        return fill(value, filled, len, Constant.FALSE);
    }

    /**
     * [将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串](Fill the existing string with the specified length. If the existing string exceeds this length, the string will be returned)
     * @description: zh - 将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串
     * @description: en - Fill the existing string with the specified length. If the existing string exceeds this length, the string will be returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/24 5:33 下午
     * @param value: 被填充的字符串
     * @param filled: 填充的字符
     * @param len: 填充长度
     * @param pre: 是否填充在前
     * @return java.lang.String
    */
    public static String fill(String value, char filled, int len, boolean pre) {
        final int strLen = value.length();
        if (strLen > len) {
            return value;
        }
        String filledStr = repeat(filled, len - strLen);
        return pre ? filledStr.concat(value) : value.concat(filledStr);
    }
}
