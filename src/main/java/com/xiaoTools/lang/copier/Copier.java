package com.xiaoTools.lang.copier;

/**
 * [拷贝接口](Copy interface)
 * @description zh - 拷贝接口
 * @description en - Copy interface
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-27 18:14:29
 */
@FunctionalInterface
public interface Copier<T> {

	/**
	 * [执行拷贝](Execute Copy)
	 * @description zh - 执行拷贝
	 * @description en - Execute Copy
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-27 18:14:02
	 * @return T
	 */
	T copy();

}
