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

import com.xiaoTools.entity.propDesc.PropDesc;
import com.xiaoTools.cache.beanDescCache.BeanDescCache;
import com.xiaoTools.cache.beanInfoCache.BeanInfoCache;
import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.exception.beanException.BeanException;
import com.xiaoTools.core.filter.Filter;
import com.xiaoTools.core.map.caseInsensitiveMap.CaseInsensitiveMap;
import com.xiaoTools.entity.beanDesc.BeanDesc;
import com.xiaoTools.entity.beanPath.BeanPath;
import com.xiaoTools.entity.dynaBean.DynaBean;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;

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

	/**
	 * [获取 Bean 描述信息](Get bean description information)
	 * @description zh - 获取 Bean 描述信息
	 * @description en - Get bean description information
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:12:35
	 * @param class Bean类
	 */
	public static BeanDesc getBeanDesc(Class<?> clazz) {
		return BeanDescCache.INSTANCE.getBeanDesc(clazz, () -> new BeanDesc(clazz));
	}

	/**
	 * [遍历Bean的属性](Traversing the properties of a bean)
	 * @description zh - 遍历Bean的属性
	 * @description en - Traversing the properties of a bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:13:59
	 * @param clazz Bean类
	 * @param action 每个元素的处理类
	 */
	public static void descForEach(Class<?> clazz, Consumer<? super PropDesc> action) {
		getBeanDesc(clazz).getProps().forEach(action);
	}

	/**
	 * [获得Bean字段描述数组](Get bean field description array)
	 * @description zh - 获得Bean字段描述数组
	 * @description en - Get bean field description array
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:41:22
	 * @param clazz Bean类
	 * @throws com.xiaoTools.core.exception.beanException.BeanException
	 * @return java.beans.PropertyDescriptor[]
	 */
	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws BeanException {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (IntrospectionException e) {
			throw new BeanException(e);
		}
		return ArrayUtil.filter(beanInfo.getPropertyDescriptors(), (Filter<PropertyDescriptor>) t -> {
			return Constant.FALSE == "class".equals(t.getName());
		});
	}

	/**
	 * [获得字段名和字段描述Map](Get field name and field description map)
	 * @description zh - 获得字段名和字段描述Map
	 * @description en - Get field name and field description map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 09:58:46
	 * @param clazz
	 * @param ignoreCase
	 * @return java.util.Map<java.lang.String, java.beans.PropertyDescriptor>
	 */
	public static Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) throws BeanException {
		return BeanInfoCache.INSTANCE.getPropertyDescriptorMap(clazz, ignoreCase, () -> internalGetPropertyDescriptorMap(clazz, ignoreCase));
	}

	/**
	 * [获得字段名和字段描述Map](Get field name and field description map)
	 * @description zh - 获得字段名和字段描述Map
	 * @description en - Get field name and field description map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 10:09:42
	 * @param clazz bean类型
	 * @param ignoreCase 是否忽略大小写
	 * @throws com.xiaoTools.core.exception.beanException.BeanException
	 * @return java.util.Map<java.lang.String, java.beans.PropertyDescriptor>
	 */
	private static Map<String, PropertyDescriptor> internalGetPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) throws BeanException {
		final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
		final Map<String, PropertyDescriptor> map = ignoreCase ? new CaseInsensitiveMap<>(propertyDescriptors.length, 1)
				: new HashMap<>((int) (propertyDescriptors.length), 1);

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			map.put(propertyDescriptor.getName(), propertyDescriptor);
		}
		return map;
	}

	/**
	 * [获得Bean类属性描述，大小写敏感](Get the bean class attribute description, case sensitive)
	 * @description zh - 获得Bean类属性描述，大小写敏感
	 * @description en - Get the bean class attribute description, case sensitive
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:15:27
	 * @param clazz 实体类
	 * @param fieldName 字段名称
	 * @return java.beans.PropertyDescriptor
	 */
	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, final String fieldName) throws BeanException {
		return getPropertyDescriptor(clazz, fieldName, Constant.FALSE);
	}

	/**
	 * [获得Bean类属性描述](Get the bean class attribute description)
	 * @description zh - 获得Bean类属性描述
	 * @description en - Get the bean class attribute description
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:16:19
	 * @param clazz bean实体类
	 * @return fieldName 字段名称
	 * @return ignoreCase 是否忽略大小写
	 * @return java.beans.PropertyDescriptor
	 */
	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, final String fieldName, boolean ignoreCase) throws BeanException {
		final Map<String, PropertyDescriptor> map = getPropertyDescriptorMap(clazz, ignoreCase);
		return (null == map) ? null : map.get(fieldName);
	}

	/**
	 * [获得字段值](Get field value)
	 * @description zh - 获得字段值
	 * @description en - Get field value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:18:41
	 * @param bean
	 * @param fieldNameOrIndex
	 * @return java.lang.Object
	 */
	public static Object getFieldValue(Object bean, String fieldNameOrIndex) {
		if (null == bean || null == fieldNameOrIndex) {
			return null;
		}

		if (bean instanceof Map) {
			return ((Map<?, ?>) bean).get(fieldNameOrIndex);
		} else if (bean instanceof Collection) {
			try{
				return CollUtil.get((Collection<?>) bean, Integer.parseInt(fieldNameOrIndex));
			} catch (NumberFormatException e){
				return CollUtil.map((Collection<?>) bean, (beanEle)-> getFieldValue(beanEle, fieldNameOrIndex), false);
			}
		} else if (ArrayUtil.isArray(bean)) {
			try{
				return ArrayUtil.get(bean, Integer.parseInt(fieldNameOrIndex));
			} catch (NumberFormatException e){
				return ArrayUtil.map(bean, Object.class, (beanEle)-> getFieldValue(beanEle, fieldNameOrIndex));
			}
		} else {
			return ReflectUtil.getFieldValue(bean, fieldNameOrIndex);
		}
	}

	/**
	 * [获得字段值](Get field value)
	 * @description zh - 获得字段值
	 * @description en - Get field value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:20:38
	 * @param bean Bean
	 * @param fieldNameOrIndex 字段名或序号
	 * @param value 值
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void setFieldValue(Object bean, String fieldNameOrIndex, Object value) {
		if (bean instanceof Map) {
			((Map) bean).put(fieldNameOrIndex, value);
		} else if (bean instanceof List) {
			CollUtil.setOrAppend((List) bean, Convert.toInt(fieldNameOrIndex), value);
		} else if (ArrayUtil.isArray(bean)) {
			ArrayUtil.setOrAppend(bean, Convert.toInt(fieldNameOrIndex), value);
		} else {
			ReflectUtil.setFieldValue(bean, fieldNameOrIndex, value);
		}
	}

	/**
	 * [解析Bean中的属性值](Resolve attribute values in a bean)
	 * @description zh - 解析Bean中的属性值
	 * @description en - Resolve attribute values in a bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-26 20:23:44
	 * @param bean 实体类
	 * @param expression 表达式
	 * @param value 属性值
	 */
	public static void setProperty(Object bean, String expression, Object value) {
		BeanPath.create(expression).set(bean, value);
	}


}
