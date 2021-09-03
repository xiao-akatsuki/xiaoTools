package com.xiaoTools.lang.hash.hash32;

/**
 * [Hash计算接口](Hash computing interface)
 * @description zh - Hash计算接口
 * @description en - Hash computing interface
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-09-03 21:27:22
 */
@FunctionalInterface
public interface Hash32<T> {
    /**
     * [计算Hash值](Calculate hash value)
     * @description zh - 计算Hash值
     * @description en - Calculate hash value
     * @version V1.0
     * @author XiaoXunYao
     * @since 2021-09-03 21:26:44
     * @param t 对象
     * @return int
     */
	int hash32(T t);
}
