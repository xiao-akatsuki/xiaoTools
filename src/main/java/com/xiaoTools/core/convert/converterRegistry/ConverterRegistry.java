package com.xiaoTools.core.convert.converterRegistry;

import com.xiaoTools.core.convert.converter.Converter;
import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.classUtil.ClassUtil;
import com.xiaoTools.util.reflectUtil.ReflectUtil;
import com.xiaoTools.util.serviceLoaderUtil.ServiceLoaderUtil;
import com.xiaoTools.util.typeUtil.TypeUtil;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * [类型转换器登记中心](Type converter registry)
 * @description: zh - 类型转换器登记中心
 * @description: en - Type converter registry
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 8:38 上午
*/
public class ConverterRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 默认类型转换器
     */
    private Map<Type, Converter<?>> defaultConverterMap;

    /**
     * 用户自定义类型转换器
     */
    private volatile Map<Type, Converter<?>> customConverterMap;

    /**
     * [类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载](Class level inner class, that is, static member type inner class. The instance of the inner class has no binding relationship with the instance of the outer class, and it will be loaded only when it is called, thus realizing delayed loading)
     * @description: zh - 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     * @description: en - Class level inner class, that is, static member type inner class. The instance of the inner class has no binding relationship with the instance of the outer class, and it will be loaded only when it is called, thus realizing delayed loading
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:45 上午
    */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static final ConverterRegistry INSTANCE = new ConverterRegistry();
    }

    /**
     * [获得单例的 ConverterRegistry](Get the ConverterRegistry of singleton)
     * @description: zh - 获得单例的 ConverterRegistry
     * @description: en - Get the ConverterRegistry of singleton
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:46 上午
     * @return com.xiaoTools.core.convert.converterRegistry.ConverterRegistry
    */
    public static ConverterRegistry getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * [无参方法](Nonparametric method)
     * @description: zh - 无参方法
     * @description: en - Nonparametric method
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:46 上午
    */
    public ConverterRegistry() {
        defaultConverter();
        putCustomBySpi();
    }

    /*加载转换器--------------------------------------------------------------------SPI*/

    /**
     * [使用SPI加载转换器](Loading converter with SPI)
     * @description: zh - 使用SPI加载转换器
     * @description: en - Loading converter with SPI
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:58 上午
    */
    private void putCustomBySpi() {
        ServiceLoaderUtil.load(Converter.class).forEach(converter -> {
            try {
                Type type = TypeUtil.getTypeArgument(ClassUtil.getClass(converter));
                if(null != type){
                    putCustom(type, converter);
                }
            } catch (Exception e) {
                // 忽略注册失败的
            }
        });
    }

    /**
     * [登记自定义转换器](Register custom converter)
     * @description: zh - 登记自定义转换器
     * @description: en - Register custom converter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:57 上午
     * @param type: 转换的目标类型
     * @param converterClass: 转换器类，必须有默认构造方法
     * @return com.xiaoTools.core.convert.converterRegistry.ConverterRegistry
    */
    public ConverterRegistry putCustom(Type type, Class<? extends Converter<?>> converterClass) {
        return putCustom(type, ReflectUtil.newInstance(converterClass));
    }

    /**
     * [登记自定义转换器](Register custom converter)
     * @description: zh - 登记自定义转换器
     * @description: en - Register custom converter
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 8:55 上午
     * @param type:  转换的目标类型
     * @param converter: 转换器
     * @return com.xiaoTools.core.convert.converterRegistry.ConverterRegistry
    */
    public ConverterRegistry putCustom(Type type, Converter<?> converter) {
        if (Constant.NULL == customConverterMap) {
            synchronized (this) {
                if (Constant.NULL == customConverterMap) {
                    customConverterMap = new ConcurrentHashMap<>();
                }
            }
        }
        customConverterMap.put(type, converter);
        return this;
    }

    /*默认转换器--------------------------------------------------------------------defaultConverter*/

    private ConverterRegistry defaultConverter() {
        defaultConverterMap = new ConcurrentHashMap<>();

    }
}
