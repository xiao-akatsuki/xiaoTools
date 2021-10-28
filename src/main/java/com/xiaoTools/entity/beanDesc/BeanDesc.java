package com.xiaoTools.entity.beanDesc;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.map.caseInsensitiveMap.CaseInsensitiveMap;
import com.xiaoTools.entity.propDesc.PropDesc;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.booleanUtil.BooleanUtil;
import com.xiaoTools.util.modifierUtil.ModifierUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [Bean信息描述做为BeanInfo替代方案](Bean information description as an alternative to beaninfo)
 * @description zh - Bean信息描述做为BeanInfo替代方案
 * @description en - Bean information description as an alternative to beaninfo
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-26 09:25:19
 */
public class BeanDesc implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Bean类
	 */
	private final Class<?> beanClass;

	/**
	 * 属性Map
	 */
	private final Map<String, PropDesc> propMap = new LinkedHashMap<>();

	public BeanDesc(Class<?> beanClass) {
		Assertion.notNull(beanClass);
		this.beanClass = beanClass;
		init();
	}

	/**
	 * [获取Bean的全类名](Gets the full class name of the bean)
	 * @description zh - 获取Bean的全类名
	 * @description en - Gets the full class name of the bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:44:27
	 * @return java.lang.String
	 */
	public String getName() {
		return this.beanClass.getName();
	}

	/**
	 * [获取Bean的简单类名](Get the simple class name of the bean)
	 * @description zh - 获取Bean的简单类名
	 * @description en - Get the simple class name of the bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:44:51
	 * @return java.lang.String
	 */
	public String getSimpleName() {
		return this.beanClass.getSimpleName();
	}

	/**
	 * [获取字段名-字段属性Map](Get field name - field property map)
	 * @description zh - 获取字段名-字段属性Map
	 * @description en - Get field name - field property map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:45:22
	 * @param ignoreCase
	 * @return java.util.Map<java.lang.String, com.xiaoTools.entity.propDesc.PropDesc>
	 */
	public Map<String, PropDesc> getPropMap(boolean ignoreCase) {
		return ignoreCase ? new CaseInsensitiveMap<>(Constant.ONE, this.propMap) : this.propMap;
	}

	/**
	 * [获取字段属性列表](Get a list of field properties)
	 * @description zh - 获取字段属性列表
	 * @description en - Get a list of field properties
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:46:19
	 * @return java.util.Collection<com.xiaoTools.entity.propDesc.PropDesc>
	 */
	public Collection<PropDesc> getProps() {
		return this.propMap.values();
	}

	/**
	 * [获取属性](get attribute)
	 * @description zh - 获取属性
	 * @description en - get attribute
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:47:10
	 * @param fieldName 字段名
	 * @return com.xiaoTools.entity.propDesc.PropDesc
	 */
	public PropDesc getProp(String fieldName) {
		return this.propMap.get(fieldName);
	}

	/**
	 * [获得字段名对应的字段对象](Get the field object corresponding to the field name)
	 * @description zh - 获得字段名对应的字段对象
	 * @description en - Get the field object corresponding to the field name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:47:53
	 * @param fieldName 字段名
	 * @return java.lang.reflect.Field
	 */
	public Field getField(String fieldName) {
		final PropDesc desc = this.propMap.get(fieldName);
		return null == desc ? null : desc.getField();
	}

	/**
	 * [获取Getter方法](Get getter method)
	 * @description zh - 获取Getter方法
	 * @description en - Get getter method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:49:59
	 * @param fieldName 字段名
	 * @return java.lang.reflect.Method
	 */
	public Method getGetter(String fieldName) {
		final PropDesc desc = this.propMap.get(fieldName);
		return null == desc ? null : desc.getGetter();
	}

	/**
	 * [获取Setter方法](Get setter method)
	 * @description zh - 获取Setter方法
	 * @description en - Get setter method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:50:46
	 * @param fieldName 字段名
	 * @return java.lang.reflect.Method
	 */
	public Method getSetter(String fieldName) {
		final PropDesc desc = this.propMap.get(fieldName);
		return null == desc ? null : desc.getSetter();
	}

	/**
	 * [初始化](initialization)
	 * @description zh - 初始化
	 * @description en - initialization
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:52:44
	 * @return com.xiaoTools.entity.beanDesc.BeanDesc
	 */
	private BeanDesc init() {
		final Method[] methods = ReflectUtil.getMethods(this.beanClass);
		PropDesc prop;
		for (Field field : ReflectUtil.getFields(this.beanClass)) {
			if (Constant.FALSE == ModifierUtil.isStatic(field)) {
				prop = createProp(field, methods);
				this.propMap.putIfAbsent(prop.getFieldName(), prop);
			}
		}
		return this;
	}

	/**
	 * [根据字段创建属性描述](Create an attribute description based on the field)
	 * @description zh - 根据字段创建属性描述
	 * @description en - Create an attribute description based on the field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:53:10
	 * @param field 字段
	 * @param methods 类中所有的方法
	 * @return com.xiaoTools.entity.propDesc.PropDesc
	 */
	private PropDesc createProp(Field field, Method[] methods) {
		final PropDesc prop = findProp(field, methods, false);
		if (null == prop.getter || null == prop.setter) {
			final PropDesc propIgnoreCase = findProp(field, methods, Constant.TRUE);
			if (null == prop.getter) {
				prop.getter = propIgnoreCase.getter;
			}
			if (null == prop.setter) {
				prop.setter = propIgnoreCase.setter;
			}
		}

		return prop;
	}

	/**
	 * [查找字段对应的Getter和Setter方法](Find the getter and setter methods corresponding to the field)
	 * @description zh - 查找字段对应的Getter和Setter方法
	 * @description en - Find the getter and setter methods corresponding to the field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:54:07
	 * @param field 字段
	 * @param methods 类中所有的方法
	 * @param ignoreCase 是否忽略大小写匹配
	 * @return com.xiaoTools.entity.propDesc.PropDesc
	 */
	private PropDesc findProp(Field field, Method[] methods, boolean ignoreCase) {
		final String fieldName = field.getName();
		final Class<?> fieldType = field.getType();
		final boolean isBooleanField = BooleanUtil.isBoolean(fieldType);

		Method getter = null;
		Method setter = null;
		String methodName;
		Class<?>[] parameterTypes;
		for (Method method : methods) {
			parameterTypes = method.getParameterTypes();
			if (parameterTypes.length > 1) {
				continue;
			}

			methodName = method.getName();
			if (parameterTypes.length == 0) {
				if (isMatchGetter(methodName, fieldName, isBooleanField, ignoreCase)) {
					getter = method;
				}
			} else if (isMatchSetter(methodName, fieldName, isBooleanField, ignoreCase)) {
				setter = method;
			}
			if (null != getter && null != setter) {
				break;
			}
		}

		return new PropDesc(field, getter, setter);
	}

	/**
	 * [方法是否为Getter方法](Whether the method is a getter method)
	 * @description zh - 方法是否为Getter方法
	 * @description en - Whether the method is a getter method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:54:53
	 * @param methodName 方法名
	 * @param fieldName 字段名
	 * @param isBooleanField 是否为Boolean类型字段
	 * @param ignoreCase 匹配是否忽略大小写
	 * @return boolean
	 */
	private boolean isMatchGetter(String methodName, String fieldName, boolean isBooleanField, boolean ignoreCase) {
		final String handledFieldName;
		if (ignoreCase) {
			methodName = methodName.toLowerCase();
			handledFieldName = fieldName.toLowerCase();
			fieldName = handledFieldName;
		} else {
			handledFieldName = StrUtil.upperFirst(fieldName);
		}

		if (Constant.FALSE == methodName.startsWith("get") && Constant.FALSE == methodName.startsWith("is")) {
			return Constant.FALSE;
		}
		if ("getclass".equals(methodName)) {
			return Constant.FALSE;
		}

		if (isBooleanField) {
			if (fieldName.startsWith("is")) {
				if (methodName.equals(fieldName)
						|| methodName.equals("get" + handledFieldName)
						|| methodName.equals("is" + handledFieldName)
				) {
					return Constant.TRUE;
				}
			} else if (methodName.equals("is" + handledFieldName)) {
				return Constant.TRUE;
			}
		}

		return methodName.equals("get" + handledFieldName);
	}

	/**
	 * [方法是否为Setter方法](Is the method a setter method)
	 * @description zh - 方法是否为Setter方法
	 * @description en - Is the method a setter method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:55:54
	 * @param methodName 方法名
	 * @param fieldName 字段名
	 * @param isBooleanField 是否为Boolean类型字段
	 * @param ignoreCase 匹配是否忽略大小写
	 * @return boolean
	 */
	private boolean isMatchSetter(String methodName, String fieldName, boolean isBooleanField, boolean ignoreCase) {
		final String handledFieldName;
		if (ignoreCase) {
			methodName = methodName.toLowerCase();
			handledFieldName = fieldName.toLowerCase();
			fieldName = handledFieldName;
		} else {
			handledFieldName = StrUtil.upperFirst(fieldName);
		}

		if (Constant.FALSE == methodName.startsWith("set")) {
			return Constant.FALSE;
		}

		if (isBooleanField && fieldName.startsWith("is")) {
			if (methodName.equals("set" + StrUtil.removePrefix(fieldName, "is"))
					|| methodName.equals("set" + handledFieldName)
			) {
				return Constant.TRUE;
			}
		}

		return methodName.equals("set" + fieldName);
	}

}
