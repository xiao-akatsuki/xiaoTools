package com.xiaoTools.lang.segment;

import java.lang.reflect.Type;

import com.xiaoTools.assertion.Assertion;
import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.util.numUtil.NumUtil;

/**
 * [片段表示](Fragment representation)
 * @description zh - 片段表示
 * @description en - Fragment representation
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-18 12:36:31
 */
public interface Segment<T extends Number> {

	/**
	 * [获取起始位置](Get start position)
	 * @description zh - 获取起始位置
	 * @description en - Get start position
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 12:38:25
	 * @return T
	 */
	T getStartIndex();

	/**
	 * [获取结束位置](Get end position)
	 * @description zh - 获取结束位置
	 * @description en - Get end position
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 12:38:55
	 * @return T
	 */
	T getEndIndex();

	/**
	 * [片段长度](Fragment length)
	 * @description zh - 片段长度
	 * @description en - Fragment length
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 12:39:24
	 * @return T
	 */
	default T length(){
		final T start = Assertion.notNull(getStartIndex(), "Start index must be not null!");
		final T end = Assertion.notNull(getEndIndex(), "End index must be not null!");

		return Convert.convert((Type) start.getClass(), NumUtil.subtraction(end, start).abs());
	}

}
