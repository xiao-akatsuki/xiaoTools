package com.xiaoTools.lang.clone.cLoneable;

/**
 * [克隆支持接口](Clone support interface)
 * @description: zh - 克隆支持接口
 * @description: en - Clone support interface
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 9:53 上午
*/
public interface Cloneable<T> extends java.lang.Cloneable {

    /**
     * [克隆当前对象，浅复制](Clone current object, shallow copy)
     * @description: zh - 克隆当前对象，浅复制
     * @description: en - Clone current object, shallow copy
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:53 上午
     * @return T
    */
    T clone();
}
