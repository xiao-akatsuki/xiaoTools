package com.xiaoTools.core.convert.atomicLongArrayConverter;

import java.util.concurrent.atomic.AtomicLongArray;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [AtomicLongArray转换器](Atomiclongarray converter)
 * @description zh - AtomicLongArray转换器
 * @description en - Atomiclongarray converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:46:57
 */
public class AtomicLongArrayConverter extends AbstractConverter<AtomicLongArray> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AtomicLongArray convertInternal(Object value) {
		return new AtomicLongArray(Convert.convert(long[].class, value));
	}

}
