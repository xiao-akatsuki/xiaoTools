package com.xiaoTools.core.convert.primitiveConverter;

import java.util.function.Function;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.core.convert.numberConverter.NumberConverter;
import com.xiaoTools.core.exception.convertException.ConvertException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [原始类型转换器](Primitive type converter)
 * @description zh - 原始类型转换器
 * @description en - Primitive type converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-18 19:51:06
 */
public class PrimitiveConverter extends AbstractConverter<Object> {

	private static final long serialVersionUID = 1L;

	private final Class<?> targetType;

	/**
	 * [构造方法](Construction method)
	 * @description zh - 构造方法
	 * @description en - Construction method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 19:53:33
	 * @param clazz 需要转换的原始
	 * @throws IllegalArgumentException 传入的转换类型非原始类型时抛出
	 */
	public PrimitiveConverter(Class<?> clazz) {
		if (null == clazz) {
			throw new NullPointerException("PrimitiveConverter not allow null target type!");
		} else if (Constant.FALSE == clazz.isPrimitive()) {
			throw new IllegalArgumentException("[" + clazz + "] is not a primitive class!");
		}
		this.targetType = clazz;
	}

	@Override
	protected Object convertInternal(Object value) {
		return PrimitiveConverter.convert(value, this.targetType, this::convertToStr);
	}

	@Override
	protected String convertToStr(Object value) {
		return StrUtil.trim(super.convertToStr(value));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<Object> getTargetType() {
		return (Class<Object>) this.targetType;
	}

	/**
	 *
	 * @param value 值
	 * @param primitiveClass 原始类型
	 * @param toStringFunc 当无法直接转换时，转为字符串后再转换的函数
	 * @return 转换结果
	 * @since 5.5.0
	 */
	/**
	 * [将指定值转换为原始类型的值](Converts the specified value to a value of the original type)
	 * @description zh - 将指定值转换为原始类型的值
	 * @description en - Converts the specified value to a value of the original type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 19:54:17
	 * @param value 值
	 * @param primitiveClass 原始类型
	 * @param toStringFunc 当无法直接转换时，转为字符串后再转换的函数
	 * @return java.lang.Object
	 */
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
