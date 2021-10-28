package com.xiaoTools.util.modifierUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.modifierUtil.modifierType.ModifierType;

/**
 * [修饰符工具类](Modifier tool class)
 * @description zh - 修饰符工具类
 * @description en - Modifier tool class
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-28 09:27:12
 */
public class ModifierUtil {

	/**
	 * [是否同时存在一个或多个修饰符](Does one or more modifiers exist at the same time)
	 * @description zh - 是否同时存在一个或多个修饰符
	 * @description en - Does one or more modifiers exist at the same time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:32:22
	 * @param clazz 类
	 * @param modifierTypes 修饰符枚举
	 * @return boolean
	 */
	public static boolean hasModifier(Class<?> clazz, ModifierType... modifierTypes) {
		return null == clazz || ArrayUtil.isEmpty(modifierTypes) ? Constant.FALSE :
			Constant.ZERO != (clazz.getModifiers() & modifiersToInt(modifierTypes));
	}

	/**
	 * [是否同时存在一个或多个修饰符](Does one or more modifiers exist at the same time)
	 * @description zh - 是否同时存在一个或多个修饰符
	 * @description en - Does one or more modifiers exist at the same time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:36:41
	 * @param constructor 构造方法
	 * @param modifierTypes 修饰符枚举
	 * @return boolean
	 */
	public static boolean hasModifier(Constructor<?> constructor, ModifierType... modifierTypes) {
		return null == constructor || ArrayUtil.isEmpty(modifierTypes) ? Constant.FALSE :
			Constant.ZERO != (constructor.getModifiers() & modifiersToInt(modifierTypes));
	}

	/**
	 * [是否同时存在一个或多个修饰符](Does one or more modifiers exist at the same time)
	 * @description zh - 是否同时存在一个或多个修饰符
	 * @description en - Does one or more modifiers exist at the same time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:38:23
	 * @param method 方法
	 * @param modifierTypes 修饰符枚举
	 * @return boolean
	 */
	public static boolean hasModifier(Method method, ModifierType... modifierTypes) {
		return null == method || ArrayUtil.isEmpty(modifierTypes) ? Constant.FALSE :
			Constant.ZERO != (method.getModifiers() & modifiersToInt(modifierTypes));
	}

	/**
	 * [是否同时存在一个或多个修饰符](Does one or more modifiers exist at the same time)
	 * @description zh - 是否同时存在一个或多个修饰符
	 * @description en - Does one or more modifiers exist at the same time
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:40:24
	 * @param field 字段
	 * @param modifierTypes 修饰符枚举
	 * @return boolean
	 */
	public static boolean hasModifier(Field field, ModifierType... modifierTypes) {
		return null == field || ArrayUtil.isEmpty(modifierTypes) ? Constant.FALSE :
			Constant.ZERO != (field.getModifiers() & modifiersToInt(modifierTypes));
	}

	/**
	 * [是否是Public字段](Is it a public field)
	 * @description zh - 是否是Public字段
	 * @description en - Is it a public field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:47:32
	 * @param field 字段
	 * @return boolean
	 */
	public static boolean isPublic(Field field) {
		return hasModifier(field, ModifierType.PUBLIC);
	}

	/**
	 * [是否是Public字段](Is it a public field)
	 * @description zh - 是否是Public字段
	 * @description en - Is it a public field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:48:09
	 * @param method 方法
	 * @return boolean
	 */
	public static boolean isPublic(Method method) {
		return hasModifier(method, ModifierType.PUBLIC);
	}

	/**
	 * [是否是Public字段](Is it a public field)
	 * @description zh - 是否是Public字段
	 * @description en - Is it a public field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:48:33
	 * @param clazz 类
	 * @return boolean
	 */
	public static boolean isPublic(Class<?> clazz) {
		return hasModifier(clazz, ModifierType.PUBLIC);
	}

	/**
	 * [是否是Public字段](Is it a public field)
	 * @description zh - 是否是Public字段
	 * @description en - Is it a public field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:48:56
	 * @param constructor 构造
	 * @return boolean
	 */
	public static boolean isPublic(Constructor<?> constructor) {
		return hasModifier(constructor, ModifierType.PUBLIC);
	}

	/**
	 * [是否是static字段](Is it a static field)
	 * @description zh - 是否是static字段
	 * @description en - Is it a static field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:49:27
	 * @param field 字段
	 * @return boolean
	 */
	public static boolean isStatic(Field field) {
		return hasModifier(field, ModifierType.STATIC);
	}

	/**
	 * [是否是static字段](Is it a static field)
	 * @description zh - 是否是static字段
	 * @description en - Is it a static field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:50:00
	 * @param method 方法
	 * @return boolean
	 */
	public static boolean isStatic(Method method) {
		return hasModifier(method, ModifierType.STATIC);
	}

	/**
	 * [是否是static字段](Is it a static field)
	 * @description zh - 是否是static字段
	 * @description en - Is it a static field
	 * @version V1.0
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:50:28
	 * @param clazz 类
	 * @return boolean
	 */
	public static boolean isStatic(Class<?> clazz) {
		return hasModifier(clazz, ModifierType.STATIC);
	}

	/**
	 * [是否是合成字段](Is it a composite field)
	 * @description zh - 是否是合成字段
	 * @description en - Is it a composite field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:50:51
	 * @param field 字段
	 * @return boolean
	 */
	public static boolean isSynthetic(Field field) {
		return field.isSynthetic();
	}

	/**
	 * [是否是合成字段](Is it a composite field)
	 * @description zh - 是否是合成字段
	 * @description en - Is it a composite field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:51:26
	 * @param method 方法
	 * @return boolean
	 */
	public static boolean isSynthetic(Method method) {
		return method.isSynthetic();
	}

	/**
	 * [是否是合成字段](Is it a composite field)
	 * @description zh - 是否是合成字段
	 * @description en - Is it a composite field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:51:48
	 * @param clazz 类
	 * @return boolean
	 */
	public static boolean isSynthetic(Class<?> clazz) {
		return clazz.isSynthetic();
	}

	/**
	 * [多个修饰符做“与”操作](Multiple modifiers do the and operation)
	 * @description zh - 多个修饰符做“与”操作
	 * @description en - Multiple modifiers do the and operation
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:41:22
	 * @param modifierTypes 修饰符列表，元素不能为空
	 * @return int
	 */
	private static int modifiersToInt(ModifierType... modifierTypes) {
		int modifier = modifierTypes[Constant.ZERO].getValue();
		for(int i = Constant.ONE; i < modifierTypes.length; i++) {
			modifier |= modifierTypes[i].getValue();
		}
		return modifier;
	}

}
