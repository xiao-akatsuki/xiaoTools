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
     *
     * @description: zh -
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 12:10 下午
     * @param type: 目标类型
     * @param value: 值
     * @param defaultValue: 默认值
     * @param quietly: 是否静默转换，true不抛异常
     * @return T
    */
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
