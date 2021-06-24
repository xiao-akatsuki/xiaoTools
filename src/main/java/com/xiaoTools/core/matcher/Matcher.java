package com.xiaoTools.core.matcher;

/**
 * [匹配接口](Matching interface)
 * @description: zh - 匹配接口
 * @description: en - Matching interface
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/24 7:29 下午
*/
@FunctionalInterface
public interface Matcher<T> {

    /**
     * [给定对象是否匹配](Does the given object match)
     * @description: zh - 给定对象是否匹配
     * @description: en - Does the given object match
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/24 7:28 下午
     * @param t: 对象
     * @return boolean
    */
    boolean match(T t);
}
