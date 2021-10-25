package com.xiaoTools.util.beanUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.xiaoTools.entity.dynaBean.DynaBean;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.classUtil.ClassUtil;

/**
 * [Bean工具类](Bean util)
 * @description zh - Bean工具类
 * @description en - Bean util
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-25 08:56:53
 */
public class BeanUtil {

	/**
	 * [判断是否为可读的Bean对象](Determine whether it is a readable bean object)
	 * @description zh - 判断是否为可读的Bean对象
	 * @description en - Determine whether it is a readable bean object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:59:41
	 * @param clazz 待测试类
	 * @return boolean
	 */
	public static boolean isReadableBean(Class<?> clazz) {
		return hasGetter(clazz) || hasPublicField(clazz);
	}

	/**
	 * [判断是否有Setter方法](Judge whether there is setter method)
	 * @description zh - 判断是否有Setter方法
	 * @description en - Judge whether there is setter method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 09:01:59
	 * @param clazz 类型
	 * @return boolean
	 */
	public static boolean hasSetter(Class<?> clazz) {
		if (ClassUtil.isNormalClass(clazz)) {
			final Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (method.getParameterTypes().length == Constant.ONE && method.getName().startsWith("set")) {
					return Constant.TRUE;
				}
			}
		}
		return Constant.FALSE;
	}

	/**
	 * [判断是否有Getter方法](Determine whether there is a getter method)
	 * @description zh - 判断是否有Getter方法
	 * @description en - Determine whether there is a getter method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 09:04:35
	 * @param clazz 类型
	 * @return boolean
	 */
	public static boolean hasGetter(Class<?> clazz) {
		if (ClassUtil.isNormalClass(clazz)) {
			for (Method method : clazz.getMethods()) {
				if (method.getParameterTypes().length == Constant.ZERO) {
					if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
						return Constant.TRUE;
					}
				}
			}
		}
		return Constant.FALSE;
	}

	/**
	 * [指定类中是否有public类型字段](Specifies whether there is a public type field in the class)
	 * @description zh - 指定类中是否有public类型字段
	 * @description en - Specifies whether there is a public type field in the class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 09:13:11
	 * @param clazz 类型
	 * @return boolean
	 */
	public static boolean hasPublicField(Class<?> clazz) {
		if (ClassUtil.isNormalClass(clazz)) {
			for (Field field : clazz.getFields()) {
				if (ModifierUtil.isPublic(field) && false == ModifierUtil.isStatic(field)) {
					return Constant.TRUE;
				}
			}
		}
		return Constant.FALSE;
	}

	/**
	 * [创建动态Bean](Create dynamic bean)
	 * @description zh - 创建动态Bean
	 * @description en - Create dynamic bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:13:10
	 * @param bean 实体类
	 */
	public static DynaBean createDynaBean(Object bean) {
		return new DynaBean(bean);
	}

	/**
	 * [查找类型转换器](Find type converter)
	 * @description zh - 查找类型转换器
	 * @description en - Find type converter
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:29:30
	 * @param type 类型
	 * @return java.beans.PropertyEditor
	 */
	public static PropertyEditor findEditor(Class<?> type) {
		return PropertyEditorManager.findEditor(type);
	}
}
