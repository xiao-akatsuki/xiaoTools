package com.xiaoTools.lang.clone.cLoneSupport;

import com.xiaoTools.core.exception.cLoneRuntimeException.CloneRuntimeException;
import com.xiaoTools.lang.clone.cLoneable.Cloneable;

/**
 * [克隆支持类，提供默认的克隆方法](Clone support class, providing the default clone method)
 * @description: zh - 克隆支持类，提供默认的克隆方法
 * @description: en - Clone support class, providing the default clone method
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/19 9:50 上午
*/
public class CloneSupport<T> implements Cloneable<T> {

    /**
     * [克隆当前对象，浅复制](Clone current object, shallow copy)
     * @description: zh - 克隆当前对象，浅复制
     * @description: en - Clone current object, shallow copy
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/19 9:55 上午
     * @return T
    */
    @Override
    public T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneRuntimeException(e);
        }
    }
}
