package com.xiaoTools.core.convert.enumConverter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.xiaoTools.cache.simple.SimpleCache;
import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.lang.enumItem.EnumItem;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.modifierUtil.ModifierUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;

/**
 * [无泛型检查的枚举转换器](Enumeration converter without generic check)
 * @description zh - 无泛型检查的枚举转换器
 * @description en - Enumeration converter without generic check
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-25 08:14:44
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EnumConverter extends AbstractConverter<Object> {
	private static final long serialVersionUID = 1L;

	private static final SimpleCache<Class<?>, Map<Class<?>, Method>> VALUE_OF_METHOD_CACHE = new SimpleCache<>();

	private final Class enumClass;

	public EnumConverter(Class enumClass) {
		this.enumClass = enumClass;
	}

	@Override
	protected Object convertInternal(Object value) {
		Enum enumValue = tryConvertEnum(value, this.enumClass);
		if (null == enumValue && Constant.FALSE == value instanceof String) {
			enumValue = Enum.valueOf(this.enumClass, convertToStr(value));
		}
		return enumValue;
	}

	@Override
	public Class getTargetType() {
		return this.enumClass;
	}

	/**
	 * [尝试找到类似转换的静态方法调用实现转换且优先使用](Try to find a static method call similar to the transformation to implement the transformation and use it first)
	 * @description zh - 尝试找到类似转换的静态方法调用实现转换且优先使用
	 * @description en - Try to find a static method call similar to the transformation to implement the transformation and use it first
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:18:13
	 * @param value 被转换的值
	 * @param enumClass enum类
	 * @return java.lang.Enum
	 */
	protected static Enum tryConvertEnum(Object value, Class enumClass) {
		if (value == null) {
			return null;
		}

		Enum enumResult = null;
		if (EnumItem.class.isAssignableFrom(enumClass)) {
			final EnumItem first = (EnumItem) EnumUtil.getEnumAt(enumClass, 0);
			if(null != first){
				if (value instanceof Integer) {
					return (Enum) first.fromInt((Integer) value);
				} else if (value instanceof String) {
					return (Enum) first.fromStr(value.toString());
				}
			}
		}

		final Map<Class<?>, Method> methodMap = getMethodMap(enumClass);
		if (MapUtil.isNotEmpty(methodMap)) {
			final Class<?> valueClass = value.getClass();
			for (Map.Entry<Class<?>, Method> entry : methodMap.entrySet()) {
				if (ClassUtil.isAssignable(entry.getKey(), valueClass)) {
					enumResult = ReflectUtil.invokeStatic(entry.getValue(), value);
				}
			}
		}

		if (null == enumResult) {
			if (value instanceof Integer) {
				enumResult = EnumUtil.getEnumAt(enumClass, (Integer) value);
			} else if (value instanceof String) {
				try {
					enumResult = Enum.valueOf(enumClass, (String) value);
				} catch (IllegalArgumentException e) {
					//ignore
				}
			}
		}
		return enumResult;
	}

	/**
	 * [获取用于转换为enum的所有static方法](Gets all static methods used to convert to enum)
	 * @description zh - 获取用于转换为enum的所有static方法
	 * @description en - Gets all static methods used to convert to enum
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:19:28
	 * @param enumClass enum类
	 * @return java.util.Map<java.lang.Class<?>, java.lang.reflect.Method>
	 */
	private static Map<Class<?>, Method> getMethodMap(Class<?> enumClass) {
		return VALUE_OF_METHOD_CACHE.get(enumClass, ()-> Arrays.stream(enumClass.getMethods())
				.filter(ModifierUtil::isStatic)
				.filter(m -> m.getReturnType() == enumClass)
				.filter(m -> m.getParameterCount() == 1)
				.filter(m -> false == "valueOf".equals(m.getName()))
				.collect(Collectors.toMap(m -> m.getParameterTypes()[0], m -> m)));
	}
}
