package com.xiaoTools.util.classUtil;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.basicType.BasicType;
import com.xiaoTools.entity.nullWrapperEntity.NullWrapperEntity;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.arrayUtil.ArrayUtil;
import com.xiaoTools.util.classLoaderUtil.ClassLoaderUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

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
	 * [加载类并初始化](Load class and initialize)
	 * @description zh - 加载类并初始化
	 * @description en - Load class and initialize
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:35:48
	 * @param className 类名字
	 * @return java.lang.Class<T>
	 */
	public static <T> Class<T> loadClass(String className) {
		return loadClass(className, true);
	}

	/**
	 * [加载类](Load class)
	 * @description zh - 加载类
	 * @description en - Load class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:36:30
	 * @param className 类的名字
	 * @param isInitialized 是否初始化
	 * @return java.lang.Class<T>

	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> loadClass(String className, boolean isInitialized) {
		return (Class<T>) ClassLoaderUtil.loadClass(className, isInitialized);
	}




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
	@SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T value) {
        return ((Constant.NULL == value) ? (Class<T>) Constant.NULL : (Class<T>) value.getClass());
    }

    /**
     * [获得对象数组的类数组](Gets the class array of the object array)
     * @description: zh - 获得对象数组的类数组
     * @description: en - Gets the class array of the object array
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:04 上午
     * @param objects: 对象数组，如果数组中存在null元素，则此元素被认为是Object类型
     * @return java.lang.Class<?>[]
    */
    public static Class<?>[] getClasses(Object... objects) {
        Class<?>[] classes = new Class<?>[objects.length];
        Object obj;
        for (int i = Constant.ZERO; i < objects.length; i++) {
            obj = objects[i];
            if (obj instanceof NullWrapperEntity) {
                // 自定义null值的参数类型
                classes[i] = ((NullWrapperEntity<?>) obj).getWrappedClass();
            } else if (Constant.NULL == obj) {
                classes[i] = Object.class;
            } else {
                classes[i] = obj.getClass();
            }
        }
        return classes;
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
		return Constant.NULL == clazz ? Constant.FALSE : (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
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

    /**
     * [获取指定类型分的默认值](Gets the default value of the specified type)
     * @description: zh - 获取指定类型分的默认值
     * @description: en - Gets the default value of the specified type
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:05 上午
     * @param clazz: 类
     * @return java.lang.Object
    */
    public static Object getDefaultValue(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (long.class == clazz) {
                return Constant.LONG_ZERO;
            } else if (int.class == clazz) {
                return Constant.ZERO;
            } else if (short.class == clazz) {
                return (short) 0;
            } else if (char.class == clazz) {
                return (char) 0;
            } else if (byte.class == clazz) {
                return (byte) 0;
            } else if (double.class == clazz) {
                return 0D;
            } else if (float.class == clazz) {
                return 0f;
            } else if (boolean.class == clazz) {
                return Constant.FALSE;
            }
        }

        return Constant.NULL;
    }

    /**
     * [获得给定类的第一个泛型参数](Gets the first generic parameter of a given class)
     * @description: zh - 获得给定类的第一个泛型参数
     * @description: en - Gets the first generic parameter of a given class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 2:36 下午
     * @param clazz: 被检查的类，必须是已经确定泛型类型的类
     * @return java.lang.Class<?>
    */
    public static Class<?> getTypeArgument(Class<?> clazz) {
        return getTypeArgument(clazz, 0);
    }

    /**
     * [获得给定类的泛型参数](Gets the generic parameters of the given class)
     * @description: zh - 获得给定类的泛型参数
     * @description: en - Gets the generic parameters of the given class
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 2:39 下午
     * @param clazz:  被检查的类，必须是已经确定泛型类型的类
     * @param index: 泛型类型的索引号，即第几个泛型类型
     * @return java.lang.Class<?>
    */
    public static Class<?> getTypeArgument(Class<?> clazz, int index) {
        final Type argumentType = TypeUtil.getTypeArgument(clazz, index);
        return TypeUtil.getClass(argumentType);
    }

	/**
	 * [是否为静态方法](Is it a static method)
	 * @description zh - 是否为静态方法
	 * @description en - Is it a static method
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-21 19:14:18
	 * @param method 方法
	 * @return boolean
	 */
	public static boolean isStatic(Method method) {
		Assertion.notNull(method, "Method to provided is null.");
		return Modifier.isStatic(method.getModifiers());
	}

	/**
	 * [获取 ClassLoader](Get classloader)
	 * @description zh - 获取 ClassLoader
	 * @description en - Get classloader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:47:56
	 * @return java.lang.ClassLoader
	 */
	public static ClassLoader getClassLoader() {
		return ClassLoaderUtil.getClassLoader();
	}

	/**
	 * [查找指定类中的所有方法](Finds all methods in the specified class)
	 * @description zh - 查找指定类中的所有方法
	 * @description en - Finds all methods in the specified class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 19:01:51
	 */
	public static Method getDeclaredMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws SecurityException {
		return ReflectUtil.getMethod(clazz, methodName, parameterTypes);
	}

	/**
	 * [检查目标类是否可以从原类转化](Check whether the target class can be converted from the original class)
	 * @description zh - 检查目标类是否可以从原类转化
	 * @description en - Check whether the target class can be converted from the original class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 08:25:49
	 * @param targetType 目标类型
	 * @param sourceType 原类型
	 * @return boolean
	 */
	public static boolean isAssignable(Class<?> targetType, Class<?> sourceType) {
		return null == targetType || null == sourceType ? Constant.FALSE :
			targetType.isAssignableFrom(sourceType) ? Constant.TRUE :
				targetType.isPrimitive() ? targetType.equals(BasicType.WRAPPER_PRIMITIVE_MAP.get(sourceType)) :
					BasicType.PRIMITIVE_WRAPPER_MAP.get(sourceType) != null && targetType.isAssignableFrom(BasicType.PRIMITIVE_WRAPPER_MAP.get(sourceType));

	}

	/**
	 * [是否为标准的类](Is it a standard class)
	 * @description zh - 是否为标准的类
	 * @description en - Is it a standard class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 09:10:50
	 * @param clazz 类型
	 * @return boolean
	 */
	public static boolean isNormalClass(Class<?> clazz) {
		return null != clazz
				&& Constant.FALSE == clazz.isInterface()
				&& Constant.FALSE == isAbstract(clazz)
				&& Constant.FALSE == clazz.isEnum()
				&& Constant.FALSE == clazz.isArray()
				&& Constant.FALSE == clazz.isAnnotation()
				&& Constant.FALSE == clazz.isSynthetic()
				&& Constant.FALSE == clazz.isPrimitive();
	}

	/**
	 * [是否为抽象类](Is it an abstract class)
	 * @description zh - 是否为抽象类
	 * @description en - Is it an abstract class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-25 09:11:29
	 * @param clazz 类型
	 * @return boolean
	 */
	public static boolean isAbstract(Class<?> clazz) {
		return Modifier.isAbstract(clazz.getModifiers());
	}
}
