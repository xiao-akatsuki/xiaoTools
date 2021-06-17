package com.xiaoTools.util.collUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class CollUtil {

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description: zh - 新建一个HashSet
     * @description: en - Create a new HashSet
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:31 下午
     * @param isSorted: 是否有序，有序返回 LinkedHashSet，否则返回 HashSet
     * @param ts: 元素数组
     * @return java.util.HashSet<T>
    */
    @SafeVarargs
    public static <T> HashSet<T> set(boolean isSorted, T... ts) {
        if (null == ts) {
            return isSorted ? new LinkedHashSet<>() : new HashSet<>();
        }
        int initialCapacity = Math.max((int) (ts.length / .75f) + 1, 16);
        final HashSet<T> set = isSorted ? new LinkedHashSet<>(initialCapacity) : new HashSet<>(initialCapacity);
        Collections.addAll(set, ts);
        return set;
    }

    /**
     * [新建一个HashSet](Create a new HashSet)
     * @description: zh - 新建一个HashSet
     * @description: en - Create a new HashSet
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/17 4:33 下午
     * @param ts: 元素数组
     * @return java.util.HashSet<T>
    */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        return set(false, ts);
    }
}
