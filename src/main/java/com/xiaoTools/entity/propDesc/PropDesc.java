package com.xiaoTools.entity.propDesc;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.xiaoTools.annotation.propIgnore.PropIgnore;
import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.exception.beanException.BeanException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.modifierUtil.ModifierUtil;
import com.xiaoTools.util.modifierUtil.modifierType.ModifierType;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;

/**
 * [属性描述](Attribute description)
 * @description zh - 属性描述
 * @description en - Attribute description
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-25 18:35:34
 */
public class PropDesc {

	/**
	 * 字段
	 */
	final Field field;

	/**
	 * Getter方法
	 */
	public Method getter;

	/**
	 * Setter方法
	 */
	public Method setter;

	public PropDesc(Field field, Method getter, Method setter) {
		this.field = field;
		this.getter = ClassUtil.setAccessible(getter);
		this.setter = ClassUtil.setAccessible(setter);
	}

	/**
	 * [获取字段名](Get field name)
	 * @description zh - 获取字段名
	 * @description en - Get field name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:44:57
	 * @return java.lang.String
	 */
	public String getFieldName() {
		return ReflectUtil.getFieldName(this.field);
	}

	/**
	 * [获取字段名称](Get field name)
	 * @description zh - 获取字段名称
	 * @description en - Get field name
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:45:31
	 * @return java.lang.String
	 */
	public String getRawFieldName() {
		return null == this.field ? null : this.field.getName();
	}

	/**
	 * [获取字段](Get field)
	 * @description zh - 获取字段
	 * @description en - Get field
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:49:03
	 * @param java.lang.reflect.Field
	 */
	public Field getField() {
		return this.field;
	}

	/**
	 * [获得字段类型](Get field type)
	 * @description zh - 获得字段类型
	 * @description en - Get field type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:50:40
	 * @return java.lang.reflect.Type
	 */
	public Type getFieldType() {
		return null != this.field ? TypeUtil.getType(this.field) : findPropType(getter, setter);
	}

	/**
	 * [获得字段类型](Get field type)
	 * @description zh - 获得字段类型
	 * @description en - Get field type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:52:34
	 * @return java.lang.Class<?>
	 */
	public Class<?> getFieldClass() {
		return null != this.field ? TypeUtil.getClass(this.field) : findPropClass(getter, setter);
	}

	/**
	 * [获取Getter方法](Get getter method)
	 * @description zh - 获取Getter方法
	 * @description en - Get getter method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:54:07
	 * @return java.lang.reflect.Method
	 */
	public Method getGetter() {
		return this.getter;
	}

	/**
	 * [获取 setter 方法](Get setter method)
	 * @description zh - 获取 setter方法
	 * @description en - Get setter method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:55:21
	 * @return java.lang.reflect.Method
	 */
	public Method getSetter() {
		return this.setter;
	}

	/**
	 * [Check whether the attribute is readable](Check whether the attribute is readable)
	 * @description zh - 检查属性是否可读
	 * @description en - Check whether the attribute is readable
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 18:58:31
	 * @param checkTransient 是否检查Transient关键字或注解
	 * @return boolean
	 */
	public boolean isReadable(boolean checkTransient){
		return null == this.getter && Constant.FALSE ? Constant.FALSE :
			checkTransient && isTransientForGet() ? Constant.FALSE :
				Constant.FALSE == isIgnoreGet();
	}

	/**
	 * [获取属性值](Get property value)
	 * @description zh - 获取属性值
	 * @description en - Get property value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:01:14
	 * @param bean 实体类
	 * @return java.lang.Object
	 */
	public Object getValue(Object bean) {
		return null != this.getter ? ReflectUtil.invoke(bean, this.getter) :
			ModifierUtil.isPublic(this.field) ? ReflectUtil.getFieldValue(bean, this.field) :
				null;
	}

	/**
	 * [获取属性值](Get property value)
	 * @description zh - 获取属性值
	 * @description en - Get property value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:02:17
	 * @param bean
	 * @param targetType
	 * @param ignoreError
	 * @return java.lang.Object
	 */
	public Object getValue(Object bean, Type targetType, boolean ignoreError) {
		Object result = null;
		try {
			result = getValue(bean);
		} catch (Exception e) {
			if (false == ignoreError) {
				throw new BeanException(e, "Get value of [{}] error!", getFieldName());
			}
		}

		if (null != result && null != targetType) {
			final Object convertValue = Convert.convertWithCheck(targetType, result, null, ignoreError);
			if (null != convertValue) {
				result = convertValue;
			}
		}
		return result;
	}

	/**
	 * [检查属性是否可读](Check whether the attribute is readable)
	 * @description zh - 检查属性是否可读
	 * @description en - Check whether the attribute is readable
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:05:25
	 * @param checkTransient 是否检查Transient关键字或注解
	 * @return boolean
	 */
	public boolean isWritable(boolean checkTransient){
		return null == this.setter && Constant.FALSE == ModifierUtil.isPublic(this.field) ? Constant.FALSE :
			checkTransient && isTransientForSet() ? Constant.FALSE :
				Constant.FALSE == isIgnoreSet();
	}

	/**
	 * [设置Bean的字段值](Set the field value of the bean)
	 * @description zh - 设置Bean的字段值
	 * @description en - Set the field value of the bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:07:58
	 * @param bean
	 * @param value
	 * @return com.xiaoTools.entity.propDesc.PropDesc
	 */
	public PropDesc setValue(Object bean, Object value) {
		if (null != this.setter) {
			ReflectUtil.invoke(bean, this.setter, value);
		} else if (ModifierUtil.isPublic(this.field)) {
			ReflectUtil.setFieldValue(bean, this.field, value);
		}
		return this;
	}

	/**
	 * [设置属性值](Set attribute value)
	 * @description zh - 设置属性值
	 * @description en - Set attribute value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 19:12:04
	 * @param bean Bean对象
	 * @param value 属性值
	 * @param ignoreNull 是否可以忽略null
	 * @param ignoreError 是否可以忽略错误
	 * @return com.xiaoTools.entity.propDesc.PropDesc
	 */
	public PropDesc setValue(Object bean, Object value, boolean ignoreNull, boolean ignoreError) {
		if (ignoreNull && null == value) {
			return this;
		}
		if (null != value) {
			final Class<?> propClass = getFieldClass();
			if (Constant.FALSE == propClass.isInstance(value)) {
				value = Convert.convertWithCheck(propClass, value, null, ignoreError);
			}
		}
		if (null != value || Constant.FALSE == ignoreNull) {
			try {
				this.setValue(bean, value);
			} catch (Exception e) {
				if (Constant.FALSE == ignoreError) {
					throw new BeanException(e, "Set value of [{}] error!", getFieldName());
				}
			}
		}

		return this;
	}

	private Type findPropType(Method getter, Method setter) {
		Type type = null;
		if (null != getter) {
			type = TypeUtil.getReturnType(getter);
		}
		if (null == type && null != setter) {
			type = TypeUtil.getParamType(setter, 0);
		}
		return type;
	}

	private Class<?> findPropClass(Method getter, Method setter) {
		Class<?> type = null;
		if (null != getter) {
			type = TypeUtil.getReturnClass(getter);
		}
		if (null == type && null != setter) {
			type = TypeUtil.getFirstParamClass(setter);
		}
		return type;
	}

	private boolean isIgnoreSet() {
		return AnnotationUtil.hasAnnotation(this.field, PropIgnore.class)
				|| AnnotationUtil.hasAnnotation(this.setter, PropIgnore.class);
	}

	private boolean isIgnoreGet() {
		return AnnotationUtil.hasAnnotation(this.field, PropIgnore.class)
				|| AnnotationUtil.hasAnnotation(this.getter, PropIgnore.class);
	}

	private boolean isTransientForGet() {
		boolean isTransient = ModifierUtil.hasModifier(this.field, ModifierType.TRANSIENT);

		if (Constant.FALSE == isTransient && null != this.getter) {
			isTransient = ModifierUtil.hasModifier(this.getter, ModifierType.TRANSIENT);

			if (Constant.FALSE == isTransient) {
				isTransient = AnnotationUtil.hasAnnotation(this.getter, Transient.class);
			}
		}

		return isTransient;
	}

	private boolean isTransientForSet() {
		boolean isTransient = ModifierUtil.hasModifier(this.field, ModifierType.TRANSIENT);

		if (Constant.FALSE == isTransient && null != this.setter) {
			isTransient = ModifierUtil.hasModifier(this.setter, ModifierType.TRANSIENT);

			if (Constant.FALSE == isTransient) {
				isTransient = AnnotationUtil.hasAnnotation(this.setter, Transient.class);
			}
		}

		return isTransient;
	}

}
