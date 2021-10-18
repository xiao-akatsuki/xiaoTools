package com.xiaoTools.lang.segment.defaultSegment;

import com.xiaoTools.lang.segment.Segment;

/**
 * [片段默认实现](Fragment default implementation)
 * @description zh - 片段默认实现
 * @description en - Fragment default implementation
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-18 12:45:49
 */
public class DefaultSegment<T extends Number> implements Segment<T> {


	protected T startIndex;
	protected T endIndex;

	/**
	 * [构造](structure)
	 * @description zh - 构造
	 * @description en - structure
	 * @version V1.0
	 * @author XiaoXunYao
	 * @since 2021-10-18 12:46:27
	 * @param startIndex 起始位置
	 * @param endIndex 结束位置
	 */
	public DefaultSegment(T startIndex, T endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	public T getStartIndex() {
		return this.startIndex;
	}

	@Override
	public T getEndIndex() {
		return this.endIndex;
	}

}
