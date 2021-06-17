package com.xiaoTools.util.serviceLoaderUtil;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.classLoaderUtil.ClassLoaderUtil;
import com.xiaoTools.util.objectUtil.ObjectUtil;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/**
 * [SPI机制中的服务加载工具类](Service loading tool class in SPI mechanism)
 * @description: zh - SPI机制中的服务加载工具类
 * @description: en - Service loading tool class in SPI mechanism
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 9:22 上午
*/
public class ServiceLoaderUtil {

    /**
     * [加载第一个可用服务，如果用户定义了多个接口实现类，只获取第一个不报错的服务。](Load the first available service. If the user defines multiple interface implementation classes, only the first non error service will be obtained.)
     * @description: zh - 加载第一个可用服务，如果用户定义了多个接口实现类，只获取第一个不报错的服务。
     * @description: en - Load the first available service. If the user defines multiple interface implementation classes, only the first non error service will be obtained.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:23 上午
     * @param clazz: 服务接口
     * @return T
    */
    public static <T> T loadFirstAvailable(Class<T> clazz) {
        final Iterator<T> iterator = load(clazz).iterator();
        while (iterator.hasNext()) {
            try {
                return iterator.next();
            } catch (ServiceConfigurationError ignore) {
                // ignore
            }
        }
        return (T) Constant.NULL;
    }

    /**
     * [加载第一个服务，如果用户定义了多个接口实现类，只获取第一个。](Load the first service. If the user has defined multiple interface implementation classes, only the first one will be obtained.)
     * @description: zh - 加载第一个服务，如果用户定义了多个接口实现类，只获取第一个。
     * @description: en - Load the first service. If the user has defined multiple interface implementation classes, only the first one will be obtained.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:25 上午
     * @param clazz: 服务接口
     * @return T
    */
    public static <T> T loadFirst(Class<T> clazz) {
        final Iterator<T> iterator = load(clazz).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return (T) Constant.NULL;
    }

    /**
     * [加载服务](Load service)
     * @description: zh - 加载服务
     * @description: en - Load service
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:25 上午
     * @param clazz: 服务接口
     * @return java.util.ServiceLoader<T>
    */
    public static <T> ServiceLoader<T> load(Class<T> clazz) {
        return load(clazz, null);
    }

    /**
     * [加载服务](Load service)
     * @description: zh - 加载服务
     * @description: en - Load service
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:32 上午
     * @param clazz:服务接口
     * @param loader: ClassLoader
     * @return java.util.ServiceLoader<T>
    */
    public static <T> ServiceLoader<T> load(Class<T> clazz, ClassLoader loader) {
        return ServiceLoader.load(clazz, ObjectUtil.defaultIfNull(loader, ClassLoaderUtil.getClassLoader()));
    }
}
