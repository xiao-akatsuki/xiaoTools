package com.xiaoTools.util.reflectUtil;

import com.xiaoTools.annotation.alias.Alias;
import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.cache.simple.SimpleCache;
import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.core.filter.Filter;
import com.xiaoTools.entity.nullWrapperEntity.NullWrapperEntity;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.strUtil.StrUtil;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * [反射工具类](Reflection tools)
 * @description: zh - 反射工具类
 * @description: en - Reflection tools
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/13 6:34 下午
*/
public class ReflectUtil {

    /**
     * 构造对象缓存
     */
    private static final SimpleCache<Class<?>, Constructor<?>[]> CONSTRUCTORS_CACHE = new SimpleCache<>();

    /**
     * 字段缓存
     */
    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();

    /**
     * 方法缓存
     */
    private static final SimpleCache<Class<?>, Method[]> METHODS_CACHE = new SimpleCache<>();

    /*建造模块--------------------------------------------------------------------Constructor*/

    /**
     * [查找类中的指定参数的构造方法，如果找到构造方法，会自动设置可访问为true](Find the construction method of the specified parameter in the class. If the construction method is found, it will be set to true automatically)
     * @description: zh - 查找类中的指定参数的构造方法，如果找到构造方法，会自动设置可访问为true
     * @description: en - Find the construction method of the specified parameter in the class. If the construction method is found, it will be set to true automatically
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:29 下午
     * @param clazz: 类
     * @param parameterTypes: 参数类型
     * @return java.lang.reflect.Constructor<T>
    */
	@SuppressWarnings("unchecked")
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        if (Constant.NULL == clazz) { return (Constructor<T>) Constant.NULL; }
        final Constructor<?>[] constructors = getConstructors(clazz);
        Class<?>[] pts;
        for (Constructor<?> constructor : constructors) {
            pts = constructor.getParameterTypes();
            if (ClassUtil.isAllAssignableFrom(pts, parameterTypes)) {
                // 构造可访问
                setAccessible(constructor);
                return (Constructor<T>) constructor;
            }
        }
        return null;
    }

    /**
     * [获得一个类中所有构造列表](Get a list of all constructs in a class)
     * @description: zh - 获得一个类中所有构造列表
     * @description: en - Get a list of all constructs in a class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 9:45 上午
     * @param beanClass: 类
     * @return java.lang.reflect.Constructor<T>[]
    */
	@SuppressWarnings("unchecked")
    public static <T> Constructor<T>[] getConstructors(Class<T> beanClass) throws SecurityException {
        Assertion.notNull(beanClass);
        Constructor<?>[] constructors = CONSTRUCTORS_CACHE.get(beanClass);
        if (null != constructors) {
            return (Constructor<T>[]) constructors;
        }

        constructors = getConstructorsDirectly(beanClass);
        return (Constructor<T>[]) CONSTRUCTORS_CACHE.put(beanClass, constructors);
    }

    /**
     * [获得一个类中所有构造列表，直接反射获取，无缓存](Get a list of all constructs in a class, get them by direct reflection, no cache)
     * @description: zh - 获得一个类中所有构造列表，直接反射获取，无缓存
     * @description: en - Get a list of all constructs in a class, get them by direct reflection, no cache
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:29 下午
     * @param beanClass: 类
     * @return java.lang.reflect.Constructor<?>[]
    */
    public static Constructor<?>[] getConstructorsDirectly(Class<?> beanClass) throws SecurityException {
        Assertion.notNull(beanClass);
        return beanClass.getDeclaredConstructors();
    }

    /**
     * [设置方法为可访问（私有方法可以被外部调用)](Set methods to be accessible (private methods can be called externally))
     * @description: zh - 设置方法为可访问（私有方法可以被外部调用)
     * @description: en - Set methods to be accessible (private methods can be called externally)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/13 8:14 下午
     * @param accessibleObject:
     * @return T
    */
    public static <T extends AccessibleObject> T setAccessible(T accessibleObject) {
        if (null != accessibleObject && !accessibleObject.canAccess(accessibleObject)) {
            accessibleObject.setAccessible(true);
        }
        return accessibleObject;
    }

    /*字段--------------------------------------------------------------------Field*/

    /**
     * [查找指定类中是否包含指定名称对应的字段，包括所有字段（包括非public字段），也包括父类和Object类的字段](Find whether the specified class contains fields corresponding to the specified name, including all fields (including non-public fields), as well as fields of parent class and object class)
     * @description: zh - 查找指定类中是否包含指定名称对应的字段，包括所有字段（包括非public字段），也包括父类和Object类的字段
     * @description: en - Find whether the specified class contains fields corresponding to the specified name, including all fields (including non-public fields), as well as fields of parent class and object class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 3:38 下午
     * @param beanClass:  被查找字段的类,不能为null
     * @param name: 字段名
     * @return boolean
    */
    public static boolean hasField(Class<?> beanClass, String name) throws SecurityException {
        return null != getField(beanClass, name);
    }

    /**
     * [获得一个类中所有字段列表，包括其父类中的字段。](Get a list of all the fields in a class, including the fields in its parent class.)
     * @description: zh - 获得一个类中所有字段列表，包括其父类中的字段。
     * @description: en - Get a list of all the fields in a class, including the fields in its parent class.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 10:10 下午
     * @param beanClass: 类
     * @return java.lang.reflect.Field[]
    */
    public static Field[] getFields(Class<?> beanClass) throws SecurityException {
        Field[] allFields = FIELDS_CACHE.get(beanClass);
        if (Constant.NULL != allFields) { return allFields; }
        allFields = getFieldsDirectly(beanClass, Constant.TRUE);
        return FIELDS_CACHE.put(beanClass, allFields);
    }

    /**
     * [获得一个类中所有字段列表，直接反射获取，无缓存](Get a list of all fields in a class, get it directly by reflection, no cache)
     * @description: zh - 获得一个类中所有字段列表，直接反射获取，无缓存
     * @description: en - Get a list of all fields in a class, get it directly by reflection, no cache
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 10:12 下午
     * @param beanClass: 类
     * @param withSuperClassFields: 是否包括父类的字段列表
     * @return java.lang.reflect.Field[]
    */
    public static Field[] getFieldsDirectly(Class<?> beanClass, boolean withSuperClassFields) throws SecurityException {
        Assertion.notNull(beanClass);
        Field[] allFields = Constant.FIELDS_NULL;
        Class<?> searchType = beanClass;
        Field[] declaredFields;
        while (searchType != Constant.NULL) {
            declaredFields = searchType.getDeclaredFields();
            if (Constant.NULL == allFields) {
                allFields = declaredFields;
            } else {
                allFields = ArrayUtil.append(allFields, declaredFields);
            }
            searchType = withSuperClassFields ? searchType.getSuperclass() : (Class<?>) Constant.NULL;
        }
        return allFields;
    }

    /**
     * [获取字段值](Get field value)
     * @description: zh - 获取字段值
     * @description: en - Get field value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 10:25 上午
     * @param obj: 对象，如果static字段，此处为类
     * @param fieldName: 字段名
     * @return java.lang.Object
    */
    public static Object getFieldValue(Object obj, String fieldName) throws UtilException {
        if (Constant.NULL == obj || StrUtil.isBlank(fieldName)) {
            return Constant.NULL;
        }
        return getFieldValue(obj, getField(obj instanceof Class ? (Class<?>) obj : obj.getClass(), fieldName));
    }

    /**
     * [获取字段值](Get field value)
     * @description: zh - 获取字段值
     * @description: en - Get field value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 3:15 下午
     * @param obj: 对象，static字段则此字段为null
     * @param field: 字段
     * @return java.lang.Object
    */
    public static Object getFieldValue(Object obj, Field field) throws UtilException {
        if (Constant.NULL  == field) { return Constant.NULL; }
        // 静态字段获取时对象为null
        if (obj instanceof Class) { obj = Constant.NULL; }
        setAccessible(field);
        Object result;
        try {
            result = field.get(obj);
        } catch (IllegalAccessException e) {
            throw new UtilException(e, "IllegalAccess for {}.{}", field.getDeclaringClass(), field.getName());
        }
        return result;
    }

    /**
     * [查找指定类中的指定name的字段（包括非public字段），也包括父类和Object类的字段， 字段不存在则返回null](Find the fields of the specified name in the specified class (including non-public fields), as well as the fields of the parent class and object class. If the field does not exist, null will be returned)
     * @description: zh - 查找指定类中的指定name的字段（包括非public字段），也包括父类和Object类的字段， 字段不存在则返回null
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 3:05 下午
     * @param beanClass: 被查找字段的类,不能为null
     * @param name: 字段名
     * @return java.lang.reflect.Field
    */
    public static Field getField(Class<?> beanClass, String name) throws SecurityException {
        final Field[] fields = getFields(beanClass);
        return ArrayUtil.firstMatch((field) -> name.equals(getFieldName(field)), fields);
    }

    /**
     * [获取字段名，如果存在 Alias 注解，读取注解的值作为名称](Get the field name. If there is an alias annotation, read the value of the annotation as the name)
     * @description: zh - 获取字段名，如果存在 Alias 注解，读取注解的值作为名称
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 3:23 下午
     * @param field: 类
     * @return java.lang.String
    */
    public static String getFieldName(Field field) {
        if (Constant.NULL == field) { return Constant.STRING_NULL; }
        final Alias alias = field.getAnnotation(Alias.class);
        if (Constant.NULL != alias) { return alias.value(); }
        return field.getName();
    }

    /**
     * [获取静态字段值](Get static field value)
     * @description: zh - 获取静态字段值
     * @description: en - Get static field value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 4:00 下午
     * @param field: 字段
     * @return java.lang.Object
    */
    public static Object getStaticFieldValue(Field field) throws UtilException {
        return getFieldValue(Constant.NULL, field);
    }

    /**
     * [获取所有字段的值](Gets the values of all fields)
     * @description: zh - 获取所有字段的值
     * @description: en - Gets the values of all fields
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 4:04 下午
     * @param obj: bean对象，如果是static字段，此处为类class
     * @return java.lang.Object[]
    */
    public static Object[] getFieldsValue(Object obj) {
        if (Constant.NULL != obj) {
            final Field[] fields = getFields(obj instanceof Class ? (Class<?>) obj : obj.getClass());
            if (Constant.NULL != fields) {
                final Object[] values = new Object[fields.length];
                for (int i = Constant.ZERO; i < fields.length; i++) {
                    values[i] = getFieldValue(obj, fields[i]);
                }
                return values;
            }
        }
        return Constant.OBJECTS_ARRAY_NULL;
    }

	/**
	 * [查找指定对象中的所有方法](Finds all methods in the specified object)
	 * @description zh - 查找指定对象中的所有方法
	 * @description en - Finds all methods in the specified object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 19:04:17
	 * @param obj 对象
	 * @param methodName 方法名称
	 * @param args 参数列表
	 * @return java.lang.reflect.Method
	 */
	public static Method getMethodOfObj(Object obj, String methodName, Object... args) throws SecurityException {
		return null == obj || StrUtil.isBlank(methodName) ? null : getMethod(obj.getClass(), methodName, ClassUtil.getClasses(args));
	}

	/**
	 * [查找指定方法](Find the specified method)
	 * @description zh - 查找指定方法
	 * @description en - Find the specified method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 19:05:54
	 * @param clazz 类
	 * @param methodName 方法名
	 * @param paramTypes 参数类型
	 * @return java.lang.reflect.Method
	 */
	public static Method getMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) throws SecurityException {
		return getMethod(clazz, false, methodName, paramTypes);
	}

	/**
	 * [查找指定方法](Find the specified method)
	 * @description zh - 查找指定方法
	 * @description en - Find the specified method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 19:07:12
	 * @param clazz 类
	 * @param ignoreCase 是否忽略大小写
	 * @param methodName 方法名
	 * @param paramTypes 参数类型
	 * @return java.lang.reflect.Method
	 */
	public static Method getMethod(Class<?> clazz, boolean ignoreCase, String methodName, Class<?>... paramTypes) throws SecurityException {
		if (null == clazz || StrUtil.isBlank(methodName)) {
			return null;
		}

		final Method[] methods = getMethods(clazz);
		if (ArrayUtil.isNotEmpty(methods)) {
			for (Method method : methods) {
				if (StrUtil.equals(methodName, method.getName(), ignoreCase)) {
					if (ClassUtil.isAllAssignableFrom(method.getParameterTypes(), paramTypes)) {
						return method;
					}
				}
			}
		}
		return null;
	}

	/**
	 * [获得一个类中所有方法列表](Get a list of all methods in a class)
	 * @description zh - 获得一个类中所有方法列表
	 * @description en - Get a list of all methods in a class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 19:09:41
	 * @param beanClass 类
	 * @return java.lang.reflect.Method
	 */
	public static Method[] getMethods(Class<?> beanClass) throws SecurityException {
		Method[] allMethods = METHODS_CACHE.get(beanClass);
		if (null != allMethods) {
			return allMethods;
		}

		allMethods = getMethodsDirectly(beanClass, true);
		return METHODS_CACHE.put(beanClass, allMethods);
	}

	/**
	 * [获得指定类过滤后的Public方法列表](Gets the filtered public method list of the specified class)
	 * @description zh - 获得指定类过滤后的Public方法列表
	 * @description en - Gets the filtered public method list of the specified class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 21:47:40
	 * @param clazz 查找方法的类
	 * @param filter 过滤器
	 * @return java.lang.reflect.Method[]
	 */
	public static Method[] getMethods(Class<?> clazz, Filter<Method> filter) throws SecurityException {
		return null == clazz ? null : ArrayUtil.filter(getMethods(clazz), filter);
	}


	/**
	 * [获得一个类中所有方法列表](Get a list of all methods in a class)
	 * @description zh - 获得一个类中所有方法列表
	 * @description en - Get a list of all methods in a class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 19:11:20
	 * @param beanClass 类
	 * @param withSuperClassMethods 是否包括父类的方法列表
	 * @return java.lang.reflect.Method[]
	 */
	public static Method[] getMethodsDirectly(Class<?> beanClass, boolean withSuperClassMethods) throws SecurityException {
		Assertion.notNull(beanClass);

		Method[] allMethods = null;
		Class<?> searchType = beanClass;
		Method[] declaredMethods;
		while (searchType != null) {
			declaredMethods = searchType.getDeclaredMethods();
			if (null == allMethods) {
				allMethods = declaredMethods;
			} else {
				allMethods = ArrayUtil.append(allMethods, declaredMethods);
			}
			searchType = withSuperClassMethods ? searchType.getSuperclass() : null;
		}

		return allMethods;
	}

    /**
     * [设置字段值](Set field value)
     * @description: zh - 设置字段值
     * @description: en - Set field value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/16 4:05 下午
     * @param obj: 对象,static字段则此处传Class
     * @param fieldName: 字段名
     * @param value: 值类型必须与字段类型匹配，不会自动转换对象类型
    */
    public static void setFieldValue(Object obj, String fieldName, Object value) throws UtilException {
        Assertion.notNull(obj);
        Assertion.notBlank(fieldName);
        final Field field = getField((obj instanceof Class) ? (Class<?>) obj : obj.getClass(), fieldName);
        Assertion.notNull(field, "Field [{}] is not exist in [{}]", fieldName, obj.getClass().getName());
        setFieldValue(obj, field, value);
    }

    /**
     * [设置字段值](Set field value)
     * @description: zh - 设置字段值
     * @description: en - Set field value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:00 上午
     * @param obj: 对象
     * @param field: 字段
     * @param value: 值，值类型必须与字段类型匹配，不会自动转换对象类型
    */
    public static void setFieldValue(Object obj, Field field, Object value) throws UtilException {
        Assertion.notNull(field, "Field in [{}] not exist !", obj);

        final Class<?> fieldType = field.getType();
        if (Constant.NULL != value) {
            if (!fieldType.isAssignableFrom(value.getClass())) {
                //对于类型不同的字段，尝试转换，转换失败则使用原对象类型
                final Object targetValue = Convert.convert(fieldType, value);
                if (Constant.NULL != targetValue) {
                    value = targetValue;
                }
            }
        } else {
            // 获取null对应默认值，防止原始类型造成空指针问题
            value = ClassUtil.getDefaultValue(fieldType);
        }
        setAccessible(field);
        try {
            field.set(obj instanceof Class ? Constant.NULL : obj, value);
        } catch (IllegalAccessException e) {
            throw new UtilException(e, "IllegalAccess for {}.{}", obj, field.getName());
        }
    }

    /*字段--------------------------------------------------------------------Field*/

    /**
     * [实例化对象](Instantiate object)
     * @description: zh - 实例化对象
     * @description: en - Instantiate object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:00 上午
     * @param clazz: 类名
     * @return T
    */
	@SuppressWarnings("unchecked")
    public static <T> T newInstance(String clazz) throws UtilException {
        try {
            return (T) Class.forName(clazz).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new UtilException(e, "Instance class [{}] error!", clazz);
        }
    }

    /**
     * [实例化对象](Instantiate object)
     * @description: zh - 实例化对象
     * @description: en - Instantiate object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:02 上午
     * @param clazz: 类
     * @param params: 构造函数
     * @return T
    */
    public static <T> T newInstance(Class<T> clazz, Object... params) throws UtilException {
        if (ArrayUtil.isEmpty(params)) {
            final Constructor<T> constructor = getConstructor(clazz);
            try {
                return constructor.newInstance();
            } catch (Exception e) {
                throw new UtilException(e, "Instance class [{}] error!", clazz);
            }
        }

        final Class<?>[] paramTypes = ClassUtil.getClasses(params);
        final Constructor<T> constructor = getConstructor(clazz, paramTypes);
        if (null == constructor) {
            throw new UtilException("No Constructor matched for parameter types: [{}]", new Object[]{paramTypes});
        }
        try {
            return constructor.newInstance(params);
        } catch (Exception e) {
            throw new UtilException(e, "Instance class [{}] error!", clazz);
        }
    }

	// 调用 ------------------------------------------------------------------------------- invoke

	/**
	 * [执行静态方法](Execute static methods)
	 * @description zh - 执行静态方法
	 * @description en - Execute static methods
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 18:42:07
	 * @param method 方法
	 * @param args 参数列表
	 * @return T
	 */
	public static <T> T invokeStatic(Method method, Object... args) throws UtilException {
		return invoke(null, method, args);
	}

	/**
	 * [执行方法](Execution method)
	 * @description zh - 执行方法
	 * @description en - Execution method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 18:44:54
	 * @param obj 对象
	 * @param method 方法
	 * @param args 参数列表
	 * @return T
	 */
	public static <T> T invokeWithCheck(Object obj, Method method, Object... args) throws UtilException {
		final Class<?>[] types = method.getParameterTypes();
		if (null != args) {
			Assertion.isTrue(args.length == types.length, "Params length [{}] is not fit for param length [{}] of method !", args.length, types.length);
			Class<?> type;
			for (int i = 0; i < args.length; i++) {
				type = types[i];
				if (type.isPrimitive() && null == args[i]) {
					// 参数是原始类型，而传入参数为null时赋予默认值
					args[i] = ClassUtil.getDefaultValue(type);
				}
			}
		}

		return invoke(obj, method, args);
	}

	/**
	 * [执行方法](Execution method)
	 * @description zh - 执行方法
	 * @description en - Execution method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 18:46:28
	 * @param obj 对象
	 * @param method 方法
	 * @param args 参数列表
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T invoke(Object obj, Method method, Object... args) throws UtilException {
		setAccessible(method);

		final Class<?>[] parameterTypes = method.getParameterTypes();
		final Object[] actualArgs = new Object[parameterTypes.length];
		if (null != args) {
			for (int i = 0; i < actualArgs.length; i++) {
				if (i >= args.length || null == args[i]) {
					actualArgs[i] = ClassUtil.getDefaultValue(parameterTypes[i]);
				} else if (args[i] instanceof NullWrapperEntity) {
					actualArgs[i] = null;
				} else if (false == parameterTypes[i].isAssignableFrom(args[i].getClass())) {
					final Object targetValue = Convert.convert(parameterTypes[i], args[i]);
					if (null != targetValue) {
						actualArgs[i] = targetValue;
					}
				} else {
					actualArgs[i] = args[i];
				}
			}
		}

		try {
			return (T) method.invoke(ClassUtil.isStatic(method) ? null : obj, actualArgs);
		} catch (Exception e) {
			throw new UtilException(e);
		}
	}

	/**
	 * [执行对象中指定方法](Executes the method specified in the object)
	 * @description zh - 执行对象中指定方法
	 * @description en - Executes the method specified in the object
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 18:17:14
	 * @param methodName 方法名称
	 * @param args 参数列表
	 * @throws com.xiaoTools.core.exception.utilException.UtilException.UtilException
	 * @return T
	 */
	public static <T> T invoke(Object obj, String methodName, Object... args) throws UtilException {
		final Method method = getMethodOfObj(obj, methodName, args);
		if (null == method) {
			throw new UtilException(StrUtil.format("No such method: [{}]", methodName));
		}
		return invoke(obj, method, args);
	}

	/**
	 * [尝试遍历并调用此类的所有构造方法](Try to traverse and call all the constructor methods of this class)
	 * @description zh - 尝试遍历并调用此类的所有构造方法
	 * @description en - Try to traverse and call all the constructor methods of this class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-28 09:53:54
	 * @param beanClass 被构造的类
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstanceIfPossible(Class<T> beanClass) {
		Assertion.notNull(beanClass);

		if (beanClass.isAssignableFrom(AbstractMap.class)) {
			beanClass = (Class<T>) HashMap.class;
		} else if (beanClass.isAssignableFrom(List.class)) {
			beanClass = (Class<T>) ArrayList.class;
		} else if (beanClass.isAssignableFrom(Set.class)) {
			beanClass = (Class<T>) HashSet.class;
		}

		try {
			return newInstance(beanClass);
		} catch (Exception e) { }

		final Constructor<T>[] constructors = getConstructors(beanClass);
		Class<?>[] parameterTypes;
		for (Constructor<T> constructor : constructors) {
			parameterTypes = constructor.getParameterTypes();
			if (Constant.ZERO == parameterTypes.length) {
				continue;
			}
			setAccessible(constructor);
			try {
				return constructor.newInstance(ClassUtil.getDefaultValues(parameterTypes));
			} catch (Exception ignore) { }
		}
		return null;
	}

}
