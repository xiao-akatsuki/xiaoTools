package com.xiaoTools.entity.nullWrapperEntity;

/**
 * [为了解决反射过程中,需要传递null参数,但是会丢失参数类型而设立的包装类](In order to solve the problem of reflection, you need to pass null parameter, but you will lose the wrapper class set by parameter type)
 * @description: zh - 为了解决反射过程中,需要传递null参数,但是会丢失参数类型而设立的包装类
 * @description: en - In order to solve the problem of reflection, you need to pass null parameter, but you will lose the wrapper class set by parameter type
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/17 9:06 上午
*/
public class NullWrapperEntity<T> {

    private final Class<T> clazz;

    /**
     * [有参函数](Parametric function)
     * @description: zh - 有参函数
     * @description: en - Parametric function
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:07 上午
     * @param clazz: 类
    */
    public NullWrapperEntity(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * [获取null值对应的类型](Gets the type corresponding to the null value)
     * @description: zh - 获取null值对应的类型
     * @description: en - Gets the type corresponding to the null value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 9:07 上午
     * @return java.lang.Class<T>
    */
    public Class<T> getWrappedClass() {
        return clazz;
    }
}
