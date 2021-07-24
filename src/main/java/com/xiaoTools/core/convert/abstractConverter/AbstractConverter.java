package com.xiaoTools.core.convert.abstractConverter;

import com.xiaoTools.core.convert.converter.Converter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.charUtil.CharUtil;
import com.xiaoTools.util.classUtil.ClassUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * [抽象转换器，提供通用的转换逻辑](Abstract converter, providing general conversion logic)
 * @description: zh - 抽象转换器，提供通用的转换逻辑
 * @description: en - Abstract converter, providing general conversion logic
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 12:59 下午
*/
public abstract class AbstractConverter<T> implements Converter<T>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * [不抛异常转换 当转换失败时返回默认值](Conversion without exception returns the default value when conversion fails)
     * @description: zh - 不抛异常转换 当转换失败时返回默认值
     * @description: en - Conversion without exception returns the default value when conversion fails
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 2:34 下午
     * @param value: 被转换的值
     * @param defaultValue: 默认值
     * @return T
    */
    public T convertQuietly(Object value, T defaultValue) {
        try {
            return convert(value, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * [内部转换器，被 convert(Object, Object) 调用，实现基本转换逻辑](Internal converter is called by convert (object, object) to realize basic conversion logic)
     * @description: zh - 内部转换器，被 convert(Object, Object) 调用，实现基本转换逻辑
     * @description: en - Internal converter is called by convert (object, object) to realize basic conversion logic
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:09 下午
     * @param value: 值
     * @return T
    */
    protected abstract T convertInternal(Object value);

    /**
     * [转换为指定类型 如果类型无法确定，将读取默认值的类型做为目标类型](Convert to the specified type. If the type cannot be determined, the type that reads the default value is used as the target type)
     * @description: zh - 转换为指定类型 如果类型无法确定，将读取默认值的类型做为目标类型
     * @description: en - Convert to the specified type. If the type cannot be determined, the type that reads the default value is used as the target type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 1:05 下午
     * @param value: 原始值
     * @param defaultValue: 默认值
     * @return T
    */
    @Override
    public T convert(Object value, T defaultValue) throws IllegalArgumentException {
        Class<T> targetType = getTargetType();
        if (Constant.NULL == targetType && Constant.NULL == defaultValue) {
            throw new NullPointerException(StrUtil.format("[type] and [defaultValue] are both null for Converter [{}], we can not know what type to convert !", this.getClass().getName()));
        }
        if (Constant.NULL == targetType) {
            // 目标类型不确定时使用默认值的类型
            targetType = (Class<T>) defaultValue.getClass();
        }
        if (Constant.NULL == value) {
            return defaultValue;
        }

        if (Constant.NULL == defaultValue || targetType.isInstance(defaultValue)) {
            if (targetType.isInstance(value) && !Map.class.isAssignableFrom(targetType)) {
                // 除Map外，已经是目标类型，不需要转换（Map类型涉及参数类型，需要单独转换）
                return targetType.cast(value);
            }
            T result = convertInternal(value);
            return ((Constant.NULL == result) ? defaultValue : result);
        } else {
            throw new IllegalArgumentException(
                    StrUtil.format("Default value [{}]({}) is not the instance of [{}]", defaultValue, defaultValue.getClass(), targetType));
        }
    }

    /**
     * [值转为String，用于内部转换中需要使用String中转的情况](The value is converted to string, which is used for internal conversion when string is needed)
     * @description: zh - 值转为String，用于内部转换中需要使用String中转的情况
     * @description: en - The value is converted to string, which is used for internal conversion when string is needed
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 2:55 下午
     * @param value: 值
     * @return java.lang.String
    */
    protected String convertToStr(Object value) {
        if (Constant.NULL == value) {
            return Constant.STRING_NULL;
        }
        if (value instanceof CharSequence) {
            return value.toString();
        } else if (ArrayUtil.isArray(value)) {
            return ArrayUtil.toString(value);
        } else if(CharUtil.isChar(value)) {
            //对于ASCII字符使用缓存加速转换，减少空间创建
            return CharUtil.toString((char)value);
        }
        return value.toString();
    }

    /**
     * [获得此类实现类的泛型类型](Gets the generic type of this implementation class)
     * @description: zh - 获得此类实现类的泛型类型
     * @description: en - Gets the generic type of this implementation class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 2:46 下午
     * @return java.lang.Class<T>
    */
    public Class<T> getTargetType() {
        return (Class<T>) ClassUtil.getTypeArgument(getClass());
    }


}
