package com.xiaoTools.core.convert;

import com.xiaoTools.core.convert.converterRegistry.ConverterRegistry;

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

}
