package com.xiaoTools.core.collection.consumer;

import java.io.Serializable;

/**
 * [针对一个参数做相应的操作](Perform corresponding operations for a parameter)
 * @description zh - 针对一个参数做相应的操作
 * @description en - Perform corresponding operations for a parameter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-07 22:07:55
 */
@FunctionalInterface
public interface Consumer<T> extends Serializable {
    /**
     * [接受并处理一个参数](Accept and process a parameter)
     * @description zh - 接受并处理一个参数
     * @description en - Accept and process a parameter
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-07 22:11:44
     * @param value 参数值
     * @param index 参数在集合中的索引
     */
    void accept(T value, int index);
}
