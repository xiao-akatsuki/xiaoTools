package com.xiaoTools.util.classUtil;

import com.xiaoTools.lang.constant.Constant;

/**
 * [类工具类](Class tool class)
 * @description: zh - 类工具类
 * @description: en - Class tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/14 11:03 上午
*/
public class ClassUtil {
    /**
     * [null安全的获取对象类型](Null safe get object type)
     * @description: zh - null安全的获取对象类型
     * @description: en - Null safe get object type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 11:07 上午
     * @param value: 对象，如果为null 返回null
     * @return java.lang.Class<T>
    */
    public static <T> Class<T> getClass(T value) {
        return ((Constant.NULL == value) ? (Class<T>) Constant.NULL : (Class<T>) value.getClass());
    }

    /**
     * [返回定义此类或匿名类所在的类，如果类本身是在包中定义的，返回null](Returns the class where the class or anonymous class is defined. If the class itself is defined in the package, null is returned)
     * @description: zh - 返回定义此类或匿名类所在的类，如果类本身是在包中定义的，返回 null
     * @description: en - Returns the class where the class or anonymous class is defined. If the class itself is defined in the package, null is returned
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 11:08 上午
     * @param clazz: 类
     * @return java.lang.Class<?>
    */
    public static Class<?> getEnclosingClass(Class<?> clazz) {
        return Constant.NULL == clazz ? (Class<?>) Constant.NULL : clazz.getEnclosingClass();
    }

    /**
     * [是否为顶层类，即定义在包中的类，而非定义在类中的内部类](Is it a top-level class, that is, a class defined in a package, rather than an inner class defined in a class)
     * @description: zh - 是否为顶层类，即定义在包中的类，而非定义在类中的内部类
     * @description: en - Is it a top-level class, that is, a class defined in a package, rather than an inner class defined in a class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 11:15 上午
     * @param clazz: 类
     * @return boolean
    */
    public static boolean isTopLevelClass(Class<?> clazz) {
        return Constant.NULL != clazz && Constant.NULL == getEnclosingClass(clazz);
    }

    /**
     * [获取类名](Get class name)
     * @description: zh - 获取类名
     * @description: en - Get class name
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 11:39 上午
     * @param obj: 获取类名的对象
     * @param isSimple: 是否简单类名，如果为true，返回不带包名的类名
     * @return java.lang.String
    */
    public static String getClassName(Object obj, boolean isSimple) {
        return Constant.NULL == obj ? Constant.STRING_NULL : getClassName(obj.getClass(),isSimple);
    }

    /**
     * [获取类名,类名并不包含“.class”这个扩展名](Get the class name. The class name does not contain the ".class" extension)
     * @description: zh - 获取类名,类名并不包含“.class”这个扩展名
     * @description: en - Get the class name. The class name does not contain the ".class" extension
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/14 11:42 上午
     * @param clazz: 类
     * @param isSimple: 是否简单类名，如果为true，返回不带包名的类名
     * @return java.lang.String
    */
    public static String getClassName(Class<?> clazz, boolean isSimple) {
        return Constant.NULL == clazz ? Constant.STRING_NULL : isSimple ? clazz.getSimpleName() : clazz.getName();
    }
}
