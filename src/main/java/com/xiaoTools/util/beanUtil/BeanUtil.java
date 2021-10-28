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
import com.xiaoTools.entity.valueProvider.ValueProvider;
import com.xiaoTools.cache.beanDescCache.BeanDescCache;
import com.xiaoTools.cache.beanInfoCache.BeanInfoCache;
import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.editor.Editor;
import com.xiaoTools.core.exception.beanException.BeanException;
import com.xiaoTools.core.filter.Filter;
import com.xiaoTools.core.map.caseInsensitiveMap.CaseInsensitiveMap;
import com.xiaoTools.entity.beanCopier.BeanCopier;
import com.xiaoTools.entity.beanDesc.BeanDesc;
import com.xiaoTools.entity.beanPath.BeanPath;
import com.xiaoTools.entity.copyOptions.CopyOptions;
import com.xiaoTools.entity.dynaBean.DynaBean;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.collUtil.CollUtil;
import com.xiaoTools.util.mapUtil.MapUtil;
import com.xiaoTools.util.modifierUtil.ModifierUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.strUtil.StrUtil;

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
	 * [判断是否为Bean对象](Determine whether it is a bean object)
	 * @description zh - 判断是否为Bean对象
	 * @description en - Determine whether it is a bean object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:58:20
	 * @param clazz 实体类
	 * @return boolean
	 */
	public static boolean isBean(Class<?> clazz) {
		return hasSetter(clazz) || hasPublicField(clazz);
	}

	/**
	 * [解析Bean中的属性值](Resolve attribute values in a bean)
	 * @description zh - 解析Bean中的属性值
	 * @description en - Resolve attribute values in a bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 10:00:21
	 * @param bean Bean对象
	 * @param expression 表达式
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProperty(Object bean, String expression) {
		return (T) BeanPath.create(expression).get(bean);
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

	/**
	 * [Map转换为Bean对象](Convert map to bean object)
	 * @description zh - Map转换为Bean对象
	 * @description en - Convert map to bean object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 08:51:25
	 * @param map Map
	 * @param beanClass 实体类
	 * @param isToCamelCase 是否将Map中的下划线风格key转换为驼峰风格
	 * @param copyOptions 转Bean选项
	 * @return T
	 */
	public static <T> T mapToBean(Map<?, ?> map, Class<T> beanClass, boolean isToCamelCase, CopyOptions copyOptions) {
		return fillBeanWithMap(map, ReflectUtil.newInstanceIfPossible(beanClass), isToCamelCase, copyOptions);
	}

	/**
	 * [使用Map填充Bean对象](Filling bean objects with map)
	 * @description zh - 使用Map填充Bean对象
	 * @description en - Filling bean objects with map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:42:44
	 * @param map Map
	 * @param bean Bean
	 * @param isIgnoreError 是否忽略注入错误
	 * @return T
	 */
	public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isIgnoreError) {
		return fillBeanWithMap(map, bean, Constant.FALSE, isIgnoreError);
	}

	/**
	 * [使用Map填充Bean对象](Filling bean objects with map)
	 * @description zh - 使用Map填充Bean对象
	 * @description en - Filling bean objects with map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:44:44
	 * @param map Map
	 * @param bean Bean
	 * @param isToCamelCase 是否将下划线模式转换为驼峰模式
	 * @param isIgnoreError 是否忽略注入错误
	 * @return T
	 */
	public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isToCamelCase, boolean isIgnoreError) {
		return fillBeanWithMap(map, bean, isToCamelCase, CopyOptions.create().setIgnoreError(isIgnoreError));
	}

	/**
	 * [使用Map填充Bean对象](Filling bean objects with map)
	 * @description zh - 使用Map填充Bean对象
	 * @description en - Filling bean objects with map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:47:10
	 * @param map Map
	 * @param bean Bean
	 * @param isIgnoreError 是否忽略注入错误
	 * @return T
	 */
	public static <T> T fillBeanWithMapIgnoreCase(Map<?, ?> map, T bean, boolean isIgnoreError) {
		return fillBeanWithMap(map, bean, CopyOptions.create().setIgnoreCase(Constant.TRUE).setIgnoreError(isIgnoreError));
	}

	/**
	 * [使用Map填充Bean对象](Filling bean objects with map)
	 * @description zh - 使用Map填充Bean对象
	 * @description en - Filling bean objects with map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:47:32
	 * @param map Map
	 * @param bean Bean
	 * @param copyOptions 转Bean选项
	 * @return T
	 */
	public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, CopyOptions copyOptions) {
		return fillBeanWithMap(map, bean, Constant.FALSE, copyOptions);
	}

	/**
	 * [使用Map填充Bean对象](Filling bean objects with map)
	 * @description zh - 使用Map填充Bean对象
	 * @description en - Filling bean objects with map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 09:48:42
	 * @param map Map
	 * @param bean Bean
	 * @param isToCamelCase 是否将下划线模式转换为驼峰模式
	 * @param copyOptions 转Bean选项
	 */
	public static <T> T fillBeanWithMap(Map<?, ?> map, T bean, boolean isToCamelCase, CopyOptions copyOptions) {
		if (MapUtil.isEmpty(map)) {
			return bean;
		}
		if (isToCamelCase) {
			map = MapUtil.toCamelCaseMap(map);
		}
		copyProperties(map, bean, copyOptions);
		return bean;
	}

	/**
	 * [对象或Map转Bean](Object or map to bean)
	 * @description zh - 对象或Map转Bean
	 * @description en - Object or map to bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 17:58:46
	 * @param source Bean对象或Map
	 * @param clazz 目标的Bean类型
	 * @return T
	 */
	public static <T> T toBean(Object source, Class<T> clazz) {
		return toBean(source, clazz, null);
	}

	/**
	 * [对象或Map转Bean](Object or map to bean)
	 * @description zh - 对象或Map转Bean
	 * @description en - Object or map to bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 17:59:49
	 * @param source Bean对象或Map
	 * @param clazz 目标的Bean类型
	 * @return T
	 */
	public static <T> T toBeanIgnoreError(Object source, Class<T> clazz) {
		return toBean(source, clazz, CopyOptions.create().setIgnoreError(Constant.TRUE));
	}

	/**
	 * [对象或Map转Bean](Object or map to bean)
	 * @description zh - 对象或Map转Bean
	 * @description en - Object or map to bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:01:55
	 * @param source Bean对象或Map
	 * @param clazz 目标的Bean类型
	 * @param ignoreError 是否忽略注入错误
	 * @return T
	 */
	public static <T> T toBeanIgnoreCase(Object source, Class<T> clazz, boolean ignoreError) {
		return toBean(source, clazz,
				CopyOptions.create()
						.setIgnoreCase(Constant.TRUE)
						.setIgnoreError(ignoreError));
	}

	/**
	 * [对象或Map转Bean](Object or map to bean)
	 * @description zh - 对象或Map转Bean
	 * @description en - Object or map to bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:02:49
	 * @param source Bean对象或Map
	 * @param clazz 目标的Bean类型
	 * @param options 属性拷贝选项
	 * @return T
	 */
	public static <T> T toBean(Object source, Class<T> clazz, CopyOptions options) {
		if(null == source){
			return null;
		}
		final T target = ReflectUtil.newInstanceIfPossible(clazz);
		copyProperties(source, target, options);
		return target;
	}

	/**
	 * [ServletRequest 参数转 Bean](ServletRequest parameter to bean)
	 * @description zh - ServletRequest 参数转 Bean
	 * @description en - ServletRequest parameter to bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:03:55
	 * @param beanClass Bean Class
	 * @param valueProvider 值提供者
	 * @param copyOptions 拷贝选项
	 * @return T
	 */
	public static <T> T toBean(Class<T> beanClass, ValueProvider<String> valueProvider, CopyOptions copyOptions) {
		return null == beanClass || null == valueProvider ? null : fillBean(ReflectUtil.newInstanceIfPossible(beanClass), valueProvider, copyOptions);
	}

	/**
	 * [填充Bean的核心方法](Core method of filling bean)
	 * @description zh - 填充Bean的核心方法
	 * @description en - Core method of filling bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:05:25
	 * @param bean Bean Class
	 * @param valueProvider 值提供者
	 * @param copyOptions 拷贝选项
	 * @return T
	 */
	public static <T> T fillBean(T bean, ValueProvider<String> valueProvider, CopyOptions copyOptions) {
		return null == valueProvider ? bean : BeanCopier.create(valueProvider, bean, copyOptions).copy();
	}

	/**
	 * [对象转Map](Object to map)
	 * @description zh - 对象转Map
	 * @description en - Object to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:09:17
	 * @param bean 实体类
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		return beanToMap(bean, Constant.FALSE, Constant.FALSE);
	}

	/**
	 * [对象转Map](Object to map)
	 * @description zh - 对象转Map
	 * @description en - Object to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:10:47
	 * @param bean 实体类
	 * @param isToUnderlineCase 是否转换为下划线模式
	 * @param ignoreNullValue 是否忽略值为空的字段
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 */
	public static Map<String, Object> beanToMap(Object bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
		return null == bean ? null : beanToMap(bean, new LinkedHashMap<>(), isToUnderlineCase, ignoreNullValue);
	}

	/**
	 * [对象转Map](Object to map)
	 * @description zh - 对象转Map
	 * @description en - Object to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:12:17
	 * @param bean 实体类
	 * @param targetMap 目标的Map
	 * @param isToUnderlineCase 是否转换为下划线模式
	 * @param ignoreNullValue 是否忽略值为空的字段
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 */
	public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, final boolean isToUnderlineCase, boolean ignoreNullValue) {
		return null == bean ? null : beanToMap(bean, targetMap, ignoreNullValue, key -> isToUnderlineCase ? StrUtil.toUnderlineCase(key) : key);
	}

	/**
	 * [对象转Map](Object to map)
	 * @description zh - 对象转Map
	 * @description en - Object to map
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:14:20
	 * @param bean 实体类
	 * @param targetMap 目标的Map
	 * @param ignoreNullValue 是否忽略值为空的字段
	 * @param keyEditor 属性字段（Map的key）编辑器
	 * @return java.util.Map<java.lang.String, java.lang.Object>
	 */
	public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, boolean ignoreNullValue, Editor<String> keyEditor) {
		return null == bean ? null :
			BeanCopier.create(bean, targetMap,
				CopyOptions.create()
						.setIgnoreNullValue(ignoreNullValue)
						.setFieldNameEditor(keyEditor)
			).copy();
	}

	/**
	 * [按照Bean对象属性创建对应的Class对象](Create the corresponding class object according to the bean object attribute)
	 * @description zh - 按照Bean对象属性创建对应的Class对象
	 * @description en - Create the corresponding class object according to the bean object attribute
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:16:47
	 * @param source 源Bean对象
	 * @param tClass 目标Class
	 * @param ignoreProperties 不拷贝的的属性列表
	 * @return T
	 */
	public static <T> T copyProperties(Object source, Class<T> tClass, String... ignoreProperties) {
		T target = ReflectUtil.newInstanceIfPossible(tClass);
		copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
		return target;
	}

	/**
	 * [复制Bean对象属性](Copy bean object properties)
	 * @description zh - 复制Bean对象属性
	 * @description en - Copy bean object properties
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:17:51
	 * @param source 源Bean对象
	 * @param target 目标Bean对象
	 * @param ignoreProperties 不拷贝的的属性列表
	 */
	public static void copyProperties(Object source, Object target, String... ignoreProperties) {
		copyProperties(source, target, CopyOptions.create().setIgnoreProperties(ignoreProperties));
	}

	/**
	 * [复制Bean对象属性](Copy bean object properties)
	 * @description zh - 复制Bean对象属性
	 * @description en - Copy bean object properties
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:19:18
	 * @param source 源Bean对象
	 * @param target 目标Bean对象
	 * @param ignoreCase 是否忽略大小写
	 */
	public static void copyProperties(Object source, Object target, boolean ignoreCase) {
		BeanCopier.create(source, target, CopyOptions.create().setIgnoreCase(ignoreCase)).copy();
	}

	/**
	 * [复制Bean对象属性](Copy bean object properties)
	 * @description zh - 复制Bean对象属性
	 * @description en - Copy bean object properties
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:20:01
	 * @param source 源Bean对象
	 * @param target 目标Bean对象
	 * @param copyOptions 拷贝选项
	 */
	public static void copyProperties(Object source, Object target, CopyOptions copyOptions) {
		if (null == copyOptions) {
			copyOptions = new CopyOptions();
		}
		BeanCopier.create(source, target, copyOptions).copy();
	}

	/**
	 * [给定的Bean的类名是否匹配指定类名字符串](Does the class name of the given bean match the specified class name string)
	 * @description zh - 给定的Bean的类名是否匹配指定类名字符串
	 * @description en - Does the class name of the given bean match the specified class name string
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:20:48
	 * @param bean Bean
	 * @param beanClassName Bean的类名
	 * @param isSimple 是否只匹配类名而忽略包名，true表示忽略包名
	 * @return boolean
	 */
	public static boolean isMatchName(Object bean, String beanClassName, boolean isSimple) {
		return ClassUtil.getClassName(bean, isSimple).equals(isSimple ? StrUtil.upperFirst(beanClassName) : beanClassName);
	}

	/**
	 * [把Bean里面的String属性做trim操作](Trim the string attribute in the bean)
	 * @description zh - 把Bean里面的String属性做trim操作
	 * @description en - Trim the string attribute in the bean
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:22:23
	 * @param bean 实体类
	 * @param ignoreFields 不需要trim的Field名称列表（不区分大小写）
	 * @return T
	 */
	public static <T> T trimStrFields(T bean, String... ignoreFields) {
		if (bean == null) {
			return null;
		}

		final Field[] fields = ReflectUtil.getFields(bean.getClass());
		for (Field field : fields) {
			if (ModifierUtil.isStatic(field)) {
				continue;
			}
			if (ignoreFields != null && ArrayUtil.containsIgnoreCase(ignoreFields, field.getName())) {
				continue;
			}
			if (String.class.equals(field.getType())) {
				final String val = (String) ReflectUtil.getFieldValue(bean, field);
				if (null != val) {
					final String trimVal = StrUtil.trim(val);
					if (Constant.FALSE == val.equals(trimVal)) {
						ReflectUtil.setFieldValue(bean, field, trimVal);
					}
				}
			}
		}

		return bean;
	}

	/**
	 * [判断Bean是否为非空对象](Determine whether the bean is a non empty object)
	 * @description zh - 判断Bean是否为非空对象
	 * @description en - Determine whether the bean is a non empty object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:23:25
	 * @param bean 实体类
	 * @param ignoreFiledNames 忽略检查的字段名
	 * @return boolean
	 */
	public static boolean isNotEmpty(Object bean, String... ignoreFiledNames) {
		return Constant.FALSE == isEmpty(bean, ignoreFiledNames);
	}

	/**
	 * [判断Bean是否为空对象](Determine whether the bean is an empty object)
	 * @description zh - 判断Bean是否为空对象
	 * @description en - Determine whether the bean is an empty object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:24:38
	 * @param bean 实体类
	 * @param ignoreFiledNames 忽略检查的字段名
	 * @return boolean
	 */
	public static boolean isEmpty(Object bean, String... ignoreFiledNames) {
		if (null != bean) {
			for (Field field : ReflectUtil.getFields(bean.getClass())) {
				if (ModifierUtil.isStatic(field)) {
					continue;
				}
				if ((Constant.FALSE == ArrayUtil.contains(ignoreFiledNames, field.getName()))
						&& null != ReflectUtil.getFieldValue(bean, field)) {
					return Constant.FALSE;
				}
			}
		}
		return Constant.TRUE;
	}

	/**
	 * [判断Bean是否包含值为 null 的属性](Determine whether the bean contains a property with null value)
	 * @description zh - 判断Bean是否包含值为 null 的属性
	 * @description en - Determine whether the bean contains a property with null value
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:25:39
	 * @param bean 实体类
	 * @param ignoreFiledNames 忽略检查的字段名
	 * @return boolean
	 */
	public static boolean hasNullField(Object bean, String... ignoreFiledNames) {
		if (null == bean) {
			return Constant.TRUE;
		}
		for (Field field : ReflectUtil.getFields(bean.getClass())) {
			if (ModifierUtil.isStatic(field)) {
				continue;
			}
			if ((Constant.FALSE == ArrayUtil.contains(ignoreFiledNames, field.getName()))
					&& null == ReflectUtil.getFieldValue(bean, field)) {
				return Constant.TRUE;
			}
		}
		return Constant.FALSE;
	}


}
