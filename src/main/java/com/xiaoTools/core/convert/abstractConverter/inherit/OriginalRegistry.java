package com.xiaoTools.core.convert.abstractConverter.inherit;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.core.convert.numberConverter.NumberConverter;
import com.xiaoTools.core.exception.convertException.ConvertException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.objectUtil.ObjectUtil;

import java.io.Serial;
import java.util.function.Function;

/**
 * [原始类型的转换器](Converter of primitive type)
 * @description: zh - 原始类型的转换器
 * @description: en - Converter of primitive type
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 3:27 下午
*/
public class OriginalRegistry extends AbstractConverter<Object> {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Class<?> targetType;

    /**
     * [构造](structure)
     * @description: zh - 构造
     * @description: en - structure
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 3:31 下午
     * @param clazz: 需要转换的原始
    */
    public OriginalRegistry(Class<?> clazz) {
        if (Constant.NULL == clazz) {
            throw new NullPointerException("PrimitiveConverter not allow null target type!");
        } else if (!clazz.isPrimitive()) {
            throw new IllegalArgumentException("[" + clazz + "] is not a primitive class!");
        }
        this.targetType = clazz;
    }

    @Override
    protected Object convertInternal(Object value) {
        return null;
    }

    protected static Object convert(Object value, Class<?> primitiveClass, Function<Object, String> toStringFunc) {
        if (byte.class == primitiveClass) {
            return ObjectUtil.defaultIfNull(NumberConverter.convert(value, Byte.class, toStringFunc), Constant.ZERO);
        } else if (short.class == primitiveClass) {
            return ObjectUtil.defaultIfNull(NumberConverter.convert(value, Short.class, toStringFunc), Constant.ZERO);
        } else if (int.class == primitiveClass) {
            return ObjectUtil.defaultIfNull(NumberConverter.convert(value, Integer.class, toStringFunc), Constant.ZERO);
        } else if (long.class == primitiveClass) {
            return ObjectUtil.defaultIfNull(NumberConverter.convert(value, Long.class, toStringFunc), Constant.ZERO);
        } else if (float.class == primitiveClass) {
            return ObjectUtil.defaultIfNull(NumberConverter.convert(value, Float.class, toStringFunc), Constant.ZERO);
        } else if (double.class == primitiveClass) {
            return ObjectUtil.defaultIfNull(NumberConverter.convert(value, Double.class, toStringFunc), Constant.ZERO);
        } else if (char.class == primitiveClass) {
            return Convert.convert(Character.class, value);
        } else if (boolean.class == primitiveClass) {
            return Convert.convert(Boolean.class, value);
        }

        throw new ConvertException("Unsupported target type: {}", primitiveClass);
    }

}
