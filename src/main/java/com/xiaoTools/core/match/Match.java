package com.xiaoTools.core.match;

/**
 * [匹配的对象类型接口](Matching object type interface)
 * @description: zh - 匹配的对象类型接口
 * @description: en - Matching object type interface
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/12 2:40 下午
*/
@FunctionalInterface
public interface Match<T> {
    /**
     * [给定对象是否匹配](Does the given object match)
     * @description: zh - 给定对象是否匹配
     * @description: en - Does the given object match
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/12 2:39 下午
     * @param t: [对象](object)
     * @return boolean
    */
    boolean match(T t);
}
