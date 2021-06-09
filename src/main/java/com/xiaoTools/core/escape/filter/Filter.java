package com.xiaoTools.core.escape.filter;

/**
 * [是否检查该对象的监听器](Do you want to check the listener for this object)
 * @description: zh - 是否检查该对象的监听器
 * @description: en - Do you want to check the listener for this object
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/8 2:33 下午
*/
@FunctionalInterface
public interface Filter<T> {
    /**
     * [是否检查该对象](Check the object)
     * @description: zh - 是否检查该对象
     * @description: en - Check the object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/8 9:55 上午
     * @param t: 检查的对象
     * @return boolean
    */
    boolean accept(T t);
}
