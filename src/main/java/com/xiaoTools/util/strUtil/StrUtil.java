package com.xiaoTools.util.strUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.charSequenceUtil.CharSequenceUtil;

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


}
