package com.xiaoTools.util.reflectUtil;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.cache.simple.SimpleCache;
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

    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        if (null == clazz) { return null; }

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
}
