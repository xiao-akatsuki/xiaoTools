package com.xiaoTools.util.reflectUtil;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.cache.simple.SimpleCache;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.classUtil.ClassUtil;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        if (null != accessibleObject && !accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
        return accessibleObject;
    }

    /*字段--------------------------------------------------------------------Field*/

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
}
