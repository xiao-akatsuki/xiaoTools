package com.xiaoTools.util.classLoaderUtil;

/**
 * [ClassLoader工具类](Classloader tool class)
 * @description: zh - ClassLoader工具类
 * @description: en - Classloader tool class
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 9:28 上午
*/
public class ClassLoaderUtil {

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
}
