package com.xiaoTools.core.convert;

import com.xiaoTools.core.convert.converterRegistry.ConverterRegistry;
import com.xiaoTools.core.convert.typeReference.TypeReference;
import com.xiaoTools.core.exception.convertException.ConvertException;
import com.xiaoTools.lang.constant.Constant;

import java.lang.reflect.Type;

/**
 * [类型转换器](Type converter)
 * @description: zh - 类型转换器
 * @description: en - Type converter
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 8:07 上午
*/
public class Convert {

    /*转换为字符串-----------------------------------------------------------to String*/

    /**
     * [转换为字符串](Convert to string)
     * @description: zh - 转换为字符串
     * @description: en - Convert to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/22 11:08 上午
     * @param value: 被转换的值
     * @param defaultValue: 转换错误时的默认值
     * @return java.lang.String
    */
    public static String toStr(Object value, String defaultValue) {
        return convertQuietly(String.class, value, defaultValue);
    }

    /**
     * [转换成为字符串](Convert to string)
     * @description: zh - 转换成为字符串
     * @description: en - Convert to string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:49 下午
     * @param value: 被转换的值
     * @return java.lang.String
    */
    public static String toStr(Object value) {
        return toStr(value, Constant.STRING_NULL);
    }

    /**
     * [转换为String数组](Convert to string array)
     * @description: zh - 转换为String数组
     * @description: en - Convert to string array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:49 下午
     * @param value: 被转换的值
     * @return java.lang.String[]
    */
    public static String[] toStrArray(Object value) {
        return convert(String[].class, value);
    }

    /*转换成为字符串-----------------------------------------------------------to char*/

    /**
     * [转换为字符](Convert to character)
     * @description: zh - 转换为字符
     * @description: en - Convert to character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:50 下午
     * @param value: 被转换的值
     * @param defaultValue: 转换错误时的默认值
     * @return java.lang.Character
    */
    public static Character toChar(Object value, Character defaultValue) {
        return convertQuietly(Character.class, value, defaultValue);
    }

    /**
     * [转换为字符](Convert to character)
     * @description: zh - 转换为字符
     * @description: en - Convert to character
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:53 下午
     * @param value: 被转换的值
     * @return java.lang.Character
    */
    public static Character toChar(Object value) {
        return toChar(value, Constant.CHARACTER_NULL);
    }

    /**
     * [转换为 Character 数组](Convert to Character array)
     * @description: zh - 转换为 Character 数组
     * @description: en - Convert to Character array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:55 下午
     * @param value: 被转换的值
     * @return java.lang.Character[]
    */
    public static Character[] toCharArray(Object value) {
        return convert(Character[].class, value);
    }

    /*转换成为字节-----------------------------------------------------------to byte*/

    /**
     * [转换为byte](Convert to byte)
     * @description: zh - 转换为byte
     * @description: en - Convert to byte
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:56 下午
     * @param value: 被转换的值
     * @param defaultValue: 转换错误时的默认值
     * @return java.lang.Byte
    */
    public static Byte toByte(Object value, Byte defaultValue) {
        return convertQuietly(Byte.class, value, defaultValue);
    }

    /**
     * [转换为byte](Convert to byte)
     * @description: zh - 转换为byte
     * @description: en - Convert to byte
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:57 下午
     * @param value: 被转换的值
     * @return java.lang.Byte
    */
    public static Byte toByte(Object value) {
        return toByte(value, null);
    }

    /**
     * [转换为Byte数组](Convert to byte array)
     * @description: zh - 转换为Byte数组
     * @description: en - Convert to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 9:58 下午
     * @param value: 被转换的值
     * @return java.lang.Byte[]
    */
    public static Byte[] toByteArray(Object value) {
        return convert(Byte[].class, value);
    }

    /**
     * [转换为Byte数组](Convert to byte array)
     * @description: zh - 转换为Byte数组
     * @description: en - Convert to byte array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 10:00 下午
     * @param value: 被转换的值
     * @return byte[]
    */
    public static byte[] toPrimitiveByteArray(Object value) {
        return convert(byte[].class, value);
    }

    /*转换为Short-----------------------------------------------------------to Short*/

    /**
     * [转换为Short](Convert to short)
     * @description: zh - 转换为Short
     * @description: en - Convert to short
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 10:02 下午
     * @param value: 被转换的值
     * @param defaultValue: 转换错误时的默认值
     * @return java.lang.Short
    */
    public static Short toShort(Object value, Short defaultValue) {
        return convertQuietly(Short.class, value, defaultValue);
    }

    /**
     * [转换为Short](Convert to short)
     * @description: zh - 转换为Short
     * @description: en - Convert to short
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 10:03 下午
     * @param value: 被转换的值
     * @return java.lang.Short
    */
    public static Short toShort(Object value) {
        return toShort(value, null);
    }

    /**
     * [转换为Short数组](Convert to short array)
     * @description: zh - 转换为Short数组
     * @description: en - Convert to short array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 10:06 下午
     * @param value: 被转换的值
     * @return java.lang.Short[]
    */
    public static Short[] toShortArray(Object value) {
        return convert(Short[].class, value);
    }

    /*转换为Number-----------------------------------------------------------to Number*/

    /**
     * [转换为Number](Convert to number)
     * @description: zh - 转换为Number
     * @description: en - Convert to number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 10:09 下午
     * @param value: 被转换的值
     * @param defaultValue: 转换错误时的默认值
     * @return java.lang.Number
    */
    public static Number toNumber(Object value, Number defaultValue) {
        return convertQuietly(Number.class, value, defaultValue);
    }

    /**
     * [转换为Number](Convert to number)
     * @description: zh - 转换为Number
     * @description: en - Convert to number
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 10:10 下午
     * @param value: 被转换的值
     * @return java.lang.Number
    */
    public static Number toNumber(Object value) {
        return toNumber(value, null);
    }

    /**
     * [转换为Number数组](Convert to number array)
     * @description: zh - 转换为Number数组
     * @description: en - Convert to number array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/27 10:11 下午
     * @param value: 被转换的值
     * @return java.lang.Number[]
    */
    public static Number[] toNumberArray(Object value) {
        return convert(Number[].class, value);
    }

    /*转换为int-----------------------------------------------------------to int*/

    public static <T> T convertWithCheck(Type type, Object value, T defaultValue, boolean quietly) {
        final ConverterRegistry registry = ConverterRegistry.getInstance();
        try {
            return registry.convert(type, value, defaultValue);
        } catch (Exception e) {
            if(quietly){
                return defaultValue;
            }
            throw e;
        }
    }

    /*转换-----------------------------------------------------------convert*/

    /**
     * [转换值为指定类型](Convert the value to the specified type)
     * @description: zh - 转换值为指定类型
     * @description: en - Convert the value to the specified type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:36 下午
     * @param type: 类型
     * @param value: 值
     * @return T
    */
    public static <T> T convert(Class<T> type, Object value) throws ConvertException {
        return convert((Type)type, value);
    }

    /**
     * [转换值为指定类型](Convert the value to the specified type)
     * @description: zh - 转换值为指定类型
     * @description: en - Convert the value to the specified type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:37 下午
     * @param reference: 类型参考，用于持有转换后的泛型类型
     * @param value: 值
     * @return T
    */
    public static <T> T convert(TypeReference<T> reference, Object value) throws ConvertException{
        return convert(reference.getType(), value, null);
    }

    /**
     * [转换值为指定类型](Convert the value to the specified type)
     * @description: zh - 转换值为指定类型
     * @description: en - Convert the value to the specified type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:43 下午
     * @param type: 类型
     * @param value: 值
     * @return T
    */
    public static <T> T convert(Type type, Object value) throws ConvertException{
        return convert(type, value, null);
    }

    /**
     * [转换值为指定类型](Convert the value to the specified type)
     * @description: zh - 转换值为指定类型
     * @description: en - Convert the value to the specified type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:45 下午
     * @param type: 类型值
     * @param value: 值
     * @param defaultValue: 默认值
     * @return T
    */
    public static <T> T convert(Class<T> type, Object value, T defaultValue) throws ConvertException {
        return convert((Type)type, value, defaultValue);
    }

    /**
     * [转换值为指定类型](Convert the value to the specified type)
     * @description: zh - 转换值为指定类型
     * @description: en - Convert the value to the specified type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:48 下午
     * @param type: 类型
     * @param value: 值
     * @param defaultValue: 默认值
     * @return T
    */
    public static <T> T convert(Type type, Object value, T defaultValue) throws ConvertException {
        return convertWithCheck(type, value, defaultValue, Constant.FALSE);
    }

}
