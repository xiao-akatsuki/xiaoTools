package com.xiaoTools.entity.mutable;

/**
 * [提供可变值类型接口](Provide variable value type interface)
 * @description: zh - 提供可变值类型接口
 * @description: en - Provide variable value type interface
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/21 8:30 下午
*/
public interface Mutable<T> {

    /**
     * [获得原始值](Get the original value)
     * @description: zh -  获得原始值
     * @description: en -  Get the original value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:30 下午
     * @return T
    */
    T get();

    /**
     * [设置值](Set value)
     * @description: zh - 设置值
     * @description: en - Set value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/21 8:29 下午
     * @param value: 值
    */
    void set(T value);
}
