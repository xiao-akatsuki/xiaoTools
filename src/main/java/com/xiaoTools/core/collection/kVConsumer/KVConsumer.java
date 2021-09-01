package com.xiaoTools.core.collection.kVConsumer;

import java.io.Serializable;

/**
 * [针对两个参数做相应的操作，例如Map中的KEY和VALUE](Perform corresponding operations on two parameters, such as key and value in map)
 * @description zh - 针对两个参数做相应的操作，例如Map中的KEY和VALUE
 * @description en - Perform corresponding operations on two parameters, such as key and value in map
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-01 19:23:00
 */
@FunctionalInterface
public interface KVConsumer<K, V> extends Serializable{

    /**
     * [Accept and process a pair of parameters](Accept and process a pair of parameters)
     * @description zh - 接受并处理一对参数
     * @description en - Accept and process a pair of parameters
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-01 19:22:02
     * @param key 键
     * @param value 值
     * @param index 参数在集合中的索引
     */
    void accept(K key, V value, int index);
    
}
