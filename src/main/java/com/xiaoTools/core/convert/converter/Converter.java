package com.xiaoTools.core.convert.converter;

/**
 * [转换器接口，实现类型转换](Converter interface to realize type conversion)
 * @description: zh - 转换器接口，实现类型转换
 * @description: en - Converter interface to realize type conversion
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 8:43 上午
*/
public interface Converter<T> {
    /**
     * [转换为指定类型 如果类型无法确定，将读取默认值的类型做为目标类型](Convert to the specified type. If the type cannot be determined, the type that reads the default value is used as the target type)
     * @description: zh - 转换为指定类型 如果类型无法确定，将读取默认值的类型做为目标类型
     * @description: en - Convert to the specified type. If the type cannot be determined, the type that reads the default value is used as the target type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:42 上午
     * @param value: 原始值
     * @param defaultValue: 默认值
     * @return T
    */
    T convert(Object value, T defaultValue) throws IllegalArgumentException;
}
