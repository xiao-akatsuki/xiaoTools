package com.xiaoTools.util.arrayUtil.filter;

/**
 * [过滤器接口](Filter interface)
 * @description: zh - 过滤器接口
 * @description: en - Filter interface
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/12 7:44 下午
*/
@FunctionalInterface
public interface Filter<T> {

    /**
     * [是否接受对象](Accept object)
     * @description: zh - 是否接受对象
     * @description: en - Accept object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 7:44 下午
     * @param t: 检查的对象
     * @return boolean
    */
    boolean accept(T t);
}
