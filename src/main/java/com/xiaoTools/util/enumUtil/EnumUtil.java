package com.xiaoTools.util.enumUtil;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * [枚举工具类](Enumerating tool classes)
 * @description zh - 枚举工具类
 * @description en - Enumerating tool classes
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 18:40:25
 */
public class EnumUtil {

	/**
	 * [指定类是否为Enum类](Specifies whether the class is enum class)
	 * @description zh - 指定类是否为Enum类
	 * @description en - Specifies whether the class is enum class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:42:03
	 * @param clazz 类型
	 * @return boolean
	 */
	public static boolean isEnum(Class<?> clazz) {
		Assertion.notNull(clazz);
		return clazz.isEnum();
	}

	/**
	 * [指定类是否为Enum类](Specifies whether the class is enum class)
	 * @description zh - 指定类是否为Enum类
	 * @description en - Specifies whether the class is enum class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:42:47
	 * @param boj 类型
	 * @return boolean
	 */
	public static boolean isEnum(Object obj) {
		Assertion.notNull(obj);
		return obj.getClass().isEnum();
	}

	/**
	 * [Enum对象转String](Enum object to string)
	 * @description zh - Enum对象转String
	 * @description en - Enum object to string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:43:37
	 * @param e 枚举类
	 * @return java.lang.String
	 */
	public static String toString(Enum<?> e) {
		return null != e ? e.name() : null;
	}

	/**
	 * [字符串转枚举](String to enumeration)
	 * @description zh - 字符串转枚举
	 * @description en - String to enumeration
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:44:45
	 * @param enumClass 枚举类型
	 * @param index 枚举索引
	 * @return E
	 */
	public static <E extends Enum<E>> E getEnumAt(Class<E> enumClass, int index) {
		final E[] enumConstants = enumClass.getEnumConstants();
		return index >= Constant.ZERO && index < enumConstants.length ?
			enumConstants[index] :
			null;
	}

	/**
	 * [字符串转枚举](String to enumeration)
	 * @description zh - 字符串转枚举
	 * @description en - String to enumeration
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:49:45
	 * @param enumClass 枚举类型
	 * @param value 值
	 * @return E
	 */
	public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value) {
		return Enum.valueOf(enumClass, value);
	}

	/**
	 * [字符串转枚举](String to enumeration)
	 * @description zh - 字符串转枚举
	 * @description en - String to enumeration
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:47:25
	 * @param enumClass 枚举类型
	 * @param value 值
	 * @param defaultValue 无对应枚举值返回的默认值
	 * @return E
	 */
	public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value, E defaultValue) {
		return ObjectUtil.defaultIfNull(fromStringQuietly(enumClass, value), defaultValue);
	}

	/**
	 * [字符串转枚举](String to enumeration)
	 * @description zh - 字符串转枚举
	 * @description en - String to enumeration
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:51:06
	 * @param enumClass 枚举类型
	 * @param value 值
	 * @return E
	 */
	public static <E extends Enum<E>> E fromStringQuietly(Class<E> enumClass, String value) {
		if (null == enumClass || StrUtil.isBlank(value)) {
			return null;
		}

		try {
			return fromString(enumClass, value);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * [模糊匹配转换为枚举](Convert fuzzy matching to enumeration)
	 * @description zh - 模糊匹配转换为枚举
	 * @description en - Convert fuzzy matching to enumeration
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:52:15
	 * @param enumClass 枚举类
	 * @param value 值
	 * @return E
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> E likeValueOf(Class<E> enumClass, Object value) {
		if (value instanceof CharSequence) {
			value = value.toString().trim();
		}

		final Field[] fields = ReflectUtil.getFields(enumClass);
		final Enum<?>[] enums = enumClass.getEnumConstants();
		String fieldName;
		for (Field field : fields) {
			fieldName = field.getName();
			if (field.getType().isEnum() || "ENUM$VALUES".equals(fieldName) || "ordinal".equals(fieldName)) {
				continue;
			}
			for (Enum<?> enumObj : enums) {
				if (ObjectUtil.equal(value, ReflectUtil.getFieldValue(enumObj, field))) {
					return (E) enumObj;
				}
			}
		}
		return null;
	}

	/**
	 * [枚举类中所有枚举对象的name列表](Name list of all enumerated objects in the enumeration class)
	 * @description zh - 枚举类中所有枚举对象的name列表
	 * @description en - Name list of all enumerated objects in the enumeration class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:54:33
	 * @param clazz 枚举类型
	 * @return java.util.List<java.lang.String>
	 */
	public static List<String> getNames(Class<? extends Enum<?>> clazz) {
		final Enum<?>[] enums = clazz.getEnumConstants();
		if (null == enums) {
			return null;
		}
		final List<String> list = new ArrayList<>(enums.length);
		for (Enum<?> e : enums) {
			list.add(e.name());
		}
		return list;
	}

	/**
	 * [获得枚举类中各枚举对象下指定字段的值](Gets the value of the specified field under each enumeration object in the enumeration class)
	 * @description zh - 获得枚举类中各枚举对象下指定字段的值
	 * @description en - Gets the value of the specified field under each enumeration object in the enumeration class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:55:38
	 * @param clazz 枚举类型
	 * @param fieldName 字段名称
	 * @return java.util.List<java.lang.Object>
	 */
	public static List<Object> getFieldValues(Class<? extends Enum<?>> clazz, String fieldName) {
		final Enum<?>[] enums = clazz.getEnumConstants();
		if (null == enums) {
			return null;
		}
		final List<Object> list = new ArrayList<>(enums.length);
		for (Enum<?> e : enums) {
			list.add(ReflectUtil.getFieldValue(e, fieldName));
		}
		return list;
	}

	/**
	 * [获得枚举类中所有的字段名](Get all the field names in the enumeration class)
	 * @description zh - 获得枚举类中所有的字段名
	 * @description en - Get all the field names in the enumeration class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:57:12
	 * @param clazz 枚举类型
	 * @return java.util.List<java.lang.String>
	 */
	public static List<String> getFieldNames(Class<? extends Enum<?>> clazz) {
		final List<String> names = new ArrayList<>();
		final Field[] fields = ReflectUtil.getFields(clazz);
		String name;
		for (Field field : fields) {
			name = field.getName();
			if (field.getType().isEnum() || name.contains("$VALUES") || "ordinal".equals(name)) {
				continue;
			}
			if (false == names.contains(name)) {
				names.add(name);
			}
		}
		return names;
	}

	/**
	 * [获取枚举字符串值和枚举对象的Map对应](Gets the map correspondence between the enumeration string value and the enumeration object)
	 * @description zh - 获取枚举字符串值和枚举对象的Map对应
	 * @description en - Gets the map correspondence between the enumeration string value and the enumeration object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:58:23
	 * @param enumClass 枚举类型
	 * @return java.util.LinkedHashMap<java.lang.String, E>
	 */
	public static <E extends Enum<E>> LinkedHashMap<String, E> getEnumMap(final Class<E> enumClass) {
		final LinkedHashMap<String, E> map = new LinkedHashMap<>();
		for (final E e : enumClass.getEnumConstants()) {
			map.put(e.name(), e);
		}
		return map;
	}

	/**
	 * [获得枚举名对应指定字段值的Map](Gets the map of the enumeration name corresponding to the specified field value)
	 * @description zh - 获得枚举名对应指定字段值的Map
	 * @description en - Gets the map of the enumeration name corresponding to the specified field value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 18:59:01
	 * @param clazz 枚举类型
	 * @param fieldName 字段类型
	 * java.util.Map<java.lang.String, java.lang.Object>
	 */
	public static Map<String, Object> getNameFieldMap(Class<? extends Enum<?>> clazz, String fieldName) {
		final Enum<?>[] enums = clazz.getEnumConstants();
		if (null == enums) {
			return null;
		}
		final Map<String, Object> map = MapUtil.newHashMap(enums.length, Constant.TRUE);
		for (Enum<?> e : enums) {
			map.put(e.name(), ReflectUtil.getFieldValue(e, fieldName));
		}
		return map;
	}

	/**
	 * [判断某个值是存在枚举中](Determine whether a value exists in the enumeration)
	 * @description zh - 判断某个值是存在枚举中
	 * @description en - Determine whether a value exists in the enumeration
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:01:53
	 * @param enumClass  枚举类型
	 * @param value 值
	 * @return boolean
	 */
	public static <E extends Enum<E>> boolean contains(final Class<E> enumClass, String value) {
		return EnumUtil.getEnumMap(enumClass).containsKey(value);
	}

	/**
	 * [判断某个值是不存在枚举中](Determine whether a value does not exist in the enumeration)
	 * @description zh - 判断某个值是不存在枚举中
	 * @description en - Determine whether a value does not exist in the enumeration
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:03:05
	 * @param enumClass  枚举类型
	 * @param value 值
	 * @return boolean
	 */
	public static <E extends Enum<E>> boolean notContains(final Class<E> enumClass, String value) {
		return Constant.FALSE == contains(enumClass, value);
	}

	/**
	 * [忽略大小检查某个枚举值是否匹配指定值](Ignore size and check whether an enumerated value matches the specified value)
	 * @description zh - 忽略大小检查某个枚举值是否匹配指定值
	 * @description en - Ignore size and check whether an enumerated value matches the specified value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:04:20
	 * @param enumClass 枚举类型
	 * @param value 值
	 * @return boolean
	 */
	public static boolean equalsIgnoreCase(final Enum<?> enumClass, String value) {
		return StrUtil.equalsIgnoreCase(toString(enumClass), value);
	}

	/**
	 * [检查某个枚举值是否匹配指定值](Checks whether an enumeration value matches the specified value)
	 * @description zh - 检查某个枚举值是否匹配指定值
	 * @description en - Checks whether an enumeration value matches the specified value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 19:04:55
	 * @param enumClass 枚举类型
	 * @param value 值
	 * @return boolean
	 */
	public static boolean equals(final Enum<?> enumClass, String value) {
		return StrUtil.equals(toString(enumClass), value);
	}

}
