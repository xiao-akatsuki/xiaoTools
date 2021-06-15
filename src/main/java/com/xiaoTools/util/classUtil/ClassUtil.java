package com.xiaoTools.util.classUtil;

import com.xiaoTools.core.basicType.BasicType;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;

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

    /**
     * [比较判断「types1」和「types2」两组类，如果「types1」中所有的类都与「types2」对应位置的类相同，或者是其父类或接口，则返回「true」](Compare and judge "types1" and "types2". If all the classes in "types1" are the same as those in the corresponding position of "types2", or are its parent class or interface, return "true")
     * @description: zh - 比较判断「types1」和「types2」两组类，如果「types1」中所有的类都与「types2」对应位置的类相同，或者是其父类或接口，则返回「true」
     * @description: en - Compare and judge "types1" and "types2". If all the classes in "types1" are the same as those in the corresponding position of "types2", or are its parent class or interface, return "true"
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:08 下午
     * @param types1: 类组1
     * @param types2: 类组2
     * @return boolean
    */
    public static boolean isAllAssignableFrom(Class<?>[] types1, Class<?>[] types2) {
        if (ArrayUtil.isEmpty(types1) && ArrayUtil.isEmpty(types2)) {
            return Constant.TRUE;
        }else if (types1 == Constant.NULL && types2 == Constant.NULL){
            return Constant.FALSE;
        }else if (types1.length != types2.length){
            return Constant.FALSE;
        }
        Class<?> type1;
        Class<?> type2;
        for (int i = Constant.ZERO; i < types1.length; i++) {
            type1 = types1[i];
            type2 = types2[i];
            if (isBasicType(type1) && isBasicType(type2)) {
                // 原始类型和包装类型存在不一致情况
                if (BasicType.unWrap(type1) != BasicType.unWrap(type2)) {
                    return Constant.FALSE;
                }
            } else if (!type1.isAssignableFrom(type2)) {
                return Constant.FALSE;
            }
        }
        return Constant.TRUE;
    }

    /**
     * [是否为基本类型（包括包装类和原始类）](Whether it is a basic type (including packaging class and original class))
     * @description: zh - 是否为基本类型（包括包装类和原始类）
     * @description: en - Whether it is a basic type (including packaging class and original class)
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:14 下午
     * @param clazz: 类
     * @return boolean
    */
    public static boolean isBasicType(Class<?> clazz) {
        if (Constant.NULL == clazz) {
            return Constant.FALSE;
        }
        return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
    }

    /**
     * [判断是否是包装类型](Judge whether it is the type of packing)
     * @description: zh - 判断是否是包装类型
     * @description: en - Judge whether it is the type of packing
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/15 8:16 下午
     * @param clazz: 类
     * @return boolean
    */
    public static boolean isPrimitiveWrapper(Class<?> clazz) {
        if (Constant.NULL == clazz) {
            return Constant.FALSE;
        }
        return BasicType.WRAPPER_PRIMITIVE_MAP.containsKey(clazz);
    }
}
