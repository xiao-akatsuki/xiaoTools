package com.xiaoTools.util.classLoaderUtil;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.cache.simple.SimpleCache;
import com.xiaoTools.core.basicType.BasicType;
import com.xiaoTools.core.exception.utilException.UtilException;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.lang.jarClassLoader.JarClassLoader;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [ClassLoader工具类](Classloader tool class)
 * @description: zh - ClassLoader工具类
 * @description: en - Classloader tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 9:28 上午
*/
public class ClassLoaderUtil {

	/** 数组类的结尾符: "[]" */
	private static final String ARRAY_SUFFIX = "[]";
	/** 内部数组类名前缀: "[" */
	private static final String INTERNAL_ARRAY_PREFIX = "[";
	/** 内部非原始类型类名前缀: "[L" */
	private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
	/** 包名分界符: '.' */
	private static final char PACKAGE_SEPARATOR = Constant.CHAR_SPOT;
	/** 内部类分界符: '$' */
	private static final char INNER_CLASS_SEPARATOR = '$';

	/** 原始类型名和其class对应表，例如：int =》 int.class */
	private static final Map<String, Class<?>> PRIMITIVE_TYPE_NAME_MAP = new ConcurrentHashMap<>(32);
	private static final SimpleCache<String, Class<?>> CLASS_CACHE = new SimpleCache<>();

	static {
		List<Class<?>> primitiveTypes = new ArrayList<>(32);
		// 加入原始类型
		primitiveTypes.addAll(BasicType.PRIMITIVE_WRAPPER_MAP.keySet());
		// 加入原始类型数组类型
		primitiveTypes.add(boolean[].class);
		primitiveTypes.add(byte[].class);
		primitiveTypes.add(char[].class);
		primitiveTypes.add(double[].class);
		primitiveTypes.add(float[].class);
		primitiveTypes.add(int[].class);
		primitiveTypes.add(long[].class);
		primitiveTypes.add(short[].class);
		primitiveTypes.add(void.class);
		for (Class<?> primitiveType : primitiveTypes) {
			PRIMITIVE_TYPE_NAME_MAP.put(primitiveType.getName(), primitiveType);
		}
	}

    /*获取ClassLoader--------------------------------------------------------------------Get ClassLoader*/

    /**
     * [获取ClassLoader](Get classloader)
     * @description: zh - 获取ClassLoader
     * @description: en - Get classloader
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:29 上午
     * @return java.lang.ClassLoader
    */
    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = getContextClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoaderUtil.class.getClassLoader();
            if (null == classLoader) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }

    /**
     * [获取当前线程的ClassLoader](Gets the ClassLoader of the current thread)
     * @description: zh - 获取当前线程的ClassLoader
     * @description: en - Gets the ClassLoader of the current thread
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:31 上午
     * @return java.lang.ClassLoader
    */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

	/**
	 * [加载类](Load class)
	 * @description zh - 加载类
	 * @description en - Load class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:10:14
	 * @param name 类名
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.Class<?>
	 */
	public static Class<?> loadClass(String name) throws UtilException {
		return loadClass(name, Constant.TRUE);
	}

	/**
	 * [加载类](Load class)
	 * @description zh - 加载类
	 * @description en - Load class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:08:49
	 * @param name 类名
	 * @param isInitialized 是否初始化类
	 * @throws com.xiaoTools.core.exception.utilException.UtilException
	 * @return java.lang.Class<?>
	 */
	public static Class<?> loadClass(String name, boolean isInitialized) throws UtilException {
		return loadClass(name, null, isInitialized);
	}

	public static Class<?> loadClass(String name, ClassLoader classLoader, boolean isInitialized) throws UtilException {
		Assertion.notNull(name, "Name must not be null");

		Class<?> clazz = loadPrimitiveClass(name);
		if (clazz == null) {
			clazz = CLASS_CACHE.get(name);
		}
		if (clazz != null) {
			return clazz;
		}

		if (name.endsWith(ARRAY_SUFFIX)) {
			final String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
			final Class<?> elementClass = loadClass(elementClassName, classLoader, isInitialized);
			clazz = Array.newInstance(elementClass, 0).getClass();
		} else if (name.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name.endsWith(";")) {
			final String elementName = name.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name.length() - 1);
			final Class<?> elementClass = loadClass(elementName, classLoader, isInitialized);
			clazz = Array.newInstance(elementClass, 0).getClass();
		} else if (name.startsWith(INTERNAL_ARRAY_PREFIX)) {
			final String elementName = name.substring(INTERNAL_ARRAY_PREFIX.length());
			final Class<?> elementClass = loadClass(elementName, classLoader, isInitialized);
			clazz = Array.newInstance(elementClass, 0).getClass();
		} else {
			if (null == classLoader) {
				classLoader = getClassLoader();
			}
			try {
				clazz = Class.forName(name, isInitialized, classLoader);
			} catch (ClassNotFoundException ex) {
				clazz = tryLoadInnerClass(name, classLoader, isInitialized);
				if (null == clazz) {
					throw new UtilException(ex);
				}
			}
		}

		return CLASS_CACHE.put(name, clazz);
	}

	/**
	 * [加载原始类型的类](Load a class of the original type)
	 * @description zh - 加载原始类型的类
	 * @description en - Load a class of the original type
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:14:51
	 * @param name 类名
	 * @return java.lang.Class<?>
	 */
	public static Class<?> loadPrimitiveClass(String name) {
		Class<?> result = null;
		if (StrUtil.isNotBlank(name)) {
			name = name.trim();
			if (name.length() <= Constant.EIGHT) {
				result = PRIMITIVE_TYPE_NAME_MAP.get(name);
			}
		}
		return result;
	}

	/**
	 * [创建新的 JarClassLoader](Create a new jarclassloader)
	 * @description zh - 创建新的 JarClassLoader
	 * @description en - Create a new jarclassloader
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:15:59
	 * @param jarOrDir jar文件或者包含jar和class文件的目录
	 * @return com.xiaoTools.lang.jarClassLoader.JarClassLoader
	 */
	public static JarClassLoader getJarClassLoader(File jarOrDir) {
		return JarClassLoader.load(jarOrDir);
	}

	/**
	 * [加载外部类](Load external class)
	 * @description zh - 加载外部类
	 * @description en - Load external class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:20:40
	 * @param jarOrDir jar文件或者包含jar和class文件的目录
	 * @param name 类名
	 * @return java.lang.Class<?>
	 */
	public static Class<?> loadClass(File jarOrDir, String name) {
		try {
			return getJarClassLoader(jarOrDir).loadClass(name);
		} catch (ClassNotFoundException e) {
			throw new UtilException(e);
		}
	}

	/**
	 * [指定类是否被提供](Specifies whether the class is provided)
	 * @description zh - 指定类是否被提供
	 * @description en - Specifies whether the class is provided
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:22:53
	 * @param className 类名
	 * @return boolean
	 */
	public static boolean isPresent(String className) {
		return isPresent(className, null);
	}

	/**
	 * [指定类是否被提供](Specifies whether the class is provided)
	 * @description zh - 指定类是否被提供
	 * @description en - Specifies whether the class is provided
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:24:15
	 * @param className 类名
	 * @param classLoader ClassLoader
	 * @return boolean
	 */
	public static boolean isPresent(String className, ClassLoader classLoader) {
		try {
			loadClass(className, classLoader, Constant.FALSE);
			return Constant.TRUE;
		} catch (Throwable ex) {
			return Constant.FALSE;
		}
	}

	/**
	 * [尝试转换并加载内部类](Try converting and loading the inner class)
	 * @description zh - 尝试转换并加载内部类
	 * @description en - Try converting and loading the inner class
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-24 18:28:25
	 * @param name 类名
	 * @param classLoader ClassLoader
	 * @param isInitialized 是否初始化类
	 * @return java.lang.Class<?>
	 */
	private static Class<?> tryLoadInnerClass(String name, ClassLoader classLoader, boolean isInitialized) {
		final int lastDotIndex = name.lastIndexOf(PACKAGE_SEPARATOR);
		if (lastDotIndex > Constant.ZERO) {
			final String innerClassName = name.substring(Constant.ZERO, lastDotIndex) + INNER_CLASS_SEPARATOR + name.substring(lastDotIndex + 1);
			try {
				return Class.forName(innerClassName, isInitialized, classLoader);
			} catch (ClassNotFoundException ex2) {}
		}
		return null;
	}

}
