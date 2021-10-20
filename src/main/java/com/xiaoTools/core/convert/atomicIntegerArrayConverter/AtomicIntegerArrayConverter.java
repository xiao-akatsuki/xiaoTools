package com.xiaoTools.core.convert.atomicIntegerArrayConverter;

import java.util.concurrent.atomic.AtomicIntegerArray;

import com.xiaoTools.core.convert.Convert;
import com.xiaoTools.core.convert.abstractConverter.AbstractConverter;

/**
 * [AtomicIntegerArray转换器](Atomicintegerarray converter)
 * @description zh - AtomicIntegerArray转换器
 * @description en - Atomicintegerarray converter
 * @version V1.0
 * @author XiaoXunYao
 * @since 2021-10-20 16:45:22
 */
public class AtomicIntegerArrayConverter extends AbstractConverter<AtomicIntegerArray> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AtomicIntegerArray convertInternal(Object value) {
		return new AtomicIntegerArray(Convert.convert(int[].class, value));
	}

}
