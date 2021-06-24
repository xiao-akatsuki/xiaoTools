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
